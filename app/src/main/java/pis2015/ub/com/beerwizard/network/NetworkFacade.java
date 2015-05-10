package pis2015.ub.com.beerwizard.network;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NetworkFacade {

    private static String serverIP;

    public static byte createGame(Context context, String gameName) {
        Intent intent = new Intent(context, Server.class);
        intent.putExtra(NetworkConstants.GAME_NAME_EXTRA_ID, gameName);
        context.startService(intent);
        return 0;
    }

    public static byte enterGame(Context context, String serverIPaddress) {
        serverIP = serverIPaddress;
        Intent intent = new Intent(context, ServerListener.class);
        context.startService(intent);
        sendPing();
        //downloadUsers();
        return 0;
    }

    private static void sendPing() {
        try {
            Log.d("NetworkHelper", "Sending ping to: " + serverIP);
            SocketChannel channel = SocketChannel.open(
                    new InetSocketAddress(serverIP, NetworkConstants.SERVER_PORT));
            byte[] tmp = {NetworkConstants.PING_INST};
            channel.write(ByteBuffer.wrap(tmp));
            channel.close();
        } catch (IOException ignored) {

        }
    }

    public void castSpell(String idCasterUser, String idTargetUser, String idSpell, String[] params) {

    }

    public void modifyUserProfile(String idUser, String name, String avatar) {

    }

    public void levelUp(String idUser) throws BossException {

    }

    private void downloadUsers() {
        try {
            SocketChannel server = SocketChannel.open(
                    new InetSocketAddress(serverIP, NetworkConstants.SERVER_PORT));
            ByteBuffer buffer = ByteBuffer.allocate(10);
            // TODO implementar l'instrucció de descàrrega d'usuaris
            server.write(buffer);
        } catch (IOException ex) {

        }

    }

    public void exitGame(Context context) {
        Intent intent = new Intent(context, ServerListener.class);
        context.stopService(intent);
    }

    private void userExitsGame(byte idUser) {
        Game game = Game.getInstance();
        game.userExitsGame(idUser);
    }
}
