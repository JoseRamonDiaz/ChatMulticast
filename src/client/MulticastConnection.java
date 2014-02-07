/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.MulticastServer;

/**
 *
 * @author Yussel
 */
public class MulticastConnection extends Thread{

    MulticastSocket s;
    ChatWindow chat;

    public MulticastConnection(ChatWindow chat) {
        this.chat = chat;
        String grp = "228.5.6.7";
        InetAddress group;
        int port = 6789;

        try {
            //Se crea el socket multicast con el puerto indicado
            s = new MulticastSocket(port);
            //Se obtiene la direccion por el nombre
            group = InetAddress.getByName(grp);
            //El socket multicast se une al grupo
            s.joinGroup(group);
            start();
        } catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        
        for (;;) {
            try {
                byte[] buffer = new byte[1000];
                
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                
                //Recibe los mensajes enviados por multicast
                s.receive(messageIn);
                
                //Actualiza el chat con el nuevo mensaje
                chat.update(new String(messageIn.getData()));
            } catch (IOException ex) {
                Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
