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

/**
 * Helper for the other classes to abstract functions from the rest of the implementation
 */
public class NetworkHelperTest {

    /**
     * Creates a game locally
     *
     * @param context the activity that requests the game to start
     */
    public static void createGame(Context context) {
        Intent intent = new Intent(context, ServerTest.class);
        context.startService(intent);
    }

    /**
     * Exits the game, shutting down the server
     * @param context the activity that requests the game to stop
     */
    public static void exitGame(Context context) {
        Intent intent = new Intent(context, ServerTest.class);
        context.stopService(intent);
    }

    /**
     * Asks to level up the user.
     *
     * Will ask the server to contact another user for permission
     */
    public static void levelUp() {
        Handler h = ServerTest.busHandler;
        h.sendMessage(h.obtainMessage(ServerTest.BusHandler.LEVEL_UP_USER));
    }

    /**
     * Levels up the given user
     * @param uuidUser the UUID of the user to level up
     */
    public static void levelUp(String uuidUser) {
        try {
            GameDataTest.getUser(uuidUser).levelUp();
        } catch (BusException e) {
            Log.e("LevelUpUser", e.getMessage());
        }
    }

    /**
     * Levels down the given user
     * @param uuidUser UUID of the user
     */
    public static void levelDown(String uuidUser) {
        try {
            GameDataTest.getUser(uuidUser).levelDown();
        } catch (BusException e) {
            Log.e("LevelDownUser", e.getMessage());
        }
    }

    /**
     * Casts a spell to the given user
     * @param idCasterUser the UUID of the user who casts the spell
     * @param idTargetUser the UUID of the user who is targeted
     * @param idSpell the spell id
     * @param params extra parameters if the spell requires them (i.e. Rule)
     */
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
                    SignalEmitter emitter = new SignalEmitter(GameDataTest.getUser(), BusAttachment.SESSION_ID_ALL_HOSTED, SignalEmitter.GlobalBroadcast.Off);
                    user = emitter.getInterface(UserInterface.class);
                    user.updateRule(GameDataTest.getRule());
                    break;
                case SpellManager.ALL_IN_BEER:
                    // TODO Change this to a signal
                    for (UserInterface userInterface : GameDataTest.getUsers()) {
                        userInterface.castedSpell(idSpell, idCasterUser, params);
                    }
                    break;
                case SpellManager.WIZARD_DUEL:
                    Random random = new Random();
                    List<UserInterface> list = GameDataTest.getUsers();
                    // If there are only two players in the game we choose either caster or target
                    // randomly
                    if (list.size() == 1) {
                        // Target
                        if (random.nextInt(2) == 1)
                            user = list.get(0);
                            // Caster
                        else
                            user = GameDataTest.getUser();
                        // Otherwise we choose a third player who is neither of those two
                    } else {
                        user = list.get(random.nextInt(list.size()));
                        while (user.getUUID().equals(idCasterUser) || user.getUUID().equals(idTargetUser))
                            user = list.get(random.nextInt(list.size()));
                    }
                    // We make a player the judge
                    user.beJudge(idCasterUser, idTargetUser);
                    // And we send a notification to the target
                    GameDataTest.getUser(idTargetUser).castedSpell(idSpell, idCasterUser, params);
                    break;
                default:
                    user = GameDataTest.getUser(idTargetUser);
                    user.castedSpell(idSpell, idCasterUser, params);
                    break;
            }
        } catch (BusException e) {
            e.printStackTrace();
        }
    }
}
