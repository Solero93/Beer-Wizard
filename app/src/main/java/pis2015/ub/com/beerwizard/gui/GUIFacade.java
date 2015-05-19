package pis2015.ub.com.beerwizard.gui;

import android.content.Context;

import org.alljoyn.bus.BusException;

import java.util.ArrayList;
import java.util.List;

import pis2015.ub.com.beerwizard.game.SpellManager;
import pis2015.ub.com.beerwizard.network.GameData;
import pis2015.ub.com.beerwizard.network.NetworkHelper;
import pis2015.ub.com.beerwizard.network.User;
import pis2015.ub.com.beerwizard.network.UserInterface;
import pis2015.ub.com.beerwizard.util.Constants;

/**
 * Façade class that has all the "services" the GUI can call.
 */
public class GUIFacade {

    /**
     * Gives all the Users that play the current GameData
     *
     * @return List of all Users in current GameData
     */
    static List<String> getAllUsers() {
        List<UserInterface> users = GameData.getUsers();
        ArrayList<String> array = new ArrayList<>();
        for (UserInterface user : users) {
            try {
                array.add(user.getName());
            } catch (BusException e) {
                e.printStackTrace();
            }
        }
        return array;
    }

    /**
     * Returns the name of a User
     * @param uuid the uuid of the user
     * @return
     */
    static String getUserName(String uuid) {
        try {
            if (uuid.equals(Constants.UUID_STRING))
                return GameData.getUser().getName();
            return GameData.getUser(uuid).getName();
        } catch (BusException ignored) {
            return "";
        }
    }

    /**
     * Returns the name of the current User
     * @return User's Name
     */
    static String getUserName() {
        return GameData.getUser().getName();
    }

    /**
     * Shows whether User has shield
     *
     * @return
     */
    static boolean haveShield() {
        return GameData.getUser().getShield();
    }

    /**
     * Breaks shield of User
     */
    static void breakShield() {
        GameData.getUser().setShield(false);
    }

    /**
     * Creates a GameData with a given name.
     *
     * @param gameName - name you want the GameData to have
     */
    static void createGame(Context context, String gameName) {
        NetworkHelper.createGame(context);
    }

    /**
     * Exits the current User from the GameData.
     */
    static void exitGame(Context context) {
        NetworkHelper.exitGame(context);
    }

    /**
     * Modifies the current User's profile.
     *
     * @param name
     * @param idAvatar
     */
    static void modifyUserProfile(String name, int idAvatar) {
        User user = GameData.getUser();
        user.setName(name);
        user.setAvatar(idAvatar);
    }

    /**
     * Returns current level of User
     *
     * @return Current Level
     */
    static int getLevel() {
        return GameData.getUser().getLevel();
    }

    /**
     * Levels up the User.
     */
    static void levelUp() {
        if (GameData.getUser().getLevel() < 10) {
            NetworkHelper.levelUp();
        }
    }

    /**
     * Levels Up a specific user.
     *
     * @param targetUser
     */
    static void levelUp(String targetUser) {
        NetworkHelper.levelUp(targetUser);
    }

    /**
     * Levels down the User
     */
    static void levelDown(String targetUser) {
        NetworkHelper.levelDown(targetUser);
    }

    /**
     * Casts a Spell at a given User.
     *
     * @param idSpell      - ID of spell to cast
     * @param param       - possible parametres (null if there aren't any)
     * @param userPosition - ID of User to cast spell on (null if it's self inflicted)
     */
    static void castSpell(int userPosition, int idSpell, String param) {
        try {
            String casterUserId = GameData.getUser().getUUID();
            switch (idSpell) {
                case SpellManager.SHIELD:
                    GameData.getUser().setShield(true);
                    break;
                case SpellManager.CREATE_RULE:
                    GameData.setRule(param);
                    NetworkHelper.castSpell(casterUserId, Constants.BROADCAST, idSpell, param); // has to be sent to everyone
                    break;
                case SpellManager.ALL_IN_BEER:
                    NetworkHelper.castSpell(casterUserId, Constants.BROADCAST, idSpell, param); // has to be sent to everyone
                    break;
                default: // Rest of cases
                    String targetUserId = GameData.getUser(userPosition).getUUID();
                    NetworkHelper.castSpell(casterUserId, targetUserId, idSpell, param);
                    break;
            }
        } catch (BusException e) {
            //TODO see what to put here
            e.printStackTrace();
        }
    }

    /**
     * Returns current rule of the Game
     * @return
     */
    static String getRule() {
        return GameData.getRule();
    }
}