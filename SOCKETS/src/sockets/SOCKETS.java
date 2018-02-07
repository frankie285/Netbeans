/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sockets;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SOCKETS {
    public static void main(String[] args) throws IOException {

        startServer();
        startSender();
    }

    public static void startSender() {
        (new Thread() {
            @Override
            public void run() {
                try {
                    Socket s = new Socket("localhost", 60010);
                    Socket t = new Socket("localhost", 60010);
                    Socket u = new Socket("localhost", 60010);
                    
                    OutputStream outStream = s.getOutputStream();
                    ObjectOutputStream out = new ObjectOutputStream(outStream);
                    
                    Edge edge = new Edge(1,2,"oranje", Color.RED);
                    
                    while (true) {
                        out.writeObject(edge);
                        out.flush();

                        Thread.sleep(200);
                    }

                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void startServer() {
        (new Thread() {
            @Override
            public void run() {
                ServerSocket ss;
                try {
                    ss = new ServerSocket(60010);

                    
                    while(!ss.isClosed()){
                     Socket socket = ss.accept();
                     System.out.println("connection setup.");
                     
                     startThread(socket);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    private static void startThread(Socket socket){
        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println(socket.getInetAddress());
            }
            
        });
        t.start();
    }
}