/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Anamnesis;
import Entities.Dolor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface AnamnesisFacadeLocal {

    void create(Anamnesis anamnesis);

    void edit(Anamnesis anamnesis);

    void remove(Anamnesis anamnesis);

    Anamnesis find(Object id);

    List<Anamnesis> findAll();

    List<Anamnesis> findRange(int[] range);
    
    List<Anamnesis>  seleccionarPorHistorialClinico(int idHistorialClinico);
    
    List<Dolor>  seleccionarPorDolor(int idDolor);

    int count();
    
}
