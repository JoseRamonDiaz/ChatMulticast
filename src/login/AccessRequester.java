/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author José Ramón Díaz
 */
public class AccessRequester {
    
    public AccessRequester(){
        
    }
    
    public static boolean isAccesAllowed(String user, String pass){
        PrintWriter out;
        BufferedReader in;
        String serverAddress = "localhost";
        int port = 7897;
        try {
            //Se solicita conexion al servidor de peticiones de acceso
            Socket socket = new Socket(serverAddress,port);
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //Se envían los datos de usuario
            out.println(user+":"+pass);
            //Si el servidor envia true se retorna cierto de lo contrario falso
            if("true".equals(in.readLine())){
                return true;
            }else{
                return false;
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(AccessRequester.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AccessRequester.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
