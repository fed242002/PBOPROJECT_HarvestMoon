package object;

import entity.Entity;
import Main.GamePanel;

public class OBJ_soil extends Entity {

    String pathWetSoil = "/assets/object/farmSoil/wet_soil.png"; // Path to the wet soil image

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

    public void watering(){
        if (!isWet) {
            isWet = true; // Set the soil to wet
            image = gp.setImage(pathWetSoil); // Change the image to wet soil
        }
    }

    public void reset() {
        isWet = false; // Reset the wet status
        image = gp.setImage(path); // Change the image back to dry soil
    }

}
