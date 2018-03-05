/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Resultadoproceso;
import java.util.ArrayList;
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
public class ResultadoprocesoFacade extends AbstractFacade<Resultadoproceso> implements ResultadoprocesoFacadeLocal {

    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultadoprocesoFacade() {
        super(Resultadoproceso.class);
    }

    @Override
    public List<Resultadoproceso> listarResultadosProcesosObtenidos(int id) {
        List<Resultadoproceso> resultados = new ArrayList();
        try {
            TypedQuery<Resultadoproceso> q = getEntityManager().createNamedQuery("Resultadoproceso.resultadosObtenidos", Resultadoproceso.class);
            q.setParameter("id", id);
            resultados = q.getResultList();
            return resultados;
        } catch (Exception e) {
            System.out.println("Error Query Res proceso: " + e.getMessage());
        }
        return resultados;
        
    }
    
    
    
}
