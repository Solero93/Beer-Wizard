package pis2015.ub.com.beerwizard.gui;

import android.content.Context;

import java.util.ArrayList;

import pis2015.ub.com.beerwizard.game.SpellManager;
import pis2015.ub.com.beerwizard.network.GameData;
import pis2015.ub.com.beerwizard.network.NetworkHelper;
import pis2015.ub.com.beerwizard.network.User;

/**
 * Façade class that has all the "services" the GUI can call.
 */
//TODO Add Exception to ServerTimeOut
public class GUIFacade {
    private static GUIFacade ourInstance = new GUIFacade();

    private GUIFacade() {
    }

    public static GUIFacade getInstance() {
        return ourInstance;
    }

    /**
     * Gives all the Users that play the current GameData
     *
     * @return List of all Users in current GameData
     */
    static ArrayList<String> getAllUsers() {
        ArrayList<String> test = new ArrayList<>();
        test.add("USER A");
        test.add("USER B");
        return test;
        //return NetWorkFacade.getAllUsers();
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
     * Enters a given GameData.
     *
     * @param serverIP
     */
    static void enterGame(String serverIP) {
        //idLocalUser = NetworkHelper.enterGame(serverIP);
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
        User user = GameData.getInstance().getUser();
        user.setName(name);
        // user.setAvatar(idAvatar);
    }

    /**
     * Levels up the User
     */
    static void levelUp() {
        //NetworkHelper.levelUp();
    }

    /**
     * Decides whether to levelUp a specific user
     *
     * @param targetUser
     * @param success
     */
    static void levelUp(String targetUser, boolean success) {
        //NetworkHelper.levelUp(targetUser, success);
    }

    /**
     * Levels down the User
     */
    static void levelDown() {
        //NetworkHelper.levelDown();
    }

    /**
     * Gets the Name of a given Spell
     *
     * @param idSpell
     * @return Spell's name
     */
    static int getSpellName(int idSpell) {
        return SpellManager.getName(idSpell);
    }

    /**
     * Gets the Description of a given Spell
     * @param idSpell
     * @return Spell's Description
     */
    static int getSpellDescription(int idSpell) {
        return SpellManager.getDescription(idSpell);
    }

    /**
     * Gets the Quote of a given Spell
     *
     * @param idSpell
     * @return Spell's Quote
     */
    static int getSpellQuote(int idSpell) {
        return SpellManager.getQuote(idSpell);
    }

    /**
     * Gets the Locked Text of a given Spell
     * @param idSpell
     * @return Spell's LockedText
     */
    static int getSpellLockedText(int idSpell) {
        return SpellManager.getLockedText(idSpell);
    }

    /**
     * Gets Cooldown of Spell
     * @param idSpell
     * @return
     */
    static int getSpellCooldown(int idSpell) {
        return SpellManager.getCooldown(idSpell);
    }

    /**
     * Casts a Spell at a given User
     *
     * @param idSpell      - ID of spell to cast
     * @param param       - possible parametres (null if there aren't any)
     * @param idTargetUser - ID of User to cast spell on (null if it's self inflicted)
     */
    static void castSpell(int idTargetUser, int idSpell, String param) {
        //NetworkFacade.castSpell(idTargetUser, idSpell, param);
    }

    /* http://stackoverflow.com/questions/17233038/how-to-implement-synchronous-method-timeouts-in-java

    ExecutorService executor = Executors.newSingleThreadExecutor();
    Future<String> future = executor.submit(new Callable() {

        public String call() throws Exception {
            //do operations you want
            return "OK";
        }
    });
    try {
        System.out.println(future.get(2, TimeUnit.SECONDS)); //timeout is in 2 seconds
    } catch (TimeoutException e) {
        System.err.println("Timeout");
    }
    executor.shutdownNow();
    */
}