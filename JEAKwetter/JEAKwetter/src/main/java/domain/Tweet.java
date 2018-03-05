/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Frank
 */

@Entity
@NamedQueries({
    @NamedQuery(name = "Tweet.findById", query = "SELECT s FROM Tweet s WHERE s.Id = :Id")})

@XmlRootElement
public class Tweet {
    @Id 
    @GeneratedValue
    private long Id;
    private String Text;
    
    @ManyToMany
    @JoinTable(name="tweet_likers")
    private List<User> Likers;
    
    @ManyToMany
    @JoinTable(name="tweet_mentions")
    private List<User> Mentions;
    
    @ManyToMany
    private List<HashTag> Hashtags;
    
    private Date TimeStamp;
    
    public Tweet(){
        Likers = new ArrayList<>();
        Mentions = new ArrayList<>();
        Hashtags = new ArrayList<>();
    }
    
    public long getId() {
        return Id;
    }

    public void setId(long Id) {
        this.Id = Id;
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

    public void addLiker(User user){
        this.Likers.add(user);
    }

    public List<User> getMentions() {
        return Mentions;
    }

    public void setMentions(List<User> Mentions) {
        this.Mentions = Mentions;
    }
    
    public void addMention(User mention){
        this.Mentions.add(mention);
    }
    
    public List<HashTag> getHashtags() {
        return Hashtags;
    }

    public void setHashtags(List<HashTag> Hashtags) {
        this.Hashtags = Hashtags;
    }
    
    public void addHashtag(HashTag hashtag){
        this.Hashtags.add(hashtag);
    }

    public Date getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(Date TimeStamp) {
        this.TimeStamp = TimeStamp;
    }
}
