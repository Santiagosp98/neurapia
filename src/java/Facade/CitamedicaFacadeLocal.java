/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Citamedica;
import Entities.Fisioterapeuta;
import Entities.Usuario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface CitamedicaFacadeLocal {

    void create(Citamedica citamedica);

    void edit(Citamedica citamedica);

    void remove(Citamedica citamedica);

    Citamedica find(Object id);

    List<Citamedica> findAll();

    List<Citamedica> findRange(int[] range);
    
    List<Citamedica> citasPendientes(String estado);
    
    List<Citamedica> citasPorUsuario(Usuario codUsuario);
    
    List<Citamedica> citasPorFisioterapeuta(Fisioterapeuta codFisioterapeuta);

    List<Citamedica> citasPorFisioterapeutaEstado(Fisioterapeuta codFisioterapeuta, String estado);

    int count();

    int countCitasMedicasPorEstado(String estado);
    
}
