package pis2015.ub.com.beerwizard.game;

public class User {
    private String id;
    private String name;
    private int level;
    private Avatar avatar;

    public void levelUp() {
        if (this.level < 10) this.level++;
    }

    public void levelDown() {
        if (this.level > 2) this.level--;
    }

    public void modifyMyProfile(String name, String idAvatar) {
        this.setName(name);
        this.setAvatar(idAvatar);
    }

    public String[] getAllUnlockedSpells() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(String idAvatar) {
        this.avatar = avatar;
    }
}
