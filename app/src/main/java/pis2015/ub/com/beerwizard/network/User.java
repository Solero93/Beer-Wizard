package pis2015.ub.com.beerwizard.network;

import android.os.Handler;

import org.alljoyn.bus.BusObject;

/**
 * Class that represents the User (or Player) object
 */
public class User implements UserInterface, BusObject {
    private byte id;
    private String name;
    private int level;
    private int avatarPhoto;

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
        }
        Handler handler = GameData.getInstance().getSpellsActivityHandler();
        handler.sendMessage(handler.obtainMessage(Constants.MSG_LEVEL_UP));
    }

    /**
     * Level's down the User.
     */
    public void levelDown() {
        if (this.level > 2) this.level--;
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

    public byte getId() {
        return this.id;
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

    }
}