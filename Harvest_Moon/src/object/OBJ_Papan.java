package object;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class OBJ_Papan extends SuperObject {
    
    public OBJ_Papan(int x, int y) {
        name = "Key";
        path = "/Assets/Tileset_Barn_1.png";
        collision = false;
        worldX = x;
        worldY = y;
        width = 48*10;
        height = 48 * 10;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.out.println("Error loading " + name + " image: " + e.getMessage());
        }
    }
    
    
    
}
