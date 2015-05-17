package pis2015.ub.com.beerwizard.network;

import org.alljoyn.bus.BusException;
import org.alljoyn.bus.annotation.BusInterface;
import org.alljoyn.bus.annotation.BusMethod;
import org.alljoyn.bus.annotation.BusProperty;
import org.alljoyn.bus.annotation.BusSignal;

/**
 * Created by jordi on 5/11/15.
 */
@BusInterface(name = "pis2015.ub.com.beerwizard.user", announced = "true")
public interface UserInterface {
    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    public String getName() throws BusException;

    @BusProperty
    public void setName(String name) throws BusException;

    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    public int getLevel() throws BusException;

    @BusProperty
    public void setLevel(int level) throws BusException;

    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    public int getAvatar() throws BusException;

    @BusProperty
    public void setAvatar(int avatar) throws BusException;

    @BusProperty
    public String getUUID() throws BusException;

    @BusMethod
    public void levelUp() throws BusException;

    @BusMethod
    public void levelDown() throws BusException;

    @BusMethod
    public void acceptsLevelUp(String uuid) throws BusException;

    @BusMethod
    public void castedSpell(int i, String uuid) throws BusException;

    @BusSignal
    public void updateRule(String newRule) throws BusException;

    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    public boolean getShield() throws BusException;

    @BusProperty
    public void setShield(boolean bool) throws BusException;
}