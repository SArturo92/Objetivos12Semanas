/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package vista;

import controlador.Controlador;
import dto.AnioDTO;
import dto.DiasDTO;
import dto.SemanaDTO;
import modelo.ConfigManager;
import dto.TacticaDTO;
import java.awt.AWTEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import util.MetodosCompartidos;
import util.Animaciones;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Locale;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javax.swing.BorderFactory;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.fxmisc.richtext.CodeArea;
import patrones.IPatronObservador;
import patrones.IEstadoChangeListener;
import patrones.PatronFormularioNuevoAnio;

/**
 *
 * @author Sergio Arturo
 */
public class ViewJPanelNuevoAnio extends javax.swing.JPanel implements IPatronObservador, IEstadoChangeListener{

    
    private byte numTacticas = 0;
    Locale locale = new Locale("es", "ES");
    
    private final PatronFormularioNuevoAnio estado = new PatronFormularioNuevoAnio();
    
    
    private Map<Long, DesingJPanelAddTactica> listaTacticas = new HashMap();
    
    private final Font fuente = MetodosCompartidos.cargarFuente("/fonts/OpenSans-Bold.ttf", 20f);
    private String selectedDia = "domingo";

    CodeArea editor;
    
    Controlador control;
            
    
    /**
     * Creates new form JPanelNuevoAnio
     */
    public ViewJPanelNuevoAnio(Controlador control) {
        initComponents();
        this.control = control;
       
        ConfigManager.addObserver(this);
        
        cargarDiseno();
    }
    
    
    private void cargarDiseno(){
        MetodosCompartidos.addHint(txtNomObjetivo, "Nombre del Objetivo");
        MetodosCompartidos.cargarIcono(jblIconAddTactica, "/iconos/add_ic.svg", 32, ConfigManager.getAcceptColor());
        MetodosCompartidos.limitarPorAncho(txtNomObjetivo);
        
        jblAdvertenciaNombreVacio.setVisible(false);
        jblAdvertencia.setVisible(false);
        jblAdvertenciaObjetivo.setVisible(false);
        jPanelSeperador2.setVisible(false);
        jPanelSeperador3.setVisible(false);
        jPanelSeperador4.setVisible(false);
        jPanelSeperador5.setVisible(false);
        
        
        cbManiana.setOpaque(false);
        
        agregarComboBoxFX();
        

        
        Toolkit.getDefaultToolkit().addAWTEventListener(event -> {
            if (event instanceof MouseEvent me && me.getID() == MouseEvent.MOUSE_PRESSED) {

                Component c = me.getComponent();
                if (!SwingUtilities.isDescendingFrom(c, jblAdvertencia)) {
                    jblAdvertencia.setVisible(false);
                }
            }
        }, AWTEvent.MOUSE_EVENT_MASK);

        
        
        cargarColores();
        cargarEditorTextoObjetivo();
        agregarValidadorNomObjetivo();
        
        agregarNuevaTactica();
        onEstadoChange();
        

    }
    
    
    
    private void cargarColores(){
        jPanelEncabezado.setBackground(ConfigManager.getPrimaryColor());
        txtNomObjetivo.setBackground(ConfigManager.getPrimaryColor());
        btnGuardar.setBackground(ConfigManager.getAcceptColor());
        
    }

