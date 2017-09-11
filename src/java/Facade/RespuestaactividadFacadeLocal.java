/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Respuestaactividad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface RespuestaactividadFacadeLocal {

    void create(Respuestaactividad respuestaactividad);

    void edit(Respuestaactividad respuestaactividad);

    void remove(Respuestaactividad respuestaactividad);

    Respuestaactividad find(Object id);

    List<Respuestaactividad> findAll();

    List<Respuestaactividad> findRange(int[] range);
    
    List<Respuestaactividad> respuestasActividadObtenidas(int id);

    int count();
    
}
