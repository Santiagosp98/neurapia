package com.neurapia.facade;

import com.neurapia.entities.Privilege;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Privilege
 * @see PrivilegeFacadeLocal
 * @see AbstractFacade
 * @since 2018-08-03
 */
@Stateless
public class PrivilegeFacade extends AbstractFacade<Privilege> implements PrivilegeFacadeLocal {
    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrivilegeFacade() {
        super(Privilege.class);
    }
}