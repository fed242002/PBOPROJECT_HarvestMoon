package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    
    public int worldX, worldY; //ini buat nentuin posisi di map
    
    public int speed;


    public BufferedImage[] up = new BufferedImage[8];
    public BufferedImage[] down = new BufferedImage[8];
    public BufferedImage[] left = new BufferedImage[8];
    public BufferedImage[] right = new BufferedImage[8];
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 0;

    public Rectangle solidArea;
    public boolean collisionOn = false;


}
