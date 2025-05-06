package Tiles;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Tile {
    public BufferedImage image;
    public boolean collision = false; // untuk nentuin tile bisa dilalui atau engga


    public Tile(String path, boolean collision) {
        try{
            this.collision = collision;
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public Tile(String path) {
        try{
            this.image = ImageIO.read(getClass().getResourceAsStream(path));
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    
}
