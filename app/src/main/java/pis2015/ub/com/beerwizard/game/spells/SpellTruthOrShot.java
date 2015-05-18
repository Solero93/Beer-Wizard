package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellTruthOrShot extends Spell {
    public SpellTruthOrShot() {
        setName(R.string.title_spell6);
        setImage(R.drawable.duel_of_wizards); // More images need to arrive
        setDescription(R.string.description_text_spell6);
        setQuote(R.string.description_quote_spell6);
        setLockedText(R.string.lock6);
        setLevelToUnlock(7);
        setCooldown(0);
    }
}
