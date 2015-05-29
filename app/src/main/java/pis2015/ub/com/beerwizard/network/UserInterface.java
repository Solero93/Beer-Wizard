package pis2015.ub.com.beerwizard.network;

import org.alljoyn.bus.BusException;
import org.alljoyn.bus.annotation.BusInterface;
import org.alljoyn.bus.annotation.BusMethod;
import org.alljoyn.bus.annotation.BusProperty;
import org.alljoyn.bus.annotation.BusSignal;

import pis2015.ub.com.beerwizard.util.Constants;

/**
 * Interface for the AllJoyn service, those classes that implement this will broadcast signals when
 * updating the rule or any property is changed
 */
@BusInterface(name = Constants.INTERFACE_NAME, announced = "true")
public interface UserInterface {
    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    String getName() throws BusException;

    @BusProperty
    void setName(String name) throws BusException;

    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    int getLevel() throws BusException;

    @BusProperty
    void setLevel(int level) throws BusException;

    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    int getAvatar() throws BusException;

    @BusProperty
    void setAvatar(int avatar) throws BusException;

    @BusProperty
    String getUUID() throws BusException;

    @BusMethod
    void levelUp() throws BusException;

    @BusMethod
    void levelDown() throws BusException;

    @BusMethod
    void acceptsLevelUp(String uuid) throws BusException;

    @BusMethod
    void castedSpell(int i, String uuid, String param) throws BusException;

    @BusSignal
    void updateRule(String newRule) throws BusException;

    @BusMethod
    String getRule() throws BusException;

    @BusMethod
    void beJudge(String uuidCaster, String uuidVictim) throws BusException;

    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    boolean getShield() throws BusException;

    @BusProperty
    void setShield(boolean bool) throws BusException;
}