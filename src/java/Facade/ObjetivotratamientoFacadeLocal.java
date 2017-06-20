/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Objetivotratamiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface ObjetivotratamientoFacadeLocal {

    void create(Objetivotratamiento objetivotratamiento);

    void edit(Objetivotratamiento objetivotratamiento);

    void remove(Objetivotratamiento objetivotratamiento);

    Objetivotratamiento find(Object id);

    List<Objetivotratamiento> findAll();

    List<Objetivotratamiento> findRange(int[] range);

    int count();
    
}
