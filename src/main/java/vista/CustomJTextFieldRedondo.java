/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import javax.swing.JTextField;

/**
 *
 * @author Sergio Arturo
 */
public class CustomJTextFieldRedondo extends JTextField{
    
    
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;
    private Color borderColor = Color.BLACK;
    private float borderThickness = 1.0f;
    private boolean borde = false;
    
    public CustomJTextFieldRedondo() {
        super.setOpaque(false);
        
    }
    

    public CustomJTextFieldRedondo(int roundTopLeft, int roundTopRight, int roundBottomLeft, int roundBottomRight, boolean borde) {
        super.setOpaque(false);
        
        this.roundBottomLeft = roundBottomLeft;
        this.roundBottomRight = roundBottomRight;
        this.roundTopLeft = roundTopLeft;
        this.roundTopRight = roundTopRight;
        this.borde = borde;
        
    }
    
    
    @Override
    protected void paintComponent(Graphics g) {
        

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // El truco está aquí: restamos el grosor del borde al ancho y alto total
        float t = borderThickness;
        double x = t / 2.0;
        double y = t / 2.0;
        double w = getWidth() - t;
        double h = getHeight() - t;

        Path2D.Double path = new Path2D.Double();

        // Esquina Superior Izquierda (usando x, y como origen)
        path.moveTo(x + roundTopLeft, y);

        // Superior Derecha
        path.lineTo(x + w - roundTopRight, y);
        path.quadTo(x + w, y, x + w, y + roundTopRight);

        // Inferior Derecha
        path.lineTo(x + w, y + h - roundBottomRight);
        path.quadTo(x + w, y + h, x + w - roundBottomRight, y + h);

        // Inferior Izquierda
        path.lineTo(x + roundBottomLeft, y + h);
        path.quadTo(x, y + h, x, y + h - roundBottomLeft);

        // Cerrar en Superior Izquierda
        path.lineTo(x, y + roundTopLeft);
        path.quadTo(x, y, x + roundTopLeft, y);
        path.closePath();

        // 1. Pintar el Fondo
        g2.setColor(getBackground());
        g2.fill(path);

        // 2. Pintar el Borde
        if(borde){
            g2.setColor(borderColor);
            g2.setStroke(new BasicStroke(borderThickness));
            g2.draw(path);
        }

        g2.dispose();
        
        super.paintComponent(g);
    }
    
    

    public int getRoundTopLeft() {
        return roundTopLeft;
    }
    
    public void setRoundTopLeft(int r) { 
        this.roundTopLeft = r; repaint(); 
    }

    public int getRoundTopRight() { 
        return roundTopRight;
    }
    
    public void setRoundTopRight(int r) { 
        this.roundTopRight = r; repaint(); 
    }

    public int getRoundBottomLeft() { 
        return roundBottomLeft;
    }
    public void setRoundBottomLeft(int r) {
        this.roundBottomLeft = r; repaint();
    }

    public int getRoundBottomRight() { 
        return roundBottomRight;
    }
    
    public void setRoundBottomRight(int r) { 
        this.roundBottomRight = r; repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }
    public void setBorderColor(Color color) {
        this.borderColor = color; repaint();
    }

    public float getBorderThickness() {
        return borderThickness;
    }
    
    public void setBorderThickness(float t) {
        this.borderThickness = t; repaint();
    }
    
    
    
}
