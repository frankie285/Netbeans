/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpelsocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Frank
 */
public class SimpelSocket {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        try{
            ServerSocket ss = new ServerSocket(60010);
            System.out.println("server setup");
        while(!ss.isClosed()){
            Socket s = null;
            s = ss.accept();
            if(s != null){
                System.out.println("accept");
                System.out.println(s.getInetAddress().toString());
            }
        }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
}
