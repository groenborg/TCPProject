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
        servercommands();
    }

    public static void servercommands() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String command;
            while (!(command = r.readLine()).contains("killall")) {
                if (command.contains("kill")) {
                    server.closeServer();
                }
            }
        } catch (IOException e) {
           e.printStackTrace();
        }
        System.out.println("server shutdown");
    }

}
