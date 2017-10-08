package ControllersGraficos;

import Facade.UsuarioFacadeLocal;
import com.google.gson.Gson;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named (value = "chartUsuario")
@ViewScoped
public class ControllerGraficaUsuario implements Serializable {

    private String dataString;
    private String monthString;
    private String documentString;
    private String statusString;
    private List<Data> dataList;
    private List<Data> monthsList;
    private List<Data> documentList;
    private List<Data> statusList;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    public ControllerGraficaUsuario() {}

    public String getDataString() {
        return dataString;
    }

    public void setDataString(String dataString) {
        this.dataString = dataString;
    }

    public String getMonthString() {
        return monthString;
    }

    public void setMonthString(String monthString) {
        this.monthString = monthString;
    }

    public String getDocumentString() {
        return documentString;
    }

    public void setDocumentString(String documentString) {
        this.documentString = documentString;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    @PostConstruct
    public void init() {
        dataList = new ArrayList<>();
        dataList.add(new Data("Super administrador", Integer.toString( usuarioFacade.countCantidadUsuariosPorRol(1))));
        dataList.add(new Data("Administrador", Integer.toString( usuarioFacade.countCantidadUsuariosPorRol(2))));
        dataList.add(new Data("Fisioterapeuta", Integer.toString(usuarioFacade.countCantidadUsuariosPorRol(3))));
        dataList.add(new Data("Usuario", Integer.toString(usuarioFacade.countCantidadUsuariosPorRol(4))));

        monthsList = new ArrayList<>();
        List<Object[]> months = usuarioFacade.usuariosRegistradosPorMes();

        for (Object[] month : months) {
            monthsList.add(new Data(month[0].toString(),month[1].toString()));
        }

        documentList = new ArrayList<>();
        List<Object[]> documents = usuarioFacade.usuariosPorTipoDocumento();

        for (Object[] document : documents) {
            documentList.add(new Data(document[0].toString(), document[1].toString()));
        }

        statusList = new ArrayList<>();
        List<Object[]> statuses = usuarioFacade.usuariosPorEstado();

        for (Object[] status : statuses) {
            statusList.add(new Data(status[0].toString(), status[1].toString()));
        }

        dataString = new Gson().toJson(dataList);
        monthString = new Gson().toJson(monthsList);
        documentString = new Gson().toJson(documentList);
        statusString = new Gson().toJson(statusList);
    }

}
