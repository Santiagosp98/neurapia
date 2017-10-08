package ControllersGraficos;

import Facade.HistorialclinicoFacadeLocal;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "chartHistorial")
@ViewScoped
public class ControllerGraficaHistorialClinico implements Serializable {
    private String monthString;
    private String patientString;
    private String rhString;
    private String countryString;

    private List<Data> monthList;
    private List<Data> patientList;
    private List<Data> rhList;
    private List<Data> countryList;

    public ControllerGraficaHistorialClinico() {
    }

    public String getMonthString() {
        return monthString;
    }

    public void setMonthString(String monthString) {
        this.monthString = monthString;
    }

    public String getPatientString() {
        return patientString;
    }

    public void setPatientString(String patientString) {
        this.patientString = patientString;
    }

    public String getRhString() {
        return rhString;
    }

    public void setRhString(String rhString) {
        this.rhString = rhString;
    }

    public String getCountryString() {
        return countryString;
    }

    public void setCountryString(String countryString) {
        this.countryString = countryString;
    }

    @EJB
    private HistorialclinicoFacadeLocal historialClinicoFacade;

    @PostConstruct
    public void init() {
        monthList = new ArrayList<>();
        List<Object[]> months = historialClinicoFacade.historialesClinicosPorMes();
        for (Object[] month : months) {
            monthList.add(new Data(month[0].toString(), month[1].toString()));
        }

        patientList = new ArrayList<>();
        List<Object[]> patients = historialClinicoFacade.pacientesPorGenero();
        for (Object[] patient : patients) {
            patientList.add(new Data(patient[0].toString(), patient[1].toString()));
        }

        rhList = new ArrayList<>();
        List<Object[]> rhs = historialClinicoFacade.pacientesPorGrupoSanguineo();
        for (Object[] rh : rhs) {
            rhList.add(new Data(rh[0].toString(), rh[1].toString()));
        }

        countryList = new ArrayList<>();
        List<Object[]> countries = historialClinicoFacade.pacientesPorPais();
        for (Object[] country : countries) {
            countryList.add(new Data(country[0].toString(), country[1].toString()));
        }

        monthString = new Gson().toJson(monthList);
        patientString = new Gson().toJson(patientList);
        rhString = new Gson().toJson(rhList);
        countryString = new Gson().toJson(countryList);
    }
}
