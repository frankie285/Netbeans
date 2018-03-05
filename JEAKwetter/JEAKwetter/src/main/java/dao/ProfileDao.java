/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Profile;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public interface ProfileDao {

    void create(Profile profile);
    
    void remove(Profile profile);

    Profile findByID(long Id);

    ArrayList<Profile> getProfiles();
    
}
