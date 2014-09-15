package chatserver;

import chatinterfaces.ClientInterface;
import chatinterfaces.Connection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Grønborg
 */
public class ClientHandler implements Runnable, ClientInterface {

    private final Connection clientConnection;
    private String clientID;
    private static final Logger logger = Logger.getLogger(ClientHandler.class.getName());

    public ClientHandler(Connection clientConnection) {
        this.clientConnection = clientConnection;
    }

    @Override
    public void closeConnection() {
        clientConnection.closeConnection();
    }

    @Override
    public void openConnection() throws IOException {
        clientConnection.openConnection();
    }

    @Override
    public String getID() {
        return clientID;
    }

    @Override
    public void setID(String ID) {
        clientID = ID;
    }

    @Override
    public void sendMessage(String message) {
        clientConnection.send(message);
    }

    @Override
    public void run() {
        try {
            openConnection();

            System.out.println("handling client: " + clientConnection.getinetInformation());
            String message = "";

            while (!message.contains("##STOP##")) {
                message = clientConnection.recieve(); // BLOCKING CALL
                sendMessage("ECHO: " + message);
                clientConnection.send("ECHO: " + message);
            }

        } catch (IOException ex) {
            logger.log(Level.INFO, "connection could not be established");
            closeConnection();
        } finally {
            closeConnection();
        }

    }

}
