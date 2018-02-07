/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Frank
 */
public class ServerRunnable implements Runnable{
    private Socket socket = null;

    public ServerRunnable() throws IOException {
	ServerSocket ss;
                try {
                    ss = new ServerSocket(60010);

                    Socket s = ss.accept();
                    System.out.println("heuj");
                }
                catch(Exception e){
                    System.out.println(e);
                }
        
    }

    public void run() {

	try {
            System.out.println("hoi");
            InputStream inStream = socket.getInputStream();
                    ObjectInputStream in = new ObjectInputStream(inStream);
                    
                    
                    Edge edge = null;
                    while (true) {
                        edge = (Edge) in.readObject();
                        System.out.println(edge.color + "," + edge.kleur + "," + edge.x1 + "," + edge.y1);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    System.out.println(ex);
                }
        }
}
