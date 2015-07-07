package pis2015.ub.com.beerwizard.game.spells;

import pis2015.ub.com.beerwizard.R;

public class SpellTruthOrShotTest extends Spell {
    public SpellTruthOrShotTest() {
        super();
        setName(R.string.title_spell6);
        setImage(R.drawable.truth_or_shot); // More images need to arrive
        setDescription(R.string.description_text_spell6);
        setQuote(R.string.description_quote_spell6);
        setLockedText(R.string.lock6);
        setCastedDescription(R.string.short_desc_truth);
        setLevelToUnlock(7);
        setCooldown(30000);
    }
}
