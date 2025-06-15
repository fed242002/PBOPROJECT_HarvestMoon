package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Bed extends Entity {

    int type;
    public OBJ_Bed(GamePanel gp, int type,int x, int y) {

        super(gp);
        if(type>imagePathList.size() - 1) {
            type = 0; // Reset to the first image if type exceeds available images
        }
        this.type = type; // Set the type of bed
        imagePathList.add("/assets/object/bed0.png"); // Add the bed image path to the list

        name = "bed";
        path = imagePathList.get(type); // Path to the bed image
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
            if(gp.player.currentTools != null) {
                gp.player.currentTools = null; 
            }
            gp.player.duvetImage = gp.setImage("/assets/object/duvet" + type + ".png"); // Set the duvet image for the bed
            
            gp.keyH.interactPressed = false; // Reset the interact key
            gp.player.setAnimation("sleep"); // Set the player's animation to sleep
            gp.player.moveDisabled = true; // Disable player movement
            gp.player.worldX = worldX; // Set player's world position to the bed's position
            gp.player.worldY = worldY; // Set player's world position to the bed's position

        }
    }

}
