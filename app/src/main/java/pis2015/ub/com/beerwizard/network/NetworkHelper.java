package pis2015.ub.com.beerwizard.network;

import android.content.Context;
import android.content.Intent;
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

    public static void levelUp(String uuidUser) {
        try {
            GameData.getInstance().getUserDb().get(uuidUser).levelUp();
        } catch (BusException e) {
            Log.e("LevelUpUser", e.getMessage());
        }
    }

    public void castSpell(String idCasterUser, String idTargetUser, String idSpell, String[] params) {

    }

    private void userExitsGame(byte idUser) {
    }
}
