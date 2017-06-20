/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Codigousuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface CodigousuarioFacadeLocal {

    void create(Codigousuario codigousuario);

    void edit(Codigousuario codigousuario);

    void remove(Codigousuario codigousuario);

    Codigousuario find(Object id);

    List<Codigousuario> findAll();

    List<Codigousuario> findRange(int[] range);

    int count();
    
}
