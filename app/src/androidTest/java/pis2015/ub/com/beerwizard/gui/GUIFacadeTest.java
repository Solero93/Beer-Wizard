package pis2015.ub.com.beerwizard.gui;

import android.content.Context;

import org.alljoyn.bus.BusException;

import java.util.ArrayList;
import java.util.List;

import pis2015.ub.com.beerwizard.game.SpellManager;
import pis2015.ub.com.beerwizard.network.GameDataTest;
import pis2015.ub.com.beerwizard.network.NetworkHelperTest;
import pis2015.ub.com.beerwizard.network.UserTest;
import pis2015.ub.com.beerwizard.network.UserInterface;
import pis2015.ub.com.beerwizard.util.ConstantsTest;

/**
 * Façade class that has all the "services" the GUI can call.
 */
public class GUIFacadeTest {

    /**
     * Gives all the Users that play the current GameData
     * @return List of all Users in current GameData
     */
    public static List<String> getAllUsers() {
        List<UserInterface> users = GameDataTest.getUsers();
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
            if (uuid.equals(ConstantsTest.UUID_STRING))
                return GameDataTest.getUser().getName();
            return GameDataTest.getUser(uuid).getName();
        } catch (BusException ignored) {
            return "";
        }
    }

    /**
     * Returns the name of the current User
     * @return User's Name
     */
    public static String getUserName() {
        return GameDataTest.getUser().getName();
    }

    /**
     * Returns avatar of current User
     * @return
     */
    public static int getUserAvatar() {
        return GameDataTest.getUser().getAvatar();
    }

    /**
     * Shows whether User has shield
     * @return
     */
    public static boolean getUserShield() {
        return GameDataTest.getUser().getShield();
    }

    /**
     * Breaks shield of User
     */
    public static void breakUserShield() {
        GameDataTest.getUser().setShield(false);
    }

    /**
     * Lets a User create or enter a Game
     * @param context
     */
    public static void createGame(Context context) {
        NetworkHelperTest.createGame(context);
    }

    /**
     * Removes a User from the list of User when he exists
     * @param context
     */
    public static void exitGame(Context context) {
        NetworkHelperTest.exitGame(context);
    }

    /**
     * Modifies the current User's profile.
     * @param name
     * @param idAvatar
     */
    public static void modifyUserProfile(String name, int idAvatar) {
        UserTest user = GameDataTest.getUser();
        user.setName(name);
        user.setAvatar(idAvatar);
    }

    /**
     * Returns current level of User
     * @return Current Level
     */
    public static int getLevel() {
        return GameDataTest.getUser().getLevel();
    }

    /**
     * Levels up the User.
     */
    public static void levelUp() {
        if (GameDataTest.getUser().getLevel() < 10) {
            NetworkHelperTest.levelUp();
        }
    }

    /**
     * Levels Up a specific user.
     * @param uuid
     */
    public static void levelUp(String uuid) {
        NetworkHelperTest.levelUp(uuid);
    }

    /**
     * Levels down a User after losing a duel
     * @param uuid
     */
    public static void levelDown(String uuid) {
        NetworkHelperTest.levelDown(uuid);
    }

    /**
     * Casts a Spell at a given User.
     * @param idSpell      - ID of spell to cast
     * @param param       - possible parametres (null if there aren't any)
     * @param targetUserPosition - ID of User to cast spell on (null if it's self inflicted)
     */
    public static void castSpell(int targetUserPosition, int idSpell, String param) {
        try {
            String casterUserId = GameDataTest.getUser().getUUID();
            switch (idSpell) {
                case SpellManager.SHIELD:
                    GameDataTest.getUser().setShield(true);
                    break;
                case SpellManager.CREATE_RULE:
                    GameDataTest.setRule(param);
                    NetworkHelperTest.castSpell(casterUserId, ConstantsTest.BROADCAST, idSpell, param); // has to be sent to everyone
                    break;
                case SpellManager.ALL_IN_BEER:
                    NetworkHelperTest.castSpell(casterUserId, ConstantsTest.BROADCAST, idSpell, param); // has to be sent to everyone
                    break;
                default: // Rest of cases
                    String targetUserId = GameDataTest.getUser(targetUserPosition).getUUID();
                    NetworkHelperTest.castSpell(casterUserId, targetUserId, idSpell, param);
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
        return GameDataTest.getRule();
    }
}