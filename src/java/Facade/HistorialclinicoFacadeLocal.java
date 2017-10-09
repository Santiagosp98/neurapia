/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Historialclinico;
import Entities.ReporteHistorialClinico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface HistorialclinicoFacadeLocal {

    void create(Historialclinico historialclinico);

    void edit(Historialclinico historialclinico);

    void remove(Historialclinico historialclinico);

    Historialclinico find(Object id);

    List<Historialclinico> findAll();

    List<Historialclinico> findRange(int[] range);
    
    List<ReporteHistorialClinico>  generarListaResultados();
    
    List<Object[]> reporteHc();
    

    int count();

    List<Object[]> historialesClinicosPorMes();

    List<Object[]> pacientesPorGenero();

    List<Object[]> pacientesPorGrupoSanguineo();

    List<Object[]> pacientesPorPais();
}
