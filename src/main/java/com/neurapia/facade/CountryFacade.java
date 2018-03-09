package com.neurapia.facade;

import com.neurapia.entities.Country;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Santiago Samper
 * @version %I%, %G%
 * @see Country
 * @see CountryFacadeLocal
 * @see AbstractFacade
 * @since 2018-08-03
 */
@Stateless
public class CountryFacade extends AbstractFacade<Country> implements CountryFacadeLocal {
    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CountryFacade() {
        super(Country.class);
    }
}