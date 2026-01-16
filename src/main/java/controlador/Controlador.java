/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import dto.AnioDTO;
import java.awt.Color;
import modelo.ConfigManager;
import vista.FrmInicio;
import vista.DesingColorPickerDialog;
import vista.ViewJPanelNuevoAnio;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.Anio12SemanasLocalDAO;
import modelo.IAnio12SemanasDAO;
import patrones.IPatronObservador;
import vista.ViewJPanelSeccioAnio;

/**
 *
 * @author Sergio Arturo
 */
public class Controlador implements IPatronObservador{
    
    
    FrmInicio inicio;
    DesingColorPickerDialog picker;
    AnioDTO anio;
    
    JPanel jPanelSeccionDatos;
    JPanel panelSeccionAnio;
    JPanel panelSeccionNuevoAnio;
    
    IAnio12SemanasDAO anioDAO = new Anio12SemanasLocalDAO();
    
    
    
    public Controlador(FrmInicio inicio){
        
        this.inicio = inicio;
        
        cargarLogicaClase();
        
    
    }
    
    
    private void cargarLogicaClase(){
        anio = anioDAO.cargarAnio();
        
        picker = new DesingColorPickerDialog(inicio, true, this);
        
        jPanelSeccionDatos = inicio.getJPanelSeccionDatos();
        
//        if(anio != null){
//            panelSeccionAnio = new ViewJPanelSeccioAnio();
//        }
        panelSeccionNuevoAnio = new ViewJPanelNuevoAnio(this);
                
        
        cargarObservadores();
        
    
    }
    
    private void cargarObservadores(){
        ConfigManager.addObserver(this);
    }
    
    
    
    
    public void cargarPanel(char seleccion){
        
        switch (seleccion) {
            case 'h' -> {
                panelInicio();
            }
            
            case 'n' -> {
                panelNuevoAnio();
            }
            
            case 'r' -> {
                panelRecuento();
                
            }
            
            case 'i' -> {
                panelInfo();
            }
            
            
            default -> sinAnio();
        }
        
    }
    
    
    
