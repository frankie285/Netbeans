/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public interface UserDao {

    void create(User user);
    
    void remove(User user);

    User findByName(String name);

    ArrayList<User> getUsers();
    
}
