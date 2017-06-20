/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Cuestionarioprediagnostico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface CuestionarioprediagnosticoFacadeLocal {

    void create(Cuestionarioprediagnostico cuestionarioprediagnostico);

    void edit(Cuestionarioprediagnostico cuestionarioprediagnostico);

    void remove(Cuestionarioprediagnostico cuestionarioprediagnostico);

    Cuestionarioprediagnostico find(Object id);

    List<Cuestionarioprediagnostico> findAll();

    List<Cuestionarioprediagnostico> findRange(int[] range);

    int count();
    
}
