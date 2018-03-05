/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Frank
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "user.findByUsername", query = "SELECT s FROM User s WHERE s.Username = :Username")})

@XmlRootElement
public class User implements Serializable{  
    @Id
    private String Username;
    @ManyToOne
    private Role Role;
    @OneToOne
    private Profile Account;
    
    @ManyToMany
    private List<User> Followers;
    
    
    @OneToMany
    private List<Tweet> Tweets;
    
    public User(){
        Followers = new ArrayList<>();
        Tweets = new ArrayList<>();
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
    
    public void addFollower(User follower){
        this.Followers.add(follower);
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