    private void sinAnio(){
                
        jPanelSeccionDatos.removeAll();
        
        JLabel jblTextoInfo = new JLabel();
        jblTextoInfo.setText("No se ha detectado un año de 12 semanas");
        jblTextoInfo.setForeground(ConfigManager.getTextColor());
        jblTextoInfo.setFont(new java.awt.Font("Segoe UI", 1, 26)); 
        jblTextoInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblTextoInfo.revalidate();
        jblTextoInfo.setBounds((jPanelSeccionDatos.getBounds().width/2) - (jblTextoInfo.getPreferredSize().width/2),
                              (jPanelSeccionDatos.getBounds().height/2) - (jblTextoInfo.getPreferredSize().height/2),
                              jblTextoInfo.getPreferredSize().width + 10, 33);
        
        
        
        JLabel jblTextoInfoSelected = new JLabel();
        jblTextoInfoSelected.setText("Agregar nuevo año");
        jblTextoInfoSelected.setForeground(ConfigManager.getPrimaryColor());
        jblTextoInfoSelected.setFont(new java.awt.Font("Segoe UI", 1, 26)); 
        jblTextoInfoSelected.setCursor(new java.awt.Cursor(12));
        jblTextoInfoSelected.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblTextoInfoSelected.setVerticalAlignment(javax.swing.SwingConstants.CENTER);
        jblTextoInfoSelected.revalidate();
        jblTextoInfoSelected.setBounds((jPanelSeccionDatos.getBounds().width/2) - (jblTextoInfoSelected.getPreferredSize().width/2), 
                                      (jPanelSeccionDatos.getBounds().height/2) - (jblTextoInfo.getPreferredSize().height/2) + 40, 
                                      jblTextoInfoSelected.getPreferredSize().width + 10, 33);
        
        
        
        jblTextoInfoSelected.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseEntered(MouseEvent evt){
                jblTextoInfoSelected.setText("<html> <u> Agregar nuevo año</u> </html>");
                jblTextoInfoSelected.revalidate();
                jblTextoInfoSelected.setBounds((jPanelSeccionDatos.getBounds().width/2) - (jblTextoInfoSelected.getPreferredSize().width/2), 
                                              (jPanelSeccionDatos.getBounds().height/2) - (jblTextoInfo.getPreferredSize().height/2) + 40, 
                                              jblTextoInfoSelected.getPreferredSize().width + 10, 33);
            }

            @Override
            public void mouseExited(MouseEvent evt){
                jblTextoInfoSelected.setText("Agregar nuevo año");
            }

            @Override
            public void mouseClicked(MouseEvent evt){
                inicio.comprobarSeleccion('n');
                cargarPanel('n');
            }

            });
        
        jPanelSeccionDatos.add(jblTextoInfo);
        jPanelSeccionDatos.add(jblTextoInfoSelected);
        jPanelSeccionDatos.revalidate();
        jPanelSeccionDatos.repaint();
    
    }
    
    private void panelInicio(){
        
        
        jPanelSeccionDatos.removeAll();
        anio = anioDAO.cargarAnio();
        if(anio != null){
            panelSeccionAnio = new ViewJPanelSeccioAnio();
            panelSeccionAnio.setBounds(0, 0, jPanelSeccionDatos.getBounds().width, jPanelSeccionDatos.getBounds().height);
       
            inicio.setSeleccion('h');
            jPanelSeccionDatos.add(panelSeccionAnio);
            jPanelSeccionDatos.revalidate();
            jPanelSeccionDatos.repaint();

        }
        else{   
            sinAnio();
        }
        
    }
    
    private void panelNuevoAnio(){
         
        jPanelSeccionDatos.removeAll();
                
        panelSeccionNuevoAnio.setBounds(0, 0, jPanelSeccionDatos.getBounds().width, jPanelSeccionDatos.getBounds().height);
        

        inicio.setSeleccion('n');
        
        jPanelSeccionDatos.add(panelSeccionNuevoAnio);
        jPanelSeccionDatos.revalidate();
        jPanelSeccionDatos.repaint();
    }
    
    
    private void panelRecuento(){
        jPanelSeccionDatos.removeAll();
        
        JLabel jblTextoInfo = new JLabel();
        jblTextoInfo.setText("Seccion de recuento aun no programada");
        jblTextoInfo.setForeground(ConfigManager.getTextColor());
        jblTextoInfo.setFont(new java.awt.Font("Segoe UI", 1, 26)); 
        jblTextoInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblTextoInfo.revalidate();
        jblTextoInfo.setBounds((jPanelSeccionDatos.getBounds().width/2) - (jblTextoInfo.getPreferredSize().width/2),
                              (jPanelSeccionDatos.getBounds().height/2) - (jblTextoInfo.getPreferredSize().height/2),
                              jblTextoInfo.getPreferredSize().width + 10, 33);
        
        
        
        
        jPanelSeccionDatos.add(jblTextoInfo);
        jPanelSeccionDatos.revalidate();
        jPanelSeccionDatos.repaint();
    }
    
    
    private void panelInfo(){
        jPanelSeccionDatos.removeAll();
        
        JLabel jblTextoInfo = new JLabel();
        jblTextoInfo.setText("Seccion de informacion aun no programada");
        jblTextoInfo.setForeground(ConfigManager.getTextColor());
        jblTextoInfo.setFont(new java.awt.Font("Segoe UI", 1, 26)); 
        jblTextoInfo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblTextoInfo.revalidate();
        jblTextoInfo.setBounds((jPanelSeccionDatos.getBounds().width/2) - (jblTextoInfo.getPreferredSize().width/2),
                              (jPanelSeccionDatos.getBounds().height/2) - (jblTextoInfo.getPreferredSize().height/2),
                              jblTextoInfo.getPreferredSize().width + 10, 33);
        
        
        
        
        jPanelSeccionDatos.add(jblTextoInfo);
        jPanelSeccionDatos.revalidate();
        jPanelSeccionDatos.repaint();
    
    }
        
    
    
    public void abrirSelectorColor() {
        picker.setVisible(true);
   
    }
    
    public void guardarColores(Color primario, Color secundario){
        ConfigManager.setPrimaryColor(primario);
        ConfigManager.setSecondaryColor(secundario);
        ConfigManager.save();
    }
    
    public void guardarAnio(AnioDTO anio){
        anioDAO.guardarAnio(anio);
        System.out.println("Se guardo");
        cargarPanel('h');
        inicio.comprobarSeleccion('h');
    }
    
    

    @Override
    public void onColorSaved() {
        cargarPanel(inicio.getSeleccion());
    }
    
    

    
    
}




