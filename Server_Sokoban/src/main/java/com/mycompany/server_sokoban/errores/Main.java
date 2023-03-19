/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.server_sokoban.errores;

/**
 *
 * @author Estuardo Ramos
 */
import com.mycompany.server_sokoban.comunication.Manejador;
import com.mycompany.server_sokoban.parser.CounterLexer;
import com.mycompany.server_sokoban.parser.Token;
import com.mycompany.server_sokoban.parser.parser;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    // CounterLexer counterLexer;
    //private parser parser;
    Manejador manejador;
    String respuestaServer;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket socket = serverSocket.accept();

            DataInputStream inputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());

            String mensaje = inputStream.readUTF();

            Reader reader = new StringReader(mensaje);
            CounterLexer counterLexer = new CounterLexer(reader);
            parser parser = new parser(counterLexer);

            try {
                //parser.parse();
                System.out.println("Esos son lo s errores sintacticos");
                System.out.println(parser.getErrores().size());
            } catch (Exception e) {
                e.printStackTrace();
                // Aqui ustedes manejan de forma correcta el error.
            }

            String respuesta = Manejador.leer(mensaje);
            outputStream.writeUTF(respuesta);
            // Procese el mensaje según sea necesario
            /*System.out.println("--------- Aqui debemos mostrar lo que nos manda el cliente------ ");
            System.out.println(mensaje);*/

            outputStream.close();
            inputStream.close();
            socket.close();
        }
    }
}
