package ControllersCargaDatos;

import Facade.UsuarioFacadeLocal;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Named(value = "cargaDatos")
@ViewScoped
public class ControllerCargaDatos implements Serializable {

    private Part file;
    private String path;
    private String fileName;
    private File aFile;

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @EJB
    private UsuarioFacadeLocal usuarioFacade;

    public String subir() {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("");

        if (path.contains("build")) {
            path = path.substring(0, path.indexOf("\\build"));
        } else {
            path = path.substring(0, path.indexOf("\\out"));
        }
        path += "\\web\\WEB-INF\\files\\";
        try {
            fileName = file.getSubmittedFileName();
            path += fileName;
            InputStream inputStream = file.getInputStream();
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            aFile = new File(path);
            FileOutputStream outputStream = new FileOutputStream(aFile);
            outputStream.write(data);
            inputStream.close();
            outputStream.close();
            path = path.replace("\\", "\\\\");
            setPath(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }

    public void eliminar() {
        aFile.delete();
    }
}
