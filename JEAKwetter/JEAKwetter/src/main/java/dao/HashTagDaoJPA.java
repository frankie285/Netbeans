/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.HashTag;
import domain.Profile;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Frank
 */
@Stateless @JPA
public class HashTagDaoJPA extends DaoFacade<HashTag> implements HashTagDao {
    @PersistenceContext
    private EntityManager em;
    
    public HashTagDaoJPA() {
        super(HashTag.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public HashTag findByText(String text){
        TypedQuery<HashTag> query = em.createNamedQuery("hashtag.findByText", HashTag.class);

        query.setParameter("Text", text);
        List<HashTag> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public ArrayList<HashTag> getHashTags() {
         Query query = em.createQuery("SELECT s FROM HashTag s");
         return  new ArrayList<>(query.getResultList());
    }
    
}

