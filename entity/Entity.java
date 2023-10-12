package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    
    public int world_x,world_y;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int sprite_counter = 0;
    public int sprite_num = 1;

    public Rectangle solid_area;
    public int solid_area_default_x, solid_area_default_y;
    public boolean collision_on = false;

}
