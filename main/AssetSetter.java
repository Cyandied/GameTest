package main;

import object.ObjBoots;
import object.ObjChest;
import object.ObjDoor;
import object.ObjKey;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp){

        this.gp = gp;

    }

    public void set_object(){

        gp.obj[0] = new ObjKey(gp);
        gp.obj[0].world_x = 23 * gp.tile_size;
        gp.obj[0].world_y = 8 * gp.tile_size;

        gp.obj[1] = new ObjKey(gp);
        gp.obj[1].world_x = 23 * gp.tile_size;
        gp.obj[1].world_y = 40 * gp.tile_size;

        gp.obj[2] = new ObjKey(gp);
        gp.obj[2].world_x = 38 * gp.tile_size;
        gp.obj[2].world_y = 8 * gp.tile_size;

        gp.obj[3] = new ObjDoor(gp);
        gp.obj[3].world_x = 10 * gp.tile_size;
        gp.obj[3].world_y = 11 * gp.tile_size;

        gp.obj[4] = new ObjDoor(gp);
        gp.obj[4].world_x = 8 * gp.tile_size;
        gp.obj[4].world_y = 28 * gp.tile_size;

        gp.obj[5] = new ObjDoor(gp);
        gp.obj[5].world_x = 12 * gp.tile_size;
        gp.obj[5].world_y = 22 * gp.tile_size;

        gp.obj[6] = new ObjChest(gp);
        gp.obj[6].world_x = 10 * gp.tile_size;
        gp.obj[6].world_y = 7 * gp.tile_size;

        gp.obj[7] = new ObjBoots(gp);
        gp.obj[7].world_x = 37 * gp.tile_size;
        gp.obj[7].world_y = 42 * gp.tile_size;

    }

}
