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
import javax.swing.JPanel;

/**
 *
 * @author Sergio Arturo
 */
public class CustomPanelConBorde extends JPanel{
    
    private Color borderColor = Color.BLUE;
    private float borderThickness = 2f;
    private int arc = 12;

    public CustomPanelConBorde() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Fondo
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        g2.dispose();
    }

    @Override
    protected void paintChildren(Graphics g) {
        super.paintChildren(g);

        // 🔥 BORDE ENCIMA
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(
                (int) (borderThickness / 2),
                (int) (borderThickness / 2),
                getWidth() - (int) borderThickness,
                getHeight() - (int) borderThickness,
                arc, arc
        );

        g2.dispose();
    }

    public void setBorderColor(Color color) {
        this.borderColor = color;
        repaint();
    }

    public void setBorderThickness(float thickness) {
        this.borderThickness = thickness;
        repaint();
    }
    
}
