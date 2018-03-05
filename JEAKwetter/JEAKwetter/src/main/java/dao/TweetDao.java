/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Tweet;
import java.util.ArrayList;

/**
 *
 * @author Frank
 */
public interface TweetDao {

    void create(Tweet tweet);
    
    void remove(Tweet tweet);

    Tweet findById(long id);

    ArrayList<Tweet> getTweets();
    
}
