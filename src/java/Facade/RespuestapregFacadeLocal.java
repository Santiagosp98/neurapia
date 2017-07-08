/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Respuestapreg;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Jeisson Diaz
 */
@Local
public interface RespuestapregFacadeLocal {

    void create(Respuestapreg respuestapreg);

    void edit(Respuestapreg respuestapreg);

    void remove(Respuestapreg respuestapreg);

    Respuestapreg find(Object id);

    List<Respuestapreg> findAll();

    List<Respuestapreg> findRange(int[] range);

    int count();
    
}
