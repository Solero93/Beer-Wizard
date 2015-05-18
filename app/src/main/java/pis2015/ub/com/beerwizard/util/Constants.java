package pis2015.ub.com.beerwizard.util;

import android.os.Build;

import java.util.UUID;

/**
 * Created by jordi on 4/27/15.
 */
public class Constants {
    public static final String UUID_STRING = UUID.randomUUID().toString();
    public static final String VERSION_NUMBER = Build.VERSION.RELEASE;
    public static final byte[] APP_ID = {42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42, 42};
    public static final int MSG_DECIDE_LEVEL = 0;
    public static final int MSG_LEVEL_UP = 1;
    public static final int MSG_LEVEL_DOWN = 2;
    public static final int MSG_CASTED_SPELL = 3;
    public static final String INTERFACE_NAME = "pis2015.ub.com.beerwizard.user";
    public static final int MSG_UPDATE_RULE = 4;
    public static final int MSG_DECIDE_DUEL = 5;
    public static final String BROADCAST = "";
    public static final String SHARED_PREFERENCES_NAME = "beer_wizard";
}
