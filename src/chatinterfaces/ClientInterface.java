package chatinterfaces;

import java.io.IOException;

/**
 *
 * @author Grønborg
 */
public interface ClientInterface {

    public void closeConnection();

    public void openConnection() throws IOException;

    public String getID();

    public void setID(String ID);

    public void sendMessage(String message);

}
