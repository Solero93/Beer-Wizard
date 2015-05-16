package pis2015.ub.com.beerwizard.network;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import org.alljoyn.bus.BusException;

public class NetworkHelper {

    public static byte createGame(Context context) {
        Intent intent = new Intent(context, Server.class);
        context.startService(intent);
        return 0;
    }

    public static byte enterGame(Context context) {
        Intent intent = new Intent(context, Server.class);
        context.startService(intent);
        return 0;
    }

    public static void exitGame(Context context) {
        Intent intent = new Intent(context, Server.class);
        context.stopService(intent);
    }

    public static void levelUp() {
        Handler h = Server.busHandler;
        h.sendMessage(h.obtainMessage(Server.BusHandler.LEVEL_UP_USER));
    }

    public static void levelUp(String uuidUser) {
        try {
            GameData.getInstance().getUserDb().get(uuidUser).levelUp();
        } catch (BusException e) {
            Log.e("LevelUpUser", e.getMessage());
        }
    }

    public static void levelDown(String uuidUser) {
        try {
            GameData.getInstance().getUserDb().get(uuidUser).levelDown();
        } catch (BusException e) {
            Log.e("LevelUpUser", e.getMessage());
        }
    }

    public static void castSpell(String idCasterUser, String idTargetUser, int idSpell, String params) {
        UserInterface user = GameData.getInstance().getUser(idTargetUser);
        try {
            user.castedSpell(idSpell, idCasterUser);
        } catch (BusException e) {
            e.printStackTrace();
        }
    }
}
