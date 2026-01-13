/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.io.StringReader;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.image.ImageTranscoder;

/**
 *
 * @author Sergio Arturo
 */
public class SVGRenderer {
    
    
     public static BufferedImage render(
            String svgPath,
            int size,
            Color color
    ) {
        try (InputStream is =
                 SVGRenderer.class.getResourceAsStream(svgPath)) {

            if (is == null) {
                throw new RuntimeException("SVG no encontrado: " + svgPath);
            }

            // Inyectar color (currentColor)
            String svg = new String(is.readAllBytes());
            String hex = toHex(color);

            svg = svg.replace(
                "<svg",
                "<svg style=\"color:" + hex + ";\""
            );

            TranscoderInput input =
                new TranscoderInput(new StringReader(svg));

            BufferedImageTranscoder t =
                new BufferedImageTranscoder();

            t.addTranscodingHint(
                ImageTranscoder.KEY_WIDTH,
                (float) size
            );
            t.addTranscodingHint(
                ImageTranscoder.KEY_HEIGHT,
                (float) size
            );

            t.transcode(input, null);
            return t.getBufferedImage();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(Color c) {
        return String.format(
            "#%02x%02x%02x",
            c.getRed(),
            c.getGreen(),
            c.getBlue()
        );
    }

    
}
