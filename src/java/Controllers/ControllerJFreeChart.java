///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package Controllers;
//
//import ControllersInfor.RulesGrafico;
//import javax.annotation.PostConstruct;
//import javax.ejb.EJB;
//import javax.inject.Named;
//import javax.enterprise.context.RequestScoped;
//
///**
// *
// * @author BRYAN BUITRAGO
// */
//@Named(value = "controllerJFreeChart")
//@RequestScoped
//public class ControllerJFreeChart {
//
//    @EJB
//    private RulesGrafico rG;
//    /**
//     * Creates a new instance of ControllerJFreeChart
//     */
//    public ControllerJFreeChart() {
//    }
//    
//    @PostConstruct
//    public void init(){
//        
//    }
//    
//    public String getGrafico(){
//        return rG.getGraficoPruebaDato();
//    }
//    
//}
