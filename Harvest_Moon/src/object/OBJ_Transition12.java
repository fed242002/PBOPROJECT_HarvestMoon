package object;

import Main.EventHandler;
import Main.GamePanel;
import entity.Entity;

public class OBJ_Transition12 extends Entity {

    public OBJ_Transition12(GamePanel gp, int x, int y) {
        super(gp);
        name = "Transition";
        path = "/assets/object/transition/Transition.png"; // Path to the soil image
        this.worldX = x;
        this.worldY = y;
        this.width = 48; // Width of the soil object
        this.height = 144; // Height of the soil object
        isObj = true; // Set this entity as an object
        image = gp.setImage(path);

        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = 48;
        solidArea.height = 144;

    }
    
    public void interact() {
        System.out.println("Interacting with " + name);
        EventHandler eventHandler = gp.eventHandler;
        eventHandler.handleMapTransition(2, 4, 37);
    }

}
