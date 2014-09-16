package chatserver;

import chatinterfaces.ClientInterface;

/**
 *
 * @author Grønborg
 */
public class Message {

    private final String message;
    private final ClientInterface client;

    public Message(String message, ClientInterface client) {
        this.message = message;
        this.client = client;
    }

    public String getMessage() {
        return message;
    }

    public ClientInterface getClient() {
        return client;
    }
}
