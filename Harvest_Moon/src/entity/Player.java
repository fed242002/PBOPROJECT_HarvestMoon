package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Main.EnergyIntake;
import Main.GamePanel;
import Main.KeyHandler;
import animation.Animation;
import animation.ToolsAnimation;
import object.*;

public class Player extends Entity {

    public String listBody[] = {"white", "krem", "black"};
    public String listEye[] = {"blue", "brown", "green"};
    public String listHair[] = {"baldBlondeAsh", "longBrownHazel", "shortBrownDark"};
    public String listOutfit[] = {"violet", "blue"};

    public int bodyIndex = 1; // Index for the current body type
    public int eyeIndex = 0; // Index for the current eye type
    public int hairIndex = 0; // Index for the current hair type
    public int outfitIndex = 1; // Index for the current outfit type
    public Random random = new Random();

    public String name = "Fedrian";
    public final int screenX; // X position on the screen
    public final int screenY; // Y position on the screen
    int hasKey = 0;
    public int maxEnergy = 100;
    public int energy = maxEnergy;
    public int normalSpeed = 4; // pas jalan normal
    public int maxSpeed = 6; // pas sprint
    public int spriteDraw = 10;
    public int gold = 100;
    public boolean lightUpdated = false;
    public int fishingTimeRandom;
    

    int targetWorldX;
    int targetWorldY;
    int targetTileCol;
    int targetTileRow;

    // ini buat sprite animation -> info2
    public String body = "krem";
    String eye = "blue";
    String outfit = "blue";
    String hair = "baldBlondeAsh";
    
    public boolean moveDisabled = false;
    public boolean isDigging = false; 
    public boolean isUndoDigging = false; 
    public int animationDone = 0;

    public boolean isChopping  = false; 
    public boolean isCasting = false;
    public boolean isFishing = false;
    public boolean fishDetected = false; // Flag to check if the fish is detected
    public boolean fishpulled = false;
    public boolean fishCaught = false; // Flag to check if the fish is caught
    public boolean throwBack = false; // Flag to check if the fish is caught

    public boolean isWatering = false;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        
        currentTools = "shovel";

