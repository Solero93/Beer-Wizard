package pis2015.ub.com.beerwizard.game.avatar;

import java.util.ArrayList;

/**
 * Singleton Class that manages the Avatars.
 */
public class AvatarManager {
    ArrayList<Avatar> avatars;
    private static AvatarManager ourInstance = new AvatarManager();

    private AvatarManager() {
        this.avatars = new ArrayList<>();
    }

    public static AvatarManager getInstance() {
        return ourInstance;
    }

    public static String getAvatar(String idAvatar){
    }

}
