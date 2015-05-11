package pis2015.ub.com.beerwizard.network;

import org.alljoyn.bus.annotation.BusInterface;
import org.alljoyn.bus.annotation.BusProperty;

/**
 * Created by jordi on 5/11/15.
 */
@BusInterface(name = "pis2015.ub.com.beerwizard.user", announced = "true")
public interface UserInterface {
    @BusProperty
    public String getName();

    @BusProperty
    public void setName(String name);

    @BusProperty
    public int getLevel();

    @BusProperty
    public void setLevel(int level);

    @BusProperty
    public String getAvatar();

    @BusProperty
    public void setAvatar(String avatar);
}
