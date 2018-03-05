/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea.DAO;

import com.mycompany.kwetterjea.Tweet;
import com.mycompany.kwetterjea.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    
    public List<Tweet> getTweetsByQuery(String query){
        List<Tweet> results = new ArrayList<>();
        for(Tweet tweet : Tweets){
            if(tweet.getText().toLowerCase().contains(query.toLowerCase())){
                results.add(tweet);
            }
        }
        return results;
    }
    
    public List<Tweet> getMentionedTweets(User user){
        List<Tweet> mentioned = new ArrayList<>();
        for(Tweet tweet : this.Tweets){
            if(tweet.getMentions().contains(user)){
                mentioned.add(tweet);
            }
        }
        return mentioned;
    }
}
