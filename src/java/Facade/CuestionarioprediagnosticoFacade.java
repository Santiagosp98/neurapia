/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Cuestionarioprediagnostico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jair3
 */
@Stateless
public class CuestionarioprediagnosticoFacade extends AbstractFacade<Cuestionarioprediagnostico> implements CuestionarioprediagnosticoFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CuestionarioprediagnosticoFacade() {
        super(Cuestionarioprediagnostico.class);
    }
    
}
