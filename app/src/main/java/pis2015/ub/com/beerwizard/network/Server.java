package pis2015.ub.com.beerwizard.network;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.List;

public class Server extends Service {
    private static String gameName;
    private static int userCounter = 0;
    private static List<String[]> userDb = new LinkedList<>();
    private Listener listener = new Listener();
    private Thread thread;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        gameName = intent.getStringExtra(NetworkConstants.GAME_NAME_EXTRA_ID);
        thread = new Thread(listener);
        thread.start();
        return START_STICKY;
    }

    private int registerUser(String address, String name, int avatarId) {
        for (String[] user : userDb) {
            sendPing(user[0]);
        }
        String[] array = {address, name, String.valueOf(avatarId)};
        userDb.add(array);
        return userCounter++;
    }

    private void sendPing(String address) {
        try {
            SocketChannel channel = SocketChannel.open(
                    new InetSocketAddress(address, NetworkConstants.SERVER_LISTENER_PORT));
            byte[] tmp = {NetworkConstants.PING_INST};
            ByteBuffer byteBuffer = ByteBuffer.wrap(tmp);
            channel.write(byteBuffer);
        } catch (IOException ignored) {

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class Listener implements Runnable {
        private ServerSocket serverSocket;

        @Override
        public void run() {
            try {
                serverSocket = new ServerSocket(NetworkConstants.SERVER_LISTENER_PORT);
                serverSocket.setSoTimeout(1000);
                while (true) {
                    Socket socket;
                    try {
                        socket = serverSocket.accept();
                    } catch (InterruptedIOException e) {
                        if (Thread.interrupted()) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    SocketChannel channel = socket.getChannel();
                    ByteBuffer buffer = ByteBuffer.allocate(200);
                    channel.read(buffer);
                    buffer.compact();
                    int instruction = buffer.get();
                    switch (instruction) {
                        case NetworkConstants.REGISTER_INST:
                            String address = socket.getInetAddress().getHostAddress();
                            registerUser(address, "Harri" + userCounter, 0);

                            break;
                    }
                }
                serverSocket.close();
            } catch (IOException ignored) {

            }
        }
    }
}
