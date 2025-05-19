package object;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class OBJ_Papan extends SuperObject {
    
    public OBJ_Papan(int x, int y) {
        name = "Key";
        path = "/Assets/tile/tile1041.png";
        collision = false;
        worldX = x;
        worldY = y;
        width = 48;
        height = 48 * 2;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (Exception e) {
            System.out.println("Error loading " + name + " image: " + e.getMessage());
        }
    }
    
    
    
}
