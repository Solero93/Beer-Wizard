package pis2015.ub.com.beerwizard.network;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

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
    private static Thread thread;
    private Listener listener = new Listener();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (thread == null) {
            gameName = intent.getStringExtra(NetworkConstants.GAME_NAME_EXTRA_ID);
            thread = new Thread(listener);
            thread.start();
            Log.d("ServerService",
                    "Server initialized, awaiting connections at port " + NetworkConstants.SERVER_PORT);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        thread.interrupt();
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
            Log.d("ServerService", "Sent ping to address: " + address);
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
                serverSocket = new ServerSocket(NetworkConstants.SERVER_PORT);
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
                    Log.d("ServerService", "Connection Established");
                    SocketChannel channel = socket.getChannel();
                    ByteBuffer buffer = ByteBuffer.allocate(200);
                    channel.read(buffer);
                    buffer.compact();
                    int instruction = buffer.get();
                    switch (instruction) {
                        case NetworkConstants.PING_INST:
                            sendPing(channel.socket().getInetAddress().getHostAddress());
                            break;
                    }
                    socket.close();
                }
                serverSocket.close();
            } catch (IOException e) {
                Log.e("ServerService", e.getMessage());
            }
        }
    }
}