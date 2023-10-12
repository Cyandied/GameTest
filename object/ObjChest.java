package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjChest extends SuperObejct {

    public ObjChest(GamePanel gp) {

        name = "Chest";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/chest.png"));
            image = util.scale_image(image, gp.tile_size, gp.tile_size);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
