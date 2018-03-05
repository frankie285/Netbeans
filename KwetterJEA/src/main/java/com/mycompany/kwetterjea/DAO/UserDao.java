/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea.DAO;

import com.mycompany.kwetterjea.User;
import java.util.ArrayList;
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
    
    public User getUserByUsername(String username){
        for(User u : Users){
            if(u.getUsername().equals(username)){
                return u;
            }
        }
        return null;
    }
    
    public List<User> getFollowing(User user){
        List<User> following = new ArrayList<>();
        for(User u : Users){
            if(u.getFollowers().contains(user)){
                following.add(u);
            }
        }
        return following;
    }
}
