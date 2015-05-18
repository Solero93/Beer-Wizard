package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellBeerekinesis extends Spell {
    public SpellBeerekinesis() {
        setName(R.string.title_spell3);
        setImage(R.drawable.duel_of_wizards); // More images need to arrive
        setDescription(R.string.description_text_spell3);
        setQuote(R.string.description_quote_spell3);
        setLockedText(R.string.lock3);
        setLevelToUnlock(4);
        setCooldown(0);
    }
}
