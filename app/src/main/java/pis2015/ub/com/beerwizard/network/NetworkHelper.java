package pis2015.ub.com.beerwizard.network;

import android.content.Context;
import android.content.Intent;

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

    public void castSpell(String idCasterUser, String idTargetUser, String idSpell, String[] params) {

    }

    public void levelUp(String idUser) {

    }

    private void userExitsGame(byte idUser) {
    }
}
