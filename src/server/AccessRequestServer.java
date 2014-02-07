/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author José Ramón Díaz
 */
public class AccessRequestServer {
    public static void main(String[] args) {    
     try {
            int accessRequestPort = 7897;
            ServerSocket listenSocket = new ServerSocket(accessRequestPort);
            while (true) {
                //Se acepta la solicitud de conexion del cliente
                Socket clientSocket = listenSocket.accept();
                //Se procesa el mensaje en la calse Connection
                new Connection(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Listen :" + e.getMessage());
        }
    }

}
