package pis2015.ub.com.beerwizard.game.spells;

/**
 * Abstract class that represents the Spell object.
 */
public abstract class Spell {
    //protected String id; FIXME should see if it's really needed, levelToUnlock should suffice as ID
    protected String name;
    protected String description;
    protected String quote;
    protected int levelToUnlock;
    protected int cooldown;
}