package chatinterfaces;

import customexceptions.ConnectionClosedException;
import java.io.IOException;

/**
 *
 * @author Grønborg
 */
public interface Connection {

    public void closeConnection() throws ConnectionClosedException;

    public void openConnection() throws IOException;

    public String recieve() throws IOException;

    public void send(String response);

    public String getinetInformation();
    
}
