/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.HashTag;
import domain.Profile;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public interface HashTagDao {

    void create(HashTag hashtag);
    
    void remove(HashTag hashtag);

    HashTag findByText(String text);

    ArrayList<HashTag> getHashTags();
    
}
