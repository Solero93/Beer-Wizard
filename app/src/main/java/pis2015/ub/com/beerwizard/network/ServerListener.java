package pis2015.ub.com.beerwizard.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jordi on 4/27/15.
 */
public class ServerListener implements Runnable {
    private ServerSocket serverSocket;
    private boolean stop = false;

    public ServerListener() {
    }

    public synchronized boolean hasToStop() {
        return stop;
    }

    public synchronized void setStop() {
        stop = true;
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(NetworkConstants.SERVER_PORT);
            serverSocket.setSoTimeout(1000);
            while (true) {
                Socket socket;
                try {
                    socket = serverSocket.accept();
                } catch (InterruptedIOException e) {
                    if (hasToStop()) {
                        break;
                    } else {
                        continue;
                    }
                }
                InputStream inputStream = socket.getInputStream();
                int instruction = inputStream.read();
                switch (instruction) {
                    case 0:
                        break;
                }
            }
        } catch (IOException e) {

        }
    }
}
