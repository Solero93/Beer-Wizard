package pis2015.ub.com.beerwizard.game.spells;

/**
 * Abstract class that represents the Spell object.
 */
public abstract class Spell {
    protected String name;
    protected String description;
    protected String quote;
    protected int levelToUnlock;
    protected int cooldown;

    //This constructor is for purposes of testing
    public Spell() {
        this.name = "name";
        this.description = "description";
        this.quote = "quote";
        this.cooldown = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getQuote() {
        return this.quote;
    }

    public int getLevelToUnlock() {
        return this.levelToUnlock;
    }

    public int getCooldown() {
        return this.cooldown;
    }
}