package pis2015.ub.com.beerwizard.game;

/**
 * SpellManager Singleton class
 */
public class SpellManager {
    private static SpellManager ourInstance = new SpellManager();

    private SpellManager() {
    }

    public static SpellManager getInstance() {
        return ourInstance;
    }

    public String getSpell(String idSpell) {
        return null;
    }
}
