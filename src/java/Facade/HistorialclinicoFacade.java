/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Historialclinico;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 *
 * @author jair3
 */
@Stateless
public class HistorialclinicoFacade extends AbstractFacade<Historialclinico> implements HistorialclinicoFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HistorialclinicoFacade() {
        super(Historialclinico.class);
    }

    @Override
    public List<Object[]> historialesClinicosPorMes() {
        List<Object[]> meses = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT monthname(STR_TO_DATE(month(fechaCreacion), '%m')), count(*) FROM historialclinico WHERE TIMESTAMPDIFF(MONTH, fechaCreacion, now()) < 6 GROUP BY month(fechaCreacion);");
            meses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return meses;
    }

    @Override
    public List<Object[]> pacientesPorGenero() {
        List<Object[]> pacientes = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT sexo, count(*) FROM historialclinico GROUP BY sexo;");
            pacientes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    @Override
    public List<Object[]> pacientesPorGrupoSanguineo() {
        List<Object[]> pacientes = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT grupoSanguineo, count(*) FROM historialclinico GROUP BY grupoSanguineo;");
            pacientes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    @Override
    public List<Object[]> pacientesPorPais() {
        List<Object[]> pacientes = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT pais, count(*) FROM historialclinico GROUP BY pais;");
            pacientes = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pacientes;
    }
    
}
