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
    private boolean running = true;

    public ChatServer() throws IOException {
        this.serverSocket = new ServerSocket();
        this.serverSocket.bind(new InetSocketAddress("127.0.0.1", 8014));
    }

    @Override
    public void run() {

        while (running) {

            try {
                Socket socket;
                socket = this.serverSocket.accept();

                
                
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
