/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Role;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public interface RoleDao {

    void create(Role role);
    
    void remove(Role role);

    Role findByRole(String name);

    ArrayList<Role> getRoles();
    
}