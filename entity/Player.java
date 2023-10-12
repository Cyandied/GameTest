package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler key_h;

    public final int screen_x;
    public final int screen_y;

    public int has_key = 0;

    public Player(GamePanel gp, KeyHandler key_h) {

        this.gp = gp;
        this.key_h = key_h;

        screen_x = gp.screen_width/2 - gp.tile_size/2;
        screen_y = gp.screen_height/2 - gp.tile_size/2;

        solid_area = new Rectangle(14,23,20,24);
        solid_area_default_x = solid_area.x;
        solid_area_default_y = solid_area.y;

        set_default_values();
        get_player_image();

    }

    public void set_default_values() {

        world_x = gp.tile_size*23;
        world_y = gp.tile_size*21;
        speed = 4;
        direction = "down";

    }

    public void get_player_image() {

            up1 = setup("cy_up_1");
            up2 = setup("cy_up_2");

            down1 = setup("cy_down_1");
            down2 = setup("cy_down_2");

            left1 = setup("cy_left_1");
            left2 = setup("cy_left_2");

            right1 = setup("cy_right_1");
            right2 = setup("cy_right_2");


    }

    public BufferedImage setup(String img_name){

        UtilityTool util = new UtilityTool();
        BufferedImage image = null;
        try {

            image = ImageIO.read(getClass().getResourceAsStream("/res/player/"+img_name+".png"));
            image = util.scale_image(image, gp.tile_size, gp.tile_size);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;

    }

    public void update() {

        if (key_h.up_pressed || key_h.down_pressed || key_h.left_pressed || key_h.right_pressed) {

            if (key_h.up_pressed) {
                direction = "up";
            } else if (key_h.down_pressed) {
                direction = "down";
            } else if (key_h.left_pressed) {
                direction = "left";
            } else if (key_h.right_pressed) {
                direction = "right";
            }

            //CHECK TILE COLLISION
            collision_on = false;
            gp.c_check.check_tile(this);

            //CHECK OBJECT COLLISION
            int object_index = gp.c_check.check_object(this, true);
            pick_up_object(object_index);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(!collision_on){

                switch(direction) {
                    case "up":
                        world_y -= speed;    
                        break;
                    case "down":
                        world_y += speed;
                        break;
                    case "left":
                        world_x -= speed;
                        break;
                    case "right":
                        world_x += speed;
                        break;
                }

            }

            sprite_counter++;
            if (sprite_counter > 10) {
                if (sprite_num == 1) {
                    sprite_num = 2;
                } else if (sprite_num == 2) {
                    sprite_num = 1;
                }
                sprite_counter = 0;
            }

        }

    }

    public void pick_up_object(int i){

        if(i != 999){

            String object_name = gp.obj[i].name;

            switch(object_name){

                case "Key":
                    gp.play_sound_effect(1);
                    has_key++;
                    gp.obj[i] = null;
                    gp.ui.show_message("Key got!");
                    break;
                case "Door":
                    if(has_key > 0){
                        gp.play_sound_effect(3);
                        gp.obj[i] = null;
                        has_key--;
                        gp.ui.show_message("Door open!");
                    }
                    else{
                        gp.ui.show_message("No key :(");
                    }
                    break;
                case "Chest":
                    gp.ui.game_done = true;
                    gp.stop_music();
                    gp.play_sound_effect(4);
                    break;
                case "Boots":
                    gp.play_sound_effect(2);
                    speed += 1;
                    gp.obj[i] = null;
                    gp.ui.show_message("Gotta go fast!");
                    break;

            }

        }

    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (sprite_num == 1) {
                    image = up1;
                } else if (sprite_num == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (sprite_num == 1) {
                    image = down1;
                } else if (sprite_num == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (sprite_num == 1) {
                    image = left1;
                } else if (sprite_num == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (sprite_num == 1) {
                    image = right1;
                } else if (sprite_num == 2) {
                    image = right2;
                }
                break;
        }
        g2.drawImage(image, screen_x, screen_y, null);

    }

}
