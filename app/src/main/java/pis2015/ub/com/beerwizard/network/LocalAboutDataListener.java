package pis2015.ub.com.beerwizard.network;

import org.alljoyn.bus.AboutDataListener;
import org.alljoyn.bus.ErrorReplyBusException;
import org.alljoyn.bus.Variant;
import org.alljoyn.bus.Version;

import java.util.HashMap;
import java.util.Map;

import pis2015.ub.com.beerwizard.util.Constants;

/**
 * Created by jordi on 5/11/15.
 */
public class LocalAboutDataListener implements AboutDataListener {

    @Override
    public Map<String, Variant> getAboutData(String language)
            throws ErrorReplyBusException {
        Map<String, Variant> aboutData = new HashMap<String, Variant>();
        // nonlocalized values
        aboutData.put("AppId", new Variant(Constants.APP_ID));
        aboutData.put("DefaultLanguage", new Variant(new String("en")));
        aboutData.put("DeviceId", new Variant(Constants.UUID_STRING));
        aboutData.put("ModelNumber", new Variant(new String("A1B2C3")));
        aboutData.put("SupportedLanguages", new Variant(new String[]{"en"}));
        aboutData.put("DateOfManufacture", new Variant(new String("2014-09-23")));
        aboutData.put("SoftwareVersion", new Variant(Constants.VERSION_NUMBER));
        aboutData.put("AJSoftwareVersion", new Variant(Version.get()));
        aboutData.put("HardwareVersion", new Variant(new String("0.1alpha")));
        aboutData.put("SupportUrl", new Variant(new String("http://www.example.com/support")));
        aboutData.put("DeviceName", new Variant(new String("A device name")));
        aboutData.put("AppName", new Variant(new String("Beer Wizard")));
        aboutData.put("Manufacturer", new Variant(new String("A mighty manufacturing company")));
        aboutData.put("Description",
                new Variant(new String("Sample showing the about feature in a service application")));
        return aboutData;
    }

    @Override
    public Map<String, Variant> getAnnouncedAboutData()
            throws ErrorReplyBusException {
        Map<String, Variant> aboutData = new HashMap<String, Variant>();
        aboutData.put("AppId", new Variant(Constants.APP_ID));
        aboutData.put("DefaultLanguage", new Variant(new String("en")));
        aboutData.put("DeviceName", new Variant(new String("A device name")));
        aboutData.put("DeviceId", new Variant(Constants.UUID_STRING));
        aboutData.put("AppName", new Variant(new String("Beer Wizard")));
        aboutData.put("Manufacturer", new Variant(new String("A mighty manufacturing company")));
        aboutData.put("ModelNumber", new Variant(new String("A1B2C3")));
        return aboutData;
    }
}
