/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import modelo.ConfigManager;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import vista.CustomJTextFieldRedondo;
import util.MetodosCompartidos;
import vista.CustomPanelRedondo;
import patrones.IEstadoChangeListener;
import patrones.PatronFormularioNuevoAnio;

/**
 *
 * @author Sergio Arturo
 */
public class DesingJPanelAddTactica extends CustomPanelRedondo {

    
    private float alpha = 1f;
    
    private boolean textoValido = false;
    private boolean frecuenciaValida = true;
    
    PatronFormularioNuevoAnio estado;
    IEstadoChangeListener listener = null;

    
    
    /**
     * Creates new form JPanelAddTactica1
     */
    public DesingJPanelAddTactica(PatronFormularioNuevoAnio estado, IEstadoChangeListener listener) {
        initComponents();
        
        this.estado = estado;
        this.listener = listener;
        
        setRoundBottomLeft(0);
        setRoundBottomRight(0);
        setRoundTopLeft(0);
        setRoundTopRight(0);
        setBorde(true);
        
        
                
        cargarDiseno();
        insertarValidadorTexto();
        
    }


    
    
    private void cargarDiseno(){
        
        panelLuSelect.putClientProperty("selected", true);
        panelMaSelect.putClientProperty("selected", true);
        panelMiSelect.putClientProperty("selected", true);
        panelJuSelect.putClientProperty("selected", true);
        panelViSelect.putClientProperty("selected", true);
        panelSaSelect.putClientProperty("selected", true);
        panelDoSelect.putClientProperty("selected", true);
        
        txtNomTactica.setVisible(false);
        MetodosCompartidos.addHint(txtNomTactica, "Nombre de la tactica");

        
    }
    
    
    private void insertarValidadorTexto(){
        txtNomTactica.getDocument().addDocumentListener(new DocumentListener() {

        private void validar() {
            if (txtNomTactica.getText().trim().isEmpty()){
                panelNombre.setBorderColor(ConfigManager.getWarningColor());
                panelNombre.setBorderThickness(3f);
                panelNombre.setBorde(true);
                textoValido = false;
                
                //estado.setTacticasValidas(textoValido);
            }  
            else{
                panelNombre.setBorderColor(ConfigManager.getBackgroundColor());
                panelNombre.setBorderThickness(1f);
                textoValido = true;  
                //estado.setTacticasValidas(textoValido);
                }    
            
            listener.onEstadoChange();
            
            panelNombre.revalidate();
            panelNombre.repaint();
        }

        @Override
        public void insertUpdate(DocumentEvent e) {
            validar();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            validar();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            validar(); // por compatibilidad
        }
    });
    }
    
   
    private void validarFrecuencia(){
        //Por lo menos 1 tiene que estar seleccionado
        
        if(!(
        (Boolean) panelLuSelect.getClientProperty("selected") ||
        (Boolean) panelMaSelect.getClientProperty("selected") ||
        (Boolean) panelMiSelect.getClientProperty("selected") ||
        (Boolean) panelJuSelect.getClientProperty("selected") ||
        (Boolean) panelViSelect.getClientProperty("selected") ||
        (Boolean) panelSaSelect.getClientProperty("selected") ||
        (Boolean) panelDoSelect.getClientProperty("selected"))) {
            
            colocarBordeFrecuencia(true, panelLuSelect);
            colocarBordeFrecuencia(true, panelMaSelect);
            colocarBordeFrecuencia(true, panelMiSelect);
            colocarBordeFrecuencia(true, panelJuSelect);
            colocarBordeFrecuencia(true, panelViSelect);
            colocarBordeFrecuencia(true, panelSaSelect);
            colocarBordeFrecuencia(true, panelDoSelect);
            frecuenciaValida = false;
           // estado.setFrecuenciaValida(false);
            
        }
        
        else{
            colocarBordeFrecuencia(false, panelLuSelect);
            colocarBordeFrecuencia(false, panelMaSelect);
            colocarBordeFrecuencia(false, panelMiSelect);
            colocarBordeFrecuencia(false, panelJuSelect);
            colocarBordeFrecuencia(false, panelViSelect);
            colocarBordeFrecuencia(false, panelSaSelect);
            colocarBordeFrecuencia(false, panelDoSelect);
            frecuenciaValida = true;
           // estado.setFrecuenciaValida(true);
        }
        
        listener.onEstadoChange();
        
    }
    
