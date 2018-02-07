/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambilight;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class database {
    
    /**
     * public static fields
     */
    public static int userID;

    /**
     * returns a list of all the User data from the database as a User object List
     * @return a list of User Objects
     * @throws SQLException 
     */
    public static List<User> login() throws SQLException {
        List<User> users = new ArrayList<User>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        try {
        
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "Succes15");
            System.out.println("Database connectie succesvol!\n");
            myStmt = myConn.createStatement();            
            myRs = myStmt.executeQuery("SELECT * FROM users");
            
            while(myRs.next()){
                User user = new User(myRs.getInt("id") ,myRs.getString("username"), myRs.getString("password"));
                users.add(user);
            }
            return users;
        }
        
        catch (Exception e){
            e.printStackTrace();
            return users;
        }
    }
    
        /**
         * executes the query and inserts the username and password, also generates a record in the color table that is empty but matches the user id
         * @param username input String
         * @param password input String 
         * @throws SQLException 
         */
        public static void register(String username, String password) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "Succes15");
            Statement myStatement = myConn.createStatement();
            int rowsAffected = myStatement.executeUpdate("insert into users (username, password) values ('" + username + "','" + password + "')"); 
            
            User user = getUser(username);
            Statement myStatement2 = myConn.createStatement();
            int rowsAffected2 = myStatement.executeUpdate("insert into colors (id, color1, color2, color3, color4, color5, color6) values ("+ user.id +",'0','0','0','0','0','0')"); 
            userID = user.id;
        }
        
        catch(Exception f){
            f.fillInStackTrace();
        }
    }
        
        /**
         * @return returns a list of all the color data in the database, as a list of ColorData objects
         * @throws SQLException 
         */
        public static List<ColorData> getColorData() throws SQLException {
        List<ColorData> colorData = new ArrayList<ColorData>();
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        try {
        
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "Succes15");
            System.out.println("Database connectie succesvol!\n");
            myStmt = myConn.createStatement();            
            myRs = myStmt.executeQuery("SELECT * FROM colors");
            
            while(myRs.next()){
                ColorData colorObject = new ColorData(myRs.getInt("id") ,myRs.getString("color1"), myRs.getString("color2"), myRs.getString("color3"), myRs.getString("color4"), myRs.getString("color5"), myRs.getString("color6"));
                colorData.add(colorObject);
            }
            return colorData;
        }
        
        catch (Exception e){
            e.printStackTrace();
            return colorData;
        }
    }
         
        /**
         * 
         * @param username input String
         * @return returns the correct Object with auto incremented ID
         * @throws SQLException 
         */
        public static User getUser(String username) throws SQLException{
        User gottenUser = new User();
        List<User> users = database.login();
                 for(User user: users){
                     if(username.equals(user.username)){
                         gottenUser = user;
                     }
                 }
                 return gottenUser;
             }
        
        /**
         * inserts the ColorData object data into the database
         * @param colorData the inputted ColorData object 
         */
        public static void setColors(ColorData colorData){
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;
        
        try{
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "Succes15");
            Statement myStatement = myConn.createStatement();
            int rowsAffected = myStatement.executeUpdate("update colors SET color1='" + colorData.color1 + "',color2='" + colorData.color2 + "',color3='" + colorData.color3 + "',color4='" + colorData.color4 + "',color5='" + colorData.color5 + "',color6='" + colorData.color6 + "' WHERE id=" + colorData.id);
            
            
        }
        
        catch(Exception f){
            f.fillInStackTrace();
        }
            
        }
}
