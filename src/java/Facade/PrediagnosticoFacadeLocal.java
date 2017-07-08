/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Prediagnostico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jeisson Diaz
 */
@Local
public interface PrediagnosticoFacadeLocal {

    void create(Prediagnostico prediagnostico);

    void edit(Prediagnostico prediagnostico);

    void remove(Prediagnostico prediagnostico);

    Prediagnostico find(Object id);

    List<Prediagnostico> findAll();

    List<Prediagnostico> findRange(int[] range);

    int count();
    
}
