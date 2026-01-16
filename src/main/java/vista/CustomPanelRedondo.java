/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Path2D;
import modelo.ConfigManager;

/**
 *
 * @author Sergio Arturo
 */
public class CustomPanelRedondo extends javax.swing.JPanel{
    
    
    private int roundTopLeft = 0;
    private int roundTopRight = 0;
    private int roundBottomLeft = 0;
    private int roundBottomRight = 0;
    private Color borderColor = Color.BLACK;
    private float borderThickness = 1.0f;
    private boolean borde = true;
    private boolean mostrarLinea = false;


    
    
    
    public CustomPanelRedondo() {
        super.setOpaque(false);
        
    }
    

    public CustomPanelRedondo(int roundTopLeft, int roundTopRight, int roundBottomLeft, int roundBottomRight, boolean borde) {
        super.setOpaque(false);
        
        this.roundBottomLeft = roundBottomLeft;
        this.roundBottomRight = roundBottomRight;
        this.roundTopLeft = roundTopLeft;
        this.roundTopRight = roundTopRight;
        this.borde = borde;
        
        
    }
    
    
    
 
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        float t = borderThickness;
        double x = t / 2.0;
        double y = t / 2.0;
        double w = getWidth() - t;
        double h = getHeight() - t;

        Path2D.Double path = new Path2D.Double();

        path.moveTo(x + roundTopLeft, y);
        path.lineTo(x + w - roundTopRight, y);
        path.quadTo(x + w, y, x + w, y + roundTopRight);
        path.lineTo(x + w, y + h - roundBottomRight);
        path.quadTo(x + w, y + h, x + w - roundBottomRight, y + h);
        path.lineTo(x + roundBottomLeft, y + h);
        path.quadTo(x, y + h, x, y + h - roundBottomLeft);
        path.lineTo(x, y + roundTopLeft);
        path.quadTo(x, y, x + roundTopLeft, y);
        path.closePath();

        g2.setColor(getBackground());
        g2.fill(path);
        
        if (mostrarLinea) {
        dibujarLineaInclinada(g2, w, h, t);
    }

        g2.dispose();
    }


    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        if (!borde) return;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        float t = borderThickness;
        double x = t / 2.0;
        double y = t / 2.0;
        double w = getWidth() - t;
        double h = getHeight() - t;

        Path2D.Double path = new Path2D.Double();

        path.moveTo(x + roundTopLeft, y);
        path.lineTo(x + w - roundTopRight, y);
        path.quadTo(x + w, y, x + w, y + roundTopRight);
        path.lineTo(x + w, y + h - roundBottomRight);
        path.quadTo(x + w, y + h, x + w - roundBottomRight, y + h);
        path.lineTo(x + roundBottomLeft, y + h);
        path.quadTo(x, y + h, x, y + h - roundBottomLeft);
        path.lineTo(x, y + roundTopLeft);
        path.quadTo(x, y, x + roundTopLeft, y);
        path.closePath();

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.draw(path);

        g2.dispose();
    }

    
    private void dibujarLineaInclinada(Graphics2D g2, double w, double h, float t) {

        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );

        g2.setColor(Color.decode("#000000"));

        g2.setStroke(new BasicStroke(
            1f,                          // grosor de la línea
            BasicStroke.CAP_ROUND,       // extremos redondos
            BasicStroke.JOIN_ROUND
        ));

        int margen = 0;

        // de abajo-izquierda a arriba-derecha
        g2.drawLine(
            margen,
            margen,
            getWidth() - margen,
            getHeight() - margen
        );
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
        this.roundBottomRight = r; 
        repaint();
    }

    public Color getBorderColor() {
        return borderColor;
    }
    public void setBorderColor(Color color) {
        this.borderColor = color; 
        repaint();
    }

    public float getBorderThickness() {
        return borderThickness;
    }
    
    public void setBorderThickness(float t) {
        this.borderThickness = t; 
        repaint();
    }

    public boolean isBorde() {
        return borde;
    }

    public void setBorde(boolean borde) {
        this.borde = borde;
    }
    
    
    public void setMostrarLinea(boolean mostrar) {
        this.mostrarLinea = mostrar;
        repaint();
    }
    
    
    
    
    
    
}
