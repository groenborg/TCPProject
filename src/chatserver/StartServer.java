package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import chathttpserver.*;

/**
 *
 * @author simon
 *
 * @servers - Execution of chatserver and webserver
 */
public class StartServer {

    private static ChatServer chatServer;
    private static WebServer webServer;

    public static void main(String[] args) {

        // Starting webserver
        webServer = WebServer.getInstance();
        webServer.startServer();
        // Starting ChatServer
        chatServer = ChatServer.getInstance();
        chatServer.startServer();
        // start admin servercommands
        serverCommands();
    }

    public static void serverCommands() {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        try {
            String command = r.readLine(); // blocking call
            do {
                //ifCommands(command);
                switchCommands(command);
            } while (!(command = r.readLine()).contains("killall"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("server shutdown");
    }

    @Deprecated
    private static void ifCommands(String command) {
        if (command.equals("stop")) {
            chatServer.closeServer();
        }
        if (command.equals("start")) {
            chatServer.startServer();
        }
        if (command.equals("reset")) {
            chatServer.closeServer();
            chatServer.startServer();
        }
    }

    private static void switchCommands(String command) {
        switch (command) {
            case "stop web":
                webServer.closeHttpServer();
                break;
            case "stop chat":
                chatServer.closeServer();
                break;
            case "start chat":
                chatServer.startServer();
                break;
            case "reset":
                chatServer.closeServer();
                chatServer.startServer();
                break;
            case "status":
                System.out.println("running");
                break;
            case "kill":
                chatServer.closeServer();
                webServer.closeHttpServer();
            default:
                if (!command.equals("killall")) 
                    System.out.println("unknow command");
        }
    }

}
