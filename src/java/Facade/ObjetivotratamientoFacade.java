/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Objetivotratamiento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jair3
 */
@Stateless
public class ObjetivotratamientoFacade extends AbstractFacade<Objetivotratamiento> implements ObjetivotratamientoFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivotratamientoFacade() {
        super(Objetivotratamiento.class);
    }
    
}
