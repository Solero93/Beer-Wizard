package pis2015.ub.com.beerwizard.network;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import pis2015.ub.com.beerwizard.R;
import pis2015.ub.com.beerwizard.util.Constants;

/**
 * A collection of global variables
 */
public class GameData extends Application {
    private static Handler spellsActivityHandler;
    private static String rule;
    private static User user;
    private static ConcurrentHashMap<String, UserInterface> users;

    /*
    We load the AllJoyn library
     */
    static {
        System.loadLibrary("alljoyn_java");
    }

    // TODO Why do you have a constructor with an onCreate()???
    public GameData() {
    }

    /**
     * Obtains the SpellsActivity's handler of messages
     *
     * @return the SpellsActivity's handler of messages
     */
    public static Handler getSpellsActivityHandler() {
        return spellsActivityHandler;
    }

    /**
     * Sets the SpellsActivity's handler of messages
     * @param spellsActivityHandler the new handler
     */
    public static void setSpellsActivityHandler(Handler spellsActivityHandler) {
        GameData.spellsActivityHandler = spellsActivityHandler;
    }

    /**
     * Obtains the local user
     * @return the local user
     */
    public static User getUser() {
        return user;
    }

    /**
     * Obtains the given user from the database. If the UUID corresponds to the local user's UUID
     * then we return the local user
     * @param uuid UUID of the user to obtain
     * @return an interface to the user
     */
    public static UserInterface getUser(String uuid) {
        if (uuid.equals(user.getUUID()))
            return user;
        return users.get(uuid);
    }

    /**
     * Obtains the user in the given position of the database
     * @param position the position on the database
     * @return the user itself
     */
    public static UserInterface getUser(int position) {
        /**
         * Note: VERY flaky to changes such as a user entering the game or leaving it in the span of
         * obtaining the position from the user and calling this
         *
         * The UI team decided not to make an adapter for UserInterfaces as recommended,
         * instead only passing us the position of the database
         */
        return getUsers().get(position);
    }

    /**
     * Obtains a List of the users excluding ourselves.
     * @return a list of users
     */
    public static List<UserInterface> getUsers() {
        return new ArrayList<>(users.values());
    }

    /**
     * Obtain the user database as is
     * @return the user database
     */
    public static ConcurrentHashMap<String, UserInterface> getUserDb() {
        return users;
    }

    /**
     * Obtain the rule of the game
     * @return the rule of the game
     */
    public static String getRule() {
        return rule;
    }

    /**
     * Set the new rule of the game
     * @param rule the new rule of the game
     */
    public static void setRule(String rule) {
        GameData.rule = rule;
    }

    public void onCreate() {
        super.onCreate();
        SharedPreferences preferences = getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        // TODO String "Change me!" should go to strings.xml -> Put R.string.user_name_default instead of Change me! (and change to getInt)
        user = new User(preferences.getString("name", "Change me!"), preferences.getInt("avatar", R.drawable.cara));
        rule = getString(R.string.rule);
        users = new ConcurrentHashMap<>();
    }
}