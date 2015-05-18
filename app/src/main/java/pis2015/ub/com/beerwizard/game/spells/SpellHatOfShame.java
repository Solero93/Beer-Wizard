package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellHatOfShame extends Spell {
    public SpellHatOfShame() {
        super();
        setName(R.string.title_spell7);
        setImage(R.drawable.duel_of_wizards); // More images need to arrive
        setDescription(R.string.description_text_spell7);
        setQuote(R.string.description_quote_spell7);
        setLockedText(R.string.lock7);
        setLevelToUnlock(8);
        setCooldown(0);
    }
}
