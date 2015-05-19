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
    protected long cooldown;
    protected boolean isCooldown;

    public Spell() {
        this.isCooldown = false;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }


    public int getQuote() {
        return quote;
    }

    public void setQuote(int quote) {
        this.quote = quote;
    }


    public int getLockedText() {
        return this.lockedText;
    }

    public void setLockedText(int lockedText) {
        this.lockedText = lockedText;
    }


    public int getLevelToUnlock() {
        return this.levelToUnlock;
    }

    public void setLevelToUnlock(int levelToUnlock) {
        this.levelToUnlock = levelToUnlock;
    }


    public long getCooldown() {
        return this.cooldown;
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }


    public boolean getIsCooldown() {
        return isCooldown;
    }

    public void setIsCooldown(boolean isCooldown) {
        this.isCooldown = isCooldown;
    }
}