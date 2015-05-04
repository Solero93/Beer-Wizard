package pis2015.ub.com.beerwizard.game;

import java.util.ArrayList;

import pis2015.ub.com.beerwizard.R;
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
        spells = new ArrayList<>();

        spells.add(new SpellCanToTheFace()
                .setName(R.string.title_spell1)
                .setImage(-1)
                .setDescription(R.string.description_text_spell1)
                .setQuote(R.string.description_quote_spell1)
                .setLockedText(R.string.lock1)
                .setLevelToUnlock(2)
                .setCooldown(0));

        spells.add(new SpellWizardDuel()
                .setName(R.string.title_spell2)
                .setImage(-1)
                .setDescription(R.string.description_text_spell2)
                .setQuote(R.string.description_quote_spell2)
                .setLockedText(R.string.lock2)
                .setLevelToUnlock(3)
                .setCooldown(0));

        spells.add(new SpellBeerekinesis()
                .setName(R.string.title_spell3)
                .setImage(-1)
                .setDescription(R.string.description_text_spell3)
                .setQuote(R.string.description_quote_spell3)
                .setLockedText(R.string.lock3)
                .setLevelToUnlock(4)
                .setCooldown(0));

        spells.add(new SpellShield()
                .setName(R.string.title_spell4)
                .setImage(-1)
                .setDescription(R.string.description_quote_spell4)
                .setQuote(R.string.description_quote_spell4)
                .setLockedText(R.string.lock4)
                .setLevelToUnlock(5)
                .setCooldown(0));

        spells.add(new SpellCreateRule()
                .setName(R.string.title_spell5)
                .setImage(-1)
                .setDescription(R.string.description_text_spell5)
                .setQuote(R.string.description_quote_spell5)
                .setLockedText(R.string.lock5)
                .setLevelToUnlock(6)
                .setCooldown(0));

        spells.add(new SpellTruthOrShot()
                .setName(R.string.title_spell6)
                .setImage(-1)
                .setDescription(R.string.description_text_spell6)
                .setQuote(R.string.description_quote_spell6)
                .setLockedText(R.string.lock6)
                .setLevelToUnlock(7)
                .setCooldown(0));

        spells.add(new SpellHatOfShame()
                .setName(R.string.title_spell7)
                .setImage(-1)
                .setDescription(R.string.description_text_spell7)
                .setQuote(R.string.description_quote_spell7)
                .setLockedText(R.string.lock7)
                .setLevelToUnlock(8)
                .setCooldown(0));

        spells.add(new SpellAllInShot()
                .setName(R.string.title_spell8)
                .setImage(-1)
                .setDescription(R.string.description_text_spell8)
                .setQuote(R.string.description_quote_spell8)
                .setLockedText(R.string.lock8)
                .setLevelToUnlock(9)
                .setCooldown(0));

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
}