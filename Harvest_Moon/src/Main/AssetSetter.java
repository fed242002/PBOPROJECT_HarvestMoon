package Main;

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


        // Add more objects as needed
    }

    public void addSoil(OBJ_soil soil) {
        gp.farmObj.add(soil);

        System.out.println("added soil at: " + soil.worldX + ", " + soil.worldY);
        System.out.println("Soil added to farmObj collection. Size now: " + gp.farmObj.size());

    }


    public void setNPC(){
        gp.npcs.add(new Npc(gp, "Eddy", 21 * gp.tileSize, 23 * gp.tileSize));

    }

}
