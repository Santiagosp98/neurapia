/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Entities.Citamedica;
import Entities.Fisioterapeuta;
import Entities.Usuario;
import Facade.CitamedicaFacadeLocal;
import Facade.FisioterapeutaFacadeLocal;
import Facade.UsuarioFacadeLocal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.inject.Named;
import java.util.List;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author jair3
 */
@Named(value = "controllerCitaMedica")
@ConversationScoped
public class ControllerCitaMedica extends ControllerApp {

    /**
     * Creates a new instance of ControllerCitaMedica
     */
    public ControllerCitaMedica() {
    }

    @Inject
    private ControllerSession cs;

    @Inject
    private ControllerEmail ce;

    @EJB
    private CitamedicaFacadeLocal citaMedicaFacade;
    private Citamedica citaMedica;
    private List<Citamedica> listaCitas;
    private List<Citamedica> listaCitas1;
    private int totalCitasMedicas;
    private int citasPendientes;
    private int citasRealizadas;
    private String fechaCitaDia;
    private String fechaCitaMes;
    private String fechaCitaAño;
    private String fechaHora;

    @EJB
    private FisioterapeutaFacadeLocal fisioterapeutaFacade;
    private Fisioterapeuta fisioterapeuta;
    private List<Fisioterapeuta> listaFisioterapeutas;

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private Usuario usuario;
    private List<Usuario> listaUsuarios;

    @PostConstruct
    public void init() {
        this.citaMedica = new Citamedica();
        this.fisioterapeuta = new Fisioterapeuta();
        this.usuario = new Usuario();
        this.listaCitas = citaMedicaFacade.findAll();
        this.listaFisioterapeutas = fisioterapeutaFacade.findAll();
        this.totalCitasMedicas = citaMedicaFacade.count();
        this.citasPendientes = citaMedicaFacade.countCitasMedicasPorEstado("Pendiente");
        this.citasRealizadas = citaMedicaFacade.countCitasMedicasPorEstado("Realizada");
    }

    public List<Citamedica> consultarCitaMedica() {
        Usuario uS = cs.getUsuario();
        System.out.println("rol: " + uS.getCodRol().getNombreRol());
        if (uS.getCodRol() != null) {
            if (uS.getCodRol().getNombreRol().equals("Super Administrador") || uS.getCodRol().getNombreRol().equals("Administrador")) {
                this.listaCitas = citaMedicaFacade.citasPorDobleEstado("Cancelada", "Realizada");
                return listaCitas;
            } else if (uS.getCodRol().getNombreRol().equals("Fisioterapeuta")) {
                listarCitasporFisioterapeuta();
            } else if (uS.getCodRol().getNombreRol().equals("Usuario")) {
                listarCitasporUsuario();
            }
        } else {
            System.out.println("Campo nulo en codRol");
        }
        return listaCitas;

    }

    public List<Citamedica> listarCitasporEstado() {
        Usuario uS = cs.getUsuario();
        System.out.println("Rol: " + uS.getCodRol().getNombreRol());
        if (uS.getCodRol().getIdRol().equals(1) || uS.getCodRol().getIdRol().equals(2)) {
            this.listaCitas = citaMedicaFacade.citasPendientes("Pendiente");
        } else if (uS.getCodRol().getIdRol().equals(3)) {
            fisioterapeuta = fisioterapeutaFacade.buscarPorCodUsuario(uS);
            this.listaCitas = citaMedicaFacade.citasPorFisioterapeutaEstado(fisioterapeuta, "Pendiente");
        } else if (uS.getCodRol().getIdRol().equals(4)) {
            this.listaCitas = citaMedicaFacade.citasPorUsuarioEstado(uS, "Pendiente");
        }

        return listaCitas;
    }

    //Consultas de el usuario desde rol usuario
    public List<Citamedica> listarCitasporUsuario() {
        System.out.println(listaCitas.size());
        listaCitas = citaMedicaFacade.citasPorUsuarioDobleEstado(cs.getUsuario(), "Cancelada", "Realizada");
        System.out.println(listaCitas.size());
        return listaCitas;
    }

