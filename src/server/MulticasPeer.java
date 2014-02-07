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

/**
 *
 * @author José Ramón Díaz
 */
public class MulticasPeer {
    public static void main(String args[]){   // args give message contents 
                     String grp = "228.5.6.7";
	MulticastSocket s =null;
   	 try {
	   	InetAddress group = InetAddress.getByName(grp);
                int port = 6789;
	    	s = new MulticastSocket(port);
	   	s.joinGroup(group);
 	    	byte [] m = "algo".getBytes();//args[0].getBytes();
	    	DatagramPacket messageOut = new DatagramPacket(m, m.length, group, port);
	    	s.send(messageOut);	
	                      // get messages from others in group
	    	byte[] buffer = new byte[1000];
 	   	for(int i=0; i< 5; i++) {
 		    DatagramPacket messageIn = new DatagramPacket(buffer, buffer.length);
 		    s.receive(messageIn);
 		    System.out.println("Received:" + new String(messageIn.getData()));
  	     	}
	    	s.leaveGroup(group);		
 	    }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
	   }catch (IOException e){System.out.println("IO: " + e.getMessage());}
     }
}
