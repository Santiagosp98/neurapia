/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Fisioterapeuta;
import Entities.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


/**
 *
 * @author jair3
 */
@Stateless
public class FisioterapeutaFacade extends AbstractFacade<Fisioterapeuta> implements FisioterapeutaFacadeLocal {

    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FisioterapeutaFacade() {
        super(Fisioterapeuta.class);
    }

    @Override
    public Fisioterapeuta buscarPorCodUsuario(Usuario codUsuario) {
        Fisioterapeuta fisioterapeuta = null; 
        try {
            TypedQuery<Fisioterapeuta> q = getEntityManager().createNamedQuery("Fisioterapeuta.buscarPorCodUsuario", Fisioterapeuta.class);
            q.setParameter("codUsuario", codUsuario);
            fisioterapeuta = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fisioterapeuta;
    }
    
}
