package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellWizardDuel extends Spell {
    public SpellWizardDuel() {
        super();
        setName(R.string.title_spell2);
        setImage(R.drawable.duel_of_wizards); // More images need to arrive
        setDescription(R.string.description_text_spell2);
        setQuote(R.string.description_quote_spell2);
        setLockedText(R.string.lock2);
        setCastedDescription(R.string.short_desc_duel);
        setLevelToUnlock(3);
        setCooldown(30000);

    }
}
