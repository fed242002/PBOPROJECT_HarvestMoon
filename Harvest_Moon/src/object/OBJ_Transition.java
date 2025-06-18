package object;

import Main.EventHandler;
import Main.GamePanel;
import entity.Entity;

public class OBJ_Transition extends Entity {

    public OBJ_Transition(GamePanel gp, int x, int y) {
        super(gp);
        name = "Transition";
        path = "/assets/object/transition/transparent.png"; // Path to the soil image
        this.worldX = x;
        this.worldY = y;
        this.width = 144; // Width of the soil object
        this.height = 48; // Height of the soil object
        isObj = true; // Set this entity as an object
        image = gp.setImage(path);

    }
    
    public void interact() {
        System.out.println("Interacting with " + name);
        EventHandler eventHandler = gp.eventHandler;
        eventHandler.handleMapTransition(1, 25, 2);
    }

}
