/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.ResultadoFinal;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface ResultadoFinalFacadeLocal {

    void create(ResultadoFinal resultadoFinal);

    void edit(ResultadoFinal resultadoFinal);

    void remove(ResultadoFinal resultadoFinal);

    ResultadoFinal find(Object id);

    List<ResultadoFinal> findAll();

    List<ResultadoFinal> findRange(int[] range);
    
    List<ResultadoFinal> consultarResultadosPorAnamnesis(int id);

    int count();
    
}
