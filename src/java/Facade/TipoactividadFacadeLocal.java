/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Tipoactividad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface TipoactividadFacadeLocal {

    void create(Tipoactividad tipoactividad);

    void edit(Tipoactividad tipoactividad);

    void remove(Tipoactividad tipoactividad);

    Tipoactividad find(Object id);

    List<Tipoactividad> findAll();

    List<Tipoactividad> findRange(int[] range);

    int count();
    
}
