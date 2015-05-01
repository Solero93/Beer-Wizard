package pis2015.ub.com.beerwizard.gui;

import java.util.ArrayList;

import pis2015.ub.com.beerwizard.game.SpellManager;
import pis2015.ub.com.beerwizard.network.NetworkFacade;

public class GUIFacade {
    private String idLocalUser;

    public GUIFacade() {
    }

    static ArrayList<String> getAllGames() {
        return NetworkFacade.getAllGames();
    }

    static void createGame(String gameName) {
        NetworkFacade.createGame(gameName);
        //TODO Ask Jordi to return generated ID for user
    }

    static void enterGame(String serverIP) {
        NetworkFacade.enterGame(serverIP);
        //TODO Ask Jordi to return generated ID for user
    }

    static void modifyUserProfile(String name, String idAvatar) {
        NetworkFacade.modifyUserProfile(idLocalUser, name, idAvatar);
    }

    static void exitGame() {
        NetworkFacade.exitGame(idLocalUser);
    }

    static ArrayList<String> getAllUsers() {
        return NetWorkFacade.getAllUsers();
    }

    static String getSpellName(String idSpell) {
        SpellManager.getName(idSpell);
    }

    static String getSpellDescription(String idSpell) {
        SpellManager.getDescription(idSpell);
    }

    static String getSpellQuote(String idSpell) {
        SpellManager.getQuote(idSpell);
    }

    static void castSpell(String idSpell, String[] params, String idTargetUser) {
        NetworkFacade.castSpell(idLocalUser, idTargetUser, idSpell, params);
    }
}