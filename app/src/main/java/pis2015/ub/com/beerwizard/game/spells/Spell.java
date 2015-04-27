package pis2015.ub.com.beerwizard.game.spells;

public abstract class Spell {
    protected String id;
    protected String name;
    protected String description;
    protected String quote;
    protected int levelToUnlock;
    protected int cooldown;

    public boolean isUnlocked(int level){
        return (level >= this.levelToUnlock);
    }
}