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
 * SpellManager Singleton class
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


}
