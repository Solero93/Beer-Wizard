package pis2015.ub.com.beerwizard.network;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import org.alljoyn.bus.AboutObj;
import org.alljoyn.bus.BusAttachment;
import org.alljoyn.bus.BusException;
import org.alljoyn.bus.Mutable;
import org.alljoyn.bus.Observer;
import org.alljoyn.bus.ProxyBusObject;
import org.alljoyn.bus.SessionOpts;
import org.alljoyn.bus.SessionPortListener;
import org.alljoyn.bus.Status;
import org.alljoyn.bus.annotation.BusSignalHandler;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import pis2015.ub.com.beerwizard.R;
import pis2015.ub.com.beerwizard.util.Constants;

public class Server extends Service {
    private static final String TAG = "ServerService";
    // TODO Except constants, all assignments should go to onCreate, here only declare
    static BusHandler busHandler;
    private ConcurrentHashMap<String, UserInterface> userDb;
    private ConcurrentHashMap<UserInterface, String> reverseUserDb;
    private User user;

    @Override
    public void onCreate() {
        userDb = GameData.getUserDb();
        reverseUserDb = new ConcurrentHashMap<>();
        HandlerThread thread = new HandlerThread("UserProvider");
        thread.start();
        user = GameData.getUser();
        busHandler = new BusHandler(thread.getLooper());
        busHandler.sendMessage(busHandler.obtainMessage(BusHandler.CONNECT));
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Starting Server");
        return START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent intent) {
        stopSelf();
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Shutting Server");
        busHandler.sendMessage(busHandler.obtainMessage(BusHandler.DISCONNECT));
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @BusSignalHandler(iface = Constants.INTERFACE_NAME, signal = "updateRule")
    public void updateRule(String newRule) {
        Message msg = busHandler.obtainMessage(BusHandler.UPDATE_RULE);
        msg.obj = newRule;
        busHandler.sendMessage(msg);
    }

    public class BusHandler extends Handler {
        public static final int CONNECT = 1;
        public static final int DISCONNECT = 2;
        public static final int LEVEL_UP_USER = 3;
        public static final int JOIN_GAME = 4;
        public static final int UPDATE_RULE = 5;
        private static final short CONTACT_PORT = 42;

        private BusAttachment mBus;
        private AboutObj aboutObj;
        private LocalAboutDataListener aboutData;

        private Observer observer;

        public BusHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONNECT:
                    Log.d(TAG, "Initializing AllJoyn Framework");
                    org.alljoyn.bus.alljoyn.DaemonInit.PrepareDaemon(getApplicationContext());
                    mBus = new BusAttachment(getPackageName(), BusAttachment.RemoteMessage.Receive);

                    /*
                    * Register the BusObject with the path "/userProperties"
                    */
                    Status status = mBus.registerBusObject(user, "/userProperties");
                    if (status != Status.OK) {
                        Log.e(TAG, "Failed to register bus object: " + status.toString());
                        return;
                    }

                    status = mBus.connect();
                    if (status != Status.OK) {
                        Log.e(TAG, "Failed to connect to the bus");
                        return;
                    }

                    status = mBus.registerSignalHandlers(Server.this);
                    if (status != Status.OK) {
                        Log.e(TAG, "Failed to register signal handler");
                        return;
                    }

                    /*
                     * Create a new session listening on the contact port of the user service
                     */
                    Mutable.ShortValue contactPort = new Mutable.ShortValue(CONTACT_PORT);

                    SessionOpts sessionOpts = new SessionOpts();
                    sessionOpts.traffic = SessionOpts.TRAFFIC_MESSAGES;
                    sessionOpts.isMultipoint = false;
                    sessionOpts.proximity = SessionOpts.PROXIMITY_ANY;
                    sessionOpts.transports = SessionOpts.TRANSPORT_ANY;

                    status = mBus.bindSessionPort(contactPort, sessionOpts, new SessionPortListener() {
                        @Override
                        public boolean acceptSessionJoiner(short sessionPort, String joiner, SessionOpts sessionOpts) {
                            return sessionPort == CONTACT_PORT;
                        }
                    });
                    if (status != Status.OK) {
                        Log.e(TAG, "Failed to bind a session port");
                        return;
                    }

                    aboutObj = new AboutObj(mBus);
                    aboutData = new LocalAboutDataListener();
                    status = aboutObj.announce(CONTACT_PORT, aboutData);
                    if (status != Status.OK) {
                        Log.e("BusHandler", "Problem while sending about info");
                        return;
                    }

                    observer = new Observer(mBus, new Class[]{UserInterface.class});
                    observer.registerListener(new Observer.Listener() {
                        @Override
                        public void objectDiscovered(ProxyBusObject proxyBusObject) {
                            Message msg = obtainMessage(JOIN_GAME);
                            msg.obj = proxyBusObject;
                            sendMessage(msg);
                        }

                        @Override
                        public void objectLost(ProxyBusObject proxyBusObject) {
                            UserInterface user = proxyBusObject.getInterface(UserInterface.class);
                            Log.d(TAG + "LostUser", "Lost user");
                            String uuid = reverseUserDb.get(user);
                            userDb.remove(uuid);
                        }
                    });
                    break;
                case DISCONNECT:
                    Log.d(TAG, "Shutting down the AllJoyn Framework");
                    observer.close();
                    mBus.unregisterBusObject(user);
                    userDb.clear();
                    reverseUserDb.clear();
                    user.setLevel(1);
                    GameData.setRule(getString(R.string.rule));
                    user = null;
                    mBus.disconnect();
                    getLooper().quit();
                    break;
                case JOIN_GAME:
                    ProxyBusObject obj = (ProxyBusObject) msg.obj;
                    obj.enablePropertyCaching();
                    obj.setReplyTimeout(5000);
                    UserInterface user = obj.getInterface(UserInterface.class);
                    try {
                        // If we discovered ourselves, we omit us
                        if (user.getUUID().equals(Constants.UUID_STRING))
                            break;
                        // If the rule is different on that phone AND we are alone, we update our rule to match it
                        if ((userDb.size() == 0) && !(user.getRule().equals(getString(R.string.rule)))) {
                            Message message = this.obtainMessage(UPDATE_RULE);
                            message.obj = user.getRule();
                            this.sendMessage(message);
                        }
                        String name = user.getName();
                        Log.d(TAG + "JoinGame", "Discovered user " + name);
                        String uuid = user.getUUID();
                        userDb.put(uuid, user);
                        reverseUserDb.put(user, uuid);
                    } catch (BusException e) {
                        Log.e(TAG + "JoinGame", e.getMessage());
                    }
                    break;
                case LEVEL_UP_USER:
                    if (userDb.size() == 0) {
                        Server.this.user.levelUp();
                        break;
                    }
                    UserInterface random = (UserInterface) userDb.values().toArray()[new Random().nextInt(userDb.size())];
                    try {
                        random.acceptsLevelUp(Server.this.user.getUUID());
                    } catch (BusException e) {
                        Log.e(TAG + "LevelUp", e.getMessage());
                    }
                    break;
                case UPDATE_RULE:
                    String newRule = (String) msg.obj;
                    Handler h = GameData.getSpellsActivityHandler();
                    Message message = h.obtainMessage(Constants.MSG_UPDATE_RULE);
                    message.obj = msg.obj;
                    GameData.setRule(newRule);
                    h.sendMessage(message);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}
