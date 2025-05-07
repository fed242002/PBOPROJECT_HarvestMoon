package tile;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tile {

    public BufferedImage image; 
    public boolean collision = false; 


    public Tile(String path, boolean collision){
        try {

            this.image = ImageIO.read(getClass().getResourceAsStream(path));
            this.collision = collision;
            
        } catch (Exception e) {
            System.out.println("error di Tile()"); // Print an error message if there is an exception
            e.printStackTrace();
        }
    }
    
}
