/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Anamnesis;
import Entities.Dolor;
import Entities.Usuario;
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
public class AnamnesisFacade extends AbstractFacade<Anamnesis> implements AnamnesisFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AnamnesisFacade() {
        super(Anamnesis.class);
    }

    @Override
    public List<Anamnesis> seleccionarPorHistorialClinico(int idHistorialClinico) {
        List<Anamnesis> listaAnamnesis = new ArrayList();
        try {
            TypedQuery<Anamnesis> q = getEntityManager().createNamedQuery("Anamnesis.historialClinico", Anamnesis.class);
            q.setParameter("idHistorialClinico", idHistorialClinico);
            listaAnamnesis = q.getResultList();
        } catch (Exception e) {
            System.out.println("error query: " + e.getMessage());
        }
        
        return listaAnamnesis;
    }
    
    @Override
    public List<Dolor> seleccionarPorDolor(int idDolor) {
        List<Dolor> listaDolor = new ArrayList();
        try {
            TypedQuery<Dolor> q = getEntityManager().createNamedQuery("Anamnesis.historialClinico", Dolor.class);
            q.setParameter("idDolor", idDolor);
            listaDolor = q.getResultList();
        } catch (Exception e) {
            System.out.println("error query: " + e.getMessage());
        }        
        return listaDolor;
    }
    
    
    
}
