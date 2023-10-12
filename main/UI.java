package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.ObjKey;

public class UI {

    GamePanel gp;
    Font alagard_40, alagard_80;
    BufferedImage key_image;
    public boolean message_on = false;
    public String message = "";
    int message_counter = 0;
    public boolean game_done = false;

    double playtime;
    DecimalFormat d_format = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {

        this.gp = gp;
        alagard_40 = new Font("Cascadia Code", Font.PLAIN, 40);
        alagard_80 = new Font("Cascadia Code", Font.BOLD, 75);
        ObjKey key = new ObjKey(gp);
        key_image = key.image;

    }

    public void draw(Graphics2D g2) {

        g2.setFont(alagard_40);
        g2.setColor(Color.white);

        if (game_done) {

            String text;
            int text_length;
            int x;
            int y;

            g2.setColor(new Color(0f,0f,0f,0.8f));
            g2.fillRect(0, 0, gp.screen_width, gp.screen_height);
            g2.setColor(Color.white);

            text = "Treasure found!";
            text_length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screen_width/2 - text_length/2;
            y = gp.screen_height/2 - gp.tile_size*1;

            g2.drawString(text, x, y);

            text = "Completion time: "+d_format.format(playtime)+" seconds!";
            text_length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screen_width/2 - text_length/2;
            y = gp.screen_height/2 + gp.tile_size*4;

            g2.drawString(text, x, y);

            g2.setFont(alagard_80);
            g2.setColor(Color.yellow);

            text = "Congratulations!";
            text_length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screen_width/2 - text_length/2;
            y = gp.screen_height/2 + gp.tile_size*2;

            g2.drawString(text, x, y);

            gp.game_thread = null;

        } else {

            g2.drawImage(key_image, gp.tile_size / 2, gp.tile_size / 2, gp.tile_size, gp.tile_size, null);
            g2.drawString("x " + gp.player.has_key, 74, 65);

            playtime +=(double)1/60;
            g2.drawString("Time:"+ d_format.format(playtime), gp.tile_size*11, 65);

            if (message_on) {

                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tile_size / 2, gp.tile_size * 3);

                message_counter++;

                if (message_counter > 120) {

                    message_counter = 0;
                    message_on = false;

                }

            }
        }

    }

    public void show_message(String text) {

        message = text;
        message_on = true;

    }

}
