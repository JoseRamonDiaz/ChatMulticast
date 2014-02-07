/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author José Ramón Díaz
 */
public class CredentialsValidator {
    private Connection connection;
    
    public boolean isAccessGranted(String credentials){
        try {
            //Se obtiene los datos del usuario
            String user = getUser(credentials);
            String introducedPass = getIntroducedPass(credentials);
            //Se crea conexion con la base de datos
            connection = DriverManager.getConnection("jdbc:mysql://localhost/credentials", "root", "");
            String query = "select * from usersdata where user = '"+user+"'";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            boolean existUser = rs.next();
            //Si el usuario existe se verifica el pass de lo contrario se retorna false
            if(existUser){
            String pass = (String)rs.getObject("pass");
            boolean passMatch = introducedPass.equals(pass);
                return passMatch;
            }else {
                return false;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CredentialsValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    //Obtiene el nombre de usuario introducido por el cliente
    private String getUser(String credentials){
        String user = credentials.split(":")[0];
        return user;
    }
    
    //Obtiene la contraseña introducida por el cliente
    private String getIntroducedPass(String credentials){
        String introducedPass = credentials.split(":")[1];
        return introducedPass;
    }
}
