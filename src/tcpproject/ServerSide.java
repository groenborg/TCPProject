/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpproject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Grønborg
 */
public class ServerSide {

    static int portNumber = 7;

    public static void main(String[] args) {
        System.out.println("Starting server");
        startServer();
    }

    public static void startServer() {
        try {
            ServerSocket socket = new ServerSocket(portNumber);
            Socket clientSocket = socket.accept();
            System.out.println("Server started");
            
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out.println(inputLine);
                System.out.println(inputLine);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
