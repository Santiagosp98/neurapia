/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Dolor;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author jair3
 */
@Stateless
public class DolorFacade extends AbstractFacade<Dolor> implements DolorFacadeLocal {

    @PersistenceContext(unitName = "NeurapiaPersistenceUnit")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DolorFacade() {
        super(Dolor.class);
    }

    @Override
    public List<Dolor> reporteDolor() {
       List<Dolor> list = null;
        try {
            Query q = getEntityManager().createNativeQuery(
                    "SELECT DISTINCT (dolor.localizacion) as localizacion, AVG(dolor.escalaNumerica) as escalaNumerica FROM dolor GROUP by dolor.localizacion ");
            list = q.getResultList();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return list;
    }
    
}
