package com.neurapia.facade;

import com.neurapia.entities.Status;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Status
 * @see AbstractFacade
 * @see StatusFacadeLocal
 * @since 2018-08-03
 */
@Stateless
public class StatusFacade extends AbstractFacade<Status> implements StatusFacadeLocal {
    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatusFacade() {
        super(Status.class);
    }
}