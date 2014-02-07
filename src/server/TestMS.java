/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

/**
 *
 * @author José Ramón Díaz
 */
public class TestMS {
    public static void main(String[] args) {
        MulticastServer ms = MulticastServer.getInstance();
        ms.sendMulticast("algo");
        ms.readMessages();
    }
}
