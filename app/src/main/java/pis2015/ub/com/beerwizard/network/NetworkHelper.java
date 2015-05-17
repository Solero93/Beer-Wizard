package pis2015.ub.com.beerwizard.network;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import org.alljoyn.bus.BusAttachment;
import org.alljoyn.bus.BusException;
import org.alljoyn.bus.SignalEmitter;

import java.util.List;
import java.util.Random;

import pis2015.ub.com.beerwizard.game.SpellManager;

public class NetworkHelper {

    public static byte createGame(Context context) {
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
            GameData.getUserDb().get(uuidUser).levelUp();
        } catch (BusException e) {
            Log.e("LevelUpUser", e.getMessage());
        }
    }

    public static void levelDown(String uuidUser) {
        try {
            GameData.getUserDb().get(uuidUser).levelDown();
        } catch (BusException e) {
            Log.e("LevelDownUser", e.getMessage());
        }
    }

    public static void castSpell(String idCasterUser, String idTargetUser, int idSpell, String params) {
        try {
            if (idTargetUser == null) {
                if (idSpell == SpellManager.CREATE_RULE) {
                    SignalEmitter emitter = new SignalEmitter(GameData.getUser(), BusAttachment.SESSION_ID_ALL_HOSTED, SignalEmitter.GlobalBroadcast.Off);
                    UserInterface userInterface = emitter.getInterface(UserInterface.class);
                    userInterface.updateRule(GameData.getRule());
                } else if (idSpell == SpellManager.ALL_IN_BEER) {

                }
                return;
            } else if (idSpell == SpellManager.WIZARD_DUEL) {
                Random random = new Random();
                List<UserInterface> list = GameData.getUsers();
                UserInterface user;
                if (list.size() <= 1) {
                    user = list.get(random.nextInt(list.size()));
                } else {
                    user = list.get(random.nextInt(list.size()));
                    while (user.getUUID().equals(idCasterUser) || user.getUUID().equals(idTargetUser))
                        user = list.get(random.nextInt(list.size()));
                }
                user.beJudge(idCasterUser, idTargetUser);
            } else {
                UserInterface user = GameData.getUser(idTargetUser);
                user.castedSpell(idSpell, idCasterUser, params);
            }
        } catch (BusException e) {
            e.printStackTrace();
        }
    }
}
