package tcpproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 *
 * @author Grønborg
 */
public class TCPProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("Starting Client");
        
        startClient();
    }

    public static void startClient() {
        int portNumber = 8014;
        try {
            
            InetAddress host = InetAddress.getLocalHost();
            Socket socket = new Socket(host, portNumber);
            System.out.println("Started");
            
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
