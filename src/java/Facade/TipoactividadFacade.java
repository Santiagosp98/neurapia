/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Tipoactividad;
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
public class TipoactividadFacade extends AbstractFacade<Tipoactividad> implements TipoactividadFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoactividadFacade() {
        super(Tipoactividad.class);
    }
    
    @Override
    public List<Tipoactividad> listaporCodActividad(Tipoactividad codActividad){
        List<Tipoactividad> lista = null;
        try {            
            TypedQuery<Tipoactividad> q = getEntityManager().createNamedQuery("Tipoactividad.findBycodActividad", Tipoactividad.class);
            q.setParameter("codActividad", codActividad);
            lista = q.getResultList();
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }
    
}
