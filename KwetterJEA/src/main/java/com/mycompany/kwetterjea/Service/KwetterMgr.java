/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.kwetterjea.Service;

import com.mycompany.kwetterjea.DAO.HashTagDao;
import com.mycompany.kwetterjea.DAO.TweetDao;
import com.mycompany.kwetterjea.DAO.UserDao;
import com.mycompany.kwetterjea.HashTag;
import com.mycompany.kwetterjea.Tweet;
import com.mycompany.kwetterjea.User;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Frank
 */
public class KwetterMgr {
    TweetDao td;
    UserDao ud;
    HashTagDao hd;

    public KwetterMgr(){
        this.td = new TweetDao();
        this.ud = new UserDao();
        this.hd = new HashTagDao();
    }  
    
    public List<Tweet> getRecentTweets(User user){
        List<Tweet> recentTweets = user.getTweets();
        Collections.sort(recentTweets, Comparator.comparing(Tweet::getTimeStamp));
        if(recentTweets.size() <= 10){
            return recentTweets;
        }
        return recentTweets.subList(0, 9);
    }
    
    public List<Tweet> searchTweets(String query){
        return td.getTweetsByQuery(query);
    }
    
    public List<Tweet> ourRecentTweets(User user){
    List<Tweet> timeline = new ArrayList<>();
        for(Tweet t : user.getTweets()){
            timeline.add(t);
        }
        
        List<User> following = ud.getFollowing(user);
        for(User u : following){
            for(Tweet t : u.getTweets()){
                timeline.add(t);
            }
        }
        
        Collections.sort(timeline, Comparator.comparing(Tweet::getTimeStamp));
        if(timeline.size() <= 10){
            return timeline;
        }
        return timeline.subList(0, 9);
    }
    
    public List<Tweet> getMentionedTweets(User user){
        List<Tweet> mentions = td.getMentionedTweets(user);
        Collections.sort(mentions, Comparator.comparing(Tweet::getTimeStamp));
        return mentions;
    }
    
    public List<User> getMentionsFromText(String text){
        List<User> mentions = new ArrayList<>();
        Pattern mentionPattern = Pattern.compile("@(\\S+)");
        Matcher matcher = mentionPattern.matcher(text);
        while (matcher.find()) {
            String username = matcher.group(1);
            User mentioned = ud.getUserByUsername(username);
            if(mentioned != null){
                mentions.add(mentioned);
            }
        }
        return mentions;
    }
    
    public void addHashTagsFromTweet(Tweet tweet){
        Pattern hashtagPattern = Pattern.compile("#(\\S+)");
        Matcher matcher = hashtagPattern.matcher(tweet.getText());
        while (matcher.find()) {
            String hashtag = matcher.group(1);
            HashTag ht = hd.getHashTagByText(hashtag);
            if(ht != null){
                if(!ht.getTweets().contains(tweet)){
                    ht.addTweet(tweet);
                }
            }
            HashTag hashTag = new HashTag();
            hashTag.setText(hashtag);
            hashTag.addTweet(tweet);
            hd.addHashTag(hashTag);
        }
    }
    
    public List<HashTag> getPopularHashTags(){
        long day = 1000 * 60 * 60 * 24;
        Date lastWeek = new Date(System.currentTimeMillis() - (7 * day));
        List<HashTag> popular = hd.getHashTagsByDate(lastWeek);
        Collections.sort(popular, Comparator.comparing((hashTag) -> hashTag.getTweets().size()));
        return popular;
    }
}
