package chatserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grønborg MultiThreaded ChatServer
 */
public class ChatServer implements Runnable {

    private ServerSocket serverSocket;
    private Thread thread;
    private boolean running = true;

    private final String ipAddress = "10.0.1.11";
    private final int port = 8014;
    private static ChatServer instance = new ChatServer();

    private ChatServer() {
    }

    public static ChatServer getInstance() {
        if (instance == null) {
            instance = new ChatServer();
        }
        return instance;
    }

    @Override
    public void run() {
        openConnection();
        while (running) {
            Socket socket;
            try {
                socket = this.serverSocket.accept();
                Thread t = new Thread(new ClientHandler(socket));
                t.start();
            } catch (IOException e) {
                if (!running) {
                    System.out.println("Server stopped Running");
                    closeServer();
                }
                e.printStackTrace();
            }
        }

    }

    private void openConnection() {
        try {
            this.serverSocket = new ServerSocket();
            this.serverSocket.bind(new InetSocketAddress(ipAddress, port));
            System.out.println("Connection opened on: " + ipAddress + " port: " + port);
        } catch (IOException e) {
            System.out.println("Server is closeing");
            e.printStackTrace();
        }
    }

    public synchronized void startServer() {
        this.thread = new Thread(this);
        this.thread.start();
        System.out.println("Server Started in: " + thread.getName());
    }

    public synchronized void closeServer() {
        try {
            running = false;
            this.serverSocket.close();
        } catch (IOException ex) {
            System.out.println("error in closing server");
            ex.printStackTrace();
        }
        System.out.println("server closed");
    }

}
