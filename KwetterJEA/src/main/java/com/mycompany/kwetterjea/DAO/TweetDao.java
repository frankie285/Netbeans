/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea.DAO;

import com.mycompany.kwetterjea.Tweet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Frank
 */
public class TweetDao {
    private List<Tweet> Tweets;
    
    public TweetDao(){
        
    }

    public List<Tweet> getTweets() {
        return Tweets;
    }

    public void setTweets(List<Tweet> Tweets) {
        this.Tweets = Tweets;
    }
    
    public List<Tweet> searchTweets(String query){
        List<Tweet> results = new ArrayList<>();
        for(Tweet tweet : Tweets){
            if(tweet.getText().toLowerCase().contains(query.toLowerCase())){
                results.add(tweet);
            }
        }
        return results;
    }
}
