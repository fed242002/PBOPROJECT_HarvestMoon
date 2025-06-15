package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Bed extends Entity {
    public OBJ_Bed(GamePanel gp, int x, int y) {
        super(gp);
        name = "bed";
        path = "/assets/object/bed0.png"; // Path to the bed image
        image = gp.setImage(path); // Set the image for the bed
        collision = true; // Set collision to true for this object
        worldX = x;
        worldY = y;
        width = 48; // Width of the bed
        height = 144; // Height of the bed
        isObj = true; // Set this entity as an object

        this.solidArea.x = 0;
        this.solidArea.y = 24;
        this.solidArea.width = width;
        this.solidArea.height = 96;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;


    }

    @Override
    public void interact() {
        if(gp.keyH.interactPressed ){
                
            
        }
    }

}
