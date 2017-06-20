/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Reportetratamiento;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface ReportetratamientoFacadeLocal {

    void create(Reportetratamiento reportetratamiento);

    void edit(Reportetratamiento reportetratamiento);

    void remove(Reportetratamiento reportetratamiento);

    Reportetratamiento find(Object id);

    List<Reportetratamiento> findAll();

    List<Reportetratamiento> findRange(int[] range);

    int count();
    
}
