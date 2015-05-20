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
            GameData.getUser(uuidUser).levelUp();
        } catch (BusException e) {
            Log.e("LevelUpUser", e.getMessage());
        }
    }

    public static void levelDown(String uuidUser) {
        try {
            GameData.getUser(uuidUser).levelDown();
        } catch (BusException e) {
            Log.e("LevelDownUser", e.getMessage());
        }
    }

    public static void castSpell(String idCasterUser, String idTargetUser, int idSpell, String params) {
        UserInterface user;
        if (params == null)
            params = "";
        try {
            switch (idSpell) {
                case SpellManager.CREATE_RULE:
                    /*
                    We emit a signal for all to update the rule
                     */
                    SignalEmitter emitter = new SignalEmitter(GameData.getUser(), BusAttachment.SESSION_ID_ALL_HOSTED, SignalEmitter.GlobalBroadcast.Off);
                    user = emitter.getInterface(UserInterface.class);
                    user.updateRule(GameData.getRule());
                    break;
                case SpellManager.ALL_IN_BEER:
                    // TODO Change this to a signal
                    for (UserInterface userInterface : GameData.getUsers()) {
                        userInterface.castedSpell(idSpell, idCasterUser, params);
                    }
                    break;
                case SpellManager.WIZARD_DUEL:
                    Random random = new Random();
                    List<UserInterface> list = GameData.getUsers();
                    // If there are only two players in the game we choose either caster or target
                    // randomly
                    if (list.size() == 1) {
                        // Target
                        if (random.nextInt(2) == 1)
                            user = list.get(0);
                            // Caster
                        else
                            user = GameData.getUser();
                        // Otherwise we choose a third player who is neither of those two
                    } else {
                        user = list.get(random.nextInt(list.size()));
                        while (user.getUUID().equals(idCasterUser) || user.getUUID().equals(idTargetUser))
                            user = list.get(random.nextInt(list.size()));
                    }
                    // We make a player the judge
                    user.beJudge(idCasterUser, idTargetUser);
                    // And we send a notification to the target
                    GameData.getUser(idTargetUser).castedSpell(idSpell, idCasterUser, params);
                    break;
                default:
                    user = GameData.getUser(idTargetUser);
                    user.castedSpell(idSpell, idCasterUser, params);
                    break;
            }
        } catch (BusException e) {
            e.printStackTrace();
        }
    }
}
