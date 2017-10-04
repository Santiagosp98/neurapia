/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Caracteristicamovilidad;
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
public class CaracteristicamovilidadFacade extends AbstractFacade<Caracteristicamovilidad> implements CaracteristicamovilidadFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaracteristicamovilidadFacade() {
        super(Caracteristicamovilidad.class);
    }

    @Override
    public List<Caracteristicamovilidad> listarPorParteCuerpo(String name) {
        List lista = null;
        try {
            lista = new ArrayList();
            TypedQuery<Caracteristicamovilidad> q = getEntityManager().createNamedQuery("Caracteristicamovilidad.listarPorParteCuerpo", Caracteristicamovilidad.class);
            q.setParameter("nombreParteCuerpo", name);
            lista = q.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println(e);
        }
        return lista;
    }

    
}
