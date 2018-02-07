/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbc.test;
import java.sql.*;

/**
 *
 * @author Frank
 */
public class JdbcTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        try {
        
            myConn = DriverManager.getConnection("jdbc:mysql://studmysql01.fhict.local/dbi310878", "dbi310878", "Iie72-HD");
            System.out.println("Database connectie succesvol!\n");
            myStmt = myConn.createStatement();            
            myRs = myStmt.executeQuery("SELECT * FROM employees");
            
            while(myRs.next()){
                System.out.println(myRs.getString("last_name") + ", " + myRs.getString("first_name"));
            }
        }
        
        catch (Exception e){
            e.printStackTrace();
        }
            }
    
}