    private void colocarBordeFrecuencia(boolean colocar, CustomPanelRedondo panel){
        
        if(colocar){
            panel.setBorderColor(ConfigManager.getWarningColor());
            panel.setBorderThickness(3f);
        }
        else{
            panel.setBorderColor(ConfigManager.getBackgroundColor());
            panel.setBorderThickness(1f);
        }
        
    }
      
    
    
    private void comprobarSeleccion(JPanel panel){
    
        if(Boolean.TRUE.equals(panel.getClientProperty("selected"))){
            panel.setBackground(Color.WHITE);
            panel.putClientProperty("selected", false);
        }
        else{
           panel.setBackground(ConfigManager.getAcceptColor());
           panel.putClientProperty("selected", true); 
        }
        
        validarFrecuencia();
        
    }
    
    
    private void hoverSelecccion(CustomPanelRedondo panel, boolean dentro){
        if(!frecuenciaValida) return;
        
        if (dentro) {
            panel.setBorderColor(ConfigManager.getPrimaryColor());
            panel.setBorde(true);
            panel.setBorderThickness(3f);
        } else {
            panel.setBorderColor(Color.BLACK);
            panel.setBorde(true);
            panel.setBorderThickness(1f);
        }
    }
    
    

    
    
    public JTextField getTxtNomTactica() {
        return txtNomTactica;
    }
    
    
    //Obtencion de paneles
    public CustomPanelRedondo getPanelDoSelect() {
        return panelDoSelect;
    }

    public CustomPanelRedondo getPanelJuSelect() {
        return panelJuSelect;
    }

    public CustomPanelRedondo getPanelLuSelect() {
        return panelLuSelect;
    }

    public CustomPanelRedondo getPanelMaSelect() {
        return panelMaSelect;
    }

    public CustomPanelRedondo getPanelMiSelect() {
        return panelMiSelect;
    }

    public CustomPanelRedondo getPanelNombre() {
        return panelNombre;
    }

    public CustomPanelRedondo getPanelSaSelect() {
        return panelSaSelect;
    }

    public CustomPanelRedondo getPanelViSelect() {
        return panelViSelect;
    }

    
    
    public boolean isTextoValido() {
        return textoValido;
    }

    public boolean isFrecuenciaValida() {
        return frecuenciaValida;
    }
    
    
    

        public void setAlpha(float alpha) {
        this.alpha = Math.max(0f, Math.min(1f, alpha));
        repaint();
    }
    
