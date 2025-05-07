package entity;

import java.awt.image.BufferedImage;

public class Entity {
    public int x,y;
    public int speed; // Speed of the entity

    public BufferedImage up[] = new BufferedImage[8];
    public BufferedImage down[] = new BufferedImage[8];
    public BufferedImage left[] = new BufferedImage[8];
    public BufferedImage right[] = new BufferedImage[8];

    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1; // Sprite number for animation
}
