/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arenatojava;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Frank
 */
public class SQL {
    Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        public SQL(){
            try{
                myConn = DriverManager.getConnection("jdbc:mysql://studmysql01.fhict.local/dbi310878", "dbi310878", "Iie72-HD");
                myStmt = myConn.createStatement();     
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        
        public void updateColors(Color color, int id){
           
            try{
                String sqlQuery = "UPDATE glowstick SET Red = ?, Green = ?, Blue = ? WHERE ID = ?;";
                PreparedStatement pstmt = myConn.prepareStatement(sqlQuery);
                pstmt.setInt(1, color.getRed());
                pstmt.setInt(2, color.getGreen());
                pstmt.setInt(3, color.getBlue());
                pstmt.setInt(4, id);
                pstmt.executeUpdate();
            
                pstmt.close(); 
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
}
