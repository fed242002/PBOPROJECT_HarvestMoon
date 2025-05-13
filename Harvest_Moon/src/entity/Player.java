package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints.Key;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;
import animation.Animation;

public class Player extends Entity{

    public final int screenX; // X position on the screen
    public final int screenY; // Y position on the screen
    
    
    //ini buat sprite animation -> info2
    String body = "black";
    String eye = "blue";
    String outfit = "blue";
    String hair = "baldBlondeAsh";
    Animation walk;
    Animation idle;
    ArrayList<Animation> animationList = new ArrayList<>(); //0: walk, 1:idle
    public int currentAnimationIndex=1;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp; // Assign the GamePanel object to the instance variable
        this.keyH = keyH; // Assign the KeyHandler object to the instance variable
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // Center the player on the screen
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // Center the player on the screen
        
        solidArea = new Rectangle(); // Set the size of the solid area for collision detection
        
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;



        setDefaultValues();

        //declare animation
        walk = new Animation("walk",6, "/Assets/player/WALK/" + getPath());
        animationList.add(walk);
        idle = new Animation("idle",6, "/Assets/player/IDLE/" + getPath());
        animationList.add(idle);
    }

    public void setAnimation(String animation){

        int i=0;
        for(Animation x: animationList){
            if(animation.equalsIgnoreCase(x.name)){
                if(this.currentAnimationIndex != i){
                    currentAnimationIndex = i;
                    //reset animation
                    spriteCounter = 0;
                    spriteCounter = 0;
                }
                break;
            }
            i++;
        }
    }
    

    public void changePath(String bagian, String nama){

        if(bagian.equalsIgnoreCase("Body")){
            this.body = nama;
        }
        if(bagian.equalsIgnoreCase("eye")){
            this.eye = eye;
        }
        if(bagian.equalsIgnoreCase("outfit")){
            this.outfit = outfit;
        }
        if(bagian.equalsIgnoreCase("hair")){
            this.hair = hair;
        }


        for(Animation x: animationList){
            x.setPath(getPath()); 
        }
    }

    public String getPath(){
        return body+"-"+eye+"-"+outfit+"-"+hair+"-";
    }

    GamePanel gp;
    KeyHandler keyH; // KeyHandler object to handle key events
    

    public void setDefaultValues() {
        worldX = 100; // Set the default x position of the player
        worldY = 100; // Set the default y position of the player
        speed = 4; // Set the default speed of the player
        direction = "down"; // Set the default direction of the player
    }


    public void update(){
        // cek kalo lagi jalan ga
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            setAnimation("walk"); //set current animation jadi walking

                    if(keyH.upPressed == true) {
                        direction = "up";
                    }
                    else if(keyH.downPressed == true) {
                        direction = "down";
                    }
                    else if(keyH.leftPressed == true) {
                        direction = "left";
                    }
                    else if(keyH.rightPressed == true) {
                        direction = "right";
                    }
                    
                    //check tile collision
                    collisionOn = false;
                    gp.cChecker.checkTile(this);
                    
                    //kalo collision -> false bisa dijalani
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
            
                    
                }
                else{
                    //ini buat idle animation
                    setAnimation("idle");
                    
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

    public void draw(Graphics2D g2){

        BufferedImage image = null; 


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

        g2.drawImage(image, screenX, screenY, gp.playerSizeX , gp.playerSizeY, null); // Draw the player image at the specified position and size

    }



}
