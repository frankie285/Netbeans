/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.Role;
import domain.User;
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
public class RoleDaoJPA extends DaoFacade<Role> implements RoleDao {
    @PersistenceContext
    private EntityManager em;
    
    public RoleDaoJPA() {
        super(Role.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    @Override
    public Role findByRole(String name){
        TypedQuery<Role> query = em.createNamedQuery("role.findByRole", Role.class);

        query.setParameter("Role", name);
        List<Role> result = query.getResultList();
        return result.get(0);
    }

    @Override
    public ArrayList<Role> getRoles() {
         Query query = em.createQuery("SELECT s FROM Role s");
         return  new ArrayList<>(query.getResultList());
    }
    
}

