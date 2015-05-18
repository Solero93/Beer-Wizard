package pis2015.ub.com.beerwizard.game;

import java.util.ArrayList;

import pis2015.ub.com.beerwizard.game.spells.Spell;
import pis2015.ub.com.beerwizard.game.spells.SpellAllInBeer;
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
    /*
    * Spell IDs
     */
    public static final int CAN_TO_THE_FACE = 0;
    public static final int WIZARD_DUEL = 1;
    public static final int BEEREKINESIS = 2;
    public static final int SHIELD = 3;
    public static final int CREATE_RULE = 4;
    public static final int TRUTH_OR_SHOT = 5;
    public static final int HAT_OF_SHAME = 6;
    public static final int ALL_IN_BEER = 7;

    private static SpellManager ourInstance = new SpellManager();
    private static ArrayList<Spell> spells;

    private SpellManager() {
        spells = new ArrayList<>();
        spells.add(new SpellCanToTheFace());
        spells.add(new SpellWizardDuel());
        spells.add(new SpellBeerekinesis());
        spells.add(new SpellShield());
        spells.add(new SpellCreateRule());
        spells.add(new SpellTruthOrShot());
        spells.add(new SpellHatOfShame());
        spells.add(new SpellAllInBeer());
    }

    public static SpellManager getInstance() {
        return ourInstance;
    }

    public static int getName(int idSpell) {
        return spells.get(idSpell).getName();
    }

    public static int getDescription(int idSpell) {
        return spells.get(idSpell).getDescription();
    }

    public static int getQuote(int idSpell) {
        return spells.get(idSpell).getQuote();
    }

    public static int getLockedText(int idSpell) {
        return spells.get(idSpell).getLockedText();
    }

    public static int getLevelToUnlock(int idSpell) {
        return spells.get(idSpell).getLevelToUnlock();
    }

    public static int getCooldown(int idSpell) {
        return spells.get(idSpell).getCooldown();
    }

    public static int getImage(int idSpell) {
        return spells.get(idSpell).getImage();
    }
}