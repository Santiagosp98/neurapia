/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Resultadoproceso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface ResultadoprocesoFacadeLocal {

    void create(Resultadoproceso resultadoproceso);

    void edit(Resultadoproceso resultadoproceso);

    void remove(Resultadoproceso resultadoproceso);

    Resultadoproceso find(Object id);

    List<Resultadoproceso> findAll();

    List<Resultadoproceso> findRange(int[] range);
    
    List<Resultadoproceso> listarResultadosProcesosObtenidos(int id);

    int count();
    
}
