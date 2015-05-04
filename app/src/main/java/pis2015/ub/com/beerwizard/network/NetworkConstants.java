package pis2015.ub.com.beerwizard.network;

/**
 * Created by jordi on 4/27/15.
 */
public class NetworkConstants {
    public static final String GAME_NAME_EXTRA_ID = "pis2015.ub.com.beerwizard.network.GameName";
    public static final int SERVER_PORT = 2526;
    public static final int SERVER_LISTENER_PORT = 2525;
    public static final int PING_INST = 0x01;
    public static final byte[] PING_PACKET = {0x4A, 0x4E, -127, -127};
    public static final int REGISTER_INST = 0x02;
    public static final int RECEIVE_USERS_INST = 0x03;
    public static final int UPDATE_USER_INST = 0x04;
}
