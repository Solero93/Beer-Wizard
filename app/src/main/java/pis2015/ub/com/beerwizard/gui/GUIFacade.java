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
     * @return List of all Users in current GameData
     */
    public static List<String> getAllUsers() {
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
    public static String getUserName(String uuid) {
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
    public static String getUserName() {
        return GameData.getUser().getName();
    }

    /**
     * Returns avatar of current User
     * @return
     */
    public static int getUserAvatar() {
        return GameData.getUser().getAvatar();
    }

    /**
     * Shows whether User has shield
     * @return
     */
    public static boolean getUserShield() {
        return GameData.getUser().getShield();
    }

    /**
     * Breaks shield of User
     */
    public static void breakUserShield() {
        GameData.getUser().setShield(false);
    }

    /**
     * Lets a User create or enter a Game
     * @param context
     */
    public static void createGame(Context context) {
        NetworkHelper.createGame(context);
    }

    /**
     * Removes a User from the list of User when he exists
     * @param context
     */
    public static void exitGame(Context context) {
        NetworkHelper.exitGame(context);
    }

    /**
     * Modifies the current User's profile.
     * @param name
     * @param idAvatar
     */
    public static void modifyUserProfile(String name, int idAvatar) {
        User user = GameData.getUser();
        user.setName(name);
        user.setAvatar(idAvatar);
    }

    /**
     * Returns current level of User
     * @return Current Level
     */
    public static int getLevel() {
        return GameData.getUser().getLevel();
    }

    /**
     * Levels up the User.
     */
    public static void levelUp() {
        if (GameData.getUser().getLevel() < 10) {
            NetworkHelper.levelUp();
        }
    }

    /**
     * Levels Up a specific user.
     * @param uuid
     */
    public static void levelUp(String uuid) {
        NetworkHelper.levelUp(uuid);
    }

    /**
     * Levels down a User after losing a duel
     * @param uuid
     */
    public static void levelDown(String uuid) {
        NetworkHelper.levelDown(uuid);
    }

    /**
     * Casts a Spell at a given User.
     * @param idSpell      - ID of spell to cast
     * @param param       - possible parametres (null if there aren't any)
     * @param targetUserPosition - ID of User to cast spell on (null if it's self inflicted)
     */
    public static void castSpell(int targetUserPosition, int idSpell, String param) {
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
                    String targetUserId = GameData.getUser(targetUserPosition).getUUID();
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
    public static String getRule() {
        return GameData.getRule();
    }
}