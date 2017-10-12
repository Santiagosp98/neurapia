/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import Entities.Prediagnostico;
import Entities.ReportePrediagnostico;
import Entities.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 * @author Jeisson Diaz
 */
@Stateless
public class PrediagnosticoFacade extends AbstractFacade<Prediagnostico> implements PrediagnosticoFacadeLocal {


    @PersistenceContext(unitName = "ProyectoPU")
    private EntityManager em;


    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PrediagnosticoFacade() {super(Prediagnostico.class);
    }

    @Override
    public List<Object[]> reportePred() {

        List<Object[]> listaPrediagnostico = null;
        Query q = getEntityManager().createNativeQuery("SELECT pr.idPrediagnostico, us.numeroDocumento, us.primerNombre, us.primerApellido, us.correoElectronico, pr.fecha FROM prediagnostico as pr  "
                + "INNER JOIN usuario AS us ON us.idUsuario=pr.codUsuario WHERE pr.estado='Pendiente';");
        listaPrediagnostico = q.getResultList();
        return listaPrediagnostico;
    }

    @Override
    public List<Prediagnostico> prediagnosticoPorUsuario(Usuario usuario) {
        List<Prediagnostico> preUsuario = null;
        TypedQuery<Prediagnostico> q = getEntityManager().createNamedQuery("Prediagnostico.prediagnosticoPorUsuario", Prediagnostico.class);
        q.setParameter("codUsuario", usuario);
        preUsuario = q.getResultList();
        return preUsuario;
    }

    @Override
    public List<Object[]> prediagnosticosPorMes() {
        List<Object[]> meses = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT monthname(STR_TO_DATE(month(fecha), '%m')), count(*) FROM prediagnostico WHERE TIMESTAMPDIFF(MONTH, fecha, now()) < 6 GROUP BY month(fecha);");
            meses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return meses;
    }

    @Override
    public List<Object[]> prediagnosticosPorEstado() {
        List<Object[]> estados = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT estado, count(*) FROM prediagnostico GROUP BY estado");
            estados = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return estados;
    }

    @Override
    public List<Prediagnostico> prediagnosticoPorResultado(String estado) {
        List<Prediagnostico> preDiagResultado =null;
        TypedQuery<Prediagnostico> q = getEntityManager().createNamedQuery("Prediagnostico.prediagnosticoPorResultado", Prediagnostico.class);
        q.setParameter("resultadoPrediagnostico", estado);
        preDiagResultado =q.getResultList();
        return preDiagResultado;
    }

    private List<Object[]>listaPrediagnostico;

    @Override
    public List<ReportePrediagnostico> listaReporte(String estado) {

        List<ReportePrediagnostico> listaResultadosPendientes=new ArrayList<>();
        listaPrediagnostico=reportePred();

        for (Object[] objPre: listaPrediagnostico)
        {
            ReportePrediagnostico rPrediagnostico = new ReportePrediagnostico();
            rPrediagnostico.setIdPrediagnostico(Integer.parseInt(objPre[0].toString()));
            rPrediagnostico.setNumeroDocumento(objPre[1].toString());
            rPrediagnostico.setPrimerNombre(objPre[2].toString());
            rPrediagnostico.setPrimerApellido(objPre[3].toString());
            rPrediagnostico.setCorreoElectronico(objPre[4].toString());
            rPrediagnostico.setFecha(objPre[5].toString());
            listaResultadosPendientes.add(rPrediagnostico);
        }
        return listaResultadosPendientes;
    }

    public List<Object[]> prediagnosticosPorFisioterapeuta() {
        List<Object[]> fisioterapeutas = null;

        try {
            Query query = getEntityManager().createNativeQuery("SELECT CONCAT(primerNombre, ' ', primerApellido), COUNT(*) FROM prediagnostico INNER JOIN fisioterapeuta ON prediagnostico.idFisioterapeuta = fisioterapeuta.idFisioterapeuta INNER JOIN usuario ON fisioterapeuta.CodUsuario = usuario.idUsuario GROUP BY prediagnostico.idFisioterapeuta");
            fisioterapeutas = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return fisioterapeutas;
    }
}
