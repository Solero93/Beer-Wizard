package pis2015.ub.com.beerwizard.network;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.alljoyn.bus.BusObject;

/**
 * Class that represents the User (or Player) object
 */
public class User implements UserInterface, BusObject {
    private static final String TAG = "User";
    private String name;
    private int level = 1;
    private int avatarPhoto;
    private boolean hasShield;

    public User() {
        this("");
    }
    public User(String name) {
        this.name = name;
    }
    /**
     * Level's up the User.
     */
    public void levelUp() {
        if (this.level < 10) {
            this.setLevel(this.getLevel() + 1);
            Log.d(TAG, "User has leveled up: " + this.getLevel());
        }
        Handler handler = GameData.getInstance().getSpellsActivityHandler();
        handler.sendMessage(handler.obtainMessage(Constants.MSG_LEVEL_UP));
    }

    /**
     * Level's down the User.
     */
    public void levelDown() {
        if (this.level > 2) {
            this.setLevel(this.getLevel() - 1);
            Log.d(TAG, "User has leveled down: " + this.getLevel());
        }
        Handler handler = GameData.getInstance().getSpellsActivityHandler();
        handler.sendMessage(handler.obtainMessage(Constants.MSG_LEVEL_DOWN));
    }

    /**
     * Modifies the profile of the User.
     */
    public void modifyProfile(String name, int avatar) {
        this.setName(name);
        this.setAvatar(avatar);
    }

    public String getUUID() {
        return Constants.UUID_STRING;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getAvatar() {
        return this.avatarPhoto;
    }

    public void setAvatar(int avatar) {
        this.avatarPhoto = avatar;
    }

    public void acceptsLevelUp(String uuid) {
        Handler handler = GameData.getInstance().getSpellsActivityHandler();
        Message msg = handler.obtainMessage(Constants.MSG_DECIDE_LEVEL);
        msg.obj = uuid;
        handler.sendMessage(msg);
    }

    public void castedSpell(int idSpell, String uuid, String param) {
        Handler handler = GameData.getInstance().getSpellsActivityHandler();
        Message msg = handler.obtainMessage(Constants.MSG_CASTED_SPELL);
        msg.obj = new Object[]{idSpell, uuid, param};
        handler.sendMessage(msg);
    }

    public boolean getShield() {
        return this.hasShield;
    }

    public void setShield(boolean hasShield) {
        this.hasShield = hasShield;
    }

    public String getRule() {
        return GameData.getInstance().getRule();
    }

    public void updateRule(String newRule) {

    }
}