package chatserver;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void main(String[] args) {
        MessageDispatcher h = new MessageDispatcher(new ArrayBlockingQueue(100), new HashMap());

        Thread t = new Thread(h);
        t.start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            Logger.getLogger(MessageDispatcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        h.putMessage(new Message("hej", "med", "dig"));
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
        ClientHandler h;
        if ((h = users.get(message.getRecieverID())) != null) {
            h.sendMessage(message.getMessage());
        } else {
            users.get(message.getSender()).sendMessage("user: " + message.getRecieverID()
                    + " not online");
        }
    }

}
