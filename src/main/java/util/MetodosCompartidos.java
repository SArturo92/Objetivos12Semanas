/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.InputStream;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Sergio Arturo
 */
public class MetodosCompartidos {
    
    
    public MetodosCompartidos(){
    
    }
    
    
    
    public static void cargarIcono(JLabel label, String ruta, int logicalSize, Color color) {
        ImageIcon icon = IconCache.getIcon( ruta, logicalSize, color);

        label.setIcon(icon);
        label.setBorder(null);
    }
    
    
    public static void addHint(JTextField textField, String hint) {
        textField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                textField.repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                textField.repaint();
            }
        });

        textField.setLayout(new BorderLayout());
        JLabel hintLabel = new JLabel(hint);
        hintLabel.setForeground(Color.decode("#B0B0B0"));
        hintLabel.setFont(textField.getFont().deriveFont(Font.BOLD));
        hintLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // Usa un overlay para que no bloquee la escritura
        textField.add(hintLabel);

        // Actualiza visibilidad dinámicamente
        Timer timer = new Timer(100, e -> {
            hintLabel.setVisible(textField.getText().isEmpty() && !textField.isFocusOwner());
        });
        timer.start();
    }
    
    
    
    public static void addHintContra(JPasswordField field, String hint) {
       final JLabel hintLabel = new JLabel(hint);
       hintLabel.setForeground(Color.GRAY);
       hintLabel.setFont(field.getFont().deriveFont(Font.ITALIC));
       hintLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

       // Usamos overlay en vez de modificar el JPasswordField
       field.setLayout(new BorderLayout());
       field.add(hintLabel);

       Runnable actualizar = () -> {
           boolean vacio = field.getPassword().length == 0;
           boolean foco = field.isFocusOwner();
           hintLabel.setVisible(vacio && !foco);
       };

       // Foco
       field.addFocusListener(new FocusAdapter() {
           @Override public void focusGained(FocusEvent e) { actualizar.run(); }
           @Override public void focusLost(FocusEvent e) { actualizar.run(); }
       });

       // Cambio de texto (cuando escribes)
       field.getDocument().addDocumentListener(new DocumentListener() {
           @Override public void insertUpdate(DocumentEvent e) { actualizar.run(); }
           @Override public void removeUpdate(DocumentEvent e) { actualizar.run(); }
           @Override public void changedUpdate(DocumentEvent e) { actualizar.run(); }
       });

       // Estado inicial
       actualizar.run();
   }
   
   
   // Metodo para Swing
   public static Font cargarFuente(String path, float size) {
        try (InputStream is = MetodosCompartidos.class.getResourceAsStream(path)) {
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(size);
        } catch (Exception e) {
            e.printStackTrace();
            return new Font("SansSerif", Font.PLAIN, (int) size);
        }
    }
   
   
   //Metodo para JavaFX
   public static javafx.scene.text.Font cargarFuenteFX(String path, double size) {
        try (InputStream is = MetodosCompartidos.class.getResourceAsStream(path)) {
            return javafx.scene.text.Font.loadFont(is, size);
        } catch (Exception e) {
            e.printStackTrace();
            return javafx.scene.text.Font.font("System", size);
        }
    }
    
   
   
   public static void limitarPorAncho(JTextField textField) {

    AbstractDocument doc = (AbstractDocument) textField.getDocument();

    doc.setDocumentFilter(new DocumentFilter() {

        @Override
        public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr)
                throws BadLocationException {

            if (textoCabe(fb, offset, 0, text)) {
                super.insertString(fb, offset, text, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
                throws BadLocationException {

            if (textoCabe(fb, offset, length, text)) {
                super.replace(fb, offset, length, text, attrs);
            }
        }

        private boolean textoCabe(FilterBypass fb, int offset, int length, String nuevoTexto)
                throws BadLocationException {

            String actual = fb.getDocument().getText(0, fb.getDocument().getLength());

            String resultado =
                    actual.substring(0, offset)
                    + nuevoTexto
                    + actual.substring(offset + length);

            FontMetrics fm = textField.getFontMetrics(textField.getFont());

            int anchoTexto = fm.stringWidth(resultado);

            int anchoCampo = textField.getWidth()
                    - textField.getInsets().left
                    - textField.getInsets().right;

            return anchoTexto <= anchoCampo;
        }
    });
}

   
    
}
