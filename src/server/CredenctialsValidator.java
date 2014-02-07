/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author José Ramón Díaz
 */
public class CredenctialsValidator {
    private Connection connection;
    
    public boolean isAccessGranted(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/arq", "root", "");
        } catch (SQLException ex) {
            Logger.getLogger(CredenctialsValidator.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
}
