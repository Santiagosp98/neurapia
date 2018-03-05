/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.ResultadoFinal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author jair3
 */
@Stateless
public class ResultadoFinalFacade extends AbstractFacade<ResultadoFinal> implements ResultadoFinalFacadeLocal {

    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultadoFinalFacade() {
        super(ResultadoFinal.class);
    }

    @Override
    public List<ResultadoFinal> consultarResultadosPorAnamnesis(int id) {
        List<ResultadoFinal> lista = null;
        try {
            TypedQuery q = getEntityManager().createNamedQuery("ResultadoFinal.findByAnamnesis", ResultadoFinal.class);
            q.setParameter("codAnamnesis", id);
            lista = q.getResultList();
        } catch (Exception e) {
            System.out.println( e.getMessage());;
        }
        return lista;
    }
    
    
}
