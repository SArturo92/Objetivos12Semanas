/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.vista12semanas;

import modelo.ConfigManager;
import vista.FrmInicio;
import util.FXBootstrap;

/**
 *
 * @author Sergio Arturo
 */
public class Vista12Semanas {

    public static void main(String[] args) {
        
        ConfigManager.load();        

        FXBootstrap.init();
        
        FrmInicio inicio = new FrmInicio();
        inicio.setVisible(true);
    }
    

    
    
}
