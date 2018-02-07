/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ambilight;

/**
 *
 * @author Frank
 */
public class User {
    
    /**
     * public fields
     */
    public String username;
    public String password;
    public int id;
    
    /**
     * Generates a User object
     * @param username input String 
     * @param password  input String
     */
    public User(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    /**
     * generates a User object
     * @param id input integer userID
     * @param username input String
     * @param password input String
     */
    public User(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    /**
     * generates an empty User object
     */
    public User(){
    }
}
