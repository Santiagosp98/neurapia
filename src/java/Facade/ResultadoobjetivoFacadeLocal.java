/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Resultadoobjetivo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface ResultadoobjetivoFacadeLocal {

    void create(Resultadoobjetivo resultadoobjetivo);

    void edit(Resultadoobjetivo resultadoobjetivo);

    void remove(Resultadoobjetivo resultadoobjetivo);

    Resultadoobjetivo find(Object id);

    List<Resultadoobjetivo> findAll();

    List<Resultadoobjetivo> findRange(int[] range);
    
    List<Resultadoobjetivo> listarResultadosObjetivosObtenidos(int id);

    int count();
    
}
