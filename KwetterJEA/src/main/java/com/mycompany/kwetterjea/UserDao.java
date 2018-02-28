/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea;

import java.util.List;

/**
 *
 * @author Frank
 */
public class UserDao {
    List<User> Users;
    
    public UserDao(){
        
    }
    
    public List<User> getUsers() {
        return Users;
    }

    public void setUsers(List<User> Users) {
        this.Users = Users;
    }
    
    public boolean userExists(String username){
        for(User u : Users){
            if(u.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    
    public List<User> getFollowing(User user){
        
    }
}
