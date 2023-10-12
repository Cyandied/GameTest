package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UtilityTool {
    
    public BufferedImage scale_image(BufferedImage orig, int width, int height){

        BufferedImage scaled_img = new BufferedImage(width, height, 2);
        Graphics2D g2 = scaled_img.createGraphics();
        g2.drawImage(orig, 0, 0, width, height, null);
        g2.dispose();

        return scaled_img;

    }

}
