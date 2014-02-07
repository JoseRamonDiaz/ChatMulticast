/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author a11216367
 */
public class TCPClient {

    String username;
    MulticastConnection multicast;
    DataOutputStream out;
    Socket s;
    
    String host = "localhost";
    int serverPort = 7896;

    public TCPClient(ChatWindow chat, String username) {
        this.username = username;

        //Se une al grupo multicast para poder recibir los mensajes de otros clientes.
        multicast = new MulticastConnection(chat);

        try {
            
            //Crea el socket para conectarse con el server.
            s = new Socket(host, serverPort);
            //Crea el stream para mandar mensajes.
            out = new DataOutputStream(s.getOutputStream());

        } catch (UnknownHostException e) {
            System.out.println("Sock:" + e.getMessage());
        } catch (EOFException e) {
            System.out.println("EOF:" + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO:" + e.getMessage());
        }

    }

    void sendMessage(String msg) {
        try {
            //Limpia el stream.
            out.flush();
            //Envia un mensaje al server.
            out.writeUTF(msg + "\n");
        } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
