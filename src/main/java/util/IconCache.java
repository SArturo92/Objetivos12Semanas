/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.ImageIcon;

/**
 *
 * @author Sergio Arturo
 */
public class IconCache {
    
    
    private static final Map<String, ImageIcon> CACHE =
            new ConcurrentHashMap<>();

    private static String key(String path, int logicalSize, Color color, double scale) {
        return path + "|" + logicalSize + "|" + color.getRGB() + "|" + scale;
    }

    public static ImageIcon getIcon(String path, int logicalSize, Color color) {
        double scale = HiDpi.scale();

        String key = key(path, logicalSize, color, scale);

        return CACHE.computeIfAbsent(key, k ->
                new ImageIcon(
                        SVGRenderer.render(
                                path,
                                logicalSize, // 👈 SIEMPRE lógico
                                color
                        )
                )
        );
    }

    public static void clear() {
        CACHE.clear();
    }
    
}
