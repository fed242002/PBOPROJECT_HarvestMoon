package object;

import entity.Entity;
import Main.GamePanel;

public class OBJ_soil extends Entity {

    public OBJ_soil(GamePanel gp, int x, int y) {
        super(gp);
        name = "Soil";
        path = "/assets/object/farmSoil/soil.png"; // Path to the soil image
        this.worldX = x;
        this.worldY = y;
        this.width = 48; // Width of the soil object
        this.height = 48; // Height of the soil object
        isObj = true; // Set this entity as an object
        image = gp.setImage(path);
        
    }

}
