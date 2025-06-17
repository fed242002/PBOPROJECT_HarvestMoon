package Main;

import entity.*;
import object.*;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        MapDB.mapList.get(0).obj.add(new OBJ_Rumah(gp ,32 * gp.tileSize, 7 * gp.tileSize ));
        MapDB.mapList.get(0).obj.add(new OBJ_Tree(gp, 15 * gp.tileSize, 23 * gp.tileSize));
        MapDB.mapList.get(0).obj.add(new OBJ_Tree(gp, 10 * gp.tileSize, 23 * gp.tileSize));
        MapDB.mapList.get(0).obj.add(new OBJ_WaterWell(gp, 5 * gp.tileSize, 15 * gp.tileSize));
        MapDB.mapList.get(3).obj.add(new OBJ_Bed(gp, 0, 15 * gp.tileSize, 10 * gp.tileSize));
        MapDB.mapList.get(0).obj.add(new OBJ_Barn(gp ,3 * gp.tileSize, 2 * gp.tileSize ));
        MapDB.mapList.get(2).obj.add(new OBJ_Market(gp ,4 * gp.tileSize, 17 * gp.tileSize));

        // Add more objects as needed
    }

    public void addSoil(OBJ_soil soil) {
        boolean found = false;
        for(Entity s : gp.farmObj) {
            if(s.worldX == soil.worldX && s.worldY == soil.worldY) {
                found = true;
                break;
            }
        }
        if(!found) {
            gp.farmObj.add(soil);
        }
    }

    public void addCrop(OBJ_Crop crop, int days) {
        boolean found = false;
        for(Entity s : gp.cropObj) {
            if(s.worldX == crop.worldX && s.worldY == crop.worldY) {
                found = true;
                break;
            }
        }
        if(!found) {
            crop.daysToMature = days;
            gp.cropObj.add(crop);
        }
    }


   

    public void setNPC(){
        MapDB.mapList.get(0).npcs.add(new Npc(gp, "Eddy", 21 * gp.tileSize, 23 * gp.tileSize));
        MapDB.mapList.get(0).npcs.add(new Npc_Merchant(gp, "tes", 20 * gp.tileSize, 20 * gp.tileSize));
        MapDB.mapList.get(0).npcs.add(new Animal(gp, "cow", 22 * gp.tileSize, 18 * gp.tileSize));
    }

}
