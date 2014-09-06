package chatserver;

/**
 *
 * @author simon
 * 
 * @Chatserver - Execution of chatserver
 */
public class StartServer {

    public static void main(String[] args) {

        // Starting ChatServer
        ChatServer server = ChatServer.getInstance();
        server.startServer();
        
    }

}
