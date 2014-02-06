/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ramón Díaz
 */
public class Connection extends Thread{
    private BufferedReader in;
    private Socket clientSocket;
    
    public Connection(Socket aClientSocket){
        try {
            this.clientSocket = aClientSocket;
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            this.start();
        } catch(IOException e)  {System.out.println("Connection:"+e.getMessage());}
    }
    
    @Override
    public void run(){
        try {
            String message = in.readLine();
            MulticastServer ms = MulticastServer.getInstance();
            ms.sendMulticast(message);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