    public float getAlpha(){
        return alpha;
    }
    
    
    @Override
    public void paint(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setComposite(
            AlphaComposite.getInstance(
                AlphaComposite.SRC_OVER, alpha
            )
        );

        super.paint(g2); // 🔥 pinta fondo + hijos + borde
        g2.dispose();
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelNombre = new vista.CustomPanelRedondo();
        txtNomTactica = new vista.CustomJTextFieldRedondo();
        panelLuSelect = new vista.CustomPanelRedondo();
        panelMaSelect = new vista.CustomPanelRedondo();
        panelMiSelect = new vista.CustomPanelRedondo();
        panelDoSelect = new vista.CustomPanelRedondo();
        panelSaSelect = new vista.CustomPanelRedondo();
        panelViSelect = new vista.CustomPanelRedondo();
        panelJuSelect = new vista.CustomPanelRedondo();

        setPreferredSize(new java.awt.Dimension(560, 40));
        setLayout(null);

        panelNombre.setBackground(new java.awt.Color(255, 255, 255));
        panelNombre.setForeground(new java.awt.Color(255, 255, 255));
        panelNombre.setLayout(null);

        txtNomTactica.setBackground(new java.awt.Color(255, 255, 255));
        txtNomTactica.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtNomTactica.setBorder(null);
        txtNomTactica.setOpaque(true);
        panelNombre.add(txtNomTactica);
        txtNomTactica.setBounds(10, 10, 190, 16);

        add(panelNombre);
        panelNombre.setBounds(0, 0, 216, 40);

        panelLuSelect.setBackground(new java.awt.Color(51, 255, 0));
        panelLuSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelLuSelectpanelLuSelectpanelLuSelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelLuSelectpanelLuSelectpanelLuSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelLuSelectpanelLuSelectpanelLuSelectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelLuSelectLayout = new javax.swing.GroupLayout(panelLuSelect);
        panelLuSelect.setLayout(panelLuSelectLayout);
        panelLuSelectLayout.setHorizontalGroup(
            panelLuSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelLuSelectLayout.setVerticalGroup(
            panelLuSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panelLuSelect);
        panelLuSelect.setBounds(215, 0, 50, 40);

        panelMaSelect.setBackground(new java.awt.Color(51, 255, 0));
        panelMaSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMaSelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMaSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMaSelectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelMaSelectLayout = new javax.swing.GroupLayout(panelMaSelect);
        panelMaSelect.setLayout(panelMaSelectLayout);
        panelMaSelectLayout.setHorizontalGroup(
            panelMaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelMaSelectLayout.setVerticalGroup(
            panelMaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panelMaSelect);
        panelMaSelect.setBounds(264, 0, 50, 40);

        panelMiSelect.setBackground(new java.awt.Color(51, 255, 0));
        panelMiSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelMiSelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelMiSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelMiSelectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelMiSelectLayout = new javax.swing.GroupLayout(panelMiSelect);
        panelMiSelect.setLayout(panelMiSelectLayout);
        panelMiSelectLayout.setHorizontalGroup(
            panelMiSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelMiSelectLayout.setVerticalGroup(
            panelMiSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panelMiSelect);
        panelMiSelect.setBounds(313, 0, 50, 40);

        panelDoSelect.setBackground(new java.awt.Color(51, 255, 0));
        panelDoSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelDoSelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelDoSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelDoSelectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelDoSelectLayout = new javax.swing.GroupLayout(panelDoSelect);
        panelDoSelect.setLayout(panelDoSelectLayout);
        panelDoSelectLayout.setHorizontalGroup(
            panelDoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelDoSelectLayout.setVerticalGroup(
            panelDoSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panelDoSelect);
        panelDoSelect.setBounds(510, 0, 50, 40);

        panelSaSelect.setBackground(new java.awt.Color(51, 255, 0));
        panelSaSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelSaSelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelSaSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelSaSelectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelSaSelectLayout = new javax.swing.GroupLayout(panelSaSelect);
        panelSaSelect.setLayout(panelSaSelectLayout);
        panelSaSelectLayout.setHorizontalGroup(
            panelSaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );
        panelSaSelectLayout.setVerticalGroup(
            panelSaSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panelSaSelect);
        panelSaSelect.setBounds(460, 0, 51, 40);

        panelViSelect.setBackground(new java.awt.Color(51, 255, 0));
        panelViSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelViSelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelViSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelViSelectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelViSelectLayout = new javax.swing.GroupLayout(panelViSelect);
        panelViSelect.setLayout(panelViSelectLayout);
        panelViSelectLayout.setHorizontalGroup(
            panelViSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelViSelectLayout.setVerticalGroup(
            panelViSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panelViSelect);
        panelViSelect.setBounds(411, 0, 50, 40);

        panelJuSelect.setBackground(new java.awt.Color(51, 255, 0));
        panelJuSelect.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelJuSelectMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                panelJuSelectMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                panelJuSelectMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelJuSelectLayout = new javax.swing.GroupLayout(panelJuSelect);
        panelJuSelect.setLayout(panelJuSelectLayout);
        panelJuSelectLayout.setHorizontalGroup(
            panelJuSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelJuSelectLayout.setVerticalGroup(
            panelJuSelectLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );

        add(panelJuSelect);
        panelJuSelect.setBounds(362, 0, 50, 40);
    }// </editor-fold>//GEN-END:initComponents

    private void panelMaSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMaSelectMouseClicked
        // TODO add your handling code here:
        comprobarSeleccion(panelMaSelect);
    }//GEN-LAST:event_panelMaSelectMouseClicked

    private void panelMaSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMaSelectMouseEntered
        // TODO add your handling code here:
        hoverSelecccion(panelMaSelect, true);
    }//GEN-LAST:event_panelMaSelectMouseEntered

    private void panelMaSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMaSelectMouseExited
        // TODO add your handling code here:
        hoverSelecccion(panelMaSelect, false);
    }//GEN-LAST:event_panelMaSelectMouseExited

    private void panelMiSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMiSelectMouseClicked
        // TODO add your handling code here:
        comprobarSeleccion(panelMiSelect);
    }//GEN-LAST:event_panelMiSelectMouseClicked

    private void panelMiSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMiSelectMouseEntered
        // TODO add your handling code here:
        hoverSelecccion(panelMiSelect, true);
    }//GEN-LAST:event_panelMiSelectMouseEntered

    private void panelMiSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelMiSelectMouseExited
        // TODO add your handling code here:
        hoverSelecccion(panelMiSelect, false);
    }//GEN-LAST:event_panelMiSelectMouseExited

