/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
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
 * @author frank
 */
@Stateless @JPA
public class UserDaoJPA extends DaoFacade<User> implements UserDao {

    @PersistenceContext
    private EntityManager em;
    
    public UserDaoJPA() {
        super(User.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public User findByName(String username){
        TypedQuery<User> query = em.createNamedQuery("user.findByUsername", User.class);

        query.setParameter("Username", username);
        List<User> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public ArrayList<User> getUsers() {
         Query query = em.createQuery("SELECT s FROM User s");
         return  new ArrayList<>(query.getResultList());
    }
    
}

