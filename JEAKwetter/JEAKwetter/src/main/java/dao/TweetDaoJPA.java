/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Profile;
import domain.Tweet;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Frank
 */
@Stateless @JPA
public class TweetDaoJPA extends DaoFacade<Tweet> implements TweetDao {
    @PersistenceContext
    private EntityManager em;
    
    public TweetDaoJPA() {
        super(Tweet.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Tweet findById(long id){
        TypedQuery<Tweet> query = em.createNamedQuery("tweet.findById", Tweet.class);

        query.setParameter("Id", id);
        List<Tweet> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public ArrayList<Tweet> getTweets() {
         Query query = em.createQuery("SELECT s FROM Tweet s");
         return  new ArrayList<>(query.getResultList());
    }
    
}

