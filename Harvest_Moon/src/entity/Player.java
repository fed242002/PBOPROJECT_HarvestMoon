package entity;

import Main.*;
import animation.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import object.*;

public class Player extends Entity {

    public String listBody[] = {"white", "krem", "black"};
    public String listEye[] = {"blue", "brown", "green"};
    public String listOutfit[] = {"violet", "blue"};
    public String listHair[] = {"baldBlondeAsh", "longBrownHazel", "shortBrownDark"};

    public int bodyIndex = 2; // Index for the current body type
    public int eyeIndex = 0; // Index for the current eye type
    public int outfitIndex = 1; // Index for the current outfit type
    public int hairIndex = 1; // Index for the current hair type
    public Random random = new Random();
    public boolean pickCounterOn;
    public int pickCounter;
    public Entity currentHarvest = null;



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
    public int maxInventorySize = 60; // Maximum size of the inventory

    public boolean isHolding = false; // Flag to check if the player is holding an item

    int targetWorldX;
    int targetWorldY;
    int targetTileCol;
    int targetTileRow;
    

    public Entity currentItem;

    // ini buat sprite animation -> info2
    public String body = listBody[bodyIndex];
    public String eye = listEye[eyeIndex];
    public String outfit = listOutfit[outfitIndex];
    public String hair = listHair[hairIndex];

    public boolean moveDisabled = false;
    public boolean isDigging = false;
    public boolean isUndoDigging = false;
    public int animationDone = 0;

    public boolean isChopping = false;
    public boolean isCasting = false;
    public boolean isFishing = false;
    public boolean fishDetected = false; // Flag to check if the fish is detected
    public boolean fishpulled = false;
    public boolean fishCaught = false; // Flag to check if the fish is caught
    public boolean throwBack = false; // Flag to check if the fish is caught

    public boolean isWatering = false;
    public boolean isPlanting = false;
    public boolean isHarvesting = false;


    
    public BufferedImage duvetImage = null; // Image for the duvet when sleeping


