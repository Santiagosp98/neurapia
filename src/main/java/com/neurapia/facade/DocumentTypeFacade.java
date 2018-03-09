package com.neurapia.facade;

import com.neurapia.entities.DocumentType;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see DocumentType
 * @see DocumentTypeFacadeLocal
 * @see AbstractFacade
 * @since 2018-08-03
 */
@Stateless
public class DocumentTypeFacade extends AbstractFacade<DocumentType> implements DocumentTypeFacadeLocal {
    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentTypeFacade() {
        super(DocumentType.class);
    }
}