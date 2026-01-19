/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import controlador.Controlador;
import dto.AnioDTO;
import dto.DiasDTO;
import dto.SemanaDTO;
import dto.TacticaDTO;
import java.awt.Color;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import modelo.Anio12SemanasLocalDAO;
import modelo.ConfigManager;
import modelo.IAnio12SemanasDAO;
import patrones.IPatronObservador;
import util.ConstantesGUI;

/**
 *
 * @author Sergio Arturo
 */
public class ViewJPanelSeccioAnio extends javax.swing.JPanel implements IPatronObservador{
    
    
    private AnioDTO anio;
    LocalDate hoy = LocalDate.now();
    long dias;
    int dia;
    int porcentaje[] = new int[2];
    
    private final IAnio12SemanasDAO anioDAO = new Anio12SemanasLocalDAO();
    private FrmInicio inicio;
    private Controlador control;
    private CustomDLG confirmar;

    

            
    /**
     * Creates new form JPanelSeccioAnio
     */
    public ViewJPanelSeccioAnio(Controlador control, FrmInicio inicio) {
        initComponents();
        
        this.inicio = inicio;
        this.control = control;
        
        cargarDatos();
        
        cargarColores();
        
        ConfigManager.addObserver(this);
                
    }
    
    
    private void cargarColores(){
        jPanelEncabezado.setBackground(ConfigManager.getPrimaryColor());
        txtNomObjetivo.setBackground(ConfigManager.getPrimaryColor());
        
    }
    
    private void cargarDatos(){
        
        anio = anioDAO.cargarAnio();
        
        txtNomObjetivo.setText(anio.getNombre());
        
        dias = ChronoUnit.DAYS.between(anio.getFechaInicio(), hoy) + 1;
        
//        int semana = 1;
//        if(dias < 8) semana = 1;
//        if(dias > 7 && dias < 15) semana = 2;
//        if(dias > 14 && dias < 22) semana = 3;
//        if(dias > 21 && dias < 29) semana = 4;
//        if(dias > 28 && dias < 36) semana = 5;
//        if(dias > 35 && dias < 43) semana = 6;
//        if(dias > 42 && dias < 50) semana = 7;
//        if(dias > 49 && dias < 57) semana = 8;
//        if(dias > 56 && dias < 64) semana = 9;
//        if(dias > 63 && dias < 71) semana = 10;
//        if(dias > 70 && dias < 78) semana = 11;
//        if(dias > 77 && dias < 85) semana = 12;
//        if(dias > 84) semana = 13;

//        int dia = (int)dias % 7;
//        if(dia == 0){
//            dia = 7;
//        }
        
        
        int semana = (int) ((dias - 1) / 7) + 1;
        dia = (int) ((dias - 1) % 7) + 1;

        
        

        
        
        jblFecha.setText("Semana " + semana + " Dia " + dia);
        
        cargarAnio();
        establecerPorncentaje();
        
        confirmar = new CustomDLG(inicio, true, "Segiro", ConstantesGUI.ADVERTENCIA);


    }
    
    
    private void cargarAnio(){
            
        TacticaDTO tactica = new TacticaDTO();
        
        for(int i = 0; i < anio.getListaTacticasDTO().size(); i++){
            tactica = (TacticaDTO) anio.getListaTacticasDTO().get(i);
            DesingJPanelCheckTactica addTactica = new DesingJPanelCheckTactica(hoy.getDayOfWeek().name());
            addTactica.setBounds(0, 40 * i, 560, 40);
            
            addTactica.getTxtNomTactica().setText(tactica.getNombre());
            
            
//            addTactica.setDiaChangeListener(completado ->{
//                if(completado){
//                    porcentaje[1] = porcentaje[1] + 1;
//
//                }
//                else{
//                    porcentaje[1] = porcentaje[1] - 1;
//
//                }
//                diaDatos.setCompletado(completado);
//                
//               // control.editarAnio(anio);
//                
//                establecerPorncentaje();
//
//            
//            });
            
            
            
            List<SemanaDTO> listaSemanas = tactica.getsemanasDTO();
            
            for (SemanaDTO semana : listaSemanas){

                boolean dentro = !hoy.isBefore(semana.getFechaInicio()) && !hoy.isAfter(semana.getFechaFin());
                
                
                
                if(dentro){
                    asignarLogicaDia(addTactica, semana);
                    break;
                }
                
                
                
            }
            
  
            panelContenedorAnio.setBounds(panelContenedorAnio.getBounds().x,
                        panelContenedorAnio.getBounds().y, panelContenedorAnio.getBounds().width,
                        panelContenedorAnio.getBounds().height + 40);
            
            

            panelContenedorAnio.add(addTactica);
            panelContenedorAnio.revalidate();
            panelContenedorAnio.repaint();
            
//            Animaciones.animarFadeIn(addTactica, () -> {
//                addTactica.getTxtNomTactica().setVisible(true);
//                }
//            );
     
        }


    }
    
    
    
