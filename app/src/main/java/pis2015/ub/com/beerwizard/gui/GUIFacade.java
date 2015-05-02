package pis2015.ub.com.beerwizard.gui;

import java.util.ArrayList;

import pis2015.ub.com.beerwizard.game.avatar.AvatarManager;
import pis2015.ub.com.beerwizard.game.spells.SpellManager;
import pis2015.ub.com.beerwizard.network.NetworkFacade;

/**
 * Façade class that has all the "services" the GUI can call.
 */
public class GUIFacade {
    private String idLocalUser;

    public GUIFacade() {
    }

    /**
     * Gives all the games that are played nearby.
     * @return Nearby game's Names
     */
    static ArrayList<String> getAllGames() {
        ArrayList<String> hardcode = new ArrayList<>(2);
        hardcode.set(0, "GAME A");
        hardcode.set(1, "GAME B");
        return hardcode;
        //return NetworkFacade.getAllGames();
    }

    /**
     * Creates a Game with a given name.
     * @param gameName - name you want the Game to have
     */
    static void createGame(String gameName) {
        NetworkFacade.createGame(gameName);
        //TODO Ask Jordi to return generated ID for user
    }

    /**
     * Enters a given Game.
     * @param serverIP
     */
    static void enterGame(String serverIP) {
        NetworkFacade.enterGame(serverIP);
        //TODO Ask Jordi to return generated ID for user
    }

    /**
     * Modifies the current User's profile.
     * @param name
     * @param idAvatar
     */
    static void modifyUserProfile(String name, String idAvatar) {
        NetworkFacade.modifyUserProfile(this.idLocalUser, name, AvatarManager.getAvatar(idAvatar));
    }

    /**
     * Exits the current User from the Game.
     */
    static void exitGame() {
        NetworkFacade.exitGame(this.idLocalUser);
    }

    /**
     * Gives all the Users that play the current Game
     * @return List of all Users in current Game
     */
    static ArrayList<String> getAllUsers() {
        ArrayList<String> hardcode = new ArrayList<>(2);
        hardcode.set(0, "USER A");
        hardcode.set(1, "USER B");
        //return NetWorkFacade.getAllUsers();
    }

    /**
     * Gets the Name of a given Spell
     * @param idSpell
     * @return Spell's name
     */
    static String getSpellName(String idSpell) {
        return SpellManager.getName(idSpell);
    }

    /**
     * Gets the Description of a given Spell
     * @param idSpell
     * @return Spell's Description
     */
    static String getSpellDescription(String idSpell) {
        return SpellManager.getDescription(idSpell);
    }

    /**
     * Gets the Quote of a given Spell
     * @param idSpell
     * @return Spell's Quote
     */
    static String getSpellQuote(String idSpell) {
        return SpellManager.getQuote(idSpell);
    }

    /**
     * Casts a Spell at a given User
     * @param idSpell - ID of spell to cast
     * @param params - possible parametres (null if there aren't any)
     * @param idTargetUser - ID of User to cast spell on (null if it's self inflicted)
     */
    static void castSpell(String idSpell, String[] params, String idTargetUser) {
        NetworkFacade.castSpell(this.idLocalUser, idTargetUser, idSpell, params);
    }
}