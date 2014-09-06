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

    private final BufferedReader input;
    private final PrintWriter output;
    private final Socket client;

    private String clientName;
    private String clientAddress;

    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.output = new PrintWriter(client.getOutputStream(), true); // autoflush on

    }

    @Override
    public void run() {
        try {
            this.clientName = this.client.getLocalSocketAddress().toString();
            this.clientAddress = this.client.getInetAddress().getHostAddress();
            System.out.println("handling client: " + clientName + " " + clientAddress);

            String message = "";
            while (!message.contains("##STOP##")) {
                message = input.readLine(); // BLOCKING CALL
                System.out.println("Server Says: " + message);
                output.println("ECHO: " + message);
            }
            shutDownClient();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    private void shutDownClient() {
        try {
            this.client.close();
            this.input.close();
            this.output.close();
            
        } catch (IOException e) {
            System.out.println("Error in client shutdown");
            e.printStackTrace();
        }
        System.out.println("Client succesfully disconnected");
    }

}
