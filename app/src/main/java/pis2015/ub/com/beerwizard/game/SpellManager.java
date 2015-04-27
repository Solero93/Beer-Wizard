package pis2015.ub.com.beerwizard.game;

import java.util.ArrayList;
import pis2015.ub.com.beerwizard.game.spells.Spell;

/**
 * SpellManager Singleton class
 */
public class SpellManager {
    private ArrayList<Spell> spells;
    private static SpellManager ourInstance = new SpellManager();

    private SpellManager() {
    }

    public static SpellManager getInstance() {
        return ourInstance;
    }

    public String getSpell(String idSpell) {
        return null;
    }

    public String[] getAllUnlockedSpellsOfUser(int levelUser){
        //TODO Should be an ArrayList
        //Because at Level 10 there are no Spells, ergo could fail
        String[] unlockedSpells = new String[levelUser-1];
        int i=0;
        for (Spell sp : spells){
            if sp.isUnlocked(levelUser){
                unlockedSpells[i] = sp.getId();
            }
        }
    }
}
