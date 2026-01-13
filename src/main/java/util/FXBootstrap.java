/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;

/**
 *
 * @author Sergio Arturo
 */
public class FXBootstrap {
    
    private static boolean initialized = false;

    private FXBootstrap() {}

    public static synchronized void init() {
        if (initialized) return;

        Platform.setImplicitExit(false);
        new JFXPanel(); // warm-up

        initialized = true;
    }
}
    
