/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Faq;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface FaqFacadeLocal {

    void create(Faq faq);

    void edit(Faq faq);

    void remove(Faq faq);

    Faq find(Object id);

    List<Faq> findAll();

    List<Faq> findRange(int[] range);

    int count();
    
}
