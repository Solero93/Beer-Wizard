package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellCanToTheFace extends Spell {
    public SpellCanToTheFace() {
        setName(R.string.title_spell1);
        setImage(-1);
        setDescription(R.string.description_text_spell1);
        setQuote(R.string.description_quote_spell1);
        setLockedText(R.string.lock1);
        setLevelToUnlock(2);
        setCooldown(0);
    }
}
