package chatserver;

/**
 *
 * @author Grønborg
 */
public class Message {

    private final String message;
    private final String recieverID;
    private final String sender;

    public Message(String message, String recieverID, String sender) {
        this.message = message;
        this.recieverID = recieverID;
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public String getRecieverID() {
        return recieverID;
    }

    public String getSender() {
        return sender;
    }

}
