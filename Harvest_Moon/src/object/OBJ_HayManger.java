package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_HayManger extends Entity{

    boolean isFilled = false; // Flag to check if the hay manger is filled

    public OBJ_HayManger(GamePanel gp, int x, int y) {
        super(gp);
        collision = true; // Enable collision for the hay manger
        name = "Hay Manger";
        path = "/assets/object/Object_Barn/Empty.png"; // Path to the hay manger image
        this.worldX = x;
        this.worldY = y;
        this.width = 48;
        this.height = 96; 
        isObj = true; 
        image = gp.setImage(path);

        this.solidArea.y = height / 2;
        this.solidArea.x = 0;
        this.solidArea.width = 49;
        this.solidArea.height = height / 2;

        solidAreaDefaultY = this.solidArea.y;
        solidAreaDefaultX = this.solidArea.x;
        objAnimationSpriteTotal = 2;


    
        for(int i = 0; i < objAnimationSpriteTotal; i++) {
            objAnimation.add(gp.setImage("/assets/objAnimation/HayManger/" + i + ".png"));
        }


    }

    public void reset(){
        isFilled = false; // Reset the filled state
        path = "/assets/object/Object_Barn/Empty.png";
        image = gp.setImage(path);

    }

    @Override
    public void interact() {
        if(gp.keyH.interactPressed) {
            if(!isFilled && gp.player.currentItem != null){
                if(gp.player.currentItem.name.equalsIgnoreCase("hay")){

                    gp.keyH.interactPressed = false; // Reset the interact key
                    objectAnimationOn = true; // Enable object animation
                    gp.player.setAnimation("idle");
                    gp.player.moveDisabled = true; // Disable player movement
                    path = "/assets/object/Object_Barn/Filled.png";
                    image = gp.setImage(path);
                    isFilled = true; // Set the hay manger as filled

                    gp.player.inventory.remove(gp.player.currentItem); // Remove the hay from the player's inventory
                    gp.player.currentItem = null; // Clear the current item
                }
            }
        }
    }
    
}
