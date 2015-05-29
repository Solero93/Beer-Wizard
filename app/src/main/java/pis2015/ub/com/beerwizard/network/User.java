package pis2015.ub.com.beerwizard.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.alljoyn.bus.BusObject;

import pis2015.ub.com.beerwizard.util.Constants;

/**
 * Class that represents the User (or Player) object
 */
public class User implements UserInterface, BusObject {
    private static final String TAG = "User";
    private String name;
    private int level;
    private int avatarPhoto;
    private boolean hasShield;

    /**
     * Instantiates the user with the given name and avatar
     *
     * @param name        the name of the user
     * @param avatarPhoto the avatar identifier of the user
     */
    public User(String name, int avatarPhoto) {
        this.level = 1;
        this.name = name;
        this.avatarPhoto = avatarPhoto;
    }

    /**
     * Levels up the User.
     *
     * Sends a message to the UI to notify it of the fact
     */
    public void levelUp() {
        if (this.level < 10) {
            this.setLevel(this.getLevel() + 1);
            Log.d(TAG, "User has leveled up: " + this.getLevel());
        }
        Handler handler = GameData.getSpellsActivityHandler();
        handler.sendMessage(handler.obtainMessage(Constants.MSG_LEVEL_UP));
    }

    /**
     * Levels down the User.
     *
     * Sends a message to the UI to notify it of the fact
     */
    public void levelDown() {
        if (this.level > 2) {
            this.setLevel(this.getLevel() - 1);
            Log.d(TAG, "User has leveled down: " + this.getLevel());
        }
        Handler handler = GameData.getSpellsActivityHandler();
        handler.sendMessage(handler.obtainMessage(Constants.MSG_LEVEL_DOWN));
    }

    /**
     * Obtains the UUID of the user.
     *
     * Note: The UUID is found inside Constants and created at startup time
     * @return the UUID of the user
     */
    public String getUUID() {
        return Constants.UUID_STRING;
    }

    /**
     * Obtains the name of the user
     * @return the name of the user
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the user.
     *
     * As a side-effect it notifies the rest of nodes in the network that the user's name has
     * changed, thus invalidating the caches
     * @param name the new name of the user
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Obtains the level of the user
     * @return the level of the user
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Sets the level of the user
     * @param level the new level of the user
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Obtains the avatar resource id of the user
     * @return the avatar resource id of the user
     */
    public int getAvatar() {
        return this.avatarPhoto;
    }

    /**
     * Sets the new avatar of the user
     * @param avatar the new avatar resource id of the user
     */
    public void setAvatar(int avatar) {
        this.avatarPhoto = avatar;
    }

    /**
     * Requests the user to decide whether or not to level up the given user
     * @param uuid the UUID of the user to level up
     */
    public void acceptsLevelUp(String uuid) {
        Handler handler = GameData.getSpellsActivityHandler();
        Message msg = handler.obtainMessage(Constants.MSG_DECIDE_LEVEL);
        msg.obj = uuid;
        handler.sendMessage(msg);
    }

    /**
     * Called to notify the user that he has been casted a spell
     * @param idSpell the spell casted upon him
     * @param uuid the UUID of the caster
     * @param param extra parameters of the spell
     */
    public void castedSpell(int idSpell, String uuid, String param) {
        Handler handler = GameData.getSpellsActivityHandler();
        Message msg = handler.obtainMessage(Constants.MSG_CASTED_SPELL);
        msg.obj = new Object[]{idSpell, uuid, param};
        handler.sendMessage(msg);
    }

    /**
     * Obtains if the user has a shield or not
     * @return a boolean telling if the user has a shield
     */
    public boolean getShield() {
        return this.hasShield;
    }

    /**
     * Sets the shield of the user
     * @param hasShield a boolean telling the new state of the shield
     */
    public void setShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    /**
     * Asks the user to be a judge for a duel between two users
     *
     * @param uuidCaster the UUID of the caster
     * @param uuidVictim the UUID of the other duelist
     */
    public void beJudge(String uuidCaster, String uuidVictim) {
        Handler h = GameData.getSpellsActivityHandler();
        Message msg = h.obtainMessage(Constants.MSG_DECIDE_DUEL);
        msg.obj = new Object[]{uuidCaster, uuidVictim};
        h.sendMessage(msg);
    }

    /**
     * This should not be here, but it is anyways as otherwise the correct way would be to
     * reimplement Server and maintain a second database for the other publicly available object.
     *
     * Instead the alternative was to use User as the gateway for these two instructions and not
     * change the code. Since the latter is the most practical one, we use User as a gateway for
     * the rule
     */

    /**
     * Obtains the rule of the game.
     * @return the rule of the game
     */
    public String getRule() {
        return GameData.getRule();
    }

    /**
     * Updates the rule of the game. This affects all the other users in the game
     * @param newRule the new rule of the game
     */
    public void updateRule(String newRule) {
        // Body is empty on purpose
        // When called emits a broadcast signal to all the phones
    }
}