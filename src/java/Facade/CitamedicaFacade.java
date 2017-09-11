/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Citamedica;
import Entities.Fisioterapeuta;
import Entities.Usuario;
import java.util.Date;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
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

    @Override
    public List<Citamedica> citasPendientes(String estado) {
        List<Citamedica> cita = null;
        TypedQuery<Citamedica> q = getEntityManager().createNamedQuery("Citamedica.findByEstado", Citamedica.class);
        q.setParameter("estado", estado);
        cita = q.getResultList();
        return cita;
    }

    //
    @Override
    public List<Citamedica> citasPorUsuario(Usuario usuario) {
        List<Citamedica> cita = null;
        TypedQuery<Citamedica> q = getEntityManager().createNamedQuery("Citamedica.citasPorUsuario", Citamedica.class);
        q.setParameter("codUsuario", usuario);
        cita = q.getResultList();
        return cita;
    }

    @Override
    public List<Citamedica> citasPorFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        List<Citamedica> cita = null;
        TypedQuery<Citamedica> q = getEntityManager().createNamedQuery("Citamedica.citasPorFisioterapeuta", Citamedica.class);
        q.setParameter("codFisioterapeuta", fisioterapeuta);
        cita = q.getResultList();
        return cita;
    }

    @Override
    public List<Citamedica> citasPorFisioterapeutaEstado(Fisioterapeuta fisioterapeuta, String estado) {
        List<Citamedica> cita = null;
        TypedQuery<Citamedica> q = getEntityManager().createNamedQuery("Citamedica.citasPorFisioterapeutaEstado", Citamedica.class);
        q.setParameter("codFisioterapeuta", fisioterapeuta);
        q.setParameter("estado", estado);
        cita = q.getResultList();
        return cita;
    }

    @Override
    public int countCitasMedicasPorEstado(String estado) {
        int cantidad = 0;

        try {
            TypedQuery<Long> query = getEntityManager().createNamedQuery("Citamedica.countByEstado", Long.class);
            query.setParameter("estado", estado);
            cantidad = query.getSingleResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidad;
    }

    @Override
    public Citamedica buscarFisioFechaYHora(Fisioterapeuta codFisioterapeuta, Date fecha, Date hora) {
        Citamedica c= null;
        try {
            TypedQuery<Citamedica> q = getEntityManager().createNamedQuery("Citamedica.citaPorFisioFechaHora", Citamedica.class);
            q.setParameter("codFisioterapeuta", codFisioterapeuta);
            q.setParameter("fecha", fecha);
            q.setParameter("hora", hora);
            c = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

}
