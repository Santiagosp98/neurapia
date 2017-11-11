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
import java.util.Date;

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
    
    List<Citamedica> citasPorDobleEstado(String estado, String estado2, String estado3);
    
    List<Citamedica> citasPorUsuario(Usuario codUsuario);
    
    List<Citamedica> citasPorUsuarioEstado(Usuario codUsuario, String estado);
    
    List<Citamedica> citasPorUsuarioDobleEstado(Usuario codUsuario, String estado, String estado2, String estado3);
    
    List<Citamedica> citasPorFisioterapeuta(Fisioterapeuta codFisioterapeuta);

    List<Citamedica> citasPorFisioterapeutaEstado(Fisioterapeuta codFisioterapeuta, String estado);
    
    List<Citamedica> citasPorFisioterapeutaDobleEstado(Fisioterapeuta codFisioterapeuta, String estado, String estado2 , String estado3);

    Citamedica buscarFisioFechaYHora (Fisioterapeuta codFisioterapeuta, Date fecha, Date hora);

    int count();

    int countCitasMedicasPorEstado(String estado);

    List<Object[]> citasMedicasPorMes();

    List<Object[]> citasMedicasRealizadasPorConsultorio();

    List<Object[]> citasMedicasRealizadasPorFisioterapeuta();
    
    List<Citamedica> citasPorDosMeses(Date fecha, Date fecha1);

    void cargarCitas(String path);
}
