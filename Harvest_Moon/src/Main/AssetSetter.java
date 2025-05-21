package Main;

import entity.Npc;
import object.OBJ_Papan;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj.add(new OBJ_Papan(gp ,12 * gp.tileSize, 12 * gp.tileSize));


        // Add more objects as needed
    }


    public void setNPC(){
        gp.npcs.add(new Npc(gp, "Eddy", 21 * gp.tileSize, 21 * gp.tileSize));

    }

}
