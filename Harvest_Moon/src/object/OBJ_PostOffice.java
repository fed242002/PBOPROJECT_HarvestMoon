package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_PostOffice extends Entity {

    public OBJ_PostOffice(GamePanel gp, int x, int y) {
        super(gp);
        name = "market";
        path = "/Assets/object/objek_townHall/22_Post_Office_48x48.png.png";
        collision = false; // gaiso dilewati
        worldX = x;
        worldY = y;
        width = 384;
        height = 624;
        isObj = true; // Set this entity as an object

        this.solidArea.y = height / 2;
        this.solidArea.x = 0;
        this.solidArea.width = width;
        this.solidArea.height = height / 2;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

       
        objAnimationSpriteTotal = 8; // Total number of animation frames

        for(int i = 0; i < objAnimationSpriteTotal; i++) {
            objAnimation.add(gp.setImage("/assets/objAnimation/postOffice/" + i + ".png"));
        }

        image = objAnimation.get(0); // Set the initial image for the object


        collision = true; // Set collision to true for this object

    }
   

    @Override
    public void interact() {

        if(gp.keyH.interactPressed) {
            gp.keyH.interactPressed = false; // Reset the interact key
            objectAnimationOn = true; // Enable object animation
            gp.player.setAnimation("idle");
            gp.player.moveDisabled = true; // Disable player movement

        }

    

    }

}