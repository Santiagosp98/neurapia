/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Resultadoobjetivo;
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
public class ResultadoobjetivoFacade extends AbstractFacade<Resultadoobjetivo> implements ResultadoobjetivoFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ResultadoobjetivoFacade() {
        super(Resultadoobjetivo.class);
    }

    @Override
    public List<Resultadoobjetivo> listarResultadosObjetivosObtenidos(int id) {
        List<Resultadoobjetivo> listaResultados = new ArrayList();
        try {
            TypedQuery<Resultadoobjetivo> q = getEntityManager().createNamedQuery("Resultadoobjetivo.resultadosObjetivo", Resultadoobjetivo.class);
            q.setParameter("id", id);
            listaResultados = q.getResultList();
            return listaResultados;
        } catch (Exception e) {
            System.out.println("Error query Res objetivo: " + e.getMessage());
        }
        return listaResultados;
    }
    
}
