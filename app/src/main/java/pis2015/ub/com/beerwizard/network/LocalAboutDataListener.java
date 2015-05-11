package pis2015.ub.com.beerwizard.network;

import org.alljoyn.bus.AboutDataListener;
import org.alljoyn.bus.ErrorReplyBusException;
import org.alljoyn.bus.Variant;
import org.alljoyn.bus.Version;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jordi on 5/11/15.
 */
public class LocalAboutDataListener implements AboutDataListener {

    @Override
    public Map<String, Variant> getAboutData(String s) throws ErrorReplyBusException {
        Map<String, Variant> map = new HashMap<>();
        map.put("AppId", new Variant(Constants.APP_ID));
        map.put("DefaultLanguage", new Variant("en"));
        map.put("DeviceId", new Variant(Constants.UUID_STRING));
        map.put("AppName", new Variant("pis2015.ub.com.beerwizard"));
        map.put("Manufacturer", new Variant("PIS_12"));
        map.put("ModelNumber", new Variant("TEST"));
        map.put("SupportedLanguages", new Variant(new String[]{"en"}));
        map.put("Description", new Variant("A game of drink and fire"));
        map.put("SoftwareVersion", new Variant(Constants.VERSION_NUMBER));
        map.put("AJSoftwareVersion", new Variant(Version.get()));
        return map;
    }

    @Override
    public Map<String, Variant> getAnnouncedAboutData() throws ErrorReplyBusException {
        Map<String, Variant> aboutData = new HashMap<String, Variant>();
        aboutData.put("AppId", new Variant(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}));
        aboutData.put("DefaultLanguage", new Variant("en"));
        aboutData.put("DeviceName", new Variant(""));
        aboutData.put("DeviceId", new Variant(Constants.UUID_STRING));
        aboutData.put("AppName", new Variant("pis2015.ub.com.beerwizard"));
        aboutData.put("Manufacturer", new Variant("PIS_12"));
        aboutData.put("ModelNumber", new Variant("TEST"));
        return aboutData;
    }
}
