package pis2015.ub.com.beerwizard.game;

import junit.framework.TestCase;

/**
 * Unit Test of SpellManager
 */
public class SpellManagerTest extends TestCase {
    public void testGetSpellName(){
        int name = SpellManager.getSpellName(0);
        assertNotNull(name);
    }
}
