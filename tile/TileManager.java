package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int map_tile_num[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;

        tile = new Tile[10];
        map_tile_num = new int[gp.max_world_col][gp.max_world_row];

        get_tile_image();
        load_map("/res/maps/world01.txt");

    }

    public void get_tile_image() {

        setup(0, "grass_blank", false);
        setup(1, "wall", true);
        setup(2, "water", true);
        setup(3, "earth", false);
        setup(4, "tree", true);
        setup(5, "sand", false);
        setup(6, "grass_acc", false);

    }

    public void setup(int index, String img_name, boolean collision) {

        UtilityTool util = new UtilityTool();

        try {

            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/" + img_name + ".png"));
            tile[index].image = util.scale_image(tile[index].image, gp.tile_size, gp.tile_size);
            tile[index].collision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void load_map(String file_path) {

        try {

            InputStream is = getClass().getResourceAsStream(file_path);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.max_world_col && row < gp.max_world_row) {

                String line = br.readLine();

                while (col < gp.max_world_col) {

                    String nums[] = line.split(" ");

                    int num = Integer.parseInt(nums[col]);

                    map_tile_num[col][row] = num;
                    col++;

                }
                if (col == gp.max_world_col) {

                    col = 0;
                    row++;

                }

            }
            br.close();

        } catch (Exception e) {

        }

    }

    public void draw(Graphics2D g2) {

        int world_col = 0;
        int world_row = 0;

        while (world_col < gp.max_world_col && world_row < gp.max_world_row) {

            int tile_num = map_tile_num[world_col][world_row];

            int world_x = world_col * gp.tile_size;
            int world_y = world_row * gp.tile_size;
            int screen_x = world_x - gp.player.world_x + gp.player.screen_x;
            int screen_y = world_y - gp.player.world_y + gp.player.screen_y;

            if (world_x + gp.tile_size > gp.player.world_x - gp.player.screen_x &&
                    world_x - gp.tile_size < gp.player.world_x + gp.player.screen_x &&
                    world_y + gp.tile_size > gp.player.world_y - gp.player.screen_y &&
                    world_y - gp.tile_size < gp.player.world_y + gp.player.screen_y) {

                g2.drawImage(tile[tile_num].image, screen_x, screen_y, null);

            }

            world_col++;

            if (world_col == gp.max_world_col) {
                world_col = 0;
                world_row++;
            }

        }

    }

}