    //Consulta de las citas por fisioterapeuta
    public List<Citamedica> listarCitasporFisioterapeuta() {
        try {
            fisioterapeuta.setCodUsuario(cs.getUsuario());
            for (Fisioterapeuta ft : listaFisioterapeutas) {
                System.out.println(ft.getCodUsuario());
                if (fisioterapeuta.getCodUsuario().equals(ft.getCodUsuario())) {
                    System.out.println("Estoy listando por fisoterapeuta");
                    fisioterapeuta.setIdFisioterapeuta(ft.getIdFisioterapeuta());
                    System.out.println(fisioterapeuta.getCodUsuario());
                    listaCitas = citaMedicaFacade.citasPorFisioterapeutaDobleEstado(fisioterapeuta, "Cancelada", "Realizada");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("No estoy en la lista de fisioterapeutas correcto");
        return listaCitas;
    }

    public List<Citamedica> listarCitasporFisioterapeutaEstado() {

        try {
            fisioterapeuta.setCodUsuario(cs.getUsuario());
            for (Fisioterapeuta ft : listaFisioterapeutas) {
                System.out.println(ft.getCodUsuario());
                if (fisioterapeuta.getCodUsuario().equals(ft.getCodUsuario())) {
                    System.out.println("Estoy listando por fisoterapeuta");
                    fisioterapeuta.setIdFisioterapeuta(ft.getIdFisioterapeuta());
                    System.out.println(fisioterapeuta.getCodUsuario());
                    listaCitas = citaMedicaFacade.citasPorFisioterapeutaEstado(fisioterapeuta, "Pendiente");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("No estoy en la lista de fisioterapeutas correcto");
        return listaCitas;
    }

    public String seleccionarCita(Citamedica citamedica) {
        iniciarConversacion();
        this.citaMedica = citamedica;
        return "ActualizarCitasMedicas?faces-redirect=true";
    }

    public String actualizarCita() {
        try {
            System.out.println("Estoy actualizando la cita");
            citaMedicaFacade.edit(citaMedica);
            System.out.println(citaMedica.getCodFisioterapeuta().getIdFisioterapeuta());
            finalizarConversacion();
            return "ConsultarCitasMedicas?faces-redirect=true";
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println(citaMedica.getCodFisioterapeuta().getIdFisioterapeuta());
        return "";

    }

    public String eliminarCita(Citamedica citamedica) {
        this.citaMedicaFacade.remove(citamedica);
        return "ConsultarCitasMedicas?faces-redirect=true";
    }

    public String cancelarCita(Citamedica citamedica) {
        try {
            if (citamedica != null) {
                citamedica.setEstado("Cancelada");
                System.out.println("Estado: " + citamedica.getEstado());
                this.citaMedicaFacade.edit(citamedica);
                return "ConsultarCitasMedicas?faces-redirect=true";
            }else{
                System.out.println("citamedica: " + citamedica);
                return "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public String crearCitaMedica() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();

            Pattern regexp;

            regexp = Pattern.compile("\\d+");

            if (!regexp.matcher(fechaHora).matches()){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Hora' solo admite carácteres numéricos.", "");
                fc.addMessage(null, message);
                return "";
            }

            if (!regexp.matcher(fechaCitaDia).matches()){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Día' solo admite carácteres numéricos.", "");
                fc.addMessage(null, message);
                return "";
            }

            if (!regexp.matcher(fechaCitaMes).matches()){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Mes' solo admite carácteres numéricos.", "");
                fc.addMessage(null, message);
                return "";
            }

            if (!regexp.matcher(fechaCitaAño).matches()){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "El campo 'Año' solo admite carácteres numéricos.", "");
                fc.addMessage(null, message);
                return "";
            }


            this.citaMedica.setEstado(String.valueOf(citaMedica.getSeleccionEstado().get(1).toString()));
            int validacionFecha = Integer.parseInt(fechaCitaMes);
            Calendar c1 = Calendar.getInstance();
            System.out.println("Año actual " + c1.get(Calendar.YEAR) + " " + c1.get(Calendar.MONTH) + " " + c1.get(Calendar.DAY_OF_MONTH));
            if (Integer.parseInt(fechaCitaAño) >= c1.get(Calendar.YEAR)) {
                if (Integer.parseInt(fechaCitaAño) > c1.get(Calendar.YEAR) || Integer.parseInt(fechaCitaMes) >= (c1.get(Calendar.MONTH) + 1)) {
                    if (Integer.parseInt(fechaCitaAño) > c1.get(Calendar.YEAR) || Integer.parseInt(fechaCitaMes) != (c1.get(Calendar.MONTH) + 1) || Integer.parseInt(fechaCitaDia) > c1.get(Calendar.DAY_OF_MONTH)) {
                        if (validacionFecha <= 12 && validacionFecha > 0) {
                            if ((validacionFecha % 2 == 1 && validacionFecha <= 7) || (validacionFecha % 2 == 0 && validacionFecha > 7)) {
                                validacionFecha = Integer.parseInt(fechaCitaDia);
                                if (validacionFecha <= 31 && validacionFecha > 0) {
                                    Calendar c2 = Calendar.getInstance();
                                    System.out.println("Hora actual: " + c2.get(Calendar.HOUR));
                                    if (Integer.parseInt(fechaHora) <= 20) {
                                        System.out.println("fecha menor a 20");
                                        if (Integer.parseInt(fechaHora) >= 8) {
                                            System.out.println("creado cita contenido");
                                            if (crearCitaContenido() == true) {
                                                return "ConsultarCitasMedicas?faces-redirect=true";
                                            } else {
                                                System.out.println("No se creo");
                                                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                        "El fisioterapeuta que selecciono ya esta ocupado en la fecha y hora digitada");
                                                fc.addMessage(null, m);
                                            }
                                        } else {
                                            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                    "Solo atendemos desde las 8 horas del dia ");
                                            fc.addMessage(null, m);
                                        }
                                    } else {
                                        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                "Atendemos hasta las 20 horas del dia");
                                        fc.addMessage(null, m);
                                    }
                                } else {
                                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                            "El dia tiene que ser mayor de 0 y menor o igual que 31 para el mes digitado");
                                    fc.addMessage(null, m);
                                }
                            } else {
                                if (validacionFecha == 2) {
                                    validacionFecha = Integer.parseInt(fechaCitaDia);
                                    if (Integer.parseInt(fechaCitaAño) % 4 == 0) {
                                        if (validacionFecha <= 29 && validacionFecha > 0) {
                                            Calendar c2 = Calendar.getInstance();
                                            System.out.println("Hora actual: " + c2.get(Calendar.HOUR));
                                            if (Integer.parseInt(fechaHora) <= 20) {
                                                System.out.println("fecha menor a 20");
                                                if (Integer.parseInt(fechaHora) >= 8) {
                                                    System.out.println("creado cita contenido");
                                                    if (crearCitaContenido() == true) {
                                                        return "ConsultarCitasMedicas?faces-redirect=true";
                                                    } else {
                                                        System.out.println("No se creo");
                                                        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                                "El fisioterapeuta que selecciono ya esta ocupado en la fecha y hora digitada");
                                                        fc.addMessage(null, m);
                                                    }
                                                } else {
                                                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                            "Solo atendemos desde las 8 horas del dia ");
                                                    fc.addMessage(null, m);
                                                }
                                            } else {
                                                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                        "Atendemos hasta las 20 horas del dia");
                                                fc.addMessage(null, m);
                                            }
                                        } else {
                                            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                    "El dia tiene que ser mayor de 0 y menor o igual que 29 para el mes febrero y año biciesto");
                                            fc.addMessage(null, m);

                                        }
                                    } else {
                                        if (validacionFecha <= 28 && validacionFecha > 0) {
                                            Calendar c2 = Calendar.getInstance();
                                            System.out.println("Hora actual: " + c2.get(Calendar.HOUR));
                                            if (Integer.parseInt(fechaHora) <= 20) {
                                                System.out.println("fecha menor a 20");
                                                if (Integer.parseInt(fechaHora) >= 8) {
                                                    System.out.println("creado cita contenido");
                                                    if (crearCitaContenido() == true) {
                                                        return "ConsultarCitasMedicas?faces-redirect=true";
                                                    } else {
                                                        System.out.println("No se creo");
                                                        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                                "El fisioterapeuta que selecciono ya esta ocupado en la fecha y hora digitada");
                                                        fc.addMessage(null, m);
                                                    }
                                                } else {
                                                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                            "Solo atendemos desde las 8 horas del dia ");
                                                    fc.addMessage(null, m);
                                                }
                                            } else {
                                                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                        "Atendemos hasta las 20 horas del dia");
                                                fc.addMessage(null, m);
                                            }
                                        } else {
                                            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                    "El dia tiene que ser mayor de 0 y menor o igual que 28 para el mes febrero y año no biciesto");
                                            fc.addMessage(null, m);
                                        }
                                    }
                                } else {
                                    validacionFecha = Integer.parseInt(fechaCitaDia);
                                    if (validacionFecha <= 30 && validacionFecha > 0) {
                                        Calendar c2 = Calendar.getInstance();
                                        System.out.println("Hora actual: " + c2.get(Calendar.HOUR));
                                        if (Integer.parseInt(fechaHora) <= 20) {
                                            System.out.println("fecha menor a 20");
                                            if (Integer.parseInt(fechaHora) >= 8) {
                                                System.out.println("creado cita contenido");
                                                if (crearCitaContenido() == true) {
                                                    return "ConsultarCitasMedicas?faces-redirect=true";
                                                } else {
                                                    System.out.println("No se creo");
                                                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                            "El fisioterapeuta que selecciono ya esta ocupado en la fecha y hora digitada");
                                                    fc.addMessage(null, m);
                                                }
                                            } else {
                                                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                        "Solo atendemos desde las 8 horas del dia ");
                                                fc.addMessage(null, m);
                                            }
                                        } else {
                                            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                    "Atendemos hasta las 20 horas del dia");
                                            fc.addMessage(null, m);
                                        }
                                    } else {
                                        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                                "El dia tiene que ser mayor de 0 y menor o igual que 30 para el mes digitado");
                                        fc.addMessage(null, m);
                                    }

                                }
                            }

                        } else {
                            FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica", "El mes tiene que ser mayor a 0 y menor o igual a 12");
                            fc.addMessage(null, m);
                        }
                    } else {
                        FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                                "La fecha que digito ya paso");
                        fc.addMessage(null, m);
                    }

                } else {
                    FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                            "La fecha digitada ya paso");
                    fc.addMessage(null, m);
                }

            } else {
                FacesMessage m = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al crear cita medica",
                        "El año no puede ser anterior al actual " + c1.get(Calendar.YEAR));
                fc.addMessage(null, m);
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }

    private boolean crearCitaContenido() {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            Fisioterapeuta f = null;
            Usuario uS = cs.getUsuario();
            System.out.println("rol :" + uS.getCodRol().getIdRol());
            if (uS.getCodRol().getIdRol() == 3) {
                f = fisioterapeutaFacade.buscarPorCodUsuario(uS);
                System.out.println("Fisioterapeuta: " + f.getFullnameFisioterapeuta());
                citaMedica.setCodFisioterapeuta(f);
            } else if (uS.getCodRol().getIdRol() == 4) {
                System.out.println("Usuario: " + uS);
                citaMedica.setCodUsuario(uS);
            }
            System.out.println("Correo fisioterapeuta: " + citaMedica.getCodFisioterapeuta().getCodUsuario().getCorreoElectronico());
            System.out.println("Correo Usuario: " + citaMedica.getCodUsuario().getCorreoElectronico());
            ce.setEmailDestinatario(citaMedica.getCodFisioterapeuta().getCodUsuario().getCorreoElectronico() + ", " + citaMedica.getCodUsuario().getCorreoElectronico());
            System.out.println("Correos a enviar: " + ce.getEmailDestinatario());
            String armarFecha;
            String armarHora;
            DateFormat formaHora = new SimpleDateFormat("HH:mm:ss");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            armarHora = fechaHora + ":00:00";
            System.out.println("hora: " + armarHora);
            citaMedica.setHora(formaHora.parse(armarHora));
            armarFecha = fechaCitaAño + "-" + fechaCitaMes + "-" + fechaCitaDia;
            System.out.println(armarFecha);
            citaMedica.setFecha(format.parse(armarFecha));
            System.out.println("fecha " + citaMedica.getFecha());
            Citamedica cm = null;
            cm = citaMedicaFacade.buscarFisioFechaYHora(citaMedica.getCodFisioterapeuta(), format.parse(armarFecha), formaHora.parse(armarHora));
            System.out.println("Cita buscada : " + cm);
            if (cm == null) {
                ce.enviarAsignacionDeCita(citaMedica);
                citaMedicaFacade.create(citaMedica);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println(e);
            return false;

        }
    }

    public CitamedicaFacadeLocal getCitaMedicaFacade() {
        return citaMedicaFacade;
    }

    public void setCitaMedicaFacadeLocal(CitamedicaFacadeLocal citaMedicaFacade) {
        this.citaMedicaFacade = citaMedicaFacade;
    }

    public Citamedica getCitaMedica() {
        return citaMedica;
    }

    public void setCitaMedica(Citamedica citaMedica) {
        this.citaMedica = citaMedica;
    }

    public List<Citamedica> getListaCitas() {
        return listaCitas;
    }

    public void setListaCitas(List<Citamedica> listaCitas) {
        this.listaCitas = listaCitas;
    }

    public FisioterapeutaFacadeLocal getFisioterapeutaFacade() {
        return fisioterapeutaFacade;
    }

    public void setFisioterapeutaFacade(FisioterapeutaFacadeLocal fisioterapeutaFacade) {
        this.fisioterapeutaFacade = fisioterapeutaFacade;
    }

    public Fisioterapeuta getFisioterapeuta() {
        return fisioterapeuta;
    }

    public void setFisioterapeuta(Fisioterapeuta fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }

    public List<Fisioterapeuta> getListaFisioterapeutas() {
        return listaFisioterapeutas;
    }

    public void setListaFisioterapeutas(List<Fisioterapeuta> listaFisioterapeutas) {
        this.listaFisioterapeutas = listaFisioterapeutas;
    }

    public UsuarioFacadeLocal getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioFacadeLocal usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public int getTotalCitasMedicas() {
        return totalCitasMedicas;
    }

    public void setTotalCitasMedicas(int totalCitasMedicas) {
        this.totalCitasMedicas = totalCitasMedicas;
    }

    public int getCitasPendientes() {
        return citasPendientes;
    }

    public void setCitasPendientes(int citasPendientes) {
        this.citasPendientes = citasPendientes;
    }

    public int getCitasRealizadas() {
        return citasRealizadas;
    }

    public void setCitasRealizadas(int citasRealizadas) {
        this.citasRealizadas = citasRealizadas;
    }

    public String getFechaCitaDia() {
        return fechaCitaDia;
    }

    public void setFechaCitaDia(String fechaCitaDia) {
        this.fechaCitaDia = fechaCitaDia;
    }

    public String getFechaCitaMes() {
        return fechaCitaMes;
    }

    public void setFechaCitaMes(String fechaCitaMes) {
        this.fechaCitaMes = fechaCitaMes;
    }

    public String getFechaCitaAño() {
        return fechaCitaAño;
    }

    public void setFechaCitaAño(String fechaCitaAño) {
        this.fechaCitaAño = fechaCitaAño;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

}
