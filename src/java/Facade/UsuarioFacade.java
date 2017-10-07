/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Rol;
import Entities.Usuario;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author jair3
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario iniciarSesion(String email, String clave) {
        Usuario u = null;
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.login", Usuario.class);
            q.setParameter("email", email);
            q.setParameter("claveUsuario", clave);
            u = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public List<Usuario> listaUsuariosPorRol(Rol rol) {
        List<Usuario> listaPorRol = null;
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.listaporRol", Usuario.class);
            q.setParameter("rol", rol);
            listaPorRol = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPorRol;
    }

    @Override
    public List<Usuario> listaUsuariosPorRolDoble(Rol rol, Rol rol2) {
        List<Usuario> listaPorRolDoble = null;
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.listaporRolDoble", Usuario.class);
            q.setParameter("rol", rol);
            q.setParameter("rol2", rol2);
            listaPorRolDoble = q.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listaPorRolDoble;
    }

    @Override
    public Usuario restableceContrasena(String email) {
        Usuario u = null;
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.findByCorreoElectronico", Usuario.class);
            q.setParameter("email", email);
            u = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public Usuario buscarId(int id) {
        Usuario u = null;
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.findByIdUsuario", Usuario.class);
            q.setParameter("idUsuario", id);
            u = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public Usuario buscarDocumento(String documento) {
        Usuario u = null;
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.findByNumeroDocumento", Usuario.class);
            q.setParameter("numeroDocumento", documento);
            u = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public int cantidadUsuariosPorEstado(String estado) {
        int cantidad = 0;

        try {
            TypedQuery<Long> query = getEntityManager().createNamedQuery("Usuario.countCantidadUsuariosPorEstado", Long.class);
            query.setParameter("estadoUsuario", estado);
            cantidad = query.getSingleResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidad;
    }

    @Override
    public int countCantidadUsuariosPorRol(int id) {
        int cantidad = 0;

        try {
            TypedQuery<Long> query = getEntityManager().createNamedQuery("Usuario.countCantidadUsuariosPorRol", Long.class);
            query.setParameter("idRol", id);
            cantidad = query.getSingleResult().intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return cantidad;
    }

    public Usuario buscarDocumentoEEmail(String documento, String correo) {
        Usuario u = null;
        try {
            TypedQuery<Usuario> q = getEntityManager().createNamedQuery("Usuario.DocumentoOEmail", Usuario.class);
            q.setParameter("numeroDocumento", documento);
            q.setParameter("email", correo);
            u = q.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public Usuario buscarEmail(String email) {
        return restableceContrasena(email);
    }

    @Override
    public List<Object[]> usuariosRegistradosPorMes() {
        List<Object[]> meses = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT monthname(STR_TO_DATE(month(fechaRegistro), '%m')), count(*) FROM usuario WHERE TIMESTAMPDIFF(MONTH, fechaRegistro, now()) < 6 GROUP BY month(fechaRegistro);");
            meses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return meses;
    }

    @Override
    public List<Object[]> usuariosPorTipoDocumento() {
        List<Object[]> usuarios = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT tipoDocumento, count(*) FROM usuario GROUP BY tipoDocumento;");
            usuarios = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

    @Override
    public List<Object[]> usuariosPorEstado() {
        List<Object[]> usuarios = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT estadoUsuario, count(*) FROM usuario GROUP BY estadoUsuario;");
            usuarios = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }

}
