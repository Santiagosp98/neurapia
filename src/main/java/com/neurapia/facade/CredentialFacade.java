package com.neurapia.facade;

import com.neurapia.entities.Credential;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Credential
 * @see CredentialFacadeLocal
 * @see AbstractFacade
 * @since 2018-08-03
 */
@Stateless
public class CredentialFacade extends AbstractFacade<Credential> implements CredentialFacadeLocal {
    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CredentialFacade() {
        super(Credential.class);
    }
}