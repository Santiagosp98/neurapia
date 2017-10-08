package ControllersGraficos;

import Facade.CitamedicaFacadeLocal;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "chartCita")
@ViewScoped
public class ControllerGraficaCitaMedica implements Serializable {
    private String monthString;
    private String quantityString;
    private String officeString;
    private String physiotherapistString;

    private List<Data> monthsList;
    private List<Data> quantityList;
    private List<Data> officeList;
    private List<Data> physiotherapistList;

    public ControllerGraficaCitaMedica() {
    }

    public String getMonthString() {
        return monthString;
    }

    public void setMonthString(String monthString) {
        this.monthString = monthString;
    }

    public String getQuantityString() {
        return quantityString;
    }

    public void setQuantityString(String quantityString) {
        this.quantityString = quantityString;
    }

    public String getOfficeString() {
        return officeString;
    }

    public void setOfficeString(String officeString) {
        this.officeString = officeString;
    }

    public String getPhysiotherapistString() {
        return physiotherapistString;
    }

    public void setPhysiotherapistString(String physiotherapistString) {
        this.physiotherapistString = physiotherapistString;
    }

    @EJB
    private CitamedicaFacadeLocal citaMedicaFacade;

    @PostConstruct
    public void init() {
        monthsList = new ArrayList<>();
        List<Object[]> months = citaMedicaFacade.citasMedicasPorMes();
        for (Object[] month : months) {
            monthsList.add(new Data(month[0].toString(), month[1].toString()));
        }

        quantityList = new ArrayList<>();
        quantityList.add(new Data("Canceladas", Integer.toString(citaMedicaFacade.countCitasMedicasPorEstado("Cancelada"))));
        quantityList.add(new Data("Pendientes", Integer.toString(citaMedicaFacade.countCitasMedicasPorEstado("Pendiente"))));
        quantityList.add(new Data("Realizadas", Integer.toString(citaMedicaFacade.countCitasMedicasPorEstado("Realizada"))));

        officeList = new ArrayList<>();
        List<Object[]> offices = citaMedicaFacade.citasMedicasRealizadasPorConsultorio();
        for (Object[] office : offices) {
            officeList.add(new Data(("Consultorio " + office[0].toString()), office[1].toString()));
        }

        physiotherapistList = new ArrayList<>();
        List<Object[]> physiotherapists = citaMedicaFacade.citasMedicasRealizadasPorFisioterapeuta();
        for (Object[] physiotherapist : physiotherapists) {
            physiotherapistList.add(new Data(physiotherapist[0].toString(), physiotherapist[1].toString()));
        }

        monthString = new Gson().toJson(monthsList);
        quantityString = new Gson().toJson(quantityList);
        officeString = new Gson().toJson(officeList);
        physiotherapistString = new Gson().toJson(physiotherapistList);
    }
}