    private void cargarEditorTextoObjetivo(){
        
        JFXPanel fxPanel = new JFXPanel();
        fxPanel.setBounds(20, 50, 320, 200);
        jPanelSeccionObjetivo.add(fxPanel);

        Platform.runLater(() -> {

            editor = new CodeArea();
            editor.setWrapText(true);
            //editor.replaceText("Lograr +2 domindas para antes del 2 de febrero");

            var fontFx = MetodosCompartidos.cargarFuenteFX("/fonts/OpenSans-Bold.ttf", 20);

            String textColor = "#9E9E9E";
            

            String css = """
            .code-area {
                -fx-background-color: %s;
                -fx-font-family: '%s';
                -fx-font-size: 20px;
            }

            .code-area .text {
                -fx-fill: %s;
            }

            .code-area .caret {
                -fx-stroke: %s;
                -fx-stroke-width: 1.5;
            }
            """.formatted(
                ConfigManager.toHex(ConfigManager.getBackgroundColor()),
                fontFx.getFamily(),
                textColor,
                ConfigManager.toHex(ConfigManager.getTextColor())
            );

            editor.getStylesheets().add(
                "data:text/css," + css.replace("\n", "")
            );
            

        final boolean[] internalChange = { false };
        int MAX_CHARS = 120;


        editor.plainTextChanges()
              .subscribe(change -> {

                  if (internalChange[0]) return;

                  int currentLength = editor.getLength();
                  int inserted = change.getInserted().length();
                  int removed  = change.getRemoved().length();

                  int newLength = currentLength - removed + inserted;

                  jblAdvertenciaObjetivo.setVisible(false);
                  


                  if (newLength > MAX_CHARS) {
                      jblAdvertenciaObjetivo.setVisible(true);                      

                      int allowed = MAX_CHARS - (currentLength - removed);

                      internalChange[0] = true;

                      Platform.runLater(() -> {
                          try {
                              if (allowed <= 0) {

                                  editor.replaceText(
                                      change.getPosition(),
                                      change.getPosition() + inserted,
                                      ""
                                  );
                              } else {

                                  String trimmed = change.getInserted()
                                                          .substring(0, allowed);

                                  editor.replaceText(
                                      change.getPosition(),
                                      change.getPosition() + inserted,
                                      trimmed
                                  );
                              }
                          }
                          finally {
                              internalChange[0] = false;
                          }
                      });
                  }
                  
                  comprobarFilas();
                      estado.setObjetivoValido(!editor.getText().trim().isBlank());
                      onEstadoChange();
                  
                  
              });


        editor.addEventFilter(KeyEvent.KEY_TYPED, e -> {
            if (editor.getLength() >= MAX_CHARS) {
                e.consume();
            }
        });


        fxPanel.setScene(new Scene(new StackPane(editor)));
        });
        
        
        
        
        
    
    
    }
    
    
    private void comprobarFilas() {

        Platform.runLater(() -> {

            editor.layout();

            int filas = editor.getParagraphLinesCount(0);

            SwingUtilities.invokeLater(() -> {
                jPanelSeperador2.setVisible(filas >= 2);
                jPanelSeperador3.setVisible(filas >= 3);
                jPanelSeperador4.setVisible(filas >= 4);
                jPanelSeperador5.setVisible(filas >= 5);
            });
        });
    }

    
    private void agregarValidadorNomObjetivo(){
        txtNomObjetivo.getDocument().addDocumentListener(new DocumentListener() {

        private void validar() {
            if (txtNomObjetivo.getText().trim().isEmpty()){
                jblAdvertenciaNombreVacio.setVisible(true);
                estado.setNombreValido(false);
            }  
            else{
                jblAdvertenciaNombreVacio.setVisible(false);
                estado.setNombreValido(true);     
                }    
            
            actualizarBoton();

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
    
    
    
    private void agregarComboBoxFX(){
        
        CustomPanelRedondo combo = new CustomPanelRedondo(2, 2, 2, 2, false);
        combo.setLayout(null);
        combo.setBounds(31, 500, 250, 32);
        combo.setBackground(ConfigManager.getBackgroundOptionColor());


        JLabel lblValue = new JLabel("Día de rendición de cuentas");
        lblValue.setFont(MetodosCompartidos.cargarFuente("/fonts/OpenSans-Bold.ttf", 12));
        lblValue.setBounds(10, 0, 200, 32);
        lblValue.setForeground(ConfigManager.getTextColor());
        combo.add(lblValue);


        JLabel lblArrow = new JLabel("▼");
        lblArrow.setBounds(225, 0, 20, 32);
        lblArrow.setForeground(ConfigManager.getTextColor());
       // lblArrow.setHorizontalAlignment(SwingConstants.CENTER);
        combo.add(lblArrow);



        JPopupMenu popup = new JPopupMenu();
        popup.setBorder(BorderFactory.createEmptyBorder());

        String[] dias = {
            "Domingo", "Lunes", "Martes",
            "Miércoles", "Jueves", "Viernes", "Sábado"
        };
        
        final JPanel[] selectedItem = { null };

        for (String dia : dias) {
            JPanel item = new JPanel(null);
            item.setPreferredSize(new Dimension(250, 32));
            item.setBackground(ConfigManager.getBackgroundOptionColor());
            item.setOpaque(true);

            JLabel lbl = new JLabel(dia);
            lbl.setBounds(10, 0, 230, 32);
            lbl.setForeground(ConfigManager.getTextColor());
            item.add(lbl);
            
            item.addMouseListener(new MouseAdapter() {
               @Override
               public void mouseEntered(MouseEvent e) {
                   if (item != selectedItem[0]) {
                       item.setBackground(ConfigManager.getPrimaryColor());
                   }
               }

               @Override
               public void mouseExited(MouseEvent e) {
                   if (item != selectedItem[0]) {
                       item.setBackground(ConfigManager.getBackgroundOptionColor());
                   }
               }

               @Override
               public void mouseClicked(MouseEvent e) {
                    if (selectedItem[0] != null) {
                        selectedItem[0].setBackground(
                            ConfigManager.getBackgroundOptionColor()
                        );
                    }

                    selectedItem[0] = item;
                    item.setBackground(ConfigManager.getPrimaryColor());

                    selectedDia = dia;            
                    lblValue.setText(dia);
                    popup.setVisible(false);
                }
           });

        popup.add(item);

        }


        combo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                popup.setPopupSize(250, popup.getPreferredSize().height);

                popup.show(combo, 0, -188);
            }
        });


    this.add(combo);


    }

    
    
    
    
    private void validadorNombresYFrecuencia(){
        boolean frecuencia = true;
        boolean nombreTactica = true;
        
        for(Map.Entry<Long, DesingJPanelAddTactica> entry : listaTacticas.entrySet()){
            DesingJPanelAddTactica panel = entry.getValue();
            
            if(panel.getTxtNomTactica().getText().trim().isBlank()) nombreTactica = false; 
            if(!panel.isFrecuenciaValida()) frecuencia = false;
                
            } 
        
        estado.setTacticasValidas(nombreTactica); 
        estado.setFrecuenciaValida(frecuencia); 
    }
    
    
    private void agregarNuevaTactica(){
            
        if(numTacticas > 4){
            jblAdvertencia.setVisible(true);
            return;
        }
            long id = System.nanoTime();

            DesingJPanelAddTactica addTactica = new DesingJPanelAddTactica(estado, this);
            addTactica.setBounds(0, 40 * listaTacticas.size(), 560, 40);


                    
            CustomPanelRedondo panelEliminar = new CustomPanelRedondo(5, 5, 5, 5, false);
                JLabel delete = new JLabel("X");
                delete.setHorizontalAlignment(SwingUtilities.CENTER);
                delete.setVerticalAlignment(SwingUtilities.CENTER);

            panelEliminar.setBackground(ConfigManager.getDeleteColor());
            panelEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
            panelEliminar.add(delete);
            
            
            listaTacticas.put(id, addTactica);
            
            if(numTacticas > 0){
                panelEliminar.setBounds(570, (40 * listaTacticas.size()) - 30 - listaTacticas.size(), 24, 24);
            }

            addTactica.putClientProperty("id", id);
            panelEliminar.putClientProperty("id", id);

            panelEliminar.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    Animaciones.animarFadeOut(addTactica, id, i -> eliminarTactica(i));

                }
                @Override
                public void mouseEntered(MouseEvent e){
                    addTactica.setBorde(true);
                    addTactica.setBorderColor(ConfigManager.getDeleteColor());
                    addTactica.setBorderThickness(3f);
                    
                }
                
                @Override
                public void mouseExited(MouseEvent e){
                    addTactica.setBorderColor(ConfigManager.getBackgroundColor());
                    addTactica.setBorde(false);
                }
                
            });


            panelContenedorAnio.add(panelEliminar);
            
            
            if(numTacticas > 0){
                panelContenedorAnio.setBounds(panelContenedorAnio.getBounds().x,
                        panelContenedorAnio.getBounds().y, panelContenedorAnio.getBounds().width,
                        panelContenedorAnio.getBounds().height + 40);
            }
            

            panelContenedorAnio.add(addTactica);
            panelContenedorAnio.revalidate();
            panelContenedorAnio.repaint();
            
            Animaciones.animarFadeIn(addTactica, () -> {
                addTactica.getTxtNomTactica().setVisible(true);
                }
            );
     

            numTacticas++;


            jbTactica.setText("Tacticas (" + numTacticas + ")");


    }
    
    
    

    
    private void eliminarTactica(long id) {

        if (!SwingUtilities.isEventDispatchThread()) {
            SwingUtilities.invokeLater(() -> eliminarTactica(id));
            return;
        }

        System.out.println("intentando eliminar");
        int altura = 40;
        int yEliminado = -1;

        for (Component c : panelContenedorAnio.getComponents()) {
            if (!(c instanceof JComponent jc)) continue;

            Object cid = jc.getClientProperty("id");
            if (cid != null && cid.equals(id)) {
                yEliminado = jc.getY();
                panelContenedorAnio.remove(jc);
            }
        }

        if (yEliminado == -1) return;

        for (Component c : panelContenedorAnio.getComponents()) {
            if (c.getY() > yEliminado) {
                c.setBounds(
                    c.getX(),
                    c.getY() - altura,
                    c.getWidth(),
                    c.getHeight()
                );
            }
        }

        listaTacticas.remove(id);
        numTacticas--;

        panelContenedorAnio.revalidate();
        panelContenedorAnio.repaint();

        jbTactica.setText("Tacticas (" + numTacticas + ")");

        
    }
    
    
    
    private void actualizarBoton() {
        if(estado.esValido()){
           btnGuardar.setEnabled(true);
           btnGuardar.setBackground(ConfigManager.getAcceptColor());
           btnGuardar.setCursor(new Cursor(12));
        }
        else{
            btnGuardar.setEnabled(false);
            btnGuardar.setBackground(ConfigManager.getBackgroundOptionColor());
            btnGuardar.setCursor(new Cursor(1));
        }

    }
    
   
    private boolean estaSeleccionado(JComponent c) {
        return Boolean.TRUE.equals(c.getClientProperty("selected"));
    }
    
    

    public JPanel getjPanelEncabezado() {
        return jPanelEncabezado;
    }
    
 
    
    
    @Override
    public void onColorSaved() {
        cargarColores();
    }
    
    @Override
    public void onEstadoChange() {
        validadorNombresYFrecuencia();
        actualizarBoton();
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

        jPanelSeccionObjetivo = new javax.swing.JPanel();
        jbTactica1 = new javax.swing.JLabel();
        jblAdvertenciaObjetivo2 = new javax.swing.JLabel();
        jblAdvertenciaObjectivo1 = new javax.swing.JLabel();
        jPanelSeperador1 = new javax.swing.JPanel();
        jPanelSeperador2 = new javax.swing.JPanel();
        jPanelSeperador3 = new javax.swing.JPanel();
        jPanelSeperador4 = new javax.swing.JPanel();
        jPanelSeperador5 = new javax.swing.JPanel();
        panelContenedorAnio = new vista.CustomPanelRedondo();
        jPanelEncabezado = new vista.CustomPanelRedondo(15,15,0,0, true);
        txtNomObjetivo = new vista.CustomJTextFieldRedondo(5,5,5,5, false);
        jPanelSeparacion = new vista.CustomPanelRedondo(0,0,0,0, true);
        jbTactica = new javax.swing.JLabel();
        jblAddTactica = new javax.swing.JLabel();
        jblIconAddTactica = new javax.swing.JLabel();
        jPanelSeperador = new javax.swing.JPanel();
        jblAdvertencia = new javax.swing.JLabel();
        panelTituloTabla = new vista.CustomPanelRedondo();
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
        panelNombre = new vista.CustomPanelRedondo();
        jblLu1 = new javax.swing.JLabel();
        jblAdvertenciaObjetivo = new javax.swing.JLabel();
        btnGuardar = new vista.CustomPanelRedondo();
        jbTactica2 = new javax.swing.JLabel();
        jblAdvertenciaNombreVacio = new javax.swing.JLabel();
        cbManiana = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(47, 47, 47));
        setPreferredSize(new java.awt.Dimension(1046, 614));
        setLayout(null);

        jPanelSeccionObjetivo.setBackground(new java.awt.Color(47, 47, 47));
        jPanelSeccionObjetivo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(72, 72, 72), 2));
        jPanelSeccionObjetivo.setLayout(null);

        jbTactica1.setBackground(new java.awt.Color(204, 204, 204));
        jbTactica1.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jbTactica1.setForeground(new java.awt.Color(255, 255, 255));
        jbTactica1.setText("¿Cual es el objetivo de este año?");
        jPanelSeccionObjetivo.add(jbTactica1);
        jbTactica1.setBounds(20, 10, 310, 27);

        jblAdvertenciaObjetivo2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblAdvertenciaObjetivo2.setForeground(new java.awt.Color(255, 255, 255));
        jblAdvertenciaObjetivo2.setText("¿Como sabras cuando lo hayas logrado?");
        jPanelSeccionObjetivo.add(jblAdvertenciaObjetivo2);
        jblAdvertenciaObjetivo2.setBounds(10, 540, 350, 16);

        jblAdvertenciaObjectivo1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblAdvertenciaObjectivo1.setForeground(new java.awt.Color(255, 255, 255));
        jblAdvertenciaObjectivo1.setText("Haz una descripcion concisa");
        jPanelSeccionObjetivo.add(jblAdvertenciaObjectivo1);
        jblAdvertenciaObjectivo1.setBounds(10, 520, 350, 16);

        jPanelSeperador1.setBackground(new java.awt.Color(158, 158, 158));
        jPanelSeperador1.setForeground(new java.awt.Color(158, 158, 158));

        javax.swing.GroupLayout jPanelSeperador1Layout = new javax.swing.GroupLayout(jPanelSeperador1);
        jPanelSeperador1.setLayout(jPanelSeperador1Layout);
        jPanelSeperador1Layout.setHorizontalGroup(
            jPanelSeperador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanelSeperador1Layout.setVerticalGroup(
            jPanelSeperador1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanelSeccionObjetivo.add(jPanelSeperador1);
        jPanelSeperador1.setBounds(22, 78, 315, 1);

        jPanelSeperador2.setBackground(new java.awt.Color(158, 158, 158));
        jPanelSeperador2.setForeground(new java.awt.Color(158, 158, 158));

        javax.swing.GroupLayout jPanelSeperador2Layout = new javax.swing.GroupLayout(jPanelSeperador2);
        jPanelSeperador2.setLayout(jPanelSeperador2Layout);
        jPanelSeperador2Layout.setHorizontalGroup(
            jPanelSeperador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanelSeperador2Layout.setVerticalGroup(
            jPanelSeperador2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanelSeccionObjetivo.add(jPanelSeperador2);
        jPanelSeperador2.setBounds(22, 108, 315, 1);

        jPanelSeperador3.setBackground(new java.awt.Color(158, 158, 158));
        jPanelSeperador3.setForeground(new java.awt.Color(158, 158, 158));

        javax.swing.GroupLayout jPanelSeperador3Layout = new javax.swing.GroupLayout(jPanelSeperador3);
        jPanelSeperador3.setLayout(jPanelSeperador3Layout);
        jPanelSeperador3Layout.setHorizontalGroup(
            jPanelSeperador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanelSeperador3Layout.setVerticalGroup(
            jPanelSeperador3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanelSeccionObjetivo.add(jPanelSeperador3);
        jPanelSeperador3.setBounds(22, 138, 315, 1);

        jPanelSeperador4.setBackground(new java.awt.Color(158, 158, 158));
        jPanelSeperador4.setForeground(new java.awt.Color(158, 158, 158));

        javax.swing.GroupLayout jPanelSeperador4Layout = new javax.swing.GroupLayout(jPanelSeperador4);
        jPanelSeperador4.setLayout(jPanelSeperador4Layout);
        jPanelSeperador4Layout.setHorizontalGroup(
            jPanelSeperador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanelSeperador4Layout.setVerticalGroup(
            jPanelSeperador4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanelSeccionObjetivo.add(jPanelSeperador4);
        jPanelSeperador4.setBounds(22, 168, 315, 1);

        jPanelSeperador5.setBackground(new java.awt.Color(158, 158, 158));
        jPanelSeperador5.setForeground(new java.awt.Color(158, 158, 158));

        javax.swing.GroupLayout jPanelSeperador5Layout = new javax.swing.GroupLayout(jPanelSeperador5);
        jPanelSeperador5.setLayout(jPanelSeperador5Layout);
        jPanelSeperador5Layout.setHorizontalGroup(
            jPanelSeperador5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 315, Short.MAX_VALUE)
        );
        jPanelSeperador5Layout.setVerticalGroup(
            jPanelSeperador5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1, Short.MAX_VALUE)
        );

        jPanelSeccionObjetivo.add(jPanelSeperador5);
        jPanelSeperador5.setBounds(22, 198, 315, 1);

        add(jPanelSeccionObjetivo);
        jPanelSeccionObjetivo.setBounds(660, 20, 370, 570);

        panelContenedorAnio.setBackground(new java.awt.Color(47, 47, 47));
        panelContenedorAnio.setBorde(false);
        panelContenedorAnio.setOpaque(true);
        panelContenedorAnio.setPreferredSize(new java.awt.Dimension(362, 80));
        panelContenedorAnio.setRequestFocusEnabled(false);
        panelContenedorAnio.setLayout(null);
        add(panelContenedorAnio);
        panelContenedorAnio.setBounds(31, 189, 610, 40);

        jPanelEncabezado.setBackground(new java.awt.Color(52, 125, 210));
        jPanelEncabezado.setLayout(null);

        txtNomObjetivo.setBackground(new java.awt.Color(52, 125, 210));
        txtNomObjetivo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        txtNomObjetivo.setForeground(new java.awt.Color(255, 255, 255));
        txtNomObjetivo.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txtNomObjetivo.setBorder(null);
        txtNomObjetivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomObjetivoActionPerformed(evt);
            }
        });
        jPanelEncabezado.add(txtNomObjetivo);
        txtNomObjetivo.setBounds(20, 20, 520, 50);

        jPanelSeparacion.setBackground(new java.awt.Color(255, 255, 255));
        jPanelSeparacion.setLayout(null);

        jbTactica.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jbTactica.setText("Tácticas (1)");
        jPanelSeparacion.add(jbTactica);
        jbTactica.setBounds(10, 12, 140, 27);

        jblAddTactica.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jblAddTactica.setForeground(new java.awt.Color(47, 242, 7));
        jblAddTactica.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jblAddTactica.setText("Añadir");
        jblAddTactica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jblAddTactica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblAddTacticaMouseClicked(evt);
            }
        });
        jPanelSeparacion.add(jblAddTactica);
        jblAddTactica.setBounds(460, 15, 65, 20);

        jblIconAddTactica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jblIconAddTactica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblIconAddTacticaMouseClicked(evt);
            }
        });
        jPanelSeparacion.add(jblIconAddTactica);
        jblIconAddTactica.setBounds(515, 9, 36, 36);

        jPanelSeperador.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanelSeperadorLayout = new javax.swing.GroupLayout(jPanelSeperador);
        jPanelSeperador.setLayout(jPanelSeperadorLayout);
        jPanelSeperadorLayout.setHorizontalGroup(
            jPanelSeperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );
        jPanelSeperadorLayout.setVerticalGroup(
            jPanelSeperadorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanelSeparacion.add(jPanelSeperador);
        jPanelSeperador.setBounds(130, 25, 320, 5);

        jPanelEncabezado.add(jPanelSeparacion);
        jPanelSeparacion.setBounds(0, 80, 560, 50);

        add(jPanelEncabezado);
        jPanelEncabezado.setBounds(31, 20, 560, 130);

        jblAdvertencia.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblAdvertencia.setForeground(new java.awt.Color(255, 51, 51));
        jblAdvertencia.setText("5 tácticas es el numero maximo para que al año sea sostenible");
        add(jblAdvertencia);
        jblAdvertencia.setBounds(240, 1, 350, 16);

        panelTituloTabla.setBackground(new java.awt.Color(255, 255, 255));
        panelTituloTabla.setLayout(null);

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

        panelTituloTabla.add(panelFrecuencia);
        panelFrecuencia.setBounds(215, 0, 345, 40);

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

        panelTituloTabla.add(panelNombre);
        panelNombre.setBounds(0, 0, 216, 40);

        add(panelTituloTabla);
        panelTituloTabla.setBounds(31, 149, 560, 40);

        jblAdvertenciaObjetivo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblAdvertenciaObjetivo.setForeground(new java.awt.Color(255, 255, 51));
        jblAdvertenciaObjetivo.setText("La descripcion no es lo suficientemente conscisa");
        add(jblAdvertenciaObjetivo);
        jblAdvertenciaObjetivo.setBounds(660, 0, 280, 16);

        btnGuardar.setBorde(false);
        btnGuardar.setRoundBottomLeft(5);
        btnGuardar.setRoundBottomRight(5);
        btnGuardar.setRoundTopLeft(5);
        btnGuardar.setRoundTopRight(5);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnGuardarMouseClicked(evt);
            }
        });
        btnGuardar.setLayout(null);

        jbTactica2.setBackground(new java.awt.Color(204, 204, 204));
        jbTactica2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jbTactica2.setForeground(new java.awt.Color(255, 255, 255));
        jbTactica2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jbTactica2.setText("Guadar");
        btnGuardar.add(jbTactica2);
        jbTactica2.setBounds(0, 0, 120, 38);

        add(btnGuardar);
        btnGuardar.setBounds(500, 550, 120, 40);

        jblAdvertenciaNombreVacio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jblAdvertenciaNombreVacio.setForeground(new java.awt.Color(255, 255, 51));
        jblAdvertenciaNombreVacio.setText("El nombre no puede estar vacio");
        add(jblAdvertenciaNombreVacio);
        jblAdvertenciaNombreVacio.setBounds(30, 0, 280, 16);

        cbManiana.setBackground(new java.awt.Color(47, 47, 47));
        cbManiana.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cbManiana.setForeground(new java.awt.Color(255, 255, 255));
        cbManiana.setText("Comenzar mañana");
        cbManiana.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        add(cbManiana);
        cbManiana.setBounds(31, 560, 130, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void jblIconAddTacticaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblIconAddTacticaMouseClicked
        // TODO add your handling code here:
        agregarNuevaTactica();
        onEstadoChange();

    }//GEN-LAST:event_jblIconAddTacticaMouseClicked

    private void jblAddTacticaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblAddTacticaMouseClicked
        // TODO add your handling code here:
        agregarNuevaTactica();
        onEstadoChange();
    }//GEN-LAST:event_jblAddTacticaMouseClicked

    private void txtNomObjetivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomObjetivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomObjetivoActionPerformed

    private void btnGuardarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseClicked
        // TODO add your handling code here:
        if(estado.esValido()){
            
            int identificacion = 1;
            List<TacticaDTO> tacticas = new ArrayList<>();
            
            
            for(Map.Entry<Long, DesingJPanelAddTactica> entry : listaTacticas.entrySet()){
                DesingJPanelAddTactica panel = entry.getValue();
                
                EnumMap<DayOfWeek, Boolean> dias = new EnumMap<>(DayOfWeek.class);
                
                dias.put(DayOfWeek.MONDAY,    estaSeleccionado(panel.getPanelLuSelect()));
                dias.put(DayOfWeek.TUESDAY,   estaSeleccionado(panel.getPanelMaSelect()));
                dias.put(DayOfWeek.WEDNESDAY, estaSeleccionado(panel.getPanelMiSelect()));
                dias.put(DayOfWeek.THURSDAY,  estaSeleccionado(panel.getPanelJuSelect()));
                dias.put(DayOfWeek.FRIDAY,    estaSeleccionado(panel.getPanelViSelect()));
                dias.put(DayOfWeek.SATURDAY,  estaSeleccionado(panel.getPanelSaSelect()));
                dias.put(DayOfWeek.SUNDAY,    estaSeleccionado(panel.getPanelDoSelect()));
                
                
                List<SemanaDTO> listaSemanas = new ArrayList<>();
                List<DiasDTO> listaDias = new ArrayList();
                
                for(int i = 1; i <= 13; i++){
                    SemanaDTO semana = new SemanaDTO();
                    semana.setId(i);
                    semana.setFechaInicio(LocalDate.now().plusDays(7 * (i-1)));
                    semana.setFechaFin(semana.getFechaInicio().plusDays(6));
                    
                    semana.setNombre("Semana " + i);
                    semana.setPorcentaje(0);
                    semana.setRendicionCuentas("");
                    
                    
                    for(int j = 0; j < 7; j++){
                        DiasDTO day = new DiasDTO();
                        day.setId(j + 1);
                        day.setCompletado(false);
                        day.setFecha(semana.getFechaInicio().plusDays(j));
                        day.setSeleccionado(dias.get(day.getFecha().getDayOfWeek()));
                        
                        listaDias.add(day);
                        
                    }
                    
                    
                    semana.setListaDias(listaDias);
                    listaSemanas.add(semana);
                    
                    
                }
                
                
//                for (int i = 1; i <= 84; i++){
//                    DiasDTO dia = new DiasDTO();                           
//                    dia.setId(i);
//                    dia.setFecha(LocalDate.now().plusDays(i));
//                    dia.setNombre(dia.getFecha().getDayOfWeek().getDisplayName(TextStyle.FULL, locale));
//                    dia.setSeleccionado(dias.get(dia.getFecha().getDayOfWeek()));
//                      
//                    listaDias.add(dia);
//                    
//                }
//                
                TacticaDTO tactica = new TacticaDTO();
                tactica.setSemanasDTO(listaSemanas);
                tactica.setNombre(panel.getTxtNomTactica().getText());
                tactica.setId(identificacion);
                
                tacticas.add(tactica);
                
                
            }
            
                AnioDTO anio = new AnioDTO();
                anio.setDescripcion(editor.getText());
                anio.setNombre(txtNomObjetivo.getText());
                anio.setlistaTacticasDTO(tacticas);
                anio.setActivo(true);
              //  anio.setDiaRendicionCuentas((String)cbcDiaRendicionCuentas.getSelectedItem());
                
                if(cbManiana.isSelected()){
                    anio.setFechaInicio(LocalDate.now().plusDays(1));
                }
                else{
                    anio.setFechaInicio(LocalDate.now());
                }
                
                anio.setFechaFin(LocalDate.now().plusDays(84));
                
                control.guardarAnio(anio);
            
        }


    }//GEN-LAST:event_btnGuardarMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private vista.CustomPanelRedondo btnGuardar;
    private javax.swing.JCheckBox cbManiana;
    private javax.swing.JPanel jPanelEncabezado;
    private javax.swing.JPanel jPanelSeccionObjetivo;
    private javax.swing.JPanel jPanelSeparacion;
    private javax.swing.JPanel jPanelSeperador;
    private javax.swing.JPanel jPanelSeperador1;
    private javax.swing.JPanel jPanelSeperador2;
    private javax.swing.JPanel jPanelSeperador3;
    private javax.swing.JPanel jPanelSeperador4;
    private javax.swing.JPanel jPanelSeperador5;
    private javax.swing.JLabel jbTactica;
    private javax.swing.JLabel jbTactica1;
    private javax.swing.JLabel jbTactica2;
    private javax.swing.JLabel jblAddTactica;
    private javax.swing.JLabel jblAdvertencia;
    private javax.swing.JLabel jblAdvertenciaNombreVacio;
    private javax.swing.JLabel jblAdvertenciaObjectivo1;
    private javax.swing.JLabel jblAdvertenciaObjetivo;
    private javax.swing.JLabel jblAdvertenciaObjetivo2;
    private javax.swing.JLabel jblDo;
    private javax.swing.JLabel jblIconAddTactica;
    private javax.swing.JLabel jblJu;
    private javax.swing.JLabel jblLu;
    private javax.swing.JLabel jblLu1;
    private javax.swing.JLabel jblMa;
    private javax.swing.JLabel jblMi;
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
    private vista.CustomPanelRedondo panelTituloTabla;
    private vista.CustomPanelRedondo panelVi;
    private javax.swing.JTextField txtNomObjetivo;
    // End of variables declaration//GEN-END:variables



}
