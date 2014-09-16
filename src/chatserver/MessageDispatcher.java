package chatserver;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Grønborg
 */
public class MessageDispatcher implements Runnable {

    private final ArrayBlockingQueue<Message> messageQue;
    private final HashMap<String, ClientHandler> users;
    private boolean running = true;

    public MessageDispatcher(ArrayBlockingQueue<Message> messageQue, HashMap<String, ClientHandler> users) {
        this.messageQue = messageQue;
        this.users = users;
    }

    public void reqistrerUser(String name, ClientHandler handler) {
        this.users.put(name, handler);
    }

    @Override
    public void run() {
        try {
            while (running) {
                System.out.println("hej");
                dispatchMessage(this.messageQue.take());
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            System.out.println("lol");
        }
        System.out.println("ending");
    }

    public synchronized void putMessage(Message message) {
        try {
            this.messageQue.put(message);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void dispatchMessage(Message message) {
        
        
        
    }

}
