/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author BRYAN BUITRAGO
 */
@Named(value = "controllerHighFaces")
@RequestScoped
public class ControllerHighFaces {

    private List<Integer> simpleList;
    /**
     * Creates a new instance of ControllerHighFaces
     */
    public ControllerHighFaces() {
        reload();
    }
    public List<Integer> getSimpleList(){
         return simpleList;
    }
    
    public void reload(){
        simpleList = new ArrayList<>();
        
        Random r = new Random();
        for (int i = 1000; i < 1010; i++) {
            simpleList.add(r.nextInt(300) + 200);
        }
    }
}
