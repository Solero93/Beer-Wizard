package pis2015.ub.com.beerwizard.network;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

public class NetworkHelper {

    private static String serverIP;
    private static Messenger messenger;
    private static ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            messenger = null;
        }
    };

    public static byte createGame(Context context) {
        Intent intent = new Intent(context, Server.class);
        context.startService(intent);
        return 0;
    }

    public static byte enterGame(Context context) {
        Intent intent = new Intent(context, Server.class);
        context.startService(intent);
        return 0;
    }

    public static void notifyUserProfileChanged(Context context, String name, String avatar, String level) {
        context.bindService(new Intent(context, Server.class), mConnection, Context.BIND_AUTO_CREATE);
        Message msg = Message.obtain(null, Constants.UPDATE_PROPERTIES_MSG);
        Bundle bundle = new Bundle();
        if (name != null) {
            bundle.putString(Constants.PROPERTY_CHANGED_KEY, "Name");
            bundle.putString(Constants.NEW_PROPERTY_VALUE, name);
        } else if (avatar != null) {
            bundle.putString(Constants.PROPERTY_CHANGED_KEY, "Avatar");
            bundle.putString(Constants.NEW_PROPERTY_VALUE, avatar);
        } else if (level != null) {
            bundle.putString(Constants.PROPERTY_CHANGED_KEY, "Level");
            bundle.putString(Constants.NEW_PROPERTY_VALUE, level);
        }
        msg.setData(bundle);
        try {
            messenger.send(msg);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        context.unbindService(mConnection);
    }

    public static void exitGame(Context context) {
        Intent intent = new Intent(context, Server.class);
        context.stopService(intent);
    }

    public void castSpell(String idCasterUser, String idTargetUser, String idSpell, String[] params) {

    }

    public void levelUp(String idUser) {

    }

    private void userExitsGame(byte idUser) {
    }
}
