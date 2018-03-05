/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class ProfileDaoJPA extends DaoFacade<Profile> implements ProfileDao {
    @PersistenceContext
    private EntityManager em;
    
    public ProfileDaoJPA() {
        super(Profile.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Profile findByID(long id){
        TypedQuery<Profile> query = em.createNamedQuery("profile.findById", Profile.class);

        query.setParameter("Id", id);
        List<Profile> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public ArrayList<Profile> getProfiles() {
         Query query = em.createQuery("SELECT s FROM Profile s");
         return  new ArrayList<>(query.getResultList());
    }
    
}

