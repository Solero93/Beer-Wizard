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

    /**
     * Gets the Name of a given Spell
     *
     * @param idSpell
     * @return Spell's name
     */
    public static int getSpellName(int idSpell) {
        return spells.get(idSpell).getName();
    }

    /**
     * Gets the Description of a given Spell.
     *
     * @param idSpell
     * @return Spell's Description
     */
    public static int getSpellDescription(int idSpell) {
        return spells.get(idSpell).getDescription();
    }

    /**
     * Gets the Quote of a given Spell
     *
     * @param idSpell
     * @return Spell's Quote
     */
    public static int getSpellQuote(int idSpell) {
        return spells.get(idSpell).getQuote();
    }

    /**
     * Gets the Locked Text of a given Spell
     *
     * @param idSpell
     * @return Spell's LockedText
     */
    public static int getSpellLockedText(int idSpell) {
        return spells.get(idSpell).getLockedText();
    }

    public static int getSpellCastedDescription(int idSpell) {
        return spells.get(idSpell).getCastedDescription();
    }

    /**
     * Gets Cooldown of Spell.
     *
     * @param idSpell
     * @return
     */
    public static long getSpellCooldown(int idSpell) {
        return spells.get(idSpell).getCooldown();
    }

    /**
     * Returns image of a spell
     *
     * @param idSpell
     * @return
     */
    public static int getSpellImage(int idSpell) {
        return spells.get(idSpell).getImage();
    }

    public static void startSpellCooldown(int idSpell) {
        spells.get(idSpell).startCooldown();
    }

    public static long getMilisecondsLeftFromSpellCooldown(int idSpell) {
        return spells.get(idSpell).getMilisecondsLeftFromCooldown();
    }

    public static boolean isSpellCooldown(int idSpell) {
        return spells.get(idSpell).isCooldown();
    }
}