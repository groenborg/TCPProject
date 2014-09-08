package chatserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Grønborg
 */
public class ClientHandler implements Runnable {

    private BufferedReader input;
    private PrintWriter output;
    private final Socket client;

    private String clientName;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public void openStreams(Socket socket) throws IOException {
        this.input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.output = new PrintWriter(socket.getOutputStream(), true); // autoflush
    }

    public void sendMessage(String message) {
        this.output.println(message);
    }

    @Override
    public void run() {
        try {
            openStreams(client);
            this.clientName = this.client.getLocalSocketAddress().toString();
            System.out.println("handling client: " + clientName);
            String message = "";
            while (!message.contains("##STOP##")) {
                message = input.readLine(); // BLOCKING CALL
                sendMessage("ECHO: " + message);
                output.println("ECHO: " + message);
            }
            shutDownClient();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public void shutDownClient() {
        try {
            this.input.close();
            this.output.close();
            this.client.close();
        } catch (IOException e) {
            System.out.println("Error in client shutdown");
            e.printStackTrace();
        }
        System.out.println("Client succesfully disconnected");
    }

}
