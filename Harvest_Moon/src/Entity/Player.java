package Entity;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.GamePanel;
import Main.KeyHandler;



public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        //default posisi n speed

        //starting point posisi
        worldX = gp.tileSize*23;
        worldY = gp.tileSize*21;
        speed = 4;
        direction = "down";

    }

    public void getPlayerImage(){
        
        try {
            
            up[0] = ImageIO.read(getClass().getResourceAsStream("/assets/player/up0.png"));
            up[1] = ImageIO.read(getClass().getResourceAsStream("/assets/player/up1.png"));
            up[2] = ImageIO.read(getClass().getResourceAsStream("/assets/player/up2.png"));
            up[3] = ImageIO.read(getClass().getResourceAsStream("/assets/player/up3.png"));
            up[4] = ImageIO.read(getClass().getResourceAsStream("/assets/player/up4.png"));
            up[5] = ImageIO.read(getClass().getResourceAsStream("/assets/player/up5.png"));
            up[6] = ImageIO.read(getClass().getResourceAsStream("/assets/player/up6.png"));
            up[7] = ImageIO.read(getClass().getResourceAsStream("/assets/player/up7.png"));

            
            down[0] = ImageIO.read(getClass().getResourceAsStream("/assets/player/down0.png"));
            down[1] = ImageIO.read(getClass().getResourceAsStream("/assets/player/down1.png"));
            down[2] = ImageIO.read(getClass().getResourceAsStream("/assets/player/down2.png"));
            down[3] = ImageIO.read(getClass().getResourceAsStream("/assets/player/down3.png"));
            down[4] = ImageIO.read(getClass().getResourceAsStream("/assets/player/down4.png"));
            down[5] = ImageIO.read(getClass().getResourceAsStream("/assets/player/down5.png"));
            down[6] = ImageIO.read(getClass().getResourceAsStream("/assets/player/down6.png"));
            down[7] = ImageIO.read(getClass().getResourceAsStream("/assets/player/down7.png"));

            
            left[0] = ImageIO.read(getClass().getResourceAsStream("/assets/player/left0.png"));
            left[1] = ImageIO.read(getClass().getResourceAsStream("/assets/player/left1.png"));
            left[2] = ImageIO.read(getClass().getResourceAsStream("/assets/player/left2.png"));
            left[3] = ImageIO.read(getClass().getResourceAsStream("/assets/player/left3.png"));
            left[4] = ImageIO.read(getClass().getResourceAsStream("/assets/player/left4.png"));
            left[5] = ImageIO.read(getClass().getResourceAsStream("/assets/player/left5.png"));
            left[6] = ImageIO.read(getClass().getResourceAsStream("/assets/player/left6.png"));
            left[7] = ImageIO.read(getClass().getResourceAsStream("/assets/player/left7.png"));

            
            right[0] = ImageIO.read(getClass().getResourceAsStream("/assets/player/right0.png"));
            right[1] = ImageIO.read(getClass().getResourceAsStream("/assets/player/right1.png"));
            right[2] = ImageIO.read(getClass().getResourceAsStream("/assets/player/right2.png"));
            right[3] = ImageIO.read(getClass().getResourceAsStream("/assets/player/right3.png"));
            right[4] = ImageIO.read(getClass().getResourceAsStream("/assets/player/right4.png"));
            right[5] = ImageIO.read(getClass().getResourceAsStream("/assets/player/right5.png"));
            right[6] = ImageIO.read(getClass().getResourceAsStream("/assets/player/right6.png"));
            right[7] = ImageIO.read(getClass().getResourceAsStream("/assets/player/right7.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update()
    {
        //movement
        if(keyH.upPressed == true) //up
        {
            direction = "up";

        }
        else if(keyH.downPressed == true) //down
        {
            direction = "down";

        }
        else if(keyH.leftPressed == true) //left
        {
            direction = "left";

        }
        else if(keyH.rightPressed == true) //right
        {
            direction = "right";
        }

        
        // CHECK TILE COLLISION 
        collisionOn = false;
        gp.cChecker.checkTile(this);
        
        //IF COLLISION IS FALSE, PLAYER CAN MOVE
        if(collisionOn == false){
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }
        spriteCounter++;
        
        if(spriteCounter > 10)  //<-animasi player ganti setiap 10 frame
        {
            if(spriteNum == 7)
                spriteNum = 0;
            else
                spriteNum++;

            spriteCounter = 0;
        }
    }

    public void draw(Graphics2D g2){

         //g2.setColor(Color.white);
        //  g2.fillRect(x, y, gp.tileSize, gp.tileSize);


        BufferedImage image = null;

        switch (direction) {
            case "up":
                image = up[spriteNum];
                break;
            case "down":
                image = down[spriteNum];
                break;
            case "left":
                image = left[spriteNum];
                break;
            case "right":
                image = right[spriteNum];
                break;
            }

            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        
    }
}
