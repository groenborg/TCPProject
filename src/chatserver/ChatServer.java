package chatserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;
import utility.Utility;

/**
 *
 * @author Grønborg MultiThreaded ChatServer
 */
public class ChatServer implements Runnable {

    private ServerSocket serverSocket;
    private Thread thread;
    private boolean running = true;
    private static ChatServer instance = new ChatServer();

    private final Properties properties = Utility.initProperties("server.properties");
    private String ipAddress = "10.0.1.11";
    private int port = 8014;

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
            Socket socket = null;
            try {
                socket = this.serverSocket.accept();
               // Thread t = new Thread(new ClientHandler(socket));
                //t.start();
            } catch (IOException e) {
                if (!running) {
                    System.out.println("Server stopped Running");
                    closeConnection(socket);
                    closeServer();
                    return;
                }
                e.printStackTrace();
            }
        }

    }

    public HashMap<String, String> getUsers() {
        return null;
    }

    public synchronized void startServer() {
        this.port = Integer.parseInt(properties.getProperty("port"));
        this.ipAddress = properties.getProperty("ipaddress");
        this.thread = new Thread(this);
        this.thread.start();
        Utility.setLogFile(ChatServer.class.getName());
        System.out.println("Server Started in: " + thread.getName());
    }

    @Deprecated
    private void closeConnection(Socket socket) {
        try {
            if (socket != null) {
                socket.shutdownInput();
                socket.shutdownOutput();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void closeServer() {
        try {
            this.running = false;
            this.serverSocket.close();
        } catch (IOException ex) {
            System.out.println("error in closing server");
            ex.printStackTrace();
        }
        Utility.logInfo(ChatServer.class.getName(), "server closed");
        System.out.println("server closed");
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

}
