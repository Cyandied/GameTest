package main;

import entity.Entity;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp) {

        this.gp = gp;

    }

    public void check_tile(Entity entity) {

        int entity_left_world_x = entity.world_x + entity.solid_area.x;
        int entity_right_world_x = entity.world_x + entity.solid_area.x + entity.solid_area.width;
        int entity_top_world_y = entity.world_y + entity.solid_area.y;
        int entity_bottom_world_y = entity.world_y + entity.solid_area.y + entity.solid_area.height;

        int entity_left_col = entity_left_world_x / gp.tile_size;
        int entity_right_col = entity_right_world_x / gp.tile_size;
        int entity_top_row = entity_top_world_y / gp.tile_size;
        int entity_bottom_row = entity_bottom_world_y / gp.tile_size;

        int tile_num_1, tile_num_2;

        switch (entity.direction) {

            case "up":
                entity_top_row = (entity_top_world_y - entity.speed) / gp.tile_size;
                tile_num_1 = gp.tile_m.map_tile_num[entity_left_col][entity_top_row];
                tile_num_2 = gp.tile_m.map_tile_num[entity_right_col][entity_top_row];

                if (gp.tile_m.tile[tile_num_1].collision || gp.tile_m.tile[tile_num_2].collision) {

                    entity.collision_on = true;

                }
                break;
            case "down":
                entity_bottom_row = (entity_bottom_world_y + entity.speed) / gp.tile_size;
                tile_num_1 = gp.tile_m.map_tile_num[entity_left_col][entity_bottom_row];
                tile_num_2 = gp.tile_m.map_tile_num[entity_right_col][entity_bottom_row];

                if (gp.tile_m.tile[tile_num_1].collision || gp.tile_m.tile[tile_num_2].collision) {

                    entity.collision_on = true;

                }
                break;
            case "left":
                entity_left_col = (entity_left_world_x - entity.speed) / gp.tile_size;
                tile_num_1 = gp.tile_m.map_tile_num[entity_left_col][entity_top_row];
                tile_num_2 = gp.tile_m.map_tile_num[entity_left_col][entity_bottom_row];

                if (gp.tile_m.tile[tile_num_1].collision || gp.tile_m.tile[tile_num_2].collision) {

                    entity.collision_on = true;

                }
                break;
            case "right":
                entity_right_col = (entity_right_world_x + entity.speed) / gp.tile_size;
                tile_num_1 = gp.tile_m.map_tile_num[entity_right_col][entity_top_row];
                tile_num_2 = gp.tile_m.map_tile_num[entity_right_col][entity_bottom_row];

                if (gp.tile_m.tile[tile_num_1].collision || gp.tile_m.tile[tile_num_2].collision) {

                    entity.collision_on = true;

                }
                break;

        }

    }

    public int check_object(Entity entity, boolean player) {

        int index = 999;

        for (int i = 0; i < gp.obj.length; i++) {
            if(gp.obj[i] != null){

                // Get entity's solid area position
                entity.solid_area.x = entity.world_x + entity.solid_area.x;
                entity.solid_area.y = entity.world_y + entity.solid_area.y;

                // Get object's solid area position
                gp.obj[i].solid_area.x = gp.obj[i].world_x + gp.obj[i].solid_area.x;
                gp.obj[i].solid_area.y = gp.obj[i].world_y + gp.obj[i].solid_area.y;

                switch(entity.direction){

                    case "up":
                        entity.solid_area.y -= entity.speed;
                        if(entity.solid_area.intersects(gp.obj[i].solid_area)){
                            if(gp.obj[i].collision){
                                entity.collision_on = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solid_area.y += entity.speed;
                        if(entity.solid_area.intersects(gp.obj[i].solid_area)){
                            if(gp.obj[i].collision){
                                entity.collision_on = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solid_area.x -= entity.speed;
                        if(entity.solid_area.intersects(gp.obj[i].solid_area)){
                            if(gp.obj[i].collision){
                                entity.collision_on = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solid_area.x += entity.speed;
                        if(entity.solid_area.intersects(gp.obj[i].solid_area)){
                            if(gp.obj[i].collision){
                                entity.collision_on = true;
                            }
                            if(player){
                                index = i;
                            }
                        }
                        break;

                }

                entity.solid_area.x = entity.solid_area_default_x;
                entity.solid_area.y = entity.solid_area_default_y;

                gp.obj[i].solid_area.x = gp.obj[i].solid_area_default_x;
                gp.obj[i].solid_area.y = gp.obj[i].solid_area_default_y;

            }
        }

        return index;

    }

}
