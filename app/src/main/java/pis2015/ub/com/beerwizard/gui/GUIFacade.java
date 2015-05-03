package pis2015.ub.com.beerwizard.gui;

import java.util.ArrayList;

import pis2015.ub.com.beerwizard.game.SpellManager;

/**
 * Façade class that has all the "services" the GUI can call.
 */
//TODO Add Exception to ServerTimeOut
public class GUIFacade {
    private static GUIFacade ourInstance = new GUIFacade();
    private static byte idLocalUser;

    private GUIFacade() {
        idLocalUser = -1;
    }

    public static GUIFacade getInstance() {
        return ourInstance;
    }

    /**
     * Gives all the games that are played nearby.
     *
     * @return Nearby game's Names
     */
    static ArrayList<String> getAllGames() {
        ArrayList<String> test = new ArrayList<>(2);
        test.set(0, "GAME A");
        test.set(1, "GAME B");
        return test;
        //return NetworkFacade.getAllGames();
    }

    /**
     * Gives all the Users that play the current Game
     *
     * @return List of all Users in current Game
     */
    static ArrayList<String> getAllUsers() {
        ArrayList<String> test = new ArrayList<>(2);
        test.set(0, "USER A");
        test.set(1, "USER B");
        return test;
        //return NetWorkFacade.getAllUsers();
    }

    /**
     * Creates a Game with a given name.
     *
     * @param gameName - name you want the Game to have
     */
    static void createGame(String gameName) {
        //idLocalUser = NetworkFacade.createGame(gameName);
    }

    /**
     * Enters a given Game.
     *
     * @param serverIP
     */
    static void enterGame(String serverIP) {
        //idLocalUser = NetworkFacade.enterGame(serverIP);
    }

    /**
     * Exits the current User from the Game.
     */
    static void exitGame() {
        //NetworkFacade.exitGame(idLocalUser);
        idLocalUser = -1;
    }

    /**
     * Modifies the current User's profile.
     *
     * @param name
     * @param idAvatar
     */
    static void modifyUserProfile(String name, int idAvatar) {
        //NetworkFacade.modifyUserProfile(idLocalUser, name, (byte)idAvatar);
    }

    /**
     * Levels up the User
     */
    static void levelUp() {
        //NetworkFacade.levelUp(idLocalUser);
    }

    /**
     * Levels down the User
     */
    static void levelDown() {
        //NetworkFacade.levelDown(idLocalUser);
    }

    /**
     * Gets the Name of a given Spell
     *
     * @param idSpell
     * @return Spell's name
     */
    static String getSpellName(int idSpell) {
        return SpellManager.getName(idSpell);
    }

    /**
     * Gets the Description of a given Spell
     *
     * @param idSpell
     * @return Spell's Description
     */
    static String getSpellDescription(int idSpell) {
        return SpellManager.getDescription(idSpell);
    }

    /**
     * Gets the Quote of a given Spell
     *
     * @param idSpell
     * @return Spell's Quote
     */
    static String getSpellQuote(int idSpell) {
        return SpellManager.getQuote(idSpell);
    }

    /**
     * Casts a Spell at a given User
     *
     * @param idSpell      - ID of spell to cast
     * @param params       - possible parametres (null if there aren't any)
     * @param idTargetUser - ID of User to cast spell on (null if it's self inflicted)
     */
    static void castSpell(int idTargetUser, int idSpell, String[] params) {
        //NetworkFacade.castSpell(idLocalUser, (byte)idTargetUser, (byte)idSpell, params);
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