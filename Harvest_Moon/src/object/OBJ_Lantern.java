package object;

import entity.Entity;
import Main.GamePanel;

public class OBJ_Lantern extends Entity {

    public OBJ_Lantern(GamePanel gp) {
        super(gp);
        name = "Lantern";
        path = "";
        lightRadius = 250;
    }

}
