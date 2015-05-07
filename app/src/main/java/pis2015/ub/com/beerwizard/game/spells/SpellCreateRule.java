package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellCreateRule extends Spell {
    public SpellCreateRule() {
        setName(R.string.title_spell5);
        setImage(-1);
        setDescription(R.string.description_text_spell5);
        setQuote(R.string.description_quote_spell5);
        setLockedText(R.string.lock5);
        setLevelToUnlock(6);
        setCooldown(0);
    }
}