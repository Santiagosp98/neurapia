/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Historialclinico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface HistorialclinicoFacadeLocal {

    void create(Historialclinico historialclinico);

    void edit(Historialclinico historialclinico);

    void remove(Historialclinico historialclinico);

    Historialclinico find(Object id);

    List<Historialclinico> findAll();

    List<Historialclinico> findRange(int[] range);

    int count();
    
}
