package pis2015.ub.com.beerwizard.network;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;

public class Server extends Service {
    private static String gameName;
    private static int userCounter = 0;

    static {
        System.loadLibrary("alljoyn_java");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class BackgroundHandler extends Handler {

    }
}
