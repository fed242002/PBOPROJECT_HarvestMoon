package Main;

import java.security.KeyStore.Entry;

import entity.Entity;
import entity.Npc;
import object.OBJ_Rumah;
import object.OBJ_soil;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj.add(new OBJ_Rumah(gp ,31 * gp.tileSize, 7 * gp.tileSize ));
        
        gp.obj.add(new object.OBJ_Tree(gp, 15 * gp.tileSize, 23 * gp.tileSize));
        gp.obj.add(new object.OBJ_Tree(gp, 10 * gp.tileSize, 23 * gp.tileSize));
        gp.obj.add(new object.OBJ_Tree(gp, 5 * gp.tileSize, 15 * gp.tileSize));


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

   

    public void setNPC(){
        gp.npcs.add(new Npc(gp, "Eddy", 21 * gp.tileSize, 23 * gp.tileSize));

    }

}
