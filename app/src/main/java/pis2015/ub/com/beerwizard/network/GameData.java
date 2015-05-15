package pis2015.ub.com.beerwizard.network;

import android.app.Application;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameData extends Application {
    private static GameData ourInstance = new GameData();
    private String rule;
    private User user = new User();
    private CopyOnWriteArrayList<UserInterface> users = new CopyOnWriteArrayList<>();

    public static GameData getInstance() {
        return ourInstance;
    }

    public void onCreate() {
        super.onCreate();
    }

    public User getUser() {
        return user;
    }

    public List<UserInterface> getUsers() {
        return users;
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
    public void modifyUserProfile(byte idUser, String name, int avatar) {
    }
}