/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersInfor;

import javax.ejb.Stateless;

import Controllers.ControllerJFreeChart;
import ControllersInfor.Dato;
import Entities.Usuario;
import Facade.UsuarioFacadeLocal;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author BRYAN BUITRAGO
 */
@Stateless
public class RulesGrafico {

    /**
     * Creates a new instance of RulesGrafico
     */
    public RulesGrafico() {
    } 
    
    @EJB
    private UsuarioFacadeLocal ufl;
    private List<Usuario> usuarioLista;
    
    public String getGraficoPruebaDato(){
        try {
            DefaultPieDataset dataset = getDatasetDatos(getDatosGraficoPrueba());
            JFreeChart chart = ChartFactory.createPieChart("Usuarios", dataset, true, true, Locale.ENGLISH);
            BufferedImage bufferedImage = chart.createBufferedImage(400, 400);
            //return Base64.getEncoder().encodeToString(ChartUtilities.encodeAsPNG(bufferedImage));
            return "data:image/png;base64," + new String(Base64.getEncoder().encode(ChartUtilities.encodeAsPNG(bufferedImage)));
        } catch (IOException ex) {
            Logger.getLogger(ControllerJFreeChart.class.getName()).log(Level.SEVERE, null, ex);
            return "";
        }
    }
    private List<Dato> getDatosGraficoPrueba(){
        List<Dato> datos = new ArrayList<>();
        int numeroRolSuper = ufl.countCantidadUsuariosPorRol(1);
        int numeroRolAdmin = ufl.countCantidadUsuariosPorRol(2);
        int numeroRolFisioterapeuta = ufl.countCantidadUsuariosPorRol(3);
        int numeroRolUsuario = ufl.countCantidadUsuariosPorRol(4);
        double total= numeroRolAdmin+numeroRolSuper+numeroRolFisioterapeuta+numeroRolUsuario;
        System.out.println(numeroRolSuper + "------ " + total);
        //Consulta BD y Organización
        datos.add(new Dato("Super Admin = " + String.format("%.2f" ,(numeroRolSuper/total)*100) +"%", (double) numeroRolSuper));
        datos.add(new Dato("Admin = " + String.format("%.2f" ,(numeroRolAdmin/total)*100)+"%", (double) numeroRolAdmin));
        datos.add(new Dato("Fisioterapeuta = " + String.format("%.2f" ,(numeroRolFisioterapeuta/total)*100)+"%", (double) numeroRolFisioterapeuta));
        datos.add(new Dato("Usuario = " + String.format("%.2f" ,(numeroRolUsuario/total)*100)+"%", (double) numeroRolUsuario));
        return datos;
    }
    
    private DefaultPieDataset getDatasetDatos(List<Dato> datos){
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (Dato dato : datos) {
            dataset.setValue(dato.getNombre(), dato.getValor());
        }
        return dataset;
    }
}
