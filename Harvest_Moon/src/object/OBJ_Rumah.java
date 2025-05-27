package object;

import javax.imageio.ImageIO;
import Main.GamePanel;
import entity.Entity;

public class OBJ_Rumah extends Entity {

    public OBJ_Rumah(GamePanel gp, int x, int y) {
        super(gp);
        name = "Key";
        path = "/assets/Farmer_House_1_48x48.png";
        collision = false; // gaiso dilewati
        worldX = x;
        worldY = y;
        width = 48 * 10;
        height = 48 * 10;
        isObj = true; // Set this entity as an object

        this.solidArea.y = height / 2;
        this.solidArea.x = 0;
        this.solidArea.width = width;
        this.solidArea.height = height / 2;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.out.println("Error loading " + name + " image: " + e.getMessage());
        }

        collision = true; // Set collision to true for this object
    }

    @Override
    public void interact() {
        System.out.println("Interacting with " + name);
    

    }

}
