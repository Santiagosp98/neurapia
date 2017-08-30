/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Prediagnostico;
import Entities.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jeisson Diaz
 */
@Stateless
public class PrediagnosticoFacade extends AbstractFacade<Prediagnostico> implements PrediagnosticoFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrediagnosticoFacade() {
        super(Prediagnostico.class);
    }
    
   

    @Override
    public List<Prediagnostico> prediagnosticoPorUsuario(Usuario usuario) {
         List<Prediagnostico> preUsuario = null;
        TypedQuery<Prediagnostico> q = getEntityManager().createNamedQuery("Prediagnostico.prediagnosticoPorUsuario", Prediagnostico.class);
        q.setParameter("codUsuario", usuario);
        preUsuario = q.getResultList();
        return preUsuario;    
    }
    
}
