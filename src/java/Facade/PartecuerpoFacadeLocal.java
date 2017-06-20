/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Partecuerpo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface PartecuerpoFacadeLocal {

    void create(Partecuerpo partecuerpo);

    void edit(Partecuerpo partecuerpo);

    void remove(Partecuerpo partecuerpo);

    Partecuerpo find(Object id);

    List<Partecuerpo> findAll();

    List<Partecuerpo> findRange(int[] range);

    int count();
    
}
