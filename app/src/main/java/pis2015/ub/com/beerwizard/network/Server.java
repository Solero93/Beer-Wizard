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

import java.util.List;
import java.util.Random;

public class Server extends Service {
    private static final String TAG = "ServerService";
    static BusHandler busHandler = null;
    private final List<UserInterface> userDb = GameData.getInstance().getUsers();
    private User user = GameData.getInstance().getUser();
    private Thread printer;

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("UserProvider");
        thread.start();
        busHandler = new BusHandler(thread.getLooper());
        busHandler.sendMessage(busHandler.obtainMessage(BusHandler.CONNECT));
        printer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        Thread.sleep(5000);
                        String tmp = "";
                        for (UserInterface user : userDb) {
                            try {
                                tmp = tmp + user.getName() + ",";
                            } catch (BusException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d(TAG + "Printer", tmp);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return;
                }
            }
        });
        printer.start();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Starting Server");
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Shutting Server");
        printer.interrupt();
        userDb.clear();
        busHandler.sendMessage(busHandler.obtainMessage(BusHandler.DISCONNECT));
        user = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class BusHandler extends Handler {
        public static final int CONNECT = 1;
        public static final int DISCONNECT = 2;
        public static final int LEVEL_UP_USER = 3;
        public static final int JOIN_GAME = 4;
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
                        Log.e(TAG, "Failed to register bus object");
                        return;
                    }

                    status = mBus.connect();
                    if (status != Status.OK) {
                        Log.e(TAG, "Failed to connect to the buss");
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
                            userDb.remove(user);
                        }
                    });
                    break;
                case DISCONNECT:
                    Log.d(TAG, "Shutting down the AllJoyn Framework");
                    mBus.unregisterBusObject(user);
                    aboutObj.unannounce();
                    mBus.disconnect();
                    busHandler.getLooper().quit();
                    break;
                case JOIN_GAME:
                    ProxyBusObject obj = (ProxyBusObject) msg.obj;
                    obj.enablePropertyCaching();
                    UserInterface user = obj.getInterface(UserInterface.class);
                    try {
                        if (user.getUUID().equals(Constants.UUID_STRING))
                            break;
                        String name = user.getName();
                        Log.d(TAG + "JoinGame", "Discovered user " + name);
                    } catch (BusException e) {
                        Log.e(TAG + "JoinGame", e.getMessage());
                    }
                    userDb.add(obj.getInterface(UserInterface.class));
                    break;
                case LEVEL_UP_USER:
                    UserInterface random = userDb.get(new Random().nextInt(userDb.size()));
                    random.acceptsLevelUp(Server.this.user.getUUID());
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }
}
