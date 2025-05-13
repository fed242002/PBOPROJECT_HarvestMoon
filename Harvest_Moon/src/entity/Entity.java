package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX,worldY;
    public int speed; // Speed of the entity

    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1; // Sprite number for animation

    public Rectangle solidArea; // Rectangle for collision detection
    public boolean collisionOn = false; // Flag for collision detection
    
}
