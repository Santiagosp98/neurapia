/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Caracteristicamovilidad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface CaracteristicamovilidadFacadeLocal {

    void create(Caracteristicamovilidad caracteristicamovilidad);

    void edit(Caracteristicamovilidad caracteristicamovilidad);

    void remove(Caracteristicamovilidad caracteristicamovilidad);

    Caracteristicamovilidad find(Object id);

    List<Caracteristicamovilidad> findAll();

    List<Caracteristicamovilidad> findRange(int[] range);

    int count();
    
}
