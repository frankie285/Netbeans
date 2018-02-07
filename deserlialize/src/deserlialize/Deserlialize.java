/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deserlialize;
import java.io.*;

/**
 *
 * @author Frank
 */
public class Deserlialize {

    /**
     * @param args the command line arguments
     */

   public static void main(String [] args) {
      ColorData c = null;
      try {
         FileInputStream fileIn = new FileInputStream("C:/temp/employee.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         c = (ColorData) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
         return;
         
      }catch(ClassNotFoundException d) {
         System.out.println("ColorData class not found");
         d.printStackTrace();
         return;
      }
      
      System.out.println("Deserialized object");
      System.out.println(c.id + "," + c.color1 + "," + c.color2 + "," + c.color3 + "," + c.color4 + "," + c.color5 + "," + c.color6);
   }
}