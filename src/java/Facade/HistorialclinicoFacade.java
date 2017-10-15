/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Historialclinico;
import Entities.ReporteHistorialClinico;
import java.util.ArrayList;
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
public class HistorialclinicoFacade extends AbstractFacade<Historialclinico> implements HistorialclinicoFacadeLocal {

    public HistorialclinicoFacade() {
        super(Historialclinico.class);
    }
    
    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }    
    
    @Override
    public List<Object[]> reporteHc(){
        List<Object[]> list = null;
        Query q = getEntityManager().createNativeQuery("SELECT u.primerNombre, u.primerApellido, eps.nombreEps, h.ocupacion, d.localizacion, d.frecuencia FROM usuario u "
                                                        + "INNER JOIN historialclinico h on u.idUsuario = h.codUsuario "
                                                        + "INNER JOIN anamnesis a on a.codHistorialClinico = h.idHistorialClinico "
                                                        + "INNER JOIN eps on eps.idEps = h.codEps "
                                                        + "INNER JOIN dolor d on d.idDolor = a.codDolor;");
        list = q.getResultList();
        
        return list;
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
    
    private List<Object[]>lista;
    
    @Override
    public List<ReporteHistorialClinico>  generarListaResultados(){
        
        List<ReporteHistorialClinico> listaResultados = new ArrayList();
        lista = reporteHc();
        
        for (Object[] o : lista) {
            ReporteHistorialClinico reporte = new ReporteHistorialClinico();
            reporte.setPrimerNombre(o[0].toString());
            reporte.setPrimerApellido(o[1].toString());
            reporte.setNombreEps(o[2].toString());
            reporte.setOcupacion(o[3].toString());
            reporte.setLocalizacion(o[4].toString());
            reporte.setFrecuencia(o[5].toString());
            listaResultados.add(reporte);
        }   
        return listaResultados;
    }
}