    private void asignarLogicaDia(DesingJPanelCheckTactica panel, SemanaDTO semana){
                
        for(DiasDTO d : semana.getListaDias()){
            String nomdia = d.getFecha().getDayOfWeek().name().toLowerCase();
            
            if(d.getFecha().isEqual(hoy)){
                panel.setDiaChangeListener(completado ->{
                    if(completado){
                        porcentaje[1] = porcentaje[1] + 1;

                    }
                    else{
                        porcentaje[1] = porcentaje[1] - 1;

                    }
                    d.setCompletado(completado);
                    System.out.println(d.toString());
                   // control.editarAnio(anio);

                    establecerPorncentaje();


                });
            }
            
            
            
            verificarDia(panel, d, nomdia);
                        
 
        }
        

    }
    
    
    private void verificarDia(DesingJPanelCheckTactica panel, DiasDTO diaDatos, String nombreDia) {
        Map<String, CustomPanelRedondo> panelPorDia = Map.of(
            "monday",    panel.getPanelLuSelect(),
            "tuesday",   panel.getPanelMaSelect(),
            "wednesday", panel.getPanelMiSelect(),
            "thursday",  panel.getPanelJuSelect(),
            "friday",    panel.getPanelViSelect(),
            "saturday",  panel.getPanelSaSelect(),
            "sunday",    panel.getPanelDoSelect()
        );

        CustomPanelRedondo panelSelect = panelPorDia.get(nombreDia);

        if (panelSelect == null) return;

        procesarDia(panel, panelSelect, diaDatos);
    }

    
    private void procesarDia(DesingJPanelCheckTactica panel, CustomPanelRedondo panelSelect, DiasDTO diaDatos) {
        if (!diaDatos.isSeleccionado()) {
            panelSelect.setMostrarLinea(true);
            return;
        }
                
        
        if(diaDatos.isCompletado()){
            panelSelect.setBackground(ConfigManager.getAcceptColor());
            panelSelect.putClientProperty("selected", true);

            porcentaje[1] = porcentaje[1] + 1;

        }
        else{
            panelSelect.setBackground(ConfigManager.getTextColor());
            panelSelect.putClientProperty("selected", false);


        }
            porcentaje[0] = porcentaje[0] + 1;
            

    }

    
    
    private void establecerPorncentaje(){
        float resultado = ((float) porcentaje[1] * 100) / porcentaje[0];
        
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);

        jblPorcentaje.setText(df.format(resultado) + "%");

    }
    
    
    private void diaCompletado(DiasDTO dia, boolean completado) {

        dia.setCompletado(completado);
        control.editarAnio(anio);
    }
    
    
    
