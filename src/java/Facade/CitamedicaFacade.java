/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Citamedica;
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
public class CitamedicaFacade extends AbstractFacade<Citamedica> implements CitamedicaFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CitamedicaFacade() {
        super(Citamedica.class);
    }
    
    public List<Citamedica> citasPendientes(String estado){
        estado = "Pendiente";
        List<Citamedica> cita = null;
        TypedQuery<Citamedica> q = getEntityManager().createNamedQuery("Citamedica.findByTipoEstado", Citamedica.class);
        q.setParameter("estado", estado);
        cita = q.getResultList();
        return cita;
    }
    
}
