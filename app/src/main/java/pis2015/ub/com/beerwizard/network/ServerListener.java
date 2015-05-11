package pis2015.ub.com.beerwizard.network;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

/**
 * Created by jordi on 4/27/15.
 */
public class ServerListener extends Service {

    private BackgroundHandler handler = null;

    private static void log(String string) {
        Log.d("ServerListener", string);
    }

    @Override
    public void onCreate() {
        HandlerThread thread = new HandlerThread("BackgroundHandler");
        thread.start();
        handler = new BackgroundHandler(thread.getLooper());
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

    private final class BackgroundHandler extends Handler {
        public BackgroundHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message msg) {

        }
    }

}
