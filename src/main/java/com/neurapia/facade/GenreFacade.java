package com.neurapia.facade;

import com.neurapia.entities.Genre;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Genre
 * @see GenreFacadeLocal
 * @see AbstractFacade
 * @since 2018-08-03
 */
@Stateless
public class GenreFacade extends AbstractFacade<Genre> implements GenreFacadeLocal {
    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GenreFacade() {
        super(Genre.class);
    }
}