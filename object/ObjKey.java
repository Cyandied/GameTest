package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjKey extends SuperObejct {
    
    public ObjKey(GamePanel gp){

        name = "Key";
        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/key.png"));
            image = util.scale_image(image, gp.tile_size, gp.tile_size);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
