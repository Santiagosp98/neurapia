/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Citamedica;
import Entities.Usuario;
import Facade.CitamedicaFacadeLocal;
import Facade.UsuarioFacadeLocal;
import java.io.File;
import java.util.List;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author BRYAN BUITRAGO
 */
@Named(value = "controllerEmail")
@SessionScoped
public class ControllerEmail implements Serializable {

    /**
     * Creates a new instance of ControllerEmail
     */
    private String emailRemitente;
    private String passRemitente;
    private String emailDestinatario;
    private String script;
    private String nuevaContraseña;
    private Session session;
    private MimeMessage mimeMessage;

    @EJB
    private UsuarioFacadeLocal ufl;
    private Usuario usuario;
    
    @EJB 
    private CitamedicaFacadeLocal citaMedicaFacade;
    private Citamedica citaMedica;

    public ControllerEmail() {
    }
    public String getScript() {
        
        return script;
    }

    public ControllerEmail(String emailRemitente, String passRemitente, String emailDestinatario) {
        this.emailRemitente = emailRemitente;
        this.passRemitente = passRemitente;
        this.emailDestinatario = emailDestinatario;
    }

    private void init() {
        try {
            Properties propiedades = new Properties();

            propiedades.setProperty("mail.smtp.host", "smtp.gmail.com");
            propiedades.setProperty("mail.smtp.starttls.enable", "true");
            propiedades.setProperty("mail.smtp.port", "25");//587-25
            propiedades.setProperty("mail.smtp.user", this.emailRemitente);
            propiedades.setProperty("mail.smtp.auth", "true");

            session = Session.getDefaultInstance(propiedades);
            mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(emailRemitente));
            mimeMessage.setRecipients(Message.RecipientType.TO, emailDestinatario);
        } catch (MessagingException ex) {
            Logger.getLogger(ControllerEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void asignacionCorreos() throws Exception {
        System.out.println("Ingresamos a enviar correo" + emailDestinatario);
        
            if (emailDestinatario != null && !emailDestinatario.equals("")) {
                usuario = ufl.restableceContrasena(emailDestinatario);
                if (usuario != null) {
                    
                    System.out.println("email es:" + emailDestinatario);
                    System.out.println("contraseña es: " + usuario.getClaveUsuario());
                    System.out.println("Documento: "+ usuario.getNumeroDocumento());
                    usuario.setClaveUsuario(usuario.getNumeroDocumento());
                    System.out.println("contraseña es: " + usuario.getClaveUsuario());
                    ufl.edit(usuario);
                    nuevaContraseña=usuario.getClaveUsuario();
                    ControllerEmail email = new ControllerEmail("neurapiaj3s@gmail.com", "neurapia12345", emailDestinatario);
                    email.enviarSimple("Nueva contraseña NEURAPIA", "Tu nueva contraseña es: " + nuevaContraseña);
                } else {
                    System.out.println("Email no esta en base de datos");
                    throw new Exception("Correo invalido");
                }

            } else {
                
                System.out.println("Email es nulo o vacio");
            }
        
    }
    
    public void enviarAsignacionDeCita(Citamedica cita){
        if (emailDestinatario != null && !emailDestinatario.equals("")) {
            System.out.println("Vamos a enviar asignacion de cita");
            java.util.Date fecha1 = cita.getFecha();
            java.util.Date fecha2 = cita.getHora();
            long lnMilisegundos = fecha1.getTime();
            long hora = fecha2.getTime();
            java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
            java.sql.Time sqlTime = new java.sql.Time(hora);
            
            System.out.println(" Fecha con util date: "+ sqlDate + " hora con date: "+ sqlTime
            +" fecha total: "+ cita.getFecha() + " Hora total: " + cita.getHora());
            ControllerEmail email = new ControllerEmail("neurapiaj3s@gmail.com", "neurapia12345", emailDestinatario);
            email.enviarSimple("Asignacion de cita Neurapia", "Se ha asignado una cita medica con los siguientes datos:" + 
                    "\nNombre del fisioterapeuta que atendera la cita: "+cita.getFullNameFisioterapeuta()
                    +"\nNombre del paciente de la cita: " + cita.getFullNameUsuario()
                    +"\nFecha de la cita: " + sqlDate
                    +"\nHora de la cita: " + sqlTime
                    +"\nConsultorio: " + cita.getNumeroConsultorio());
        }else{
            System.out.println("Email es nulo");
        }

    }
    public boolean enviarSimple(String asunto, String contenido) {
        try {
            if (asunto != null && contenido != null) {

                init();

                Multipart contenidoMensaje = new MimeMultipart();
                BodyPart text = new MimeBodyPart();
                text.setText(contenido);
                contenidoMensaje.addBodyPart(text);

                mimeMessage.setSubject(asunto);
                mimeMessage.setContent(contenidoMensaje);

                Transport transport = session.getTransport("smtp");
                transport.connect(emailRemitente, passRemitente);
                transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
                transport.close();
                emailDestinatario = "";
                return true;
            } else {
                System.out.println("Falso");
                return false;
            }

        } catch (MessagingException ex) {
            Logger.getLogger(ControllerEmail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public String getEmailRemitente() {
        return emailRemitente;
    }

    public void setEmailRemitente(String emailRemitente) {
        this.emailRemitente = emailRemitente;
    }

    public String getPassRemitente() {
        return passRemitente;
    }

    public void setPassRemitente(String passRemitente) {
        this.passRemitente = passRemitente;
    }

    public String getEmailDestinatario() {
        return emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

}
