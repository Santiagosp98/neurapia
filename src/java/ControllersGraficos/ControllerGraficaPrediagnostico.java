package ControllersGraficos;

import Facade.PrediagnosticoFacadeLocal;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "chartPrediagnostico")
@ViewScoped
public class ControllerGraficaPrediagnostico implements Serializable {
    private String monthString;
    private String statusString;
    private String physiotherapistString;

    private List<Data> monthList;
    private List<Data> statusList;
    private List<Data> physiotherapistList;

    public String getMonthString() {
        return monthString;
    }

    public void setMonthString(String monthString) {
        this.monthString = monthString;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public String getPhysiotherapistString() {
        return physiotherapistString;
    }

    public void setPhysiotherapistString(String physiotherapistString) {
        this.physiotherapistString = physiotherapistString;
    }

    @EJB
    private PrediagnosticoFacadeLocal prediagnosticoFacade;

    @PostConstruct
    public void init() {
        monthList = new ArrayList<>();
        List<Object[]> months = prediagnosticoFacade.prediagnosticosPorMes();
        for (Object[] month : months) {
            monthList.add(new Data(month[0].toString(), month[1].toString()));
        }

        statusList = new ArrayList<>();
        List<Object[]> statuses = prediagnosticoFacade.prediagnosticosPorEstado();
        for (Object[] status :
                statuses) {
            statusList.add(new Data(status[0].toString(), status[1].toString()));
        }

        physiotherapistList = new ArrayList<>();
        List<Object[]> physiotheraperists = prediagnosticoFacade.prediagnosticosPorFisioterapeuta();
        for (Object[] physiotheraperist : physiotheraperists) {
            physiotherapistList.add(new Data(physiotheraperist[0].toString(), physiotheraperist[1].toString()));
        }

        monthString = new Gson().toJson(monthList);
        statusString = new Gson().toJson(statusList);
        physiotherapistString = new Gson().toJson(physiotherapistList);
    }
}
