/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package patrones;

import vista.FrmInicio;
import javax.swing.JPanel;

/**
 *
 * @author Sergio Arturo
 */
public class PatronSingleton {
    
    public static FrmInicio inicio;
    public static JPanel panel;
    
    
    private PatronSingleton(){
    
    }
    
    
    public static FrmInicio regresarInstanciaFrmInicio(){
        if(inicio == null){
            inicio = new FrmInicio();
            
        }
        
        return inicio;
    }
    
    public static JPanel regresarInstanciaJPanelDatos(){
        if(panel == null){
            panel = inicio.getJPanelSeccionDatos();
            
        }
        
        return panel;
    }
    

    
    
}
