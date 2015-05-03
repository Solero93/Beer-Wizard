package pis2015.ub.com.beerwizard.network;

/**
 * Class that represents the User (or Player) object
 */
public class User {
    private String id;
    private String name;
    private int level;
    private String avatarPhoto;

    /**
     * Level's up the User.
     */
    public void levelUp() {
        if (this.level < 10) this.level++;
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
    public void modifyProfile(String name, String avatar) {
        this.setName(name);
        this.setAvatar(avatar);
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

    public String getAvatar() {
        return this.avatarPhoto;
    }

    public void setAvatar(String avatar) {
        this.avatarPhoto = avatar;
    }
}