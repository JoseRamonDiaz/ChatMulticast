/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ramón Díaz
 */
public class ServerTest {
    public static void main(String[] args) {
        int port = 7896;
        try {
            Socket clientSocket = new Socket("localhost",port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(),true);
            out.println("hola desde prueba");
            MulticastServer.getInstance().readMessages();
        } catch (UnknownHostException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
