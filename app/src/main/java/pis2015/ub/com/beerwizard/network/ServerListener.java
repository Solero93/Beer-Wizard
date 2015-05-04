package pis2015.ub.com.beerwizard.network;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by jordi on 4/27/15.
 */
public class ServerListener extends Service {

    private static Listener listener = new Listener();
    private static Thread thread;

    private static void log(String string) {
        Log.d("ServerListener", string);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (thread == null) {
            thread = new Thread(listener);
            thread.start();
            log("Server Listener initialized");
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        thread.interrupt();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private static class Listener implements Runnable {
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
                    log("Connection established");
                    SocketChannel channel = socket.getChannel();
                    ByteBuffer buffer = ByteBuffer.allocate(200);
                    channel.read(buffer);
                    buffer.compact();
                    int instruction = buffer.get();
                    switch (instruction) {
                        case NetworkConstants.PING_INST:
                            log("Ping Received");
                            break;
                    }
                    socket.close();
                }
                serverSocket.close();

            } catch (IOException ignored) {

            }
        }
    }

}
