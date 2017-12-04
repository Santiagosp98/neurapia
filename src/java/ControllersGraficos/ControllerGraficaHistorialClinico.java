package ControllersGraficos;

import ControllersConfiguracionSistema.ControllerResultadoProceso;
import Facade.HistorialclinicoFacadeLocal;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

@Named(value = "chartHistorial")
@ViewScoped
public class ControllerGraficaHistorialClinico implements Serializable {
    
    @Inject
    private ControllerResultadoProceso crp;
    
    private String characteristicString;
    
    private String monthString;
    private String patientString;
    private String rhString;
    private String countryString;

    private List<Data> characteristicList;
    private List<Data> monthList;
    private List<Data> patientList;
    private List<Data> rhList;
    private List<Data> countryList;

    String[] lista = {"Estado Inicial", "Objetivo", "Resultado", "Porcentaje Cumplimiento"};

    public ControllerGraficaHistorialClinico() {
    }

    public String getCharacteristicString() {
        return characteristicString;
    }

    public void setCharacteristicString(String characteristicString) {
        this.characteristicString = characteristicString;
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
        
        
        Iterator entries = crp.getMapa().entrySet().iterator();
        while(entries.hasNext()){
            int i = 0;
            Map.Entry entry = (Map.Entry) entries.next();
            Integer key = (Integer)entry.getKey();
            Integer value = (Integer)entry.getValue();
            characteristicList.add(new Data(lista[i], value.toString()));
            i++;
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
        
        characteristicString = new Gson().toJson(characteristicList);
        monthString = new Gson().toJson(monthList);
        patientString = new Gson().toJson(patientList);
        rhString = new Gson().toJson(rhList);
        countryString = new Gson().toJson(countryList);
    }
}
