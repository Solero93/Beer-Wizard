package pis2015.ub.com.beerwizard.network;

import org.alljoyn.bus.AboutDataListener;
import org.alljoyn.bus.ErrorReplyBusException;
import org.alljoyn.bus.Variant;
import org.alljoyn.bus.Version;

import java.util.HashMap;
import java.util.Map;

import pis2015.ub.com.beerwizard.util.ConstantsTest;

/**
 * Class that contains the data to be announced to the network. Required by the AllJoyn framework
 */
class LocalAboutDataListenerTest implements AboutDataListener {

    @Override
    public Map<String, Variant> getAboutData(String language)
            throws ErrorReplyBusException {
        Map<String, Variant> aboutData = new HashMap<>();
        // nonlocalized values
        aboutData.put("AppId", new Variant(ConstantsTest.APP_ID));
        aboutData.put("DefaultLanguage", new Variant("en"));
        aboutData.put("DeviceId", new Variant(ConstantsTest.UUID_STRING));
        aboutData.put("ModelNumber", new Variant("A1B2C3"));
        aboutData.put("SupportedLanguages", new Variant(new String[]{"en"}));
        aboutData.put("DateOfManufacture", new Variant("2014-09-23"));
        aboutData.put("SoftwareVersion", new Variant(ConstantsTest.VERSION_NUMBER));
        aboutData.put("AJSoftwareVersion", new Variant(Version.get()));
        aboutData.put("HardwareVersion", new Variant("0.1alpha"));
        aboutData.put("SupportUrl", new Variant("http://www.example.com/support"));
        aboutData.put("DeviceName", new Variant("A device name"));
        aboutData.put("AppName", new Variant("Beer Wizard"));
        aboutData.put("Manufacturer", new Variant("A mighty manufacturing company"));
        aboutData.put("Description",
                new Variant("Sample showing the about feature in a service application"));
        return aboutData;
    }

    @Override
    public Map<String, Variant> getAnnouncedAboutData()
            throws ErrorReplyBusException {
        Map<String, Variant> aboutData = new HashMap<>();
        aboutData.put("AppId", new Variant(ConstantsTest.APP_ID));
        aboutData.put("DefaultLanguage", new Variant("en"));
        aboutData.put("DeviceName", new Variant("A device name"));
        aboutData.put("DeviceId", new Variant(ConstantsTest.UUID_STRING));
        aboutData.put("AppName", new Variant("Beer Wizard"));
        aboutData.put("Manufacturer", new Variant("A mighty manufacturing company"));
        aboutData.put("ModelNumber", new Variant("A1B2C3"));
        return aboutData;
    }
}
