package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjDoor extends SuperObejct {
    
    public ObjDoor(GamePanel gp){

        name = "Door";
        try {
            
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door.png"));
            image = util.scale_image(image, gp.tile_size, gp.tile_size);

        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;

    }

}
