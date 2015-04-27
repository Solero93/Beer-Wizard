package pis2015.ub.com.beerwizard.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by jordi on 4/27/15.
 */
public class ServerListener implements Runnable {
    private ServerSocket serverSocket;

    public ServerListener() {
    }

    public void run() {
        try {
            serverSocket = new ServerSocket(NetworkConstants.SERVER_PORT);
        } catch (IOException e) {
            //hlep
        }
        try {
            while (true) {
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                int instruction = inputStream.read();
            }
        } catch (IOException e) {

        }
    }
}
