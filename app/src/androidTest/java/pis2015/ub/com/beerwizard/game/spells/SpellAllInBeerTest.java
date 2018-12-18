package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellAllInBeerTest extends Spell {
    public SpellAllInBeerTest() {
        super();
        setName(R.string.title_spell8);
        setImage(R.drawable.all_in_beer); // More images need to arrive
        setDescription(R.string.description_text_spell8);
        setQuote(R.string.description_quote_spell8);
        setLockedText(R.string.lock8);
        setCastedDescription(R.string.short_desc_all);
        setLevelToUnlock(9);
        setCooldown(30000);
    }
}
