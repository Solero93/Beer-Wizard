package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellBeerekinesisTest extends Spell {
    public SpellBeerekinesisTest() {
        super();
        setName(R.string.title_spell3);
        setImage(R.drawable.duel_of_wizards); // More images need to arrive
        setDescription(R.string.description_text_spell3);
        setQuote(R.string.description_quote_spell3);
        setLockedText(R.string.lock3);
        setCastedDescription(R.string.short_desc_beerk);
        setLevelToUnlock(4);
        setCooldown(30000);
    }
}
