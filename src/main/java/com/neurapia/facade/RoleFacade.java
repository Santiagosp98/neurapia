package com.neurapia.facade;

import com.neurapia.entities.Role;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Role
 * @see RoleFacadeLocal
 * @see AbstractFacade
 * @since 2018-08-03
 */
@Stateless
public class RoleFacade extends AbstractFacade<Role> implements RoleFacadeLocal {
    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleFacade() {
        super(Role.class);
    }
}