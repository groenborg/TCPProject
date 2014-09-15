package customexceptions;

/**
 *
 * @author Grønborg
 */
public class ConnectionClosedException extends RuntimeException {

    public ConnectionClosedException(String message) {
        super(message);
    }

}
