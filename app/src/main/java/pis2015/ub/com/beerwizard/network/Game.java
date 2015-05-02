package pis2015.ub.com.beerwizard.network;

import java.util.ArrayList;

public class Game {
    private static Game ourInstance = new Game();
    private String name;
    private String rule;
    private ArrayList<User> users;

    private Game() {
    }

    public static Game getInstance() {
        return ourInstance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRule() {
        return this.rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    /**
     * Modifies the profile of a User.
     * @param idUser
     * @param name
     * @param avatar
     */
    public void modifyUserProfile(String idUser, String name, String avatar) {
    }

    /**
     * Deletes a User from the list of Game's players.
     * @param idUser
     */
    public void userExitsGame(String idUser) {
    }
}