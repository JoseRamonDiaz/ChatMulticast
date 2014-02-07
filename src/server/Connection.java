/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.DataInputStream;
import java.io.IOException;
//<<<<<<< HEAD
//=======
import java.io.InputStreamReader;
import java.io.PrintWriter;
//>>>>>>> muchas cosas
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author José Ramón Díaz
 */
public class Connection extends Thread {
//<<<<<<< HEAD

    private DataInputStream in;
//=======
    private final int MESSAGE_PORT = 7896;
    private final int ACCESS_REQUEST_PORT = 7897;
    private PrintWriter out;
//>>>>>>> muchas cosas
    private Socket clientSocket;

    //Se recibe el socket cliente con el que se conecto
    public Connection(Socket aClientSocket) {
        try {
            //Se guarda el socket cliente
            clientSocket = aClientSocket;
            //Se guarda en un buffered reader el inputStream del cliente
//<<<<<<< HEAD
            in = new DataInputStream(clientSocket.getInputStream());
//=======
            //in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new PrintWriter(clientSocket.getOutputStream(), true);
//>>>>>>> muchas cosas
            this.start();
        } catch (IOException e) {
            System.out.println("Connection:" + e.getMessage());
        }
    }

    @Override
    public void run() {
        try {
//<<<<<<< HEAD
            //Si la entrada fue por el puerto de mensajes se trata como tal
            if (clientSocket.getLocalPort() == MESSAGE_PORT) {
                for (;;) {
                    //Se lee el mensaje entrante del cliente
                    String message = in.readUTF();
                    //Se obtiene la instancia del MulticasServer
                    MulticastServer ms = MulticastServer.getInstance();
                    //Se difunde el mensaje a todo el grupo
                    ms.sendMulticast(message);
                }
            }
//=======
//            if(clientSocket.getLocalPort() == MESSAGE_PORT){
//            //Se lee el mensaje entrante del cliente
//            String message = in.readLine();
//            //Se obtiene la instancia del MulticasServer
//            MulticastServer ms = MulticastServer.getInstance();
//            //Se difunde el mensaje a todo el grupo
//            ms.sendMulticast(message);
//            }
            //Si la entrada fue por el puerto de solicitudes se trata como solicitud
            if (clientSocket.getLocalPort() == ACCESS_REQUEST_PORT) {
                out.println("true");
//               CredentialsValidator cv = new CredentialsValidator();
//               String credentials = in.readUTF();
//               boolean accessGranted = cv.isAccessGranted(credentials);
//               if(accessGranted){
//                    out.println("true");
//               }else{
//                    out.println("false");
//               }
//>>>>>>> muchas cosas
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
