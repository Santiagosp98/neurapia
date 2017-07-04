/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;

/**
 *
 * @author jair3
 */
@Named(value = "controllerMovilidad")
@ConversationScoped
public class ControllerMovilidad implements Serializable {

    /**
     * Creates a new instance of ControllerMovilidad
     */

    public ControllerMovilidad() {
    }
    
}
