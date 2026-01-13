/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import modelo.ConfigManager;
import controlador.Controlador;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.MetodosCompartidos;
import vista.CustomPanelRedondo;
import patrones.IPatronObservador;

/**
 *
 * @author Sergio Arturo
 */
public class FrmInicio extends javax.swing.JFrame implements IPatronObservador{
    
    
    private char seleccion = 'h';
    Controlador control;
    
    
    //private JPanelSeccioAnio panelAnio;
        
    

    /**
     * Creates new form FrmInicio
     */
    public FrmInicio() {
        initComponents();
        
        
        ConfigManager.addObserver(this);
        
        
        control = new Controlador(this);
        
        cargarDiseno();
        
    }
    
    
    public void cargarDiseno(){
        
        
        MetodosCompartidos.cargarIcono(jblIconInicio, "/iconos/home_ic.svg", 32, ConfigManager.getPrimaryColor());
        jblInicio.setForeground(ConfigManager.getPrimaryColor());
        
        MetodosCompartidos.cargarIcono(jblIconNuevoAnio, "/iconos/calendar_ic.svg", 32, ConfigManager.getSecondaryColor());
        MetodosCompartidos.cargarIcono(jblIconRecuento, "/iconos/list_ic.svg", 32, ConfigManager.getSecondaryColor());
        MetodosCompartidos.cargarIcono(jblIconInfo, "/iconos/info_ic.svg", 32, ConfigManager.getSecondaryColor());
        MetodosCompartidos.cargarIcono(jblIconConfig, "/iconos/settings_ic.svg", 32, ConfigManager.getSecondaryColor());
        
        jblNuevoAnio.setForeground(ConfigManager.getSecondaryColor());
        jblRecuento.setForeground(ConfigManager.getSecondaryColor());
        jblInfo.setForeground(ConfigManager.getSecondaryColor());



        
        jblFecha.setText(formatear(new Date()));
                
        control.cargarPanel(seleccion);
        
                
    }
    
    
       
    
    public void comprobarSeleccion(char seleccion){
        
        switch(seleccion){
        
            case 'h' -> {
                jPanelSeccionInicio.setBackground(ConfigManager.getHoverColor());
                jblInicio.setForeground(ConfigManager.getPrimaryColor());
                MetodosCompartidos.cargarIcono(jblIconInicio, "/iconos/home_ic.svg", 32, ConfigManager.getPrimaryColor());
                
                jPanelSeccionNuevoAnio.setBackground(ConfigManager.getBackgroundOptionColor());
                jblNuevoAnio.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconNuevoAnio, "/iconos/calendar_ic.svg", 32, ConfigManager.getSecondaryColor());
                
                jPanelSeccionRecuento.setBackground(ConfigManager.getBackgroundOptionColor());
                jblRecuento.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconRecuento, "/iconos/list_ic.svg", 32, ConfigManager.getSecondaryColor());
                
                jPanelSeccionInfo.setBackground(ConfigManager.getBackgroundOptionColor());
                jblInfo.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconInfo, "/iconos/info_ic.svg", 32, ConfigManager.getSecondaryColor());
                
            }
            
            case 'n' -> {
                jPanelSeccionNuevoAnio.setBackground(ConfigManager.getHoverColor());
                jblNuevoAnio.setForeground(ConfigManager.getPrimaryColor());
                MetodosCompartidos.cargarIcono(jblIconNuevoAnio, "/iconos/calendar_ic.svg", 32, ConfigManager.getPrimaryColor());
                
                jPanelSeccionInicio.setBackground(ConfigManager.getBackgroundOptionColor());
                jblInicio.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconInicio, "/iconos/home_ic.svg", 32, ConfigManager.getSecondaryColor());
                
                jPanelSeccionRecuento.setBackground(ConfigManager.getBackgroundOptionColor());
                jblRecuento.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconRecuento, "/iconos/list_ic.svg", 32, ConfigManager.getSecondaryColor());
                
                jPanelSeccionInfo.setBackground(ConfigManager.getBackgroundOptionColor());
                jblInfo.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconInfo, "/iconos/info_ic.svg", 32, ConfigManager.getSecondaryColor());
                
            }
            
            case 'r' -> {
                jPanelSeccionRecuento.setBackground(ConfigManager.getHoverColor());
                jblRecuento.setForeground(ConfigManager.getPrimaryColor());
                MetodosCompartidos.cargarIcono(jblIconRecuento, "/iconos/list_ic.svg", 32, ConfigManager.getPrimaryColor());
                
                jPanelSeccionNuevoAnio.setBackground(ConfigManager.getBackgroundOptionColor());
                jblNuevoAnio.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconNuevoAnio, "/iconos/calendar_ic.svg", 32, ConfigManager.getSecondaryColor());
                
                jPanelSeccionInicio.setBackground(ConfigManager.getBackgroundOptionColor());
                jblInicio.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconInicio, "/iconos/home_ic.svg", 32, ConfigManager.getSecondaryColor());
                
                jPanelSeccionInfo.setBackground(ConfigManager.getBackgroundOptionColor());
                jblInfo.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconInfo, "/iconos/info_ic.svg", 32, ConfigManager.getSecondaryColor());
                
            }
            
            case 'i' -> {
                jPanelSeccionInfo.setBackground(ConfigManager.getHoverColor());
                jblInfo.setForeground(ConfigManager.getPrimaryColor());
                MetodosCompartidos.cargarIcono(jblIconInfo, "/iconos/info_ic.svg", 32, ConfigManager.getPrimaryColor());
                
                jPanelSeccionNuevoAnio.setBackground(ConfigManager.getBackgroundOptionColor());
                jblNuevoAnio.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconNuevoAnio, "/iconos/calendar_ic.svg", 32, ConfigManager.getSecondaryColor());
                
                jPanelSeccionRecuento.setBackground(ConfigManager.getBackgroundOptionColor());
                jblRecuento.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconRecuento, "/iconos/list_ic.svg", 32, ConfigManager.getSecondaryColor());
                
                jPanelSeccionInicio.setBackground(ConfigManager.getBackgroundOptionColor());
                jblInicio.setForeground(ConfigManager.getSecondaryColor());
                MetodosCompartidos.cargarIcono(jblIconInicio, "/iconos/home_ic.svg", 32, ConfigManager.getSecondaryColor());
                
            }
        
        }
    
    }
    
    public void hoverSeleccion(JPanel panel, boolean dentro, JLabel icon, JLabel jbl, String ruta,  char selection, char letra){
        
        if(dentro){
            panel.setBackground(ConfigManager.getHoverColor());
            
        }
        else{
            if(selection != letra){
            MetodosCompartidos.cargarIcono(icon, ruta, 32, ConfigManager.getSecondaryColor());
            jbl.setForeground(ConfigManager.getSecondaryColor());
            panel.setBackground(ConfigManager.getBackgroundOptionColor());
        }
        }
        
    }
    
    
    private String formatear(Date date) {
        LocalDate localDate = date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "EEEE d 'de' MMMM 'del' yyyy",
                        new Locale("es", "ES")
                );

        // Capitalizar primera letra
        String texto = localDate.format(formatter);
        return texto.substring(0, 1).toUpperCase()
                + texto.substring(1);
    }
   
    
    
    public JPanel getJPanelSeccionDatos(){
        return jPanelSeccionDatos;
    }
    

    
    
    @Override
    public void onColorSaved() {
        comprobarSeleccion(seleccion);
    }

    
    
    
    public char getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(char seleccion) {
        this.seleccion = seleccion;
    }
    
    
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelFondo = new javax.swing.JPanel();
        jPanelSeccionDatos = new javax.swing.JPanel();
        jPanelNavegacion = new javax.swing.JPanel();
        jPanelUsuario = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanelSeccionInfo = new vista.CustomPanelRedondo(0,4,0,4, false);
        jblInfo = new javax.swing.JLabel();
        jblIconInfo = new javax.swing.JLabel();
        jPanelSeccionInicio = new vista.CustomPanelRedondo(0,4,0,4, false);
        jblInicio = new javax.swing.JLabel();
        jblIconInicio = new javax.swing.JLabel();
        jPanelSeccionNuevoAnio = new vista.CustomPanelRedondo(0,4,0,4, false);
        jblNuevoAnio = new javax.swing.JLabel();
        jblIconNuevoAnio = new javax.swing.JLabel();
        jPanelSeccionRecuento = new vista.CustomPanelRedondo(0,4,0,4, false);
        jblRecuento = new javax.swing.JLabel();
        jblIconRecuento = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jblFecha = new javax.swing.JLabel();
        jblIconConfig = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Objetivos: Año de 12 Semanas");
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setSize(new java.awt.Dimension(1280, 720));
        getContentPane().setLayout(null);

        jPanelFondo.setBackground(new java.awt.Color(234, 234, 234));
        jPanelFondo.setLayout(null);

        jPanelSeccionDatos.setBackground(new java.awt.Color(47, 47, 47));
        jPanelSeccionDatos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 67, 67)));
        jPanelSeccionDatos.setLayout(null);
        jPanelFondo.add(jPanelSeccionDatos);
        jPanelSeccionDatos.setBounds(220, 70, 1046, 614);

        jPanelNavegacion.setBackground(new java.awt.Color(47, 47, 47));
        jPanelNavegacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 67, 67)));
        jPanelNavegacion.setLayout(null);

        jPanelUsuario.setBackground(new java.awt.Color(47, 47, 47));
        jPanelUsuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 67, 67)));

        javax.swing.GroupLayout jPanelUsuarioLayout = new javax.swing.GroupLayout(jPanelUsuario);
        jPanelUsuario.setLayout(jPanelUsuarioLayout);
        jPanelUsuarioLayout.setHorizontalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );
        jPanelUsuarioLayout.setVerticalGroup(
            jPanelUsuarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 138, Short.MAX_VALUE)
        );

        jPanelNavegacion.add(jPanelUsuario);
        jPanelUsuario.setBounds(0, 0, 220, 140);

        jPanel1.setBackground(new java.awt.Color(67, 67, 67));
        jPanel1.setLayout(null);

        jPanelSeccionInfo.setBackground(new java.awt.Color(67, 67, 67));
        jPanelSeccionInfo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSeccionInfoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelSeccionInfoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelSeccionInfoMouseExited(evt);
            }
        });
        jPanelSeccionInfo.setLayout(null);

        jblInfo.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jblInfo.setForeground(new java.awt.Color(255, 255, 255));
        jblInfo.setText("Informacion");
        jPanelSeccionInfo.add(jblInfo);
        jblInfo.setBounds(50, 12, 140, 30);

        jblIconInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSeccionInfo.add(jblIconInfo);
        jblIconInfo.setBounds(10, 12, 32, 32);

        jPanel1.add(jPanelSeccionInfo);
        jPanelSeccionInfo.setBounds(0, 150, 215, 50);

        jPanelSeccionInicio.setBackground(new java.awt.Color(90, 90, 90));
        jPanelSeccionInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSeccionInicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelSeccionInicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelSeccionInicioMouseExited(evt);
            }
        });
        jPanelSeccionInicio.setLayout(null);

        jblInicio.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jblInicio.setForeground(new java.awt.Color(52, 125, 210));
        jblInicio.setText("Inicio");
        jPanelSeccionInicio.add(jblInicio);
        jblInicio.setBounds(50, 12, 70, 30);

        jblIconInicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jblIconInicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblIconInicioMouseEntered(evt);
            }
        });
        jPanelSeccionInicio.add(jblIconInicio);
        jblIconInicio.setBounds(10, 12, 32, 32);

        jPanel1.add(jPanelSeccionInicio);
        jPanelSeccionInicio.setBounds(0, 0, 215, 52);

        jPanelSeccionNuevoAnio.setBackground(new java.awt.Color(67, 67, 67));
        jPanelSeccionNuevoAnio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSeccionNuevoAnioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelSeccionNuevoAnioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelSeccionNuevoAnioMouseExited(evt);
            }
        });
        jPanelSeccionNuevoAnio.setLayout(null);

        jblNuevoAnio.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jblNuevoAnio.setForeground(new java.awt.Color(242, 242, 242));
        jblNuevoAnio.setText("Nuevo Año");
        jPanelSeccionNuevoAnio.add(jblNuevoAnio);
        jblNuevoAnio.setBounds(50, 12, 130, 30);

        jblIconNuevoAnio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSeccionNuevoAnio.add(jblIconNuevoAnio);
        jblIconNuevoAnio.setBounds(10, 12, 32, 32);

        jPanel1.add(jPanelSeccionNuevoAnio);
        jPanelSeccionNuevoAnio.setBounds(0, 50, 215, 52);

        jPanelSeccionRecuento.setBackground(new java.awt.Color(67, 67, 67));
        jPanelSeccionRecuento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelSeccionRecuentoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelSeccionRecuentoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelSeccionRecuentoMouseExited(evt);
            }
        });
        jPanelSeccionRecuento.setLayout(null);

        jblRecuento.setBackground(new java.awt.Color(255, 255, 255));
        jblRecuento.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jblRecuento.setForeground(new java.awt.Color(255, 255, 255));
        jblRecuento.setText("Recuento");
        jPanelSeccionRecuento.add(jblRecuento);
        jblRecuento.setBounds(50, 12, 140, 30);

        jblIconRecuento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanelSeccionRecuento.add(jblIconRecuento);
        jblIconRecuento.setBounds(10, 12, 32, 32);

        jPanel1.add(jPanelSeccionRecuento);
        jPanelSeccionRecuento.setBounds(0, 100, 215, 52);

        jPanelNavegacion.add(jPanel1);
        jPanel1.setBounds(0, 140, 220, 200);

        jPanelFondo.add(jPanelNavegacion);
        jPanelNavegacion.setBounds(0, 1, 220, 683);

        jPanel2.setBackground(new java.awt.Color(47, 47, 47));
        jPanel2.setLayout(null);

        jblFecha.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jblFecha.setForeground(new java.awt.Color(255, 255, 255));
        jblFecha.setText("Fecha");
        jPanel2.add(jblFecha);
        jblFecha.setBounds(30, 20, 420, 30);

        jblIconConfig.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jblIconConfig.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jblIconConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblIconConfigMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblIconConfigMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblIconConfigMouseExited(evt);
            }
        });
        jPanel2.add(jblIconConfig);
        jblIconConfig.setBounds(990, 20, 32, 32);

        jPanelFondo.add(jPanel2);
        jPanel2.setBounds(220, 1, 1046, 70);

        getContentPane().add(jPanelFondo);
        jPanelFondo.setBounds(0, 0, 1280, 720);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jblIconInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblIconInicioMouseEntered
        // TODO add your handling code here:
        
        
        
    }//GEN-LAST:event_jblIconInicioMouseEntered

    private void jPanelSeccionInicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionInicioMouseEntered
        // TODO add your handling code here:
        hoverSeleccion(jPanelSeccionInicio, true, null, null, null, seleccion, seleccion);
    }//GEN-LAST:event_jPanelSeccionInicioMouseEntered

    private void jPanelSeccionInicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionInicioMouseExited
        // TODO add your handling code here:
        hoverSeleccion(jPanelSeccionInicio, false, jblIconInicio, jblInicio, "/iconos/home_ic.svg", seleccion, 'h');

    }//GEN-LAST:event_jPanelSeccionInicioMouseExited

    private void jPanelSeccionInicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionInicioMouseClicked
        // TODO add your handling code here:
        seleccion = 'h';
        control.cargarPanel(seleccion);
        comprobarSeleccion(seleccion);
    }//GEN-LAST:event_jPanelSeccionInicioMouseClicked

    private void jPanelSeccionNuevoAnioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionNuevoAnioMouseEntered
        // TODO add your handling code here:
        hoverSeleccion(jPanelSeccionNuevoAnio, true, null, null, null, seleccion, seleccion);

    }//GEN-LAST:event_jPanelSeccionNuevoAnioMouseEntered

    private void jPanelSeccionNuevoAnioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionNuevoAnioMouseExited
        // TODO add your handling code here:
        hoverSeleccion(jPanelSeccionNuevoAnio, false, jblIconNuevoAnio, jblNuevoAnio, "/iconos/calendar_ic.svg", seleccion, 'n');

    }//GEN-LAST:event_jPanelSeccionNuevoAnioMouseExited

    private void jPanelSeccionNuevoAnioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionNuevoAnioMouseClicked
        // TODO add your handling code here:
        seleccion = 'n';
        control.cargarPanel(seleccion);
        comprobarSeleccion(seleccion);
        
    }//GEN-LAST:event_jPanelSeccionNuevoAnioMouseClicked

    private void jPanelSeccionRecuentoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionRecuentoMouseEntered
        // TODO add your handling code here:
        hoverSeleccion(jPanelSeccionRecuento, true, null, null, null, seleccion, seleccion);
    }//GEN-LAST:event_jPanelSeccionRecuentoMouseEntered

    private void jPanelSeccionRecuentoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionRecuentoMouseExited
        // TODO add your handling code here:
        hoverSeleccion(jPanelSeccionRecuento, false, jblIconRecuento, jblRecuento, "/iconos/list_ic.svg", seleccion, 'r');
    }//GEN-LAST:event_jPanelSeccionRecuentoMouseExited

    private void jPanelSeccionRecuentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionRecuentoMouseClicked
        // TODO add your handling code here:
        seleccion = 'r';
        control.cargarPanel(seleccion);
        comprobarSeleccion(seleccion);
    }//GEN-LAST:event_jPanelSeccionRecuentoMouseClicked

    private void jPanelSeccionInfoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionInfoMouseEntered
        // TODO add your handling code here:
        hoverSeleccion(jPanelSeccionInfo, true, null, null, null, seleccion, seleccion);
    }//GEN-LAST:event_jPanelSeccionInfoMouseEntered

    private void jPanelSeccionInfoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionInfoMouseExited
        // TODO add your handling code here:
        hoverSeleccion(jPanelSeccionInfo, false, jblIconInfo, jblInfo, "/iconos/info_ic.svg", seleccion, 'i');
    }//GEN-LAST:event_jPanelSeccionInfoMouseExited

    private void jPanelSeccionInfoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelSeccionInfoMouseClicked
        // TODO add your handling code here:
        seleccion = 'i';
        control.cargarPanel(seleccion);
        comprobarSeleccion(seleccion);
    }//GEN-LAST:event_jPanelSeccionInfoMouseClicked

    private void jblIconConfigMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblIconConfigMouseEntered
        // TODO add your handling code here:
        MetodosCompartidos.cargarIcono(jblIconConfig, "/iconos/settings_ic.svg", 32, ConfigManager.getPrimaryColor());
    }//GEN-LAST:event_jblIconConfigMouseEntered

    private void jblIconConfigMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblIconConfigMouseExited
        // TODO add your handling code here:
        MetodosCompartidos.cargarIcono(jblIconConfig, "/iconos/settings_ic.svg", 32, ConfigManager.getSecondaryColor());
    }//GEN-LAST:event_jblIconConfigMouseExited

    private void jblIconConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblIconConfigMouseClicked
        // TODO add your handling code here:
        control.abrirSelectorColor();
        
    }//GEN-LAST:event_jblIconConfigMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanelFondo;
    private javax.swing.JPanel jPanelNavegacion;
    private javax.swing.JPanel jPanelSeccionDatos;
    private javax.swing.JPanel jPanelSeccionInfo;
    private javax.swing.JPanel jPanelSeccionInicio;
    private javax.swing.JPanel jPanelSeccionNuevoAnio;
    private javax.swing.JPanel jPanelSeccionRecuento;
    private javax.swing.JPanel jPanelUsuario;
    private javax.swing.JLabel jblFecha;
    private javax.swing.JLabel jblIconConfig;
    private javax.swing.JLabel jblIconInfo;
    private javax.swing.JLabel jblIconInicio;
    private javax.swing.JLabel jblIconNuevoAnio;
    private javax.swing.JLabel jblIconRecuento;
    private javax.swing.JLabel jblInfo;
    private javax.swing.JLabel jblInicio;
    private javax.swing.JLabel jblNuevoAnio;
    private javax.swing.JLabel jblRecuento;
    // End of variables declaration//GEN-END:variables


}
