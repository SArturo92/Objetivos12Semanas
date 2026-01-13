/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package vista;

import controlador.Controlador;
import util.AppConfig;
import modelo.ConfigManager;
import java.awt.Color;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javax.swing.SwingUtilities;

import util.MetodosCompartidos;

/**
 *
 * @author Sergio Arturo
 */
public class DesingColorPickerDialog extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DesingColorPickerDialog.class.getName());
    Controlador control;
    
    ColorPicker colorPickerPrimario;
    ColorPicker colorPickerSecundario;
            
    
    Color primario;
    Color secundario;
    
    
    /**
     * Creates new form ColorPickerDialog
     */
    public DesingColorPickerDialog(java.awt.Frame parent, boolean modal, Controlador control) {
        super(parent, modal);
        this.control = control;
                
        initComponents();
                
        SwingUtilities.invokeLater(this::cargarColorPicker);
       
        cargarDiseno();
        
    }
    
    
    private void cargarDiseno(){
                
        MetodosCompartidos.cargarIcono(jblIconPrimario, "/iconos/settings_ic.svg", 32, ConfigManager.getPrimaryColor());
        MetodosCompartidos.cargarIcono(jblIconSecundario, "/iconos/settings_ic.svg", 32, ConfigManager.getSecondaryColor());
        
        jblColorPrimario.setForeground(ConfigManager.getPrimaryColor());
        jblColorSecundario.setForeground(ConfigManager.getSecondaryColor());
        
        
    }
    
    
    
    private void cargarColorPicker() {

        JFXPanel fxPanel = new JFXPanel();
        fxPanel.setBounds(350, 30, 120, 30);
        fxPanel.setOpaque(false);
        fxPanel.setBackground(jPanelElementos.getBackground());


        Platform.runLater(() -> {

            colorPickerPrimario = new ColorPicker(
                    awtToFx(ConfigManager.getPrimaryColor())
            );


            // 🔹 Quitar fondo blanco del control
            colorPickerPrimario.setStyle(
                    "-fx-background-color: transparent;"
            );

            colorPickerPrimario.setOnAction(e -> {
                Color awtColor = fxToAwt(colorPickerPrimario.getValue());

                SwingUtilities.invokeLater(() -> {
                    primario = awtColor;

                    MetodosCompartidos.cargarIcono(jblIconPrimario, "/iconos/settings_ic.svg", 32, primario);
                    jblColorPrimario.setForeground(primario);
                });
            });

            StackPane root = new StackPane(colorPickerPrimario);

            // 🔹 Quitar padding y fondo
            root.setPadding(Insets.EMPTY);
            root.setBackground(Background.EMPTY);

            // 🔹 Scene TRANSPARENTE
            Scene scene = new Scene(root);

            fxPanel.setScene(scene);
        });



        JFXPanel fxPanel2 = new JFXPanel();
        fxPanel2.setBounds(350, 100, 120, 30);
        fxPanel2.setOpaque(false);
        fxPanel2.setBackground(jPanelElementos.getBackground());

            Platform.runLater(() -> {

            colorPickerSecundario = new ColorPicker(
                    awtToFx(ConfigManager.getSecondaryColor())
            );


            // 🔹 Quitar fondo blanco del control
            colorPickerSecundario.setStyle(
                    "-fx-background-color: transparent;"
            );

            colorPickerSecundario.setOnAction(e -> {
                Color awtColor = fxToAwt(colorPickerSecundario.getValue());

                SwingUtilities.invokeLater(() -> {
                    secundario = awtColor;

                    MetodosCompartidos.cargarIcono(jblIconSecundario, "/iconos/settings_ic.svg", 32, secundario);
                    jblColorSecundario.setForeground(secundario);

                });
            });

            StackPane root = new StackPane(colorPickerSecundario);

            // 🔹 Quitar padding y fondo
            root.setPadding(Insets.EMPTY);
            root.setBackground(Background.EMPTY);

            // 🔹 Scene TRANSPARENTE
            Scene scene = new Scene(root);

            fxPanel2.setScene(scene);
        });

        jPanelElementos.add(fxPanel);
        jPanelElementos.add(fxPanel2);
        jPanelElementos.revalidate();
        jPanelElementos.repaint();
    }

    

    public javafx.scene.paint.Color awtToFx(Color c) {
        return javafx.scene.paint.Color.rgb(
                c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha() / 255.0
        );
    }

    public Color fxToAwt(javafx.scene.paint.Color c) {
        return new Color(
                (float) c.getRed(),
                (float) c.getGreen(),
                (float) c.getBlue(),
                (float) c.getOpacity()
        );
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanelElementos = new javax.swing.JPanel();
        jblColorPrimario = new javax.swing.JLabel();
        jblIconPrimario = new javax.swing.JLabel();
        jblIconSecundario = new javax.swing.JLabel();
        jblColorSecundario = new javax.swing.JLabel();
        jblTitulo = new javax.swing.JLabel();
        btnPorDefecto = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSize(new java.awt.Dimension(575, 365));
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(47, 47, 47));
        jPanel1.setLayout(null);

        jPanelElementos.setBackground(new java.awt.Color(67, 67, 67));
        jPanelElementos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(67, 67, 67)));
        jPanelElementos.setLayout(null);

        jblColorPrimario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jblColorPrimario.setText("Color Primario");
        jPanelElementos.add(jblColorPrimario);
        jblColorPrimario.setBounds(100, 30, 160, 30);

        jblIconPrimario.setPreferredSize(new java.awt.Dimension(32, 32));
        jPanelElementos.add(jblIconPrimario);
        jblIconPrimario.setBounds(30, 30, 32, 32);

        jblIconSecundario.setPreferredSize(new java.awt.Dimension(32, 32));
        jPanelElementos.add(jblIconSecundario);
        jblIconSecundario.setBounds(30, 100, 32, 32);

        jblColorSecundario.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jblColorSecundario.setText("Color Secunadario");
        jPanelElementos.add(jblColorSecundario);
        jblColorSecundario.setBounds(100, 100, 170, 30);

        jPanel1.add(jPanelElementos);
        jPanelElementos.setBounds(30, 80, 510, 160);

        jblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jblTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jblTitulo.setText("Personalizar Colores");
        jPanel1.add(jblTitulo);
        jblTitulo.setBounds(30, 30, 270, 30);

        btnPorDefecto.setBackground(new java.awt.Color(67, 67, 67));
        btnPorDefecto.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnPorDefecto.setForeground(new java.awt.Color(255, 255, 255));
        btnPorDefecto.setText("Por defecto");
        btnPorDefecto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPorDefectoActionPerformed(evt);
            }
        });
        jPanel1.add(btnPorDefecto);
        btnPorDefecto.setBounds(420, 40, 120, 30);

        btnGuardar.setBackground(new java.awt.Color(102, 255, 51));
        btnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnGuardar.setForeground(new java.awt.Color(0, 0, 0));
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnGuardar);
        btnGuardar.setBounds(420, 280, 120, 35);

        btnCancelar.setBackground(new java.awt.Color(67, 67, 67));
        btnCancelar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnCancelar.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar);
        btnCancelar.setBounds(30, 280, 120, 35);

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 580, 370);

        setSize(new java.awt.Dimension(589, 373));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnPorDefectoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPorDefectoActionPerformed
        // TODO add your handling code here:
        
        
        Platform.runLater(() ->{
        
            AppConfig a = new AppConfig();

            
            colorPickerPrimario.setValue(awtToFx(Color.decode(a.ui.primaryColor)));
            colorPickerSecundario.setValue(awtToFx(Color.decode(a.ui.secondaryColor)));
            
            jblColorPrimario.setForeground(Color.decode(a.ui.primaryColor));
            jblColorSecundario.setForeground(Color.decode(a.ui.secondaryColor));
            
            MetodosCompartidos.cargarIcono(jblIconPrimario, "/iconos/settings_ic.svg", 32, Color.decode(a.ui.primaryColor));
            MetodosCompartidos.cargarIcono(jblIconSecundario, "/iconos/settings_ic.svg", 32, Color.decode(a.ui.secondaryColor));
            
        
        });
        

    }//GEN-LAST:event_btnPorDefectoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        control.guardarColores(primario, secundario);
        this.dispose();
        
    }//GEN-LAST:event_btnGuardarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnPorDefecto;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanelElementos;
    private javax.swing.JLabel jblColorPrimario;
    private javax.swing.JLabel jblColorSecundario;
    private javax.swing.JLabel jblIconPrimario;
    private javax.swing.JLabel jblIconSecundario;
    private javax.swing.JLabel jblTitulo;
    // End of variables declaration//GEN-END:variables
}
