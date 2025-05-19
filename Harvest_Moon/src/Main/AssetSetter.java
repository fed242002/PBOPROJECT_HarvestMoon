package Main;

import object.OBJ_Papan;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj.add(new OBJ_Papan(2 * gp.tileSize, 2 * gp.tileSize));


        // Add more objects as needed
    }
    
}
