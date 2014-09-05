/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    
    
    public ClientHandler(Socket client) throws IOException {
        this.client = client;
        this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.output = new PrintWriter(client.getOutputStream());

    }

    @Override
    public void run() {
        System.out.println("handling new client: " + this.client.getInetAddress().toString());
        
        
        
        try {

            String message;
            while ((message = this.input.readLine()) != null ) {
                System.out.println("");
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