        this.gp = gp; // Assign the GamePanel object to the instance variable
        this.keyH = keyH; // Assign the KeyHandler object to the instance variable
        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); // Center the player on the screen
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2); // Center the player on the screen
        lightRadius = 250; // Radius of the light source for the player

        solidArea = new Rectangle(); // Set the size of the solid area for collision detection

        solidArea.x = 10;
        solidArea.y = 80;
        solidArea.width = 32;
        solidArea.height = 15;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();

        // declare animationiswa
        redeclareAnimation();

        //TOOLS ANIMATION -> ini nanti maw kak pindah ke gp aja soalnya sama aja di smua entity biar gausah ke declare banyak kali
        toolsAnimationList.add(new ToolsAnimation(gp,"axe", "idle", 6));
        toolsAnimationList.add(new ToolsAnimation(gp,"axe", "walk", 6));
        toolsAnimationList.add(new ToolsAnimation(gp,"axe", "chop", 10));
        toolsAnimationList.add(new ToolsAnimation(gp,"FISHROD", "FISHCAUGHT", 9,true));
        toolsAnimationList.add(new ToolsAnimation(gp,"FISHROD", "CAST", 9, true));
        toolsAnimationList.add(new ToolsAnimation(gp,"FISHROD", "FISHIDLE", 6,true));
        toolsAnimationList.add(new ToolsAnimation(gp,"FISHROD", "FISHIDLE1", 6, true));
        toolsAnimationList.add(new ToolsAnimation(gp,"fishRod", "fishpulled", 2, true));
        toolsAnimationList.add(new ToolsAnimation(gp,"fishRod", "idle", 6));
        toolsAnimationList.add(new ToolsAnimation(gp,"fishRod", "walk", 6));
        toolsAnimationList.add(new ToolsAnimation(gp,"shovel", "idle", 6));
        toolsAnimationList.add(new ToolsAnimation(gp,"shovel", "walk", 6));
        toolsAnimationList.add(new ToolsAnimation(gp,"shovel", "dig", 9));
        toolsAnimationList.add(new ToolsAnimation(gp,"WateringCan", "idle", 6));
        toolsAnimationList.add(new ToolsAnimation(gp,"WateringCan", "walk", 6));
        toolsAnimationList.add(new ToolsAnimation(gp,"WateringCan", "watering", 14));

    }


    public void redeclareAnimation(){
        animationList.clear();

        // declare animation
        animationList.add(new Animation("walk", 6, "/assets/player/WALK/" + getPath(), 0));
        animationList.add(new Animation("idle", 6, "/assets/player/IDLE/" + getPath()));
        animationList.add(new Animation("chop", 10, "/assets/player/AXE CHOP/" + getPath(),6));
        animationList.add(new Animation("dig", 9, "/assets/player/DIG/" + getPath(), 5));
        animationList.add(new Animation("cast", 9, "/assets/player/FISH CAST LINE/" + getPath(),7));
        animationList.add(new Animation("FISHIDLE", 6, "/assets/player/FISH IDLE/" + getPath()));
        animationList.add(new Animation("FISHIDLE1", 6, "/assets/player/FISH IDLE/" + getPath()));
        animationList.add(new Animation("FISHCAUGHT", 9, "/assets/player/FISH CAUGHT/" + getPath()));
        animationList.add(new Animation("FISHPULLED", 2, "/assets/player/FISH REEL IN/" + getPath()));
        animationList.add(new Animation("HARVEST", 9, "/assets/player/HARVEST/" + getPath()));
        animationList.add(new Animation("lift", 14, "/assets/player/LIFT/" + getPath()));
        animationList.add(new Animation("PickUp", 12, "/assets/player/PICK UP/" + getPath()));
        animationList.add(new Animation("Sit", 6, "/assets/player/SIT 1/" + getPath()));
        animationList.add(new Animation("sleep", 6, "/assets/player/SLEEP/" + getPath(), true));
        animationList.add(new Animation("throw", 14, "/assets/player/THROW/" + getPath()));
        animationList.add(new Animation("watering", 14, "/assets/player/WATERING/" + getPath(), 8));




    }

    public void changePath(String bagian, String nama) {

        if (bagian.equalsIgnoreCase("Body")) {
            this.body = nama;
        }
        if (bagian.equalsIgnoreCase("eye")) {
            this.eye = nama;
        }
        if (bagian.equalsIgnoreCase("outfit")) {
            this.outfit = nama;
        }
        if (bagian.equalsIgnoreCase("hair")) {
            this.hair = nama;
        }

        // for (Animation x : animationList) {
        //     x.setPath(getPath());
        // }
    }

    public String getPath() {
        return body + "-" + eye + "-" + outfit + "-" + hair + "-";
    }

    public String getIconPath() {
        return body + "-" + eye + "-" + outfit + "-" + hair;
    }

    KeyHandler keyH; // KeyHandler object to handle key events

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; // Set the default x position of the player
        worldY = gp.tileSize * 21; // Set the default y position of the player
        speed = 4; // Set the default speed of the player
        direction = "down"; // Set the default direction of the player
    }

    // function buat soil area
    public void setSolidArea() {
        
    }



    public void update() {
        // cek kalo lagi jalan ga
        if(!moveDisabled){
            if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true
                    || keyH.rightPressed == true) {
                setAnimation("walk"); // set current animation jadi walking
    
                if (keyH.upPressed == true) {
                    direction = "up";
                } else if (keyH.downPressed == true) {
                    direction = "down";
                } else if (keyH.leftPressed == true) {
                    direction = "left";
                } else if (keyH.rightPressed == true) {
                    direction = "right";
                }
    
                // check tile collision
                collisionOn = false;
                gp.cChecker.checkTile(this);
    
                // check object collision
                int objIndex = gp.cChecker.checkObject(this, true);
                pickUpObject(objIndex);
    
                // check npc collision
                int npcIndex = gp.cChecker.checkEntity(this, gp.npcs);
                interactNPC(npcIndex);
    
                // check event collision
                gp.eventHandler.checkEvent();
    
                // kalo collision -> false bisa dijalani
                if (collisionOn == false) {
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
    
            } else {
                // ini buat idle animation
                setAnimation("idle");
    
            }
    
            spriteCounter++;
            if (spriteCounter > spriteDraw) {
                spriteNum++;
    
                if (spriteNum > animationList.get(currentAnimationIndex).spriteTotal - 1) // If the sprite number exceeds
                                                                                          // the number of images
                    spriteNum = 0; // Reset the sprite number to 0
    
                spriteCounter = 0; // Reset the sprite counter to 0
            }

        }



    }

    public void pickUpObject(int i) {
        if (i != 999) {
            gp.obj.get(i).interact(); // Call the interact method of the object
        }
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.interactPressed == true) {
                gp.gameState = gp.dialogueState; // Set the game state to dialogue
                setAnimation("idle");
                gp.gameState = gp.dialogueState; // Set the game state to dialogue
                gp.npcs.get(i).speak(); // Call the speak method of the NPC
                // gp.npcs.get(i).interact(); // Call the setAction method of the NPC

            }
        }
        gp.keyH.interactPressed = false; // Reset the interact key
    }


    public void drawFrontBlock(Graphics2D g2) {
        //hitbox player di world posisi
    int hitboxCenterWorldX = worldX + solidArea.x + (solidArea.width / 2);
    int hitboxCenterWorldY = worldY + solidArea.y + (solidArea.height / 2);
    
    targetWorldX = hitboxCenterWorldX;
    targetWorldY = hitboxCenterWorldY;
    
    switch(direction) {
        case "up":
            targetWorldY -= gp.tileSize;
            break;
        case "down":
            targetWorldY += gp.tileSize;
            break;
        case "left":
            targetWorldX -= gp.tileSize;
            break;
        case "right":
            targetWorldX += gp.tileSize;
            break;
    }
    
    // jadiin tile trus balekin ke px balek
    targetWorldX = (targetWorldX / gp.tileSize) * gp.tileSize;
    targetWorldY = (targetWorldY / gp.tileSize) * gp.tileSize;

    //ini versi row col nya
    targetTileCol = targetWorldX / gp.tileSize;
    targetTileRow = targetWorldY / gp.tileSize;

    // ini buat pas print nya
    int targetScreenX = targetWorldX - worldX + screenX;
    int targetScreenY = targetWorldY - worldY + screenY;
    
    
    
    
    // Load cursor imagedui
    BufferedImage cursor = null;
    try {
        cursor = ImageIO.read(getClass().getResourceAsStream("/assets/ui/target1Block.png"));
    } catch (IOException e) {
        System.out.println("Error loading drawFrontBlock cursor image: " + e.getMessage());
    }
    
    //show info if it can be done or nahwdwud
    if(currentTools!= null){
        if(currentTools!=null && gp.keyH.useTool && !currentTools.equalsIgnoreCase("fishRod")){
            if(currentTools.equalsIgnoreCase("shovel")){
                if((gp.tileM.mapTileNum[targetTileCol][targetTileRow] >= 40 && gp.tileM.mapTileNum[targetTileCol][targetTileRow] <=59) || gp.tileM.mapTileNum[targetTileCol][targetTileRow] == 0 ){
                    //kalo dia grass bisa di dig trus kasih mark ijo
                    g2.setColor(new Color(0, 255, 0, 100));
                    g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
                } else {
                    g2.setColor(new Color(255, 0, 0, 100));
                    g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
                    g2.drawImage(cursor, targetScreenX, targetScreenY, gp.tileSize, gp.tileSize, null);  //ini jangan lupa kalo ada red soalnya ak lgsng return hehe
                    return;
                }
            }
            if(currentTools.equalsIgnoreCase("axe")){
                int objIndex = gp.cChecker.checkObject(this, true);
                if(objIndex != 999 && gp.obj.get(objIndex) instanceof OBJ_Tree && gp.obj.get(objIndex).isChopped == false) {
                    g2.setColor(new Color(0, 255, 0, 100));
                    g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
                }
                else{
                    g2.setColor(new Color(255, 0, 0, 100));
                    g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
                    g2.drawImage(cursor, targetScreenX, targetScreenY, gp.tileSize, gp.tileSize, null);  //ini jangan lupa kalo ada red soalnya ak lgsng return hehe
                    return;
                }
            }
            if(currentTools.equalsIgnoreCase("WateringCan")){
                int objIndex = gp.cChecker.checkObjectFarm(targetWorldX,targetWorldY,this, true);
                if(objIndex != 999 && gp.farmObj.get(objIndex) instanceof OBJ_soil) {
                    g2.setColor(new Color(0, 255, 0, 100));
                    g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
                }else{
                    g2.setColor(new Color(255, 0, 0, 100));
                    g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
                    g2.drawImage(cursor, targetScreenX, targetScreenY, gp.tileSize, gp.tileSize, null);  //ini jangan lupa kalo ada red soalnya ak lgsng return hehe
                    return;
                }

            }

    
            g2.drawImage(cursor, targetScreenX, targetScreenY, gp.tileSize, gp.tileSize, null);
            
    
        }

    }
    



    if(gp.keyH.interactPressed ) {
            // Reset the interact key
        gp.keyH.interactPressed = false;

            if(currentTools == null){
                return;
            }


            if(currentTools.equalsIgnoreCase("shovel")){
                isDigging = true;
                moveDisabled = true; // Disable movement while digging
            }

            
            if(currentTools.equalsIgnoreCase("axe")){
                int objIndex = gp.cChecker.checkObject(this, true);
                if(objIndex != 999 && gp.obj.get(objIndex) instanceof OBJ_Tree) {
                    if(gp.obj.get(objIndex).isChopped == false){
                        isChopping = true;
                        moveDisabled = true;

                    }
                }
            }

            if(currentTools.equalsIgnoreCase("WateringCan")){
                int objIndex = gp.cChecker.checkObjectFarm(targetWorldX,targetWorldY,this, true);

                if(objIndex != 999 && gp.farmObj.get(objIndex) instanceof OBJ_soil) {
                    isWatering = true;
                    moveDisabled = true; // Disable movement while watering
                }
            }
            
            if(currentTools.equalsIgnoreCase("fishRod")) {
                if(gp.tileM.mapTileNum[targetTileCol][targetTileRow] >= 25 && gp.tileM.mapTileNum[targetTileCol][targetTileRow] <= 48){
                    isCasting = true;
                    moveDisabled = true;
                }
    
            }
            if(isFishing){
                resetAllAnimation();
                setAnimation("fishCaught");
                throwBack = true;             
            }
            if(fishDetected){
                resetAllAnimation();
                setAnimation("fishpulled");
                fishpulled = true;
                fishingTimeRandom = random.nextInt(5,15 ); // Random value between 5 and 20
                turnOffEmote();

                //ini nanti buat nambah ikan kalo misal ke tangkap
                fishCaught = true;
            }

        }

        action(targetWorldX, targetWorldY);
        if(gp.keyH.undoToolsPressed){
            isUndoDigging = true;
            moveDisabled = true; // Disable movement while undo digging
            gp.keyH.undoToolsPressed = false; // Reset the undo key
        }
        
    }


    
    public void action(int x, int y){

        if(isWatering){
            

            setAnimation("watering");
            animation(5);

            if(animationDone == 3){
                energy -= EnergyIntake.watering;
                animationDone = 0;
                resetAllAnimation();
                setAnimation("idle");
                moveDisabled = false; 
                int objIndex = gp.cChecker.checkObjectFarm(targetWorldX,targetWorldY,this, true);

                if(objIndex != 999 && gp.farmObj.get(objIndex) instanceof OBJ_soil) {
                    gp.farmObj.get(objIndex).watering();
                }
            }
        }


        //interact with soil -> buat grass jadi soil (tambahin pengecekan nanti)
        if(isDigging){
            setAnimation("dig");
            animation(3);
            
            if(animationDone == 3){
                energy -= EnergyIntake.shovel;
                animationDone = 0;
                resetAllAnimation();
                gp.aSetter.addSoil(new OBJ_soil(gp, x, y));
                moveDisabled = false; // Enable movement after digging
                setAnimation("idle");

            }
        }
        if(isUndoDigging){
            setAnimation("dig");
            animation(3); 
            
            if(animationDone == 3){
                energy -= EnergyIntake.shovel;
                resetAllAnimation();
                moveDisabled = false; // Enable movement after digging
                animationDone = 0;
                setAnimation("idle");

                for(Entity s :gp.farmObj){
                    if(s instanceof OBJ_soil && s.worldX == x && s.worldY == y) {
                        gp.farmObj.remove(s); // Remove the soil object
                        break; // Exit the loop after removing the first matching soil object
                    }

                }

            }
        }


        //chopping tree
        if(isChopping){
            setAnimation("chop");
            animation(3);
            
            if(animationDone == 3){
                energy -= EnergyIntake.axe;
                animationDone = 0;
                resetAllAnimation();

                moveDisabled = false; // Enable movement after digging
                setAnimation("idle");

                int objIndex = gp.cChecker.checkObject(this, true);
                if(objIndex != 999 ) {
                   gp.obj.get(objIndex).chop(); // Call the chop method of the tree object
                }
            }
        }


        //mancing fishing
        if(isCasting){
            setAnimation("cast");
            animation(3);

            
            if(animationDone == 1){
                animationDone = 0;
                resetAllAnimation();
                setAnimation("FISHIDLE");
                // Set the fishing time random value
                fishingTimeRandom = random.nextInt(5,10 ); // Random value between 5 and 20
                isFishing = true;
            }
        }

        if(isFishing){
            if(gp.keyH.interactPressed){
                isFishing = false;
                setAnimation("FISHCAUGHT");
                animationDone = 0;
                spriteCounter = 0;
                spriteNum = 0;
                return;
            }
            setAnimation("FISHIDLE");

            animation(10);
                        
            if(animationDone == fishingTimeRandom){
                animationDone = 0;
                resetAllAnimation();
                setAnimation("FISHIDLE1");
                fishDetected = true;
                emoteOn = true; // Show emote when fish is detected
            }

        }

        if(fishDetected){
            setAnimation("FISHIDLE1");

            animation(10);

            if(animationDone == 3){
                animationDone = 0;
                resetAllAnimation();
                setAnimation("FISHIDLE");
                isFishing = true;
                turnOffEmote();
            }
        }

        if(throwBack){
            setAnimation("fishcaught");
            animation(3);

            if(animationDone >= 1){
                energy -= EnergyIntake.fishing;
                animationDone = 0;
                resetAllAnimation();
                setAnimation("idle");
                moveDisabled = false; // bisa jalan lagi


                if(fishCaught){
                    //disini nanti tambahin ikan ke inventory


                    fishCaught = false; // Reset the fish caught flag
                }
            }
        }
        if(fishpulled){
            setAnimation("FISHPULLED");
            animation(7);

            if(animationDone >= fishingTimeRandom){
                animationDone = 0;
                resetAllAnimation();
                throwBack = true; // Set throwBack to true to throw the fish back
            }
        }

        
    }



    void animation(int counter){
        spriteCounter++;
        if(spriteCounter > counter) {
            spriteCounter = 0; // Reset the sprite counter to 0
            spriteNum++;
        }

        if(spriteNum >= animationList.get(currentAnimationIndex).spriteTotal) {
            animationDone++;
            spriteCounter = 0;
            spriteNum = 0;
        }

    }

    void resetAllAnimation(){
        isDigging = false; 
        isUndoDigging = false; 
        isChopping  = false; 
        isCasting = false;
        isFishing = false;
        fishDetected = false; 
        fishpulled = false;
        fishCaught = false; 
        throwBack = false; 
        isWatering = false;

        animationDone = 0;
        spriteCounter = 0;
        spriteNum = 0;
    }

    public void drawEmote(Graphics2D g2) {
            // Draw the emote
            BufferedImage emoteImage = bubble[thinkSpriteNum];
            g2.drawImage(emoteImage, screenX + 28, screenY - 30, 48, 96, null);
            if(thinkSpriteNum == 2 ){
                g2.drawImage(emote.get(currentEmote).get(0), screenX + 28, screenY - 30, 48, 48, null);
            }
            if(thinkSpriteNum == 3 ){
                g2.drawImage(emote.get(currentEmote).get(1), screenX + 28, screenY - 30, 48, 48, null);
            }

            if(thinkSpriteNum<3){
                thinkSpriteCount++;
                if (thinkSpriteCount > 10) {
                    thinkSpriteNum++;
                    thinkSpriteCount = 0;
                    
                }
            }

            

    }


    public void draw(Graphics2D g2) {
        
        if(emoteOn){
            drawEmote(g2);
        }



        BufferedImage image = null;
        BufferedImage image1 = null;
        ToolsAnimation currentTool = null;




        if(currentTools!= null){
            if(gp.keyH.useTool || currentTools.equalsIgnoreCase("fishRod")){
                drawFrontBlock(g2); // Draw the block in front of the player
            }
        }
 
        if(currentTools != null){
            
            for(ToolsAnimation tool : toolsAnimationList){
                if(tool.tools.equalsIgnoreCase(currentTools) && tool.AnimationName.equalsIgnoreCase(animationList.get(currentAnimationIndex).name)){
                    currentTool = tool;
                    break;
                }
            }

            if(currentTool == null){
                System.out.println("Error: Current tool not found in toolsAnimationList ");
                return;
            }
        }

        switch (direction) {
            case "up":
                image = animationList.get(currentAnimationIndex).up[spriteNum]; // Get the idle up image
                if(currentTool != null ) {
                    image1 = currentTool.up[spriteNum]; // Get the tool up image
                }
                break;
            case "down":
                image = animationList.get(currentAnimationIndex).down[spriteNum]; // Get the idle down image
                if(currentTool != null) {
                    image1 = currentTool.down[spriteNum]; // Get the tool down image
                }
                break;
            case "left":
                image = animationList.get(currentAnimationIndex).left[spriteNum]; // Get the idle left image
                if(currentTool != null) {
                    image1 = currentTool.left[spriteNum]; // Get the tool left image
                }
                break;
            case "right":
                image = animationList.get(currentAnimationIndex).right[spriteNum]; // Get the idle right image
                if(currentTool != null) {
                    image1 = currentTool.right[spriteNum]; // Get the tool right image
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.playerSizeX, gp.playerSizeY, null); // Draw player image
        if(currentTool != null && image1 != null) {
            g2.drawImage(image1, currentTool.x, currentTool.y, currentTool.width, currentTool.height, null); // Draw tool image
        }
        }

}
