package pis2015.ub.com.beerwizard.game;

import java.util.ArrayList;

import pis2015.ub.com.beerwizard.game.spells.Spell;
import pis2015.ub.com.beerwizard.game.spells.SpellAllInShot;
import pis2015.ub.com.beerwizard.game.spells.SpellBeerekinesis;
import pis2015.ub.com.beerwizard.game.spells.SpellCanToTheFace;
import pis2015.ub.com.beerwizard.game.spells.SpellCreateRule;
import pis2015.ub.com.beerwizard.game.spells.SpellHatOfShame;
import pis2015.ub.com.beerwizard.game.spells.SpellShield;
import pis2015.ub.com.beerwizard.game.spells.SpellTruthOrShot;
import pis2015.ub.com.beerwizard.game.spells.SpellWizardDuel;

/**
 * Singleton class that manages the Spells.
 */
public class SpellManager {
    private static SpellManager ourInstance = new SpellManager();
    private static ArrayList<Spell> spells;

    private SpellManager() {
        spells = new ArrayList<>(8);
        spells.set(0, new SpellCanToTheFace());
        spells.set(1, new SpellWizardDuel());
        spells.set(2, new SpellBeerekinesis());
        spells.set(3, new SpellShield());
        spells.set(4, new SpellCreateRule());
        spells.set(5, new SpellTruthOrShot());
        spells.set(6, new SpellHatOfShame());
        spells.set(7, new SpellAllInShot());
    }

    public static SpellManager getInstance() {
        return ourInstance;
    }

    public static String getName(int idSpell) {
        return spells.get(idSpell).getName();
    }

    public static String getDescription(int idSpell) {
        return spells.get(idSpell).getDescription();
    }

    public static String getQuote(int idSpell) {
        return spells.get(idSpell).getQuote();
    }

    public static int getLevelToUnlock(int idSpell) {
        return spells.get(idSpell).getLevelToUnlock();
    }

    public static int getCooldown(int idSpell) {
        return spells.get(idSpell).getCooldown();
    }
}