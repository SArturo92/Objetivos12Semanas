/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;

/**
 *
 * @author Sergio Arturo
 */
public class HiDpi {
    
    
    public static double scale() {
        GraphicsConfiguration gc =
            GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration();

        return gc.getDefaultTransform().getScaleX();
    }

    
}
