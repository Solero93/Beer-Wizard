package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellHatOfShame extends Spell {
    public SpellHatOfShame() {
        setName(R.string.title_spell7);
        setImage(-1);
        setDescription(R.string.description_text_spell7);
        setQuote(R.string.description_quote_spell7);
        setLockedText(R.string.lock7);
        setLevelToUnlock(8);
        setCooldown(0);
    }
}
