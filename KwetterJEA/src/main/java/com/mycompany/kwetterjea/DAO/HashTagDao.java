/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea.DAO;

import com.mycompany.kwetterjea.HashTag;
import com.mycompany.kwetterjea.Tweet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Frank
 */
public class HashTagDao {
    private List<HashTag> HashTags;
    
    public HashTagDao(){

    }

    public List<HashTag> getHashTags() {
        return HashTags;
    }

    public void setHashTags(List<HashTag> HashTags) {
        this.HashTags = HashTags;
    }
    
    public HashTag getHashTagByText(String text){
        for(HashTag ht : HashTags){
            if(ht.getText().equals(text)){
                return ht;
            }
        }
        return null;
    }
    
    public void addHashTag(HashTag ht){
        this.HashTags.add(ht);
    }
    
    public List<HashTag> getHashTagsByDate(Date date){
        List<HashTag> tags = new ArrayList<>();
        for(HashTag ht : this.HashTags){
            for(Tweet t : ht.getTweets()){
                if(t.getTimeStamp().after(date) && !tags.contains(ht)){
                    tags.add(ht);
                }
            }
        }
        return tags;
    }
}
