/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControllersInfor;

/**
 *
 * @author Bryan
 */
public class Dato {
    
    private String nombre;
    private Double valor;
    private Double valor1;

    public Dato() {
    }

    public Dato(String nombre, Double valor) {
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
    
}
