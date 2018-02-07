/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package android.ambilight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javafx.scene.image.ImageView;

/**
 *
 * @author Frank
 */
public class SQL {
   
    
    Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        byte[] array = null;
        
        
        public byte[] getPicture(){
        try {
        
            String url = "jdbc:mysql://sql7.freemysqlhosting.net/sql7144524";
            myConn = DriverManager.getConnection(url, "sql7144524", "ieSwyn1XFZ");
            System.out.println("Database connectie succesvol!\n");
            myStmt = myConn.createStatement();            
            myRs = myStmt.executeQuery("SELECT * FROM ambilight");
            
            while(myRs.next()){
                array = myRs.getBytes("image");
            }
            return array;
        }
        
        catch (Exception e){
            e.printStackTrace();
            return array;
        }
    }
}
