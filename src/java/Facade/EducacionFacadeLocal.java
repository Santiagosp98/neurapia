/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Educacion;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface EducacionFacadeLocal {

    void create(Educacion educacion);

    void edit(Educacion educacion);

    void remove(Educacion educacion);

    Educacion find(Object id);

    List<Educacion> findAll();

    List<Educacion> findRange(int[] range);

    int count();
    
}
