package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author simon
 *
 * @Chatserver - Execution of chatserver
 */
public class StartServer {

    private static ChatServer server;

    public static void main(String[] args) {

        // Starting ChatServer
        server = ChatServer.getInstance();
        server.startServer();
        serverCommands();
    }

    public static void serverCommands() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String command = r.readLine(); // blocking call
            do {
                if (command.equals("stop")) {
                    server.closeServer();
                }
                if (command.equals("start")) {
                    server.startServer();
                }
                if (command.equals("reset")) {
                    server.closeServer();
                    server.startServer();
                }
                
            } while (!(command = r.readLine()).contains("killall"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server shutdown");
    }

}
