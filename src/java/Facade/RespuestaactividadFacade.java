/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Respuestaactividad;
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
public class RespuestaactividadFacade extends AbstractFacade<Respuestaactividad> implements RespuestaactividadFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RespuestaactividadFacade() {
        super(Respuestaactividad.class);
    }

    @Override
    public List<Respuestaactividad> respuestasActividadObtenidas(int id) {
        List<Respuestaactividad> respActlist = new ArrayList();
        try {
            TypedQuery<Respuestaactividad> q = getEntityManager().createNamedQuery("Respuestaactividad.respActObtenidas", Respuestaactividad.class);
            q.setParameter("id", id);
            respActlist = q.getResultList();
            return respActlist;
        } catch (Exception e) {
            System.out.println("Error Query: " + e);
        }
        return respActlist;
    }
    
}