    private void panelJuSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJuSelectMouseClicked
        // TODO add your handling code here:
        comprobarSeleccion(panelJuSelect);
    }//GEN-LAST:event_panelJuSelectMouseClicked

    private void panelJuSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJuSelectMouseEntered
        // TODO add your handling code here:
        hoverSelecccion(panelJuSelect, true);
    }//GEN-LAST:event_panelJuSelectMouseEntered

    private void panelJuSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelJuSelectMouseExited
        // TODO add your handling code here:
        hoverSelecccion(panelJuSelect, false);
    }//GEN-LAST:event_panelJuSelectMouseExited

    private void panelViSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViSelectMouseClicked
        // TODO add your handling code here:
        comprobarSeleccion(panelViSelect);
    }//GEN-LAST:event_panelViSelectMouseClicked

    private void panelViSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViSelectMouseEntered
        // TODO add your handling code here:
        hoverSelecccion(panelViSelect, true);
    }//GEN-LAST:event_panelViSelectMouseEntered

    private void panelViSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelViSelectMouseExited
        // TODO add your handling code here:
        hoverSelecccion(panelViSelect, false);
    }//GEN-LAST:event_panelViSelectMouseExited

    private void panelSaSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSaSelectMouseClicked
        // TODO add your handling code here:
        comprobarSeleccion(panelSaSelect);
    }//GEN-LAST:event_panelSaSelectMouseClicked

    private void panelSaSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSaSelectMouseEntered
        // TODO add your handling code here:
        hoverSelecccion(panelSaSelect, true);
    }//GEN-LAST:event_panelSaSelectMouseEntered

    private void panelSaSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelSaSelectMouseExited
        // TODO add your handling code here:
        hoverSelecccion(panelSaSelect, false);
    }//GEN-LAST:event_panelSaSelectMouseExited

    private void panelDoSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDoSelectMouseClicked
        // TODO add your handling code here:
        comprobarSeleccion(panelDoSelect);
    }//GEN-LAST:event_panelDoSelectMouseClicked

    private void panelDoSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDoSelectMouseEntered
        // TODO add your handling code here:
        hoverSelecccion(panelDoSelect, true);
    }//GEN-LAST:event_panelDoSelectMouseEntered

    private void panelDoSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelDoSelectMouseExited
        // TODO add your handling code here:
        hoverSelecccion(panelDoSelect, false);
    }//GEN-LAST:event_panelDoSelectMouseExited

    private void panelLuSelectpanelLuSelectpanelLuSelectMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLuSelectpanelLuSelectpanelLuSelectMouseExited
        // TODO add your handling code here:
        hoverSelecccion(panelLuSelect, false);
    }//GEN-LAST:event_panelLuSelectpanelLuSelectpanelLuSelectMouseExited

    private void panelLuSelectpanelLuSelectpanelLuSelectMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLuSelectpanelLuSelectpanelLuSelectMouseEntered
        // TODO add your handling code here:
        hoverSelecccion(panelLuSelect, true);
    }//GEN-LAST:event_panelLuSelectpanelLuSelectpanelLuSelectMouseEntered

    private void panelLuSelectpanelLuSelectpanelLuSelectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelLuSelectpanelLuSelectpanelLuSelectMouseClicked
        // TODO add your handling code here:
        comprobarSeleccion(panelLuSelect);
        
    }//GEN-LAST:event_panelLuSelectpanelLuSelectpanelLuSelectMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.CustomPanelRedondo panelDoSelect;
    private vista.CustomPanelRedondo panelJuSelect;
    private vista.CustomPanelRedondo panelLuSelect;
    private vista.CustomPanelRedondo panelMaSelect;
    private vista.CustomPanelRedondo panelMiSelect;
    private vista.CustomPanelRedondo panelNombre;
    private vista.CustomPanelRedondo panelSaSelect;
    private vista.CustomPanelRedondo panelViSelect;
    private javax.swing.JTextField txtNomTactica;
    // End of variables declaration//GEN-END:variables
}
