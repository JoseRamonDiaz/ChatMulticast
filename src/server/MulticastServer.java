/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ramón Díaz
 */
public class MulticastServer {
    private MulticastSocket s =null;
    private String grp = "228.5.6.7";
    private InetAddress group;
    private int port = 6789;
    private static MulticastServer multicastServer;
    
    private MulticastServer(){
        
   	 try {               
	    	s = new MulticastSocket(port);
                group = InetAddress.getByName(grp);
	   	s.joinGroup(group);	
 	    }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
	   }catch (IOException e){System.out.println("IO: " + e.getMessage());}
    }
    
    static MulticastServer getInstance(){
        if(multicastServer==null){
            multicastServer = new MulticastServer();
            return multicastServer;
        }else {
            return multicastServer;
        }
    }
    
    public void sendMulticast(String message){
        byte [] m = message.getBytes();
	    	DatagramPacket messageOut=null;
                
        try {
            messageOut = new DatagramPacket(m, m.length, group, port);
            s.send(messageOut);
        } catch (SocketException ex) {
            Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException e){System.out.println("IO: " + e.getMessage());}
	    		
    }
    
    public void close(){
        try {
            s.leaveGroup(group);
        } catch (IOException ex) {
            Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void readMessages(){
        byte[] buffer = new byte[1000];
 	   	for(;;) {
            try {
                DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
                s.receive(messageIn);
                System.out.println("Received:" + new String(messageIn.getData()));
            } catch (IOException ex) {
                Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
            }
  	     	}
    }
}
