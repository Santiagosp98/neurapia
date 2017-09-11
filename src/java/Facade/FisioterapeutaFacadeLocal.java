/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Fisioterapeuta;
import Entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface FisioterapeutaFacadeLocal {

    void create(Fisioterapeuta fisioterapeuta);

    void edit(Fisioterapeuta fisioterapeuta);

    void remove(Fisioterapeuta fisioterapeuta);

    Fisioterapeuta find(Object id);

    List<Fisioterapeuta> findAll();

    List<Fisioterapeuta> findRange(int[] range);
    
    int count();
    
    Fisioterapeuta buscarPorCodUsuario(Usuario codUsuario);
    
}
