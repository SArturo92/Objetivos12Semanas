/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.awt.Color;
import java.awt.image.BufferedImage;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.ImageTranscoder;

/**
 *
 * @author Sergio Arturo
 */
public class BufferedImageTranscoder extends ImageTranscoder {
    
    
    private BufferedImage image;
    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public BufferedImage createImage(int w, int h) {
        return new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    public void writeImage(BufferedImage img, TranscoderOutput out) {
        this.image = img;
    }

    public BufferedImage getBufferedImage() {
        return image;
    }
    
}
