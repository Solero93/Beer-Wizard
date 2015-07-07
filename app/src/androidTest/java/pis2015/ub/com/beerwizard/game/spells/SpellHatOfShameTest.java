package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellHatOfShameTest extends Spell {
    public SpellHatOfShameTest() {
        super();
        setName(R.string.title_spell7);
        setImage(R.drawable.hat_of_shame); // More images need to arrive
        setDescription(R.string.description_text_spell7);
        setQuote(R.string.description_quote_spell7);
        setLockedText(R.string.lock7);
        setCastedDescription(R.string.short_desc_hat);
        setLevelToUnlock(8);
        setCooldown(30000);
    }
}
