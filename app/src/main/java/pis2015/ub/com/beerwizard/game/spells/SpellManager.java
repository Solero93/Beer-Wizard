package pis2015.ub.com.beerwizard.game.spells;

import java.util.ArrayList;

/**
 * Singleton class that manages the Spells.
 */
public class SpellManager {
    private static SpellManager ourInstance = new SpellManager();
    private ArrayList<Spell> spells;

    private SpellManager() {
        this.spells = new ArrayList<>(8);
        spells.set(0, new SpellCanToTheFace());
        spells.set(1, new SpellWizardDuel());
        spells.set(2, new SpellBeerekinesis());
        spells.set(3, new SpellShield());
        spells.set(4, new SpellCreateRule());
        spells.set(5, new SpellTruthOrShot());
        spells.set(6, new SpellHatOfShame());
        spells.set(7, new SpellAllInBeer());
    }

    public static SpellManager getInstance() {
        return ourInstance;
    }

    public static String getName(String idSpell){
    }

    public static String getDescription(String idSpell){
    }

    public static String getQuote(String idSpell){
    }
}