package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author simon
 */
public class ChatClient implements Runnable {

    private ArrayList<Observer> listeners = new ArrayList();
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    private String inetAddress;
    private int port;

    public ChatClient(String inetAddress, int port) {
        this.inetAddress = inetAddress;
        this.port = port;
    }

    public void addListeners(Observer observer) {
        this.listeners.add(observer);
    }

    public void removeListener(Observer observer) {
        this.listeners.remove(observer);
    }

    public void notifyListeners(String message) {
        listeners.stream().forEachOrdered((listener) -> {
            listener.recieveMessage(message);
        });
    }

    public void connect() {
        try {
            this.socket = new Socket(inetAddress, port);
            this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.output = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException ex) {
            System.out.println("Failed to connect to server");
        }

    }

    public void close() {
        try {
            input.close();
            output.close();
            socket.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println("client succesfully closed");
    }

    public void send(String message) {
        output.print(message);
    }

    @Override
    public void run() {

        String message;
        try {
            do {
                message = input.readLine();
                notifyListeners(message);
            } while (message != null || message.contains("##STOP##"));
            close();
        } catch (IOException ex) {
            System.out.println("Something wrong with input");
            ex.printStackTrace();
        }
    }

}
