/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Frank
 */
public class Tweet {
    private String Text;
    private List<User> Likers;
    private List<User> Mentions;
    private Date TimeStamp;
    
    public Tweet(){
        
    }

    public String getText() {
        return Text;
    }

    public boolean setText(String Text) {
        if(Text.length() <= 140){
            this.Text = Text; 
            return true;
        }
        return false;
    }

    public List<User> getLikers() {
        return Likers;
    }

    public void setLikers(List<User> Likers) {
        this.Likers = Likers;
    }

    public List<User> getMentions() {
        return Mentions;
    }

    public void setMentions(List<User> Mentions) {
        this.Mentions = Mentions;
    }

    public Date getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Date TimeStamp) {
        this.TimeStamp = TimeStamp;
    }
    
    public void addLiker(User user){
        this.Likers.add(user);
    }
}