    public String getSleepPath(){
        return body + "-" + hair + "-";
    }

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);
        width = gp.playerSizeX;
        height = gp.playerSizeY; // Adjust based on your NPC sprites



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

        // set items
        setItems();

        setDefaultValues();

        // declare animationiswa
        redeclareAnimation();

        //TOOLS ANIMATION -> ini nanti maw kak pindah ke gp aja soalnya sama aja di smua entity biar gausah ke declare banyak kali
        toolsAnimationList.add(new ToolsAnimation(gp, "axe", "IDLE", 6));
        toolsAnimationList.add(new ToolsAnimation(gp, "axe", "WALK", 6));
        toolsAnimationList.add(new ToolsAnimation(gp, "axe", "AXECHOP", 10));
        toolsAnimationList.add(new ToolsAnimation(gp, "FISHROD", "FISHCAUGHT", 9, true));
        toolsAnimationList.add(new ToolsAnimation(gp, "FISHROD", "CAST", 9, true));
        toolsAnimationList.add(new ToolsAnimation(gp, "FISHROD", "FISHIDLE", 6, true));
        toolsAnimationList.add(new ToolsAnimation(gp, "FISHROD", "FISHIDLE2", 6, true));
        toolsAnimationList.add(new ToolsAnimation(gp, "FISHROD", "PULLHOOK", 2, true));
        toolsAnimationList.add(new ToolsAnimation(gp, "FISHROD", "IDLE", 6));
        toolsAnimationList.add(new ToolsAnimation(gp, "FISHROD", "WALK", 6));
        toolsAnimationList.add(new ToolsAnimation(gp, "shovel", "IDLE", 6));
        toolsAnimationList.add(new ToolsAnimation(gp, "shovel", "WALK", 6));
        toolsAnimationList.add(new ToolsAnimation(gp, "shovel", "DIG", 9));
        toolsAnimationList.add(new ToolsAnimation(gp, "WateringCan", "IDLE", 6));
        toolsAnimationList.add(new ToolsAnimation(gp, "WateringCan", "WALK", 6));
        toolsAnimationList.add(new ToolsAnimation(gp, "WateringCan", "WATERING", 14));



    }

    public void preview(){
        animationList.clear();
        gp.ui.bodyPreview = gp.setImage("/assets/player/common/body/" + body + "/IDLE/down/0.png");
        gp.ui.eyePreview = gp.setImage("/assets/player/common/eye/" + eye + "/IDLE/down/0.png");
        gp.ui.hairPreview = gp.setImage("/assets/player/common/hair/" + hair + "/IDLE/down/0.png");
        gp.ui.outfitPreview = gp.setImage("/assets/player/common/outfit/" + outfit + "/IDLE/down/0.png");


    }

    public void redeclareAnimation() {

        
        animationList.clear();


        animationList.add(new Animation("IDLE", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("WALK", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("SLEEP", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("SIT", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("SIT2", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("PHONE", 12, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("BOOK", 12, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("PUSHCART", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("PICKUP", 12, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("GIFT", 10, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("LIFT", 14, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("THROW", 14, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("HIT", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("PUNCH", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("STAB", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("GRABGUN", 4, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("GUNIDLE", 6, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("SHOOT", 3, "/assets/player/common/", body, eye, hair, outfit));
        animationList.add(new Animation("HURT", 3, "/assets/player/common/", body, eye, hair, outfit));
        
        
        animationList.add(new Animation("HARVEST", 9, "/assets/player/farmer/", body, eye, hair, outfit));
        animationList.add(new Animation("DIG", 9, "/assets/player/farmer/", body, eye, hair, outfit));
        animationList.add(new Animation("WATERING", 14, "/assets/player/farmer/", body, eye, hair, outfit));
        animationList.add(new Animation("AXECHOP", 10, "/assets/player/farmer/", body, eye, hair, outfit));
        animationList.add(new Animation("CAST", 9, "/assets/player/farmer/", body, eye, hair, outfit));
        animationList.add(new Animation("FISHIDLE", 6, "/assets/player/farmer/", body, eye, hair, outfit));
        animationList.add(new Animation("FISHIDLE2", 6, "/assets/player/farmer/", body, eye, hair, outfit));
        animationList.add(new Animation("PULLHOOK", 2, "/assets/player/farmer/", body, eye, hair, outfit));
        animationList.add(new Animation("FISHCAUGHT", 9, "/assets/player/farmer/", body, eye, hair, outfit));

    }

    public void changePath(String bagian, String nama) {

        if (bagian.equalsIgnoreCase("Body")) {
            this.body = nama;
            bodyIndex = java.util.Arrays.asList(listBody).indexOf(nama); // Update bodyIndex based on the new body name
        }
        if (bagian.equalsIgnoreCase("eye")) {
            this.eye = nama;
            eyeIndex = java.util.Arrays.asList(listEye).indexOf(nama); // Update eyeIndex based on the new eye name
        }
        if (bagian.equalsIgnoreCase("outfit")) {
            this.outfit = nama;
            outfitIndex = java.util.Arrays.asList(listOutfit).indexOf(nama); // Update outfitIndex based on the new outfit name
        }
        if (bagian.equalsIgnoreCase("hair")) {
            this.hair = nama;
            hairIndex = java.util.Arrays.asList(listHair).indexOf(nama); // Update hairIndex based on the new hair name
        }

    }

    public String getPath() {
        return body + "-" + eye + "-" + outfit + "-" + hair + "-";
    }

    public String getIconPath() {
        return body + "-" + eye + "-" + outfit + "-" + hair;
    }

    KeyHandler keyH; // KeyHandler object to handle key events
    public int spriteTotal;

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; // Set the default x position of the player
        worldY = gp.tileSize * 21; // Set the default y position of the player
        speed = 4; // Set the default speed of the player
        direction = "down"; // Set the default direction of the player
    }

    // function buat soil area
    public void setSolidArea() {

    }

    public void setItems() {
        // inventory.add() // set item default player apa belum tau mau diisi apa\

        inventory.add(ItemList.apple.clone()); 
        inventory.add(ItemList.shovel.clone()); 
        inventory.add(ItemList.wateringCan.clone()); 
        inventory.add(ItemList.fishRod.clone()); 
        inventory.add(ItemList.axe.clone()); 
        inventory.add(ItemList.chiliSeedBag.clone()); 
        inventory.add(ItemList.lettuceSeedBag.clone()); 
        inventory.add(ItemList.shear.clone()); 
        inventory.add(ItemList.carrotSeedBag.clone());
        inventory.add(ItemList.cauliflowerSeedBag.clone());
        inventory.add(ItemList.cauliflowerSeedBag2.clone());
        inventory.add(ItemList.cauliflowerSeedBag3.clone());


        
    }

    @Override
    public void update() {

        // cek kalo lagi jalan ga
        if (!moveDisabled) {
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

                if (spriteNum > animationList.get(currentAnimationIndex).spriteTotal - 1) // If the sprite number
                // exceeds
                // the number of images
                {
                    spriteNum = 0; // Reset the sprite number to 0
                }
                spriteCounter = 0; // Reset the sprite counter to 0
            }

        }

        if (pickCounterOn) {
            pickCounter++;
            if (pickCounter == 180) {
                pickCounter = 0;
                pickCounterOn = false;
            }
        }

    }


    
    public void pickUpObject(int i) {

        String text = " "; // Initialize text to an empty string

        if (i != 999 && !gp.obj.get(i).pickUpAble)
        {
            gp.obj.get(i).interact(); // Call the interact method of the object
            return;
        }

        if (i != 999) {
             if (canObtainItem(gp.obj.get(i)) == true) {
                 if(gp.obj.get(i).pickUpAble) {
                     text = "You picked up " + gp.obj.get(i).name + "!"; // Set the text to display
                     gp.ui.showMessage(text); // Show the message on the UI
                     gp.obj.remove(i); // Remove the object from the game world
                     inventory.add(gp.obj.get(i)); // Add the object to the inventory
                    }else{
                        gp.obj.get(i).interact(); // Call the interact method of the object
                    }
             } else {
                    if(gp.obj.get(i).pickUpAble) {
                     text = "Your inventory is full!"; // Set the text to display if the inventory is full
                     gp.ui.showMessage(text); // Show the message on the UI
                     pickCounterOn = true; // Set the pick counter on flag to true
                    }else{
                        gp.obj.get(i).interact(); // Call the interact method of the object
                    }


             }

        }
    }

    public int searchItemInInventory(String itemName) {
        int itemIndex = 999; // Default value if item is not found

        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).name.equalsIgnoreCase(itemName)) {
                itemIndex = i; // Set the item index if found
                break; // Exit the loop once the item is found
            }
        }
        return itemIndex; // Return the index of the item in the inventory
    }

    public boolean canObtainItem(Entity item) {


        boolean canObtain = false;

        // check kalo stackable
        if (item.stackable == true) {
            int index = searchItemInInventory(item.name);

            if (index != 999) {
                inventory.get(index).amount += item.amount; // Add the amount to the existing item
                canObtain = true; // Item successfully obtained
            } else {
                if (inventory.size() != maxInventorySize) {
                    inventory.add(item); // Add the item to the inventory
                    canObtain = true; // Item successfully obtained
                }
            }
        } else // not stackable
        {
            if (inventory.size() != maxInventorySize) {
                inventory.add(item); // Add the item to the inventory
                canObtain = true; // Item successfully obtained
            }
        }
        return canObtain; // Return whether the item can be obtained or not
    }

    public void interactNPC(int i) {
        if (i != 999) {
            if (gp.keyH.interactPressed == true) {
                if( gp.npcs.get(i).isAnimal) {
                    gp.npcs.get(i).interact(); // Call the interact method of the animal
                    return; // Exit the method if it's an animal
                }
                gp.gameState = gp.dialogueState; // Set the game state to dialogue
                setAnimation("idle");
                gp.gameState = gp.dialogueState; // Set the game state to dialogue
                gp.npcs.get(i).speak(); // Call the speak method of the NPC
                // gp.npcs.get(i).interact(); // Call the setAction method of the NPC

            }
        }
        gp.keyH.interactPressed = false; // Reset the interact key
    }


    public void harvest(Graphics2D g2){               
         // hitbox player di world posisi
            int hitboxCenterWorldX = worldX + solidArea.x + (solidArea.width / 2);
            int hitboxCenterWorldY = worldY + solidArea.y + (solidArea.height / 2);

            targetWorldX = hitboxCenterWorldX;
            targetWorldY = hitboxCenterWorldY;

            switch (direction) {
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

            // ini versi row col nya
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

        if(gp.cropObj.size() == 0){
            g2.setColor(new Color(255, 0, 0, 100));
            g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
            g2.drawImage(cursor, targetScreenX, targetScreenY, gp.tileSize, gp.tileSize, null);  //ini jangan lupa kalo ada red soalnya ak lgsng return hehe
            return;
        }

        int xCrop, yCrop;
        int objIndex = gp.cChecker.checkObjectFarm(targetWorldX,targetWorldY,this, true);

        if(objIndex != 999) {
            xCrop = gp.farmObj.get(objIndex).worldX;
            yCrop = gp.farmObj.get(objIndex).worldY;
        

        
        for(Entity s : gp.cropObj) {
            if (s instanceof OBJ_Crop && s.worldX == xCrop && s.worldY == yCrop) {
                objIndex = gp.cropObj.indexOf(s); // Get the index of the crop object
                break; 
            }
        }
        // System.out.println("obj size " + gp.cropObj.size());
        // System.out.println("checking obj: " + gp.cropObj.get(objIndex).name);
        // System.out.println("checking is it harvestable : " + gp.cropObj.get(objIndex).harvestable);
        // System.out.println("checking is it rotten : " + gp.cropObj.get(objIndex).isRotten);
        }

        if(objIndex != 999 && (gp.cropObj.get(objIndex).harvestable || gp.cropObj.get(objIndex).isRotten)) {
            g2.setColor(new Color(0, 255, 0, 100));
            g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
            g2.drawImage(cursor, targetScreenX, targetScreenY, gp.tileSize, gp.tileSize, null);  //ini jangan lupa kalo ada red soalnya ak lgsng return hehe
        }else{
            g2.setColor(new Color(255, 0, 0, 100));
            g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
            g2.drawImage(cursor, targetScreenX, targetScreenY, gp.tileSize, gp.tileSize, null);  //ini jangan lupa kalo ada red soalnya ak lgsng return hehe
            return;
        }


        if(gp.keyH.interactPressed ) {
            // Reset the interact key
            gp.keyH.interactPressed = false;

            if (objIndex != 999 && (gp.cropObj.get(objIndex).harvestable || gp.cropObj.get(objIndex).isRotten)) {
                isHarvesting = true;
                moveDisabled = true; // Disable movement while harvesting
            } 

        }


        if(isHarvesting) {
            setAnimation("harvest");
            animation(3);

            if (animationDone == 1) {
                energy -= EnergyIntake.harvest;
                animationDone = 0;
                resetAllAnimation();
                setAnimation("idle");
                moveDisabled = false; // Enable movement after harvesting

                // Add the harvested crop to the inventory
                Entity harvestedCrop = gp.cropObj.get(objIndex);

                if(!harvestedCrop.isRotten)
                    inventory.add(ItemList.getItem(harvestedCrop.name)); // Clone the crop to add to inventory

                gp.cropObj.remove(objIndex); // Remove the crop from the game world
            }
        }


        

    }

    public void drawFrontBlock(Graphics2D g2) {


        // hitbox player di world posisi
        int hitboxCenterWorldX = worldX + solidArea.x + (solidArea.width / 2);
        int hitboxCenterWorldY = worldY + solidArea.y + (solidArea.height / 2);

        targetWorldX = hitboxCenterWorldX;
        targetWorldY = hitboxCenterWorldY;

        switch (direction) {
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

        // ini versi row col nya
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
                if(objIndex != 999 && (gp.farmObj.get(objIndex) instanceof OBJ_soil )) {
                    g2.setColor(new Color(0, 255, 0, 100));
                    g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
                }else{
                    g2.setColor(new Color(255, 0, 0, 100));
                    g2.fillRect(targetScreenX, targetScreenY, gp.tileSize, gp.tileSize);
                    g2.drawImage(cursor, targetScreenX, targetScreenY, gp.tileSize, gp.tileSize, null);  //ini jangan lupa kalo ada red soalnya ak lgsng return hehe
                    return;
                }

            }

            
    
        }

    }

    if(currentItem!=null){
        if(!currentItem.name.equalsIgnoreCase("fishrod")){
            if(currentItem.type_item == type_seed){
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

            if (currentTools != null) {
                if (currentTools.equalsIgnoreCase("shovel")) {
                if(energy < EnergyIntake.shovel) {
                    System.out.println("energy not enough");
                    return;
                }
                isDigging = true;
                moveDisabled = true; // Disable movement while digging
            }

            if (currentTools.equalsIgnoreCase("axe")) {
                int objIndex = gp.cChecker.checkObject(this, true);
                if (objIndex != 999 && gp.obj.get(objIndex) instanceof OBJ_Tree) {
                    if (gp.obj.get(objIndex).isChopped == false) {
                        if(energy < EnergyIntake.axe) {
                            System.out.println("energy not enough");
                            return;
                        }

                        isChopping = true;
                        moveDisabled = true;

                    }
                }
            }

            if (currentTools.equalsIgnoreCase("WateringCan")) {
                int objIndex = gp.cChecker.checkObjectFarm(targetWorldX, targetWorldY, this, true);

                if (objIndex != 999 && gp.farmObj.get(objIndex) instanceof OBJ_soil) {
                    if(energy < EnergyIntake.watering) {
                    System.out.println("energy not enough");
                    return;
                    }
                    isWatering = true;
                    moveDisabled = true; // Disable movement while watering
                }
            }

            if (currentTools.equalsIgnoreCase("fishRod")) {
                if (gp.tileM.mapTileNum[targetTileCol][targetTileRow] >= 25 && gp.tileM.mapTileNum[targetTileCol][targetTileRow] <= 48) {
                    if(energy < EnergyIntake.fishing) {
                    System.out.println("energy not enough");
                    return;
                    }
                    isCasting = true;
                    moveDisabled = true;
                }

            }
            if (isFishing) {
                resetAllAnimation();
                setAnimation("fishCaught");
                throwBack = true;
                moveDisabled = true; // Disable movement while planting

            }
            if (fishDetected) {
                //ini nanti buat nambah ikan kalo misal ke tangkap
                fishCaught = true;
                resetAllAnimation();
                setAnimation("PULLHOOK");
                fishpulled = true;
                moveDisabled = true;
                fishingTimeRandom = random.nextInt(5, 15); // Random value between 5 and 20
                turnOffEmote();

            }
            
        }

        if(currentItem != null){
            if(currentItem.type_item == type_seed){
                isPlanting = true;
                moveDisabled = true; // Disable movement while planting
            }

            
        }    

        }
                

        action(targetWorldX, targetWorldY);
        if (gp.keyH.undoToolsPressed) {
            isUndoDigging = true;
            moveDisabled = true; // Disable movement while undo digging
            gp.keyH.undoToolsPressed = false; // Reset the undo key
        }

    }


    
    public void action(int x, int y){


        if(isPlanting){
            gp.aSetter.addCrop(Crop.getCrop(currentItem.seedCrop, x, y), currentItem.daysToMature);
            inventory.remove(currentItem); // Remove the seed from the inventory
            currentItem = null; // Reset the current item
            resetAllAnimation();
            
            setAnimation("idle");
        }

        if(isWatering){
            

            setAnimation("watering");
            animation(5);

            if (animationDone == 3) {
                energy -= EnergyIntake.watering;
                animationDone = 0;
                resetAllAnimation();
                setAnimation("idle");
                moveDisabled = false;
                int objIndex = gp.cChecker.checkObjectFarm(targetWorldX, targetWorldY, this, true);

                if (objIndex != 999 && gp.farmObj.get(objIndex) instanceof OBJ_soil) {
                    gp.farmObj.get(objIndex).watering();
                }

                for (Entity s : gp.cropObj) {
                    if (s instanceof OBJ_Crop && s.worldX == x && s.worldY == y) {
                        gp.cropObj.get(gp.cropObj.indexOf(s)).wateredCount++; // Set the watered flag to true
                    }
                }

            }
        }

        //interact with soil -> buat grass jadi soil (tambahin pengecekan nanti)
        if (isDigging) {
            setAnimation("dig");
            animation(3);

            if (animationDone == 3) {
                energy -= EnergyIntake.shovel;
                animationDone = 0;
                resetAllAnimation();
                gp.aSetter.addSoil(new OBJ_soil(gp, x, y));
                moveDisabled = false; // Enable movement after digging
                setAnimation("idle");

            }
        }
        if (isUndoDigging) {
            setAnimation("dig");
            animation(3);

            if (animationDone == 3) {
                energy -= EnergyIntake.shovel;
                resetAllAnimation();
                moveDisabled = false; // Enable movement after digging
                animationDone = 0;
                setAnimation("idle");

                for (Entity s : gp.farmObj) {
                    if (s instanceof OBJ_soil && s.worldX == x && s.worldY == y) {
                        gp.farmObj.remove(s); // Remove the soil object
                        break; // Exit the loop after removing the first matching soil object
                    }
                }

                for (Entity s : gp.cropObj) {
                    if (s instanceof OBJ_Crop && s.worldX == x && s.worldY == y) {
                        gp.cropObj.remove(s); // Remove the crop object
                        break; // Exit the loop after removing the first matching crop object
                    }
                }


            }
        }

        // chopping tree
        if (isChopping) {
            setAnimation("AXECHOP");
            animation(3);

            if (animationDone == 3) {
                energy -= EnergyIntake.axe;
                animationDone = 0;
                resetAllAnimation();

                moveDisabled = false; // Enable movement after digging
                setAnimation("idle");

                int objIndex = gp.cChecker.checkObject(this, true);
                if (objIndex != 999) {
                    gp.obj.get(objIndex).chop(); // Call the chop method of the tree object
                }
            }
        }

        // mancing fishing
        if (isCasting) {
            setAnimation("cast");
            animation(3);

            if (animationDone == 1) {
                animationDone = 0;
                resetAllAnimation();
                setAnimation("FISHIDLE");
                // Set the fishing time random value
                fishingTimeRandom = random.nextInt(5, 10); // Random value between 5 and 20
                isFishing = true;
                moveDisabled = true;
            }
        }

        if (isFishing) {
            setAnimation("FISHIDLE");

            animation(10);

            

            if (animationDone == fishingTimeRandom) {
                animationDone = 0;
                resetAllAnimation();
                setAnimation("FISHIDLE2");
                fishDetected = true;
                emoteOn = true; // Show emote when fish is detected
                moveDisabled = true;
            }

        }

        if (fishDetected) {
            setAnimation("FISHIDLE2");

            animation(10);

            if (animationDone == 3) {
                animationDone = 0;
                resetAllAnimation();
                setAnimation("FISHIDLE");
                isFishing = true;
                turnOffEmote();
                moveDisabled = true;
            }
        }

        if (throwBack) {
            setAnimation("fishcaught");
            animation(3);

            if (animationDone >= 1) {
                energy -= EnergyIntake.fishing;
                animationDone = 0;
                resetAllAnimation();
                setAnimation("idle");
                moveDisabled = false; // bisa jalan lagi

                if (fishCaught) {
                    //disini nanti tambahin ikan ke inventory
                    Random random = new Random();
                    int randomCaught = random.nextInt(0, 101); 

                    if(randomCaught<30){ //30% chance to get boots
                        inventory.add(ItemList.boots.clone()); // Add fish1 to the inventory
                        System.out.println("Caught Boots");
                    }else if(randomCaught<60){ //30%
                        inventory.add(ItemList.orangeFish.clone()); // Add fish1 to the inventory
                        System.out.println("Caught Orange Fish");
                    }else if(randomCaught<80){ //20%
                        inventory.add(ItemList.greenFish.clone()); // Add fish1 to the inventory
                        System.out.println("Caught Green Fish");
                    }else if(randomCaught<95){ //15%
                        inventory.add(ItemList.redFish.clone()); // Add fish1 to the inventory
                        System.out.println("Caught Red Fish");
                    }else if(randomCaught<100){ //5%
                        inventory.add(ItemList.blueFish.clone()); // Add fish1 to the inventory
                        System.out.println("Caught Blue Fish");
                    }
                



                    fishCaught = false; // Reset the fish caught flag
                }
            }
        }
        if (fishpulled) {
            setAnimation("PULLHOOK");
            animation(7);

            if (animationDone >= fishingTimeRandom) {
                animationDone = 0;
                resetAllAnimation();
                moveDisabled = true;
                throwBack = true; // Set throwBack to true to throw the fish back
            }
        }

    }

    void animation(int counter) {
        spriteCounter++;
        if (spriteCounter > counter) {
            spriteCounter = 0; // Reset the sprite counter to 0
            spriteNum++;
        }

        if (spriteNum >= animationList.get(currentAnimationIndex).spriteTotal) {
            animationDone++;
            spriteCounter = 0;
            spriteNum = 0;
        }

    }

    void resetAllAnimation() {
        isDigging = false;
        isUndoDigging = false;
        isChopping = false;
        isCasting = false;
        isFishing = false;
        fishDetected = false;
        fishpulled = false;
        throwBack = false;
        isWatering = false;
        isPlanting = false;
        isHarvesting = false;

        animationDone = 0;
        spriteCounter = 0;
        spriteNum = 0;
        moveDisabled = false; // Enable movement after the action is done
    }

    public void drawEmote(Graphics2D g2) {
        // Draw the emote
        BufferedImage emoteImage = bubble[thinkSpriteNum];
        g2.drawImage(emoteImage, screenX + 28, screenY - 30, 48, 96, null);
        if (thinkSpriteNum == 2) {
            g2.drawImage(emote.get(currentEmote).get(0), screenX + 28, screenY - 30, 48, 48, null);
        }
        if (thinkSpriteNum == 3) {
            g2.drawImage(emote.get(currentEmote).get(1), screenX + 28, screenY - 30, 48, 48, null);
        }

        if (thinkSpriteNum < 3) {
            thinkSpriteCount++;
            if (thinkSpriteCount > 10) {
                thinkSpriteNum++;
                thinkSpriteCount = 0;

            }
        }

    }

    public int choosedFoodState = 0;

    public void selectItem() {
        int itemIndex = gp.ui.getItemIndexOnSlot(gp.ui.playerSlotCol, gp.ui.playerSlotRow); // Get the selected item

        // index from the UI

        if (itemIndex < inventory.size()) {
            Entity selectedItem = inventory.get(itemIndex); // Get the selected item from the inventory
            gp.player.currentItem = selectedItem; // Set the current item to the selected item
            currentTools = null; // Set current tools to null if the selected item is not a tool
            gp.gameState = gp.playState; // Set the game state to play state

            if(selectedItem.type_item == type_food) // Check if the selected item is food
            {
                gp.gameState = gp.foodItemChooseState;
                gp.ui.commandNum = 0;

            }else if(selectedItem.type_item == type_tool){ //tipe tools misalnya{
                currentTools = selectedItem.name;
            }
            else{
                isHolding = true; // Set the isHolding flag to true if the selected item is not food or tool
                if(selectedItem.type_item == type_seed){
                    System.out.println("day to mature: " + selectedItem.daysToMature);
                }
            }
        
        }
            


            // if(selectedItem == type_food) // misal makanan
            // {
            // if(selectedItem.use(this) == true)
            // {
            // if(selectedItem.amount > 1)
            // {
            // selectedItem.amount--; // Reduce the amount of the item by 1
            // }
            // else
            // {
            // inventory.remove(itemIndex); // Remove the item from the inventory if amount
            // is 1
            // }
            // }
            // }
    }
    

    public void draw(Graphics2D g2) {
        


        if (emoteOn) {
            drawEmote(g2);
        }

        BufferedImage image = null;
        BufferedImage image1 = null;
        ToolsAnimation currentTool = null;

        if (currentTools != null || currentItem!=null) {
            if(currentTools!=null){
                if (gp.keyH.useTool || currentTools.equalsIgnoreCase("fishRod")) {
                    drawFrontBlock(g2); // Draw the block in front of the player
                }
            }
            else if(currentItem!=null && gp.keyH.useTool && currentItem.type_item == type_seed){
                drawFrontBlock(g2);
            }
        }

        if(currentTools == null && currentItem == null && gp.keyH.useTool) {
            harvest(g2);
        }

        if (currentTools != null) {

            for (ToolsAnimation tool : toolsAnimationList) {
                if (tool.tools.equalsIgnoreCase(currentTools)
                        && tool.AnimationName.equalsIgnoreCase(animationList.get(currentAnimationIndex).name)) {
                    currentTool = tool;
                    break;
                }
            }

            if (currentTool == null) {
                System.out.println("Error: Current tool not found in toolsAnimationList ");
                return;
            }
        }


        BufferedImage body = null;
        BufferedImage eye = null;
        BufferedImage hair = null;
        BufferedImage outfit = null;
        switch (direction) {
            case "up":
                body = animationList.get(currentAnimationIndex).body_up[spriteNum]; // Get the idle up image
                eye = animationList.get(currentAnimationIndex).eye_up[spriteNum]; // Get the idle up image
                hair = animationList.get(currentAnimationIndex).hair_up[spriteNum]; // Get the idle up image
                outfit = animationList.get(currentAnimationIndex).outfit_up[spriteNum]; // Get the idle up image
                if (currentTool != null) {
                    image1 = currentTool.up[spriteNum]; // Get the tool up image
                }
                break;
            case "down":
                body = animationList.get(currentAnimationIndex).body_down[spriteNum]; // Get the idle down image
                eye = animationList.get(currentAnimationIndex).eye_down[spriteNum]; // Get the idle down image
                hair = animationList.get(currentAnimationIndex).hair_down[spriteNum]; // Get the idle down image
                outfit = animationList.get(currentAnimationIndex).outfit_down[spriteNum]; // Get the idle down image
                if (currentTool != null) {
                    image1 = currentTool.down[spriteNum]; // Get the tool down image
                }
                break;
            case "left":
                body = animationList.get(currentAnimationIndex).body_left[spriteNum]; // Get the idle left image
                eye = animationList.get(currentAnimationIndex).eye_left[spriteNum]; // Get the idle left image
                hair = animationList.get(currentAnimationIndex).hair_left[spriteNum]; // Get the idle left image
                outfit = animationList.get(currentAnimationIndex).outfit_left[spriteNum]; // Get the idle left image
                if (currentTool != null) {
                    image1 = currentTool.left[spriteNum]; // Get the tool left image
                }
                break;
            case "right":
                body = animationList.get(currentAnimationIndex).body_right[spriteNum]; // Get the idle right image
                eye = animationList.get(currentAnimationIndex).eye_right[spriteNum]; // Get the idle right image
                hair = animationList.get(currentAnimationIndex).hair_right[spriteNum]; // Get the idle right image
                outfit = animationList.get(currentAnimationIndex).outfit_right[spriteNum]; // Get the idle right image
                if (currentTool != null) {
                    image1 = currentTool.right[spriteNum]; // Get the tool right image
                }
                break;
        }

        System.out.println("drawing player at screenX: " + screenX + ", screenY: " + screenY);
        g2.drawImage(body, screenX, screenY, gp.playerSizeX, gp.playerSizeY, null); // Draw player image
        g2.drawImage(eye, screenX, screenY, gp.playerSizeX, gp.playerSizeY, null); // Draw player image
        g2.drawImage(hair, screenX, screenY, gp.playerSizeX, gp.playerSizeY, null); // Draw player image
        g2.drawImage(outfit, screenX, screenY, gp.playerSizeX, gp.playerSizeY, null); // Draw player image
        if (currentTool != null && image1 != null) {
            g2.drawImage(image1, currentTool.x, currentTool.y, currentTool.width, currentTool.height, null); // Draw tool image
        }

        if (currentAnimationIndex == 2) {
            if (duvetImage != null) {
                g2.drawImage(duvetImage, screenX, screenY + 48, gp.playerSizeX, gp.playerSizeY, null);
            } else {
                System.out.println("Error: duvetImage is null in Player draw method");
            }
        }
    }    public void addItemToInventory(Item item) {
        if (itemInventory.size() < maxInventorySize) {
            // Check if we already have this item type
            boolean added = false;
            for (Item existingItem : itemInventory) {
                if (existingItem.name.equals(item.name) && 
                    existingItem.type_item == item.type_item && 
                    item.type_item != Entity.TYPE_TOOL) { // Don't stack tools
                    existingItem.amount++;
                    added = true;
                    break;
                }
            }
            
            // If item wasn't stacked, add it as new item
            if (!added) {
                item.amount = 1;
                itemInventory.add(item);
            }

            // Play success sound
            gp.playSFX(gp.sfx, 2);
        } else {
            System.out.println("Inventory is full!");
            // Play error sound
            gp.playSFX(gp.sfx, 4);
        }
    }
}
