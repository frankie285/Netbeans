/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Frank
 */
public class User {
    private String Username;
    private Role Role;
    private Profile Account;
    private List<User> Followers;
    private List<Tweet> Tweets;
    
    public User(){
        
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;  
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role Role) {
        this.Role = Role;
    }

    public Profile getAccount() {
        return Account;
    }

    public void setAccount(Profile Account) {
        this.Account = Account;
    }

    public List<User> getFollowers() {
        return Followers;
    }

    public void setFollowers(List<User> Followers) {
        this.Followers = Followers;
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
    
    public List<Tweet> getRecentTweets(){
        List<Tweet> recentTweets = this.Tweets;
        Collections.sort(recentTweets, Comparator.comparing(Tweet::getTimeStamp));
        if(recentTweets.size() <= 10){
            return recentTweets;
        }
        return recentTweets.subList(0, 9);
    }
}
