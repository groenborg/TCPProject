package chatclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author simon
 */
public class Terminal implements Observer {

    public static void main(String[] args) {
        String inetAddress = "10.0.1.5";
        int port = 8014;
        if (args.length == 2) {
            inetAddress = args[0];
            port = Integer.parseInt(args[1]);
        }
        ChatClient c = new ChatClient(inetAddress, port);
        Terminal t = new Terminal();
        c.addListeners(t);
        c.connect();
        t.startChat(c);
    }

    public void startChat(ChatClient c) {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        try {
            String message = "";
            while (!message.contains("##STOP##")) {
                message = input.readLine();
                c.send(message);
                System.out.println("you sent: " + message);
            }
            System.out.println("stopped");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void recieveMessage(String message) {
        System.out.println(message);
    }

}
