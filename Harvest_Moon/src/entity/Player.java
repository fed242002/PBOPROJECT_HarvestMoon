package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints.Key;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;

public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH; // KeyHandler object to handle key events
    
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp; // Assign the GamePanel object to the instance variable
        this.keyH = keyH; // Assign the KeyHandler object to the instance variable
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100; // Set the default x position of the player
        y = 100; // Set the default y position of the player
        speed = 4; // Set the default speed of the player
        direction = "down"; // Set the default direction of the player
    }

    public void getPlayerImage() {
        try {
            
            for(int i = 0; i < 8; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream("/Assets/player/up" + i + ".png")); // Load the up images
                down[i] = ImageIO.read(getClass().getResourceAsStream("/Assets/player/down" + i + ".png")); // Load the down images
                left[i] = ImageIO.read(getClass().getResourceAsStream("/Assets/player/left" + i + ".png")); // Load the left images
                right[i] = ImageIO.read(getClass().getResourceAsStream("/Assets/player/right" + i + ".png")); // Load the right images
            }


        } catch (Exception e) {
            System.out.println("error di getPlayerImage()"); // Print an error message if there is an exception
            e.printStackTrace(); 
        }

    }

    public void update(){

        // cek kalo lagi jalan ga
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
                    if(keyH.upPressed == true) {
                        direction = "up";
                        y -= speed; // Move the player up
                    }
                    else if(keyH.downPressed == true) {
                        direction = "down";
                        y += speed; // Move the player down
                    }
                    else if(keyH.leftPressed == true) {
                        direction = "left";
                        x -= speed; // Move the player left
                    }
                    else if(keyH.rightPressed == true) {
                        direction = "right";
                        x += speed; // Move the player right
                    }
            
                    spriteCounter++;
                    if(spriteCounter > 12)
                    {
                        spriteNum++; 
                    
                        if(spriteNum > 7) // If the sprite number exceeds the number of images, reset it to 0
                            spriteNum = 0; // Reset the sprite number to 0
                        
                            spriteCounter = 0; // Reset the sprite counter to 0
                    }
            
        }
        else{
            //ini buat idle animation
        }

    }

    public void draw(Graphics2D g2){

        BufferedImage image = null; 

        switch(direction) {
            case "up":
                image = up[spriteNum]; // Get the up image
                break;
            case "down":
                image = down[spriteNum]; // Get the down image
                break;
            case "left":
                image = left[spriteNum]; // Get the left image
                break;
            case "right":
                image = right[spriteNum]; // Get the right image
                break;
        }

        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null); // Draw the player image at the specified position and size

    }



}
