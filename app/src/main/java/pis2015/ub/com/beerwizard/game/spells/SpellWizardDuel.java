package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellWizardDuel extends Spell {
    public SpellWizardDuel() {
        setName(R.string.title_spell2);
        setImage(-1);
        setDescription(R.string.description_text_spell2);
        setQuote(R.string.description_quote_spell2);
        setLockedText(R.string.lock2);
        setLevelToUnlock(3);
        setCooldown(0);

    }
}
