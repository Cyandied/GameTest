package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;

public class SuperObejct {
    
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int world_x, world_y;
    public Rectangle solid_area = new Rectangle(0,0,48,48);
    public int solid_area_default_x = 0;
    public int solid_area_default_y = 0;
    GamePanel gp;
    UtilityTool util = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp){

        int screen_x = world_x - gp.player.world_x + gp.player.screen_x;
        int screen_y = world_y - gp.player.world_y + gp.player.screen_y;

        if (world_x + gp.tile_size > gp.player.world_x - gp.player.screen_x &&
            world_x - gp.tile_size < gp.player.world_x + gp.player.screen_x &&
            world_y + gp.tile_size > gp.player.world_y - gp.player.screen_y &&
            world_y - gp.tile_size < gp.player.world_y + gp.player.screen_y){

                g2.drawImage(image, screen_x, screen_y, null);

            }

    }

}
