package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellAllInBeer extends Spell {
    public SpellAllInBeer() {
        super();
        setName(R.string.title_spell8);
        setImage(R.drawable.duel_of_wizards); // More images need to arrive
        setDescription(R.string.description_text_spell8);
        setQuote(R.string.description_quote_spell8);
        setLockedText(R.string.lock8);
        setLevelToUnlock(9);
        setCooldown(0);
    }
}
