package pis2015.ub.com.beerwizard.network;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;

import org.alljoyn.bus.BusAttachment;
import org.alljoyn.bus.BusException;
import org.alljoyn.bus.BusListener;
import org.alljoyn.bus.Mutable;
import org.alljoyn.bus.PropertyChangedEmitter;
import org.alljoyn.bus.SessionOpts;
import org.alljoyn.bus.SessionPortListener;
import org.alljoyn.bus.Status;
import org.alljoyn.bus.Variant;

public class Server extends Service {

    static {
        System.loadLibrary("alljoyn_java");
    }

    private User user = null;
    private BusHandler busHandler = null;
    private Messenger messenger;

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("UserProvider");
        thread.start();
        busHandler = new BusHandler(thread.getLooper());
        messenger = new Messenger(busHandler);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        messenger = null;
        busHandler.getLooper().quit();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return messenger.getBinder();
    }

    private class BusHandler extends Handler {
        public static final int CONNECT = 1;
        public static final int DISCONNECT = 2;
        public static final int UPDATE_PROPERTIES = 3;
        private static final String SERVICE_NAME = "pis2015.ub.com.beerwizard.user";
        private static final short CONTACT_PORT = 42;
        private BusAttachment mBus;

        public BusHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case CONNECT:
                    org.alljoyn.bus.alljoyn.DaemonInit.PrepareDaemon(getApplicationContext());
                    mBus = new BusAttachment(getPackageName(), BusAttachment.RemoteMessage.Receive);

                    mBus.registerBusListener(new BusListener());

                    /*
                    * Register the BusObject with the path "/userProperties"
                    */
                    Status status = mBus.registerBusObject(user, "/userProperties");
                    if (status != Status.OK) {
                        // FAIL
                        return;
                    }

                    status = mBus.connect();
                    if (status != Status.OK) {
                        // FAIL
                        return;
                    }

                    /*
                     * Create a new session listening on the contact port of the user service
                     */
                    Mutable.ShortValue contactPort = new Mutable.ShortValue(CONTACT_PORT);

                    SessionOpts sessionOpts = new SessionOpts();
                    sessionOpts.traffic = SessionOpts.TRAFFIC_MESSAGES;
                    sessionOpts.isMultipoint = true;
                    sessionOpts.proximity = SessionOpts.PROXIMITY_ANY;
                    sessionOpts.transports = SessionOpts.TRANSPORT_ANY;

                    status = mBus.bindSessionPort(contactPort, sessionOpts, new SessionPortListener() {
                        @Override
                        public boolean acceptSessionJoiner(short sessionPort, String joiner, SessionOpts sessionOpts) {
                            if (sessionPort == CONTACT_PORT) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    });
                    if (status != Status.OK) {
                        // FAIL
                        return;
                    }

                    /*
                     * request a well-known name from the bus
                     */
                    int flag = BusAttachment.ALLJOYN_REQUESTNAME_FLAG_REPLACE_EXISTING | BusAttachment.ALLJOYN_REQUESTNAME_FLAG_DO_NOT_QUEUE;

                    status = mBus.requestName(SERVICE_NAME, flag);
                    if (status == Status.OK) {
                    /*
                     * If we successfully obtain a well-known name from the bus
                     * advertise the same well-known name
                     */
                        status = mBus.advertiseName(SERVICE_NAME, SessionOpts.TRANSPORT_ANY);
                        if (status != Status.OK) {
                        /*
                         * If we are unable to advertise the name, release
                         * the name from the local bus.
                         */
                            status = mBus.releaseName(SERVICE_NAME);
                            // FAIL
                            return;
                        }
                    }

                    break;
                case DISCONNECT:
                    mBus.unregisterBusObject(user);
                    mBus.disconnect();
                    busHandler.getLooper().quit();
                    break;
                case UPDATE_PROPERTIES:
                    PropertyChangedEmitter propertyChangedEmitter = new PropertyChangedEmitter(user);
                    try {
                        propertyChangedEmitter.PropertyChanged(SERVICE_NAME,
                                msg.getData().getString(Constants.PROPERTY_CHANGED_KEY),
                                new Variant(msg.getData().getString(Constants.NEW_PROPERTY_VALUE)));
                    } catch (BusException e) {
                        e.printStackTrace();
                    }
                    break;
                //case BOSS_ENCOUNTERED:
                //    break;
            }
        }
    }
}
