package pis2015.ub.com.beerwizard.game.spells;

/**
 * Abstract class that represents the Spell object.
 */
public abstract class Spell {
    protected int name;
    protected int image;
    protected int description;
    protected int quote;
    protected int lockedText;
    protected int levelToUnlock;
    protected int cooldown;

    //This constructor is for purposes of testing
    public Spell() {
    }

    public int getName() {
        return name;
    }

    public Spell setName(int name) {
        this.name = name;
        return this;
    }

    public int getImage() {
        return image;
    }

    public Spell setImage(int image) {
        this.image = image;
        return this;
    }

    public int getDescription() {
        return description;
    }

    public Spell setDescription(int description) {
        this.description = description;
        return this;
    }

    public int getQuote() {
        return quote;
    }

    public Spell setQuote(int quote) {
        this.quote = quote;
        return this;
    }

    public int getLockedText() {
        return this.lockedText;
    }

    public Spell setLockedText(int lockedText) {
        this.lockedText = lockedText;
        return this;
    }

    public int getLevelToUnlock() {
        return this.levelToUnlock;
    }

    public Spell setLevelToUnlock(int levelToUnlock) {
        this.levelToUnlock = levelToUnlock;
        return this;
    }

    public int getCooldown() {
        return this.cooldown;
    }

    public Spell setCooldown(int cooldown) {
        this.cooldown = cooldown;
        return this;
    }
}