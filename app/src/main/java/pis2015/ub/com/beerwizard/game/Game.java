package pis2015.ub.com.beerwizard.game;

/**
 * Singleton Game Class
 */
public class Game {
    private static Game ourInstance = new Game();
    private String name;
    private String rule;

    private Game() {
    }

    public static Game getInstance() {
        return ourInstance;
    }

    public void modifyUserProfile(String idUser, String name, String idAvatar) {

    }

    public void userExitsGame(String idUser) {

    }

    public String[] getAllUnlockedSpellsOfUser(String idUser) {
        return null;
    }

    public String getSpellDescription(String idSpell) {
        return null;
    }

    public void exitGame() {

    }
}