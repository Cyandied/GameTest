package object;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ObjBoots extends SuperObejct {

        public ObjBoots(GamePanel gp) {

        name = "Boots";
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/boot.png"));
            image = util.scale_image(image, gp.tile_size, gp.tile_size);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
