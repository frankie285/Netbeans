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
public class HashTag {
    private String text;
    private List<Tweet> Tweets;
    
    public HashTag(){
        
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Tweet> getTweets() {
        return Tweets;
    }

    public void setTweets(List<Tweet> Tweets) {
        this.Tweets = Tweets;
    }
    
    public void addTweet(Tweet tweet){
        this.Tweets.add(tweet);
    }
}
