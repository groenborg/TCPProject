package chatserver;

import chatinterfaces.Connection;
import customexceptions.ConnectionClosedException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Grønborg
 */
public class SocketConnection implements Connection {

    BufferedReader input;
    PrintWriter output;
    Socket socket;

    public SocketConnection(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void closeConnection() throws ConnectionClosedException {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (Exception e) {
            throw new ConnectionClosedException(ChatServerProperties.CONNECTION_CLOSED);
        }
    }

    @Override
    public void openConnection() throws IOException {
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream());
    }

    @Override
    public String recieve() throws IOException {
        StringBuilder b = new StringBuilder();
        int value;
        while ((value = input.read()) != -1) {
            b.append((char) value);
        }
        return b.toString();
    }

    @Override
    public void send(String response) {
        output.print(response);
    }

    @Override
    public String getinetInformation() {
        return socket.getInetAddress().toString();
    }

}
