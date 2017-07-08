/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Cuestionario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jeisson Diaz
 */
@Local
public interface CuestionarioFacadeLocal {

    void create(Cuestionario cuestionario);

    void edit(Cuestionario cuestionario);

    void remove(Cuestionario cuestionario);

    Cuestionario find(Object id);

    List<Cuestionario> findAll();

    List<Cuestionario> findRange(int[] range);

    int count();
    
}
