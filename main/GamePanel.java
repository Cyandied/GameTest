package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObejct;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    // SCREEN SETTINGS
    final int orig_tile_size = 16; // 16x16 tile
    final int scale = 3;

    public final int tile_size = orig_tile_size * scale; // 48x48 tile
    public final int max_screen_col = 16;
    public final int max_screen_row = 12;

    public final int screen_width = tile_size * max_screen_col; // 768 px
    public final int screen_height = tile_size * max_screen_row; // 576 px

    // World settings
    public final int max_world_col = 50;
    public final int max_world_row = 50;

    // FPS
    int fps = 60;

    public TileManager tile_m = new TileManager(this);
    KeyHandler key_h = new KeyHandler();
    Sound music = new Sound();
    Sound sound_effect = new Sound();
    public AssetSetter a_set = new AssetSetter(this);
    public UI ui = new UI(this);
    public CollisionChecker c_check = new CollisionChecker(this);
    Thread game_thread;

    // ENTITY AND OBJECT
    public Player player = new Player(this, key_h);
    public SuperObejct obj[] = new SuperObejct[10];

    public GamePanel() {

        this.setPreferredSize(new Dimension(screen_width, screen_height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key_h);
        this.setFocusable(true);

    }

    public void setup_game() {

        a_set.set_object();

        play_music(0);

    }

    public void start_game_thread() {

        game_thread = new Thread(this);
        game_thread.start();

    }

    @Override
    public void run() {

        double draw_interval = 1000000000 / fps; // 0.01666 seconds
        double next_draw_time = System.nanoTime() + draw_interval;

        while (game_thread != null) {

            // 1 UPDATE: update info such as char position
            update();
            // 2 DRAW: draw the screen with the updated info
            repaint();

            try {
                double remaining_time = next_draw_time - System.nanoTime();
                remaining_time /= 1000000;

                if (remaining_time < 0) {
                    remaining_time = 0;
                }

                Thread.sleep((long) remaining_time);

                next_draw_time += draw_interval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

    public void update() {

        player.update();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Debug stuff
        long draw_start = 0;
        if (key_h.check_draw_time) {
            draw_start = System.nanoTime();
        }

        // TILE
        tile_m.draw(g2);

        // OBJECT
        for (int i = 0; i < obj.length; i++) {
            if (obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        ui.draw(g2);

        if (key_h.check_draw_time) {
            long draw_end = System.nanoTime();
            long passed = draw_end - draw_start;
            System.out.println("Draw time: " + passed);
        }

        g2.dispose();

    }

    public void play_music(int i) {

        music.set_file(i);
        music.play();
        music.loop();

    }

    public void stop_music() {

        music.stop();

    }

    public void play_sound_effect(int i) {

        sound_effect.set_file(i);
        sound_effect.play();

    }

}
