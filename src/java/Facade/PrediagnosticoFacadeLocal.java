/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Prediagnostico;
import Entities.ReportePrediagnostico;
import Entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jeisson Diaz
 */
@Local
public interface PrediagnosticoFacadeLocal {

    void create(Prediagnostico prediagnostico);

    void edit(Prediagnostico prediagnostico);

    void remove(Prediagnostico prediagnostico);

    Prediagnostico find(Object id);

    List<Prediagnostico> findAll();

    List<Prediagnostico> findRange(int[] range);
    
    List<Prediagnostico> prediagnosticoPorUsuario (Usuario codUsuario);

    List<Prediagnostico> prediagnosticoPorResultado (String estado);

    List<ReportePrediagnostico> listaReporte (String estado);

    List<Object[]> reportePred();

    int count();

    List<Object[]> prediagnosticosPorMes();

    List<Object[]> prediagnosticosPorEstado();

    List<Object[]> prediagnosticosPorFisioterapeuta();
}
