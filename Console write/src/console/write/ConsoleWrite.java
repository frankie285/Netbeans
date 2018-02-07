/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.write;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import timeutil.TimeStamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;
import java.io.*;

/**
 *
 * @author Frank
 */
public class ConsoleWrite implements Observer{
                private static ArrayList<Edge> edgeList = new ArrayList<Edge>();
                static FileWriter schrijver;
                static BufferedWriter out;
                static FileOutputStream fos;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        if(args != null){
            int level = Integer.parseInt(args[0]);
            KochFractal kf = new KochFractal();
            edgeList.clear();
            kf.setLevel(level);
            
            kf.generateLeftEdge();
            kf.generateBottomEdge();
            kf.generateRightEdge();
            
            writeText(level);
            //writeTextBuffer(level);
            //writeBinary(level);
            //writeBinaryBuffer(level);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
       edgeList.add((Edge)arg);
    }
    
    private static void writeText(int level){
        try{
            
            TimeStamp ts = new TimeStamp();
            ts.setBegin("Begin schrijven");
            
            
            schrijver = new FileWriter("text.txt");
                
                for(Edge edge: edgeList){
                    schrijver.write(edge.toString());
                    schrijver.write("\n");
                }
                
            schrijver.close();
            
            ts.setEnd("Eind schrijven");
            
            } 
        catch (IOException e) {
                System.out.println(e);
            }
    }
    
    private static void writeTextBuffer(int level){
        try{
            
            TimeStamp ts = new TimeStamp();
            ts.setBegin("Begin schrijven");
            
            
            schrijver = new FileWriter("text.txt");
            out = new BufferedWriter(schrijver);
                
                for(Edge edge: edgeList){
                    out.write(edge.toString());
                    out.write("\n");
                }
            out.close();
            schrijver.close();
            
            ts.setEnd("Eind schrijven");
            
            } 
        catch (IOException e) {
                System.out.println(e);
            }
    }
    
    private static void writeBinary(int level) throws FileNotFoundException, IOException{
        TimeStamp ts = new TimeStamp();
        ts.setBegin("Begin schrijven");
        
        fos = new FileOutputStream("text.ser");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(edgeList);
        out.close();
        fos.close();
        
        ts.setEnd("Eind schrijven");
    }
    
    private static void writeBinaryBuffer(int level) throws FileNotFoundException, IOException{
        TimeStamp ts = new TimeStamp();
        ts.setBegin("Begin schrijven");
        
        fos = new FileOutputStream("text.ser");
        BufferedOutputStream bout = new BufferedOutputStream(fos);
        ObjectOutputStream out = new ObjectOutputStream(bout);
        out.writeObject(edgeList);
        out.flush();
        bout.close();
        fos.close();
        
        ts.setEnd("Eind schrijven");
    }
}
