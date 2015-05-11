package pis2015.ub.com.beerwizard.network;

import org.alljoyn.bus.annotation.BusInterface;
import org.alljoyn.bus.annotation.BusProperty;

/**
 * Created by jordi on 5/11/15.
 */
@BusInterface(name = "pis2015.ub.com.beerwizard.user", announced = "true")
public interface UserInterface {
    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    public String getName();

    @BusProperty
    public void setName(String name);

    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    public int getLevel();

    @BusProperty
    public void setLevel(int level);

    @BusProperty(annotation = BusProperty.ANNOTATE_EMIT_CHANGED_SIGNAL)
    public int getAvatar();

    @BusProperty
    public void setAvatar(int avatar);
}
