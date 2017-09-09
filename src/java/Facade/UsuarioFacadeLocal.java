/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Usuario;
import Entities.Rol;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author jair3
 */
@Local
public interface UsuarioFacadeLocal {

    void create(Usuario usuario);

    void edit(Usuario usuario);

    void remove(Usuario usuario);

    Usuario find(Object id);

    List<Usuario> findAll();

    List<Usuario> findRange(int[] range);

    int count();
    
    Usuario iniciarSesion(String email, String clave);

    Usuario restableceContrasena(String email);

    Usuario buscarId(int id);

    Usuario buscarDocumento(String documento);
    
    Usuario buscarDocumentoEEmail(String documento, String correo);
    
    List<Usuario> listaUsuariosPorRol(Rol rol);

    List<Usuario> listaUsuariosPorRolDoble(Rol rol, Rol rol2);

    int cantidadUsuariosPorEstado(String estado);

    int countCantidadUsuariosPorRol(int id);
}
