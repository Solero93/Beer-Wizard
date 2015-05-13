package pis2015.ub.com.beerwizard.network;

import android.os.Build;

import java.util.UUID;

/**
 * Created by jordi on 4/27/15.
 */
public class Constants {
    public static final String GAME_NAME_EXTRA_ID = "pis2015.ub.com.beerwizard.network.GameName";
    public static final String PROPERTY_CHANGED_KEY = "property_changed";
    public static final String NEW_PROPERTY_VALUE = "property_value";
    public final static int BROADCAST_ALL = 0;
    public static final int SERVER_PORT = 2526;
    public static final int CONNECT_MSG = 1;
    public static final int DISCONNECT_MSG = 2;
    public static final int UPDATE_PROPERTIES_MSG = 3;
    public static final int SERVER_LISTENER_PORT = 2525;
    public static final int PING_INST = 0x01;
    public static final byte[] PING_PACKET = {0x4A, 0x4E, -127, -127};
    public static final int REGISTER_INST = 0x02;
    public static final int RECEIVE_USERS_INST = 0x03;
    public static final int UPDATE_USER_INST = 0x04;
    public static final String UUID_STRING = UUID.randomUUID().toString();
    public static final String VERSION_NUMBER = Build.VERSION.RELEASE;
    public static final byte[] APP_ID = {42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42};
}
