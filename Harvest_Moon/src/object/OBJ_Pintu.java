package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Pintu extends Entity {

    public OBJ_Pintu(GamePanel gp, int x, int y) {
        super(gp);
        name = "door";
        collision = true; // Set collision to true for this object
        worldX = x;
        worldY = y;
        width = 48; // Width of the door
        height = 48; // Height of the door
        isObj = true; // Set this entity as an object

        this.solidArea.x = 0;
        this.solidArea.y = 0;
        this.solidArea.width = width;
        this.solidArea.height = height;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
        objAnimationSpriteTotal = 14; // Total number of animation frames

        for(int i = 0; i < objAnimationSpriteTotal; i++) {
            objAnimation.add(gp.setImage("/assets/objAnimation/homeDoor/door_" + i + ".png"));
        }

    }

    @Override
    public void interact() {
        if(gp.keyH.interactPressed ){
                objectAnimationOn = true; // Enable object animation
                gp.player.moveDisabled = true;
                gp.player.setAnimation("idle");
            
        }
    }
    
}
