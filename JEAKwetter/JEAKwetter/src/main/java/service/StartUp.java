/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import dao.HashTagDao;
import dao.JPA;
import dao.ProfileDao;
import dao.RoleDao;
import dao.TweetDao;
import dao.UserDao;
import domain.HashTag;
import domain.Profile;
import domain.Role;
import domain.Tweet;
import domain.User;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author frank
 */
@Singleton
@Startup
public class StartUp {
    @Inject @JPA
    private HashTagDao hashtagDao;
    @Inject @JPA
    private TweetDao tweetDao;
    @Inject @JPA
    private UserDao userDao;
    @Inject @JPA
    private RoleDao roleDao;
    @Inject @JPA
    private ProfileDao profileDao;
      
    public StartUp() {
    }
     
    @PostConstruct
    private void intData(){
        HashTag h1 = new HashTag();
        h1.setText("#klote");
        hashtagDao.create(h1);
        
        Role r1 = new Role();
        r1.setRole("admin");
        roleDao.create(r1);
        
        Role r2 = new Role();
        r2.setRole("user");
        roleDao.create(r2);
        
        Profile p1 = new Profile();
        p1.setBio("Ik ben Frankie en programmeren is cool");
        p1.setUrl("www.google.nl");
        profileDao.create(p1);
        
        Profile p2 = new Profile();
        p2.setBio("Ik ben Yannick en programmeren vind ik niks");
        profileDao.create(p2);
        
        User u1 = new User();
        u1.setUsername("frankie285");
        u1.setRole(r1);
        u1.setAccount(p1);
        userDao.create(u1);
        
        Tweet t1 = new Tweet();
        t1.setText("Ik zit op de wc #klote");
        t1.addLiker(u1);
        t1.addMention(u1);
        t1.addHashtag(h1);
        tweetDao.create(t1);
        
        User u2 = new User();
        u2.setUsername("yannick134");
        u2.setRole(r2);
        u2.setAccount(p2);
        u2.addFollower(u1);
        u2.addTweet(t1);
        userDao.create(u2);
    }
    
}
