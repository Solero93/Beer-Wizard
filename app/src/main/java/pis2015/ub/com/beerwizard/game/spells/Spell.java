package pis2015.ub.com.beerwizard.game.spells;

import java.util.Date;

/**
 * Abstract class that represents the Spell object.
 */
public abstract class Spell {
    protected int name;
    protected int image;
    protected int description;
    protected int quote;
    protected int lockedText;
    protected int castedDescription;
    protected int levelToUnlock;
    protected long cooldown;
    protected Date startCooldown;

    public Spell() {
        this.startCooldown = new Date(0); // Initialize
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


    public int getCastedDescription() {
        return this.castedDescription;
    }

    public void setCastedDescription(int castedDescription) {
        this.castedDescription = castedDescription;
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

    /**
     * Starts a cooldown a spell
     */
    public void startCooldown() {
        this.startCooldown = new Date(); // Initialize with current time
    }

    /**
     * Returns Seconds left from a Cooldown
     *
     * @return
     */
    public long getMilisecondsLeftFromCooldown() {
        long miliSecondsPassed = (new Date()).getTime() - this.startCooldown.getTime();
        if (miliSecondsPassed < this.cooldown) {
            return (this.cooldown - miliSecondsPassed);
        } else {
            return 0;
        }
    }

    public boolean isCooldown() {
        return (this.getMilisecondsLeftFromCooldown() > 0);
    }

    public void setCooldown(long cooldown) {
        this.cooldown = cooldown;
    }
}