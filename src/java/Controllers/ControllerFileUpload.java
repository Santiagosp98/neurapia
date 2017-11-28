/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Usuario;
import Facade.UsuarioFacadeLocal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import util.FileBean;
/**
 *
 * @author jair3
 */
@Named(value = "controllerFileUpload")
@ViewScoped
public class ControllerFileUpload implements Serializable {

    private final static String UPLOAD_DIR = "/files/uploads/";
    
    private Part file;
    private String nombre;
    private String pathReal;
    @EJB
    private UsuarioFacadeLocal ufl;
    private String img = "";
    @Inject ControllerSession cS;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
        
    /**
     * Creates a new instance of FileUploadController
     */
    public ControllerFileUpload() {
    }

    @PostConstruct
    public void init() {

    }

    public void upload() {
        try {
            System.out.println("Usuario: " + cS.getUsuario().getFullNameUsuario());
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            List<FileBean> filesBeans = getFilesUpload(ec);
            
            for (FileBean fileBean : filesBeans) {
                savePart(ec, fileBean);
                System.out.println("Datos: " + fileBean.toString());
                img = fileBean.getFileNameFull();
                cS.getUsuario().setImagen(img);
                ufl.edit(cS.getUsuario());
                System.out.println("Archivo: " + cS.getUsuario().getImagen());
            }
            deleteFile(ec, "Tablero y TV.c4d");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ServletException ex) {
            ex.printStackTrace();
        }
    }

    private List<FileBean> getFilesUpload(ExternalContext ec) throws IOException, ServletException {
        List<FileBean> files = new ArrayList();
        Collection<Part> parts = getParts(ec);
        for (Part part : parts) {
            if (part.getSize() > 0 && part.getSubmittedFileName() != null) {
                files.add(new FileBean(part.getName(), part.getContentType(), part.getSize(), part));
            }
        }
        return files;
    }

    private Collection<Part> getParts(ExternalContext ec) throws IOException, ServletException {
        HttpServletRequest rq = (HttpServletRequest) ec.getRequest();
        return rq.getParts();
    }

    private void savePart(ExternalContext ec, FileBean fileBean) throws IOException {
        String path = ec.getRealPath("");
        System.out.println("el path es: " + path);
//        path = path.substring(0, path.indexOf("\build"));
//        System.out.println("Nuevo path: " + path);
        File dir = new File(path + UPLOAD_DIR);
        FacesContext fc = FacesContext.getCurrentInstance();
        dir.mkdirs();
        File file = new File(dir, fileBean.getFileNameFull());
        file.createNewFile();
        System.out.println("Faces Context: " + fc.getExternalContext().getRealPath(""));
        System.out.println("archivo guardado en: " + file.getAbsolutePath());
        FileOutputStream outputStream = new FileOutputStream(file);
        InputStream inputStream = fileBean.getPart().getInputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, length);
        }
        outputStream.close();
        inputStream.close();
    }
    
    private void deleteFile(ExternalContext ec, String name){
         File dir = new File(ec.getRealPath("") + UPLOAD_DIR);
         dir.mkdirs();
         File file = new File(dir, name);
         file.delete();
    }
}
