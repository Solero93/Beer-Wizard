package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellShield extends Spell {
    public SpellShield() {
        setName(R.string.title_spell4);
        setImage(R.drawable.duel_of_wizards); // More images need to arrive
        setDescription(R.string.description_text_spell4);
        setQuote(R.string.description_quote_spell4);
        setLockedText(R.string.lock4);
        setLevelToUnlock(5);
        setCooldown(0);
    }
}
