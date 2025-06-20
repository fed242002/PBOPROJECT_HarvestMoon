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
        MapDB.mapList.get(4).obj.add(new OBJ_HayManger(gp, 23 * gp.tileSize, 28 * gp.tileSize));
        MapDB.mapList.get(4).obj.add(new OBJ_HayManger(gp, 23 * gp.tileSize, 22 * gp.tileSize));
        MapDB.mapList.get(4).obj.add(new OBJ_HayManger(gp, 27 * gp.tileSize, 25 * gp.tileSize));

        // transition objects

        // map 0 to map 1
        MapDB.mapList.get(0).obj.add(new OBJ_Transition01(gp, 23 * gp.tileSize, 48 * gp.tileSize)); 

        // map 1 to map 0
        MapDB.mapList.get(1).obj.add(new OBJ_Transition10(gp, 24 * gp.tileSize, 0 * gp.tileSize));

        // map 1 to map 2
        MapDB.mapList.get(1).obj.add(new OBJ_Transition12(gp, 48 * gp.tileSize, 24 * gp.tileSize));

        // map 2 to map 1
        MapDB.mapList.get(2).obj.add(new OBJ_Transition21(gp, 1 * gp.tileSize, 36 * gp.tileSize));

        // get out of house transition 3-0
        MapDB.mapList.get(3).obj.add(new OBJ_Transition30(gp, 24 * gp.tileSize, 27 * gp.tileSize));

        // get out of barn
        MapDB.mapList.get(4).obj.add(new OBJ_Transition40(gp, 24 * gp.tileSize, 31 * gp.tileSize));

        // get out of market transition
        MapDB.mapList.get(5).obj.add(new OBJ_Transition52(gp, 24 * gp.tileSize, 20 * gp.tileSize));
    
        // get out of market transition
        MapDB.mapList.get(6).obj.add(new OBJ_Transition62(gp, 0 * gp.tileSize, 15 * gp.tileSize));

        // MapDB.mapList.get(0).obj.add(new OBJ_Transition(gp, 24 * gp.tileSize, 47 * gp.tileSize)); 
        // MapDB.mapList.get(1).obj.add(new OBJ_Transition(gp, 23 * gp.tileSize, 47 * gp.tileSize)); 
        // MapDB.mapList.get(2).obj.add(new OBJ_Transition(gp, 25 * gp.tileSize, 47 * gp.tileSize)); 
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
        MapDB.mapList.get(0).npcs.add(new Npc_Merchant(gp, "tes", 20 * gp.tileSize, 20
         * gp.tileSize));
        //MapDB.mapList.get(5).npcs.add(new Npc_Merchant(gp, "tes", 23 * gp.tileSize, 14
        // * gp.tileSize));
        MapDB.mapList.get(4).npcs.add(new Animal(gp, "yellowsheep", 1004, 1017));
        MapDB.mapList.get(4).npcs.add(new Animal(gp, "cow", 1392, 1120));
        MapDB.mapList.get(4).npcs.add(new Animal(gp, "chicken", 1027, 1301));
    }

}
