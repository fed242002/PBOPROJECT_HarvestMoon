package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.GamePanel;
import animation.Animation;

public class Entity {
    GamePanel gp; 
    public int worldX,worldY;
    public int speed; // Speed of the entity
    public String name;

    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1; // Sprite number for animation
    public int currentAnimationIndex=0;
    public int actionLockCounter = 0;

    String path;

    public Rectangle solidArea = new Rectangle(0,0,48,48); // Rectangle for collision detection


    public int solidAreaDefaultX, solidAreaDefaultY; // Default position of the solid area
    public boolean collisionOn = false; // Flag for collision detection


    //animation
    Animation walk;
    Animation idle;
    ArrayList<Animation> animationList = new ArrayList<>(); //0: walk, 1:idle



    public Entity(GamePanel gp) {
        this.gp = gp;
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null; 
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // Calculate the screen X position
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // Calculate the screen Y position

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // If the tile is within the screen bounds
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) 
            { 


        switch(direction) {
            case "up":
                    image = animationList.get(currentAnimationIndex).up[spriteNum]; // Get the idle up image
                break;
            case "down":
                    image = animationList.get(currentAnimationIndex).down[spriteNum]; // Get the idle up image
                break;
            case "left":
                    image = animationList.get(currentAnimationIndex).left[spriteNum]; // Get the idle up image
                break;
            case "right":
                    image = animationList.get(currentAnimationIndex).right[spriteNum]; // Get the idle up image
                break;

            }

            // g2.setColor(Color.RED);
            // g2.fillRect(screenX, screenY, gp.playerSizeX, gp.playerSizeY);
            g2.drawImage(image, screenX, screenY, gp.playerSizeX, gp.playerSizeY,null);
            }

    }

    public void setAction() {} //npc action

    public void update(){
        setAction();

        collisionOn = false; 
        gp.cChecker.checkTile(this); 
        gp.cChecker.checkObject(this, false); // Check for collision with objects
        gp.cChecker.checkPlayer(this); // Check for collision with NPCs
        
        if(collisionOn == false){
            switch (direction) {
                case "up":
                    worldY -= speed; // Move the player up
                    break;
                case "down":
                    worldY += speed; // Move the player down
                    break;
                case "left":
                    worldX -= speed; // Move the pldddddayer left
                    break;
                case "right":
                    worldX += speed; // Move the player right
                    break;
        
            }
        }

        spriteCounter++;
        if(spriteCounter > 10)
        {
            spriteNum++; 
        
            if(spriteNum > animationList.get(currentAnimationIndex).spriteTotal-1) // If the sprite number exceeds the number of images
                spriteNum = 0; // Reset the sprite number to 0
            
            spriteCounter = 0; // Reset the sprite counter to 0
        }


    }

    public void setAnimation(String animation){
        
        int i=0;
        for(Animation x: animationList){
            if(animation.equalsIgnoreCase(x.name)){
                if(this.currentAnimationIndex != i){
                    gp.stopMusic(gp.sfx);
                    //set animation
                    currentAnimationIndex = i;
                    //reset animation
                    spriteCounter = 0;
                    spriteCounter = 0;
                    //play sfx
                    if(x.soundFX != -1){
                        gp.playMusic(gp.sfx ,x.soundFX);
                    }

                }
                break;
            }
            i++;
        }
    }

    
}
