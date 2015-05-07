package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellShield extends Spell {
    public SpellShield() {
        setName(R.string.title_spell4);
        setImage(-1);
        setDescription(R.string.description_quote_spell4);
        setQuote(R.string.description_quote_spell4);
        setLockedText(R.string.lock4);
        setLevelToUnlock(5);
        setCooldown(0);
    }
}
