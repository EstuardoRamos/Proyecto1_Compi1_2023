/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.server_sokoban.comunication;

/**
 * 
 * @author Estuardo Ramos
 */
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Listener extends Thread {

    private int portServer;

    public Listener(int portServer) {
        this.portServer = portServer;
    }

    @Override
    public void run() {
        try(ServerSocket server = new ServerSocket(this.portServer)) {
            while (true) {
                String hostAddress = InetAddress.getLocalHost().getHostAddress();
                System.out.printf("Listening on %s:%d\n", hostAddress, this.portServer);
                Socket socket = server.accept();

                ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                SimpleMessage request = (SimpleMessage) inputStream.readObject();

                // TODO: read message
                System.out.println("Message received");
                System.out.println(request);


                // TODO: write your code here

                // TODO: write another message
                SimpleMessage response = new SimpleMessage("Hello from server: " + LocalDateTime.now());
                System.out.println("\nSending message:");
                System.out.println(response);

                outputStream.writeObject(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(System.out);
        }
    }

    public int getPortServer() {
        return portServer;
    }

    public void setPortServer(int portServer) {
        this.portServer = portServer;
    }
}