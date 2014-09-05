package chatserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Grønborg MultiThreaded ChatServer
 */
public class ChatServer implements Runnable {

    private ServerSocket serverSocket;
    private Thread thread;
    private boolean running = true;

    private final String ipAddress = "127.0.0.1";
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
            try {
                Socket socket;
                socket = this.serverSocket.accept();
                Thread thread = new Thread(new ClientHandler(socket));
                thread.start();
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
            this.thread.join();
        } catch (IOException | InterruptedException ex) {
            System.out.println("Closing server");
            ex.printStackTrace();
        }

    }

}