//    private void verificarDia(DesingJPanelCheckTactica panel, DiasDTO diaDatos, String nombreDia){
//
//        switch(nombreDia){
//            case "monday":{
//                if(!diaDatos.isSeleccionado()){
//                    panel.setDeshabilidato(true);
//                    panel.getPanelLuSelect().setMostrarLinea(true);
//                    break;
//               }
//
//               if(diaDatos.isCompletado()){
//                  panel.getPanelLuSelect().setBackground(ConfigManager.getAcceptColor());
//                }
//               else{
//                   panel.getPanelLuSelect().setBackground(ConfigManager.getTextColor());
//               }
//               break;
//            }
//            
//            case "tuesday":{
//                if(!diaDatos.isSeleccionado()){
//                    panel.setDeshabilidato(true);
//                    panel.getPanelMaSelect().setMostrarLinea(true);
//                    break;
//               }
//
//               if(diaDatos.isCompletado()){
//                  panel.getPanelMaSelect().setBackground(ConfigManager.getAcceptColor());
//                }
//               else{
//                   panel.getPanelMaSelect().setBackground(ConfigManager.getTextColor());
//               }
//               break;
//            }
//            
//            case "wednesday":{
//
//                if(!diaDatos.isSeleccionado()){
//                    panel.setDeshabilidato(true);
//                    panel.getPanelMiSelect().setMostrarLinea(true);
//                    break;
//               }
//
//               if(diaDatos.isCompletado()){
//                  panel.getPanelMiSelect().setBackground(ConfigManager.getAcceptColor());
//                }
//               else{
//                   panel.getPanelMiSelect().setBackground(ConfigManager.getTextColor());
//               }
//               break;
//            }
//            
//            
//            case "thursday":{
//                if(!diaDatos.isSeleccionado()){
//                    panel.setDeshabilidato(true);
//                    panel.getPanelJuSelect().setMostrarLinea(true);
//                    break;
//               }
//
//               if(diaDatos.isCompletado()){
//                  panel.getPanelJuSelect().setBackground(ConfigManager.getAcceptColor());
//                }
//               else{
//                   panel.getPanelJuSelect().setBackground(ConfigManager.getTextColor());
//               }
//               break;
//            }
//            
//            
//            case "friday":{
//                if(!diaDatos.isSeleccionado()){
//                    panel.setDeshabilidato(true);
//                    panel.getPanelViSelect().setMostrarLinea(true);
//                    break;
//               }
//
//               if(diaDatos.isCompletado()){
//                  panel.getPanelViSelect().setBackground(ConfigManager.getAcceptColor());
//                }
//               else{
//                   panel.getPanelViSelect().setBackground(ConfigManager.getTextColor());
//               }
//               break;
//            }
//            
//            
//            case "saturday":{
//                if(!diaDatos.isSeleccionado()){
//                    panel.setDeshabilidato(true);
//                    panel.getPanelSaSelect().setMostrarLinea(true);
//                    break;
//               }
//
//               if(diaDatos.isCompletado()){
//                  panel.getPanelSaSelect().setBackground(ConfigManager.getAcceptColor());
//                }
//               else{
//                   panel.getPanelSaSelect().setBackground(ConfigManager.getTextColor());
//               }
//               break;
//            }
//            
//            
//            case "sunday":{
//                if(!diaDatos.isSeleccionado()){
//                    panel.setDeshabilidato(true);
//                    panel.getPanelDoSelect().setMostrarLinea(true);
//                    //panel.getPanelDoSelect().setBackground(ConfigManager.getDeleteColor());
//                    break;
//               }
//
//               if(diaDatos.isCompletado()){
//                  panel.getPanelDoSelect().setBackground(ConfigManager.getAcceptColor());
//                }
//               else{
//                   panel.getPanelDoSelect().setBackground(ConfigManager.getTextColor());
//               }
//               break;
//            }
//            
//
//            
//        }
//
//    
//    }
    
    
    
    @Override
    public void onColorSaved() {
        cargarColores();
    }
    
    @Override
    public void removeNotify() {
        super.removeNotify();
        ConfigManager.removeObserver(this);
    }
    

    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jblInicio1 = new javax.swing.JLabel();
        jPanelEncabezado = new vista.CustomPanelRedondo(15,15,0,0, true);
        txtNomObjetivo = new vista.CustomJTextFieldRedondo(5,5,5,5, false);
        jblFecha = new javax.swing.JLabel();
        jblPorcentaje = new javax.swing.JLabel();
        panelNombre = new vista.CustomPanelRedondo();
        jblLu1 = new javax.swing.JLabel();
        panelContenedorAnio = new vista.CustomPanelRedondo();
        panelFrecuencia = new vista.CustomPanelRedondo();
        panelLu = new vista.CustomPanelRedondo();
        jblLu = new javax.swing.JLabel();
        panelMa = new vista.CustomPanelRedondo();
        jblMa = new javax.swing.JLabel();
        panelMi = new vista.CustomPanelRedondo();
        jblMi = new javax.swing.JLabel();
        panelJu = new vista.CustomPanelRedondo();
        jblJu = new javax.swing.JLabel();
        panelVi = new vista.CustomPanelRedondo();
        jblVi = new javax.swing.JLabel();
        panelSa = new vista.CustomPanelRedondo();
        jblSa = new javax.swing.JLabel();
        panelDo = new vista.CustomPanelRedondo();
        jblDo = new javax.swing.JLabel();
        btnEliminar = new vista.CustomPanelRedondo();
        jblEliminar = new javax.swing.JLabel();

        setBackground(new java.awt.Color(47, 47, 47));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 67, 67)));
        setPreferredSize(new java.awt.Dimension(1046, 614));
        setLayout(null);

        jblInicio1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jblInicio1.setForeground(new java.awt.Color(255, 255, 255));
        jblInicio1.setText("Año de 12 Semanas");
        add(jblInicio1);
        jblInicio1.setBounds(30, 20, 220, 30);

        jPanelEncabezado.setBackground(new java.awt.Color(52, 125, 210));
        jPanelEncabezado.setLayout(null);

        txtNomObjetivo.setEditable(false);
        txtNomObjetivo.setBackground(new java.awt.Color(52, 125, 210));
        txtNomObjetivo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtNomObjetivo.setForeground(new java.awt.Color(255, 255, 255));
        txtNomObjetivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNomObjetivo.setBorder(null);
        txtNomObjetivo.setFocusable(false);
        txtNomObjetivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomObjetivoActionPerformed(evt);
            }
        });
        jPanelEncabezado.add(txtNomObjetivo);
        txtNomObjetivo.setBounds(20, 20, 520, 50);

        jblFecha.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jblFecha.setForeground(new java.awt.Color(255, 255, 255));
        jblFecha.setText("Semana 1 - Día 1");
        jPanelEncabezado.add(jblFecha);
        jblFecha.setBounds(20, 90, 200, 27);

        jblPorcentaje.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jblPorcentaje.setForeground(new java.awt.Color(255, 255, 255));
        jblPorcentaje.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jblPorcentaje.setText("100 %");
        jPanelEncabezado.add(jblPorcentaje);
        jblPorcentaje.setBounds(340, 90, 200, 27);

        add(jPanelEncabezado);
        jPanelEncabezado.setBounds(30, 70, 560, 130);

        panelNombre.setBackground(new java.awt.Color(255, 255, 255));
        panelNombre.setBorde(false);
        panelNombre.setBorderThickness(1.5F);
        panelNombre.setLayout(null);

        jblLu1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblLu1.setForeground(new java.awt.Color(0, 0, 0));
        jblLu1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jblLu1.setText("Nombre de la táctica");
        panelNombre.add(jblLu1);
        jblLu1.setBounds(10, 0, 200, 40);

        add(panelNombre);
        panelNombre.setBounds(30, 200, 216, 40);

        panelContenedorAnio.setBackground(new java.awt.Color(47, 47, 47));
        panelContenedorAnio.setBorde(false);
        panelContenedorAnio.setOpaque(true);
        panelContenedorAnio.setPreferredSize(new java.awt.Dimension(362, 80));
        panelContenedorAnio.setRequestFocusEnabled(false);
        panelContenedorAnio.setLayout(null);
        add(panelContenedorAnio);
        panelContenedorAnio.setBounds(30, 240, 610, 40);

        panelFrecuencia.setBackground(new java.awt.Color(255, 255, 255));
        panelFrecuencia.setLayout(null);

        panelLu.setBackground(new java.awt.Color(255, 255, 255));
        panelLu.setLayout(null);

        jblLu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblLu.setForeground(new java.awt.Color(0, 0, 0));
        jblLu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblLu.setText("Lu");
        panelLu.add(jblLu);
        jblLu.setBounds(0, 0, 50, 40);

        panelFrecuencia.add(panelLu);
        panelLu.setBounds(0, 0, 50, 40);

        panelMa.setBackground(new java.awt.Color(255, 255, 255));
        panelMa.setLayout(null);

        jblMa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblMa.setForeground(new java.awt.Color(0, 0, 0));
        jblMa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblMa.setText("Ma");
        panelMa.add(jblMa);
        jblMa.setBounds(0, 0, 50, 40);

        panelFrecuencia.add(panelMa);
        panelMa.setBounds(49, 0, 50, 40);

        panelMi.setBackground(new java.awt.Color(255, 255, 255));
        panelMi.setLayout(null);

        jblMi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblMi.setForeground(new java.awt.Color(0, 0, 0));
        jblMi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblMi.setText("Mi");
        panelMi.add(jblMi);
        jblMi.setBounds(0, 0, 50, 40);

        panelFrecuencia.add(panelMi);
        panelMi.setBounds(98, 0, 50, 40);

        panelJu.setBackground(new java.awt.Color(255, 255, 255));
        panelJu.setLayout(null);

        jblJu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblJu.setForeground(new java.awt.Color(0, 0, 0));
        jblJu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblJu.setText("Ju");
        panelJu.add(jblJu);
        jblJu.setBounds(0, 0, 50, 40);

        panelFrecuencia.add(panelJu);
        panelJu.setBounds(147, 0, 50, 40);

        panelVi.setBackground(new java.awt.Color(255, 255, 255));
        panelVi.setLayout(null);

        jblVi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblVi.setForeground(new java.awt.Color(0, 0, 0));
        jblVi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblVi.setText("Vi");
        panelVi.add(jblVi);
        jblVi.setBounds(0, 0, 50, 40);

        panelFrecuencia.add(panelVi);
        panelVi.setBounds(195, 0, 51, 40);

        panelSa.setBackground(new java.awt.Color(255, 255, 255));
        panelSa.setLayout(null);

        jblSa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblSa.setForeground(new java.awt.Color(0, 0, 0));
        jblSa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblSa.setText("Sa");
        panelSa.add(jblSa);
        jblSa.setBounds(0, 0, 50, 40);

        panelFrecuencia.add(panelSa);
        panelSa.setBounds(245, 0, 51, 40);

        panelDo.setBackground(new java.awt.Color(255, 255, 255));
        panelDo.setLayout(null);

        jblDo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblDo.setForeground(new java.awt.Color(0, 0, 0));
        jblDo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblDo.setText("Do");
        panelDo.add(jblDo);
        jblDo.setBounds(0, 0, 50, 40);

        panelFrecuencia.add(panelDo);
        panelDo.setBounds(295, 0, 50, 40);

        add(panelFrecuencia);
        panelFrecuencia.setBounds(245, 200, 345, 40);

        btnEliminar.setBackground(new java.awt.Color(244, 50, 11));
        btnEliminar.setRoundBottomLeft(5);
        btnEliminar.setRoundBottomRight(5);
        btnEliminar.setRoundTopLeft(5);
        btnEliminar.setRoundTopRight(5);
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEliminarMouseExited(evt);
            }
        });
        btnEliminar.setLayout(null);

        jblEliminar.setBackground(new java.awt.Color(0, 0, 0));
        jblEliminar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jblEliminar.setForeground(new java.awt.Color(0, 0, 0));
        jblEliminar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblEliminar.setText("X");
        jblEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jblEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblEliminarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblEliminarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblEliminarMouseExited(evt);
            }
        });
        btnEliminar.add(jblEliminar);
        jblEliminar.setBounds(0, 0, 40, 40);

        add(btnEliminar);
        btnEliminar.setBounds(550, 20, 40, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomObjetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomObjetivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomObjetivoActionPerformed

    private void jblEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblEliminarMouseEntered
        // TODO add your handling code here:
        btnEliminar.setBorderColor(ConfigManager.getPrimaryColor());
        btnEliminar.setBorderThickness(1.5f);
        
    }//GEN-LAST:event_jblEliminarMouseEntered

    private void btnEliminarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseEntered
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnEliminarMouseEntered

    private void btnEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseExited
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnEliminarMouseExited

    private void jblEliminarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblEliminarMouseExited
        // TODO add your handling code here:
        btnEliminar.setBorderColor(Color.BLACK);
        btnEliminar.setBorderThickness(1.0f);
    }//GEN-LAST:event_jblEliminarMouseExited

    private void jblEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblEliminarMouseClicked
        // TODO add your handling code here:
        CustomDLG confirmar = new CustomDLG(inicio, true, "Seguro", ConstantesGUI.ADVERTENCIA);
        confirmar.setVisible(true);
        
        if(confirmar.isConfirmado()){
            control.eliminarAnio(anio);
        }
        
    }//GEN-LAST:event_jblEliminarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.CustomPanelRedondo btnEliminar;
    private javax.swing.JPanel jPanelEncabezado;
    private javax.swing.JLabel jblDo;
    private javax.swing.JLabel jblEliminar;
    private javax.swing.JLabel jblFecha;
    private javax.swing.JLabel jblInicio1;
    private javax.swing.JLabel jblJu;
    private javax.swing.JLabel jblLu;
    private javax.swing.JLabel jblLu1;
    private javax.swing.JLabel jblMa;
    private javax.swing.JLabel jblMi;
    private javax.swing.JLabel jblPorcentaje;
    private javax.swing.JLabel jblSa;
    private javax.swing.JLabel jblVi;
    private vista.CustomPanelRedondo panelContenedorAnio;
    private vista.CustomPanelRedondo panelDo;
    private vista.CustomPanelRedondo panelFrecuencia;
    private vista.CustomPanelRedondo panelJu;
    private vista.CustomPanelRedondo panelLu;
    private vista.CustomPanelRedondo panelMa;
    private vista.CustomPanelRedondo panelMi;
    private vista.CustomPanelRedondo panelNombre;
    private vista.CustomPanelRedondo panelSa;
    private vista.CustomPanelRedondo panelVi;
    private javax.swing.JTextField txtNomObjetivo;
    // End of variables declaration//GEN-END:variables


}
