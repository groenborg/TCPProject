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
    private Socket client;

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
            this.clientName = this.client.getInetAddress().getHostName();
            this.clientAddress = this.client.getInetAddress().getHostAddress();
            System.out.println("handling client: " + clientName + " " + clientAddress);

            String message;
            while ((message = this.input.readLine()) != null || message.contains("##STOP##")) {
                System.out.println("Server Says: " + message);
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

    }

}
