package object;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class OBJ_Papan extends SuperObject {
    
    public OBJ_Papan(int x, int y) {
        name = "Key";
        path = "/Assets/tile/tile1012.png";
        collision = false;
        worldX = x;
        worldY = y;
        width = 48*1;
        height = 48 * 1;

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
