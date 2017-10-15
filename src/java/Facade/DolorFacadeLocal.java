/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Dolor;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface DolorFacadeLocal {

    void create(Dolor dolor);

    void edit(Dolor dolor);

    void remove(Dolor dolor);

    Dolor find(Object id);

    List<Dolor> findAll();

    List<Dolor> findRange(int[] range);
    
    List<Dolor> reporteDolor();

    int count();
    
}
