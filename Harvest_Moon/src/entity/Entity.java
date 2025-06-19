package entity;

import Main.GamePanel;
import animation.*;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import object.*;
import object.OBJ_Transition;


public class Entity extends SuperEntity implements Cloneable{

    public void chop(){}
    public void reset(){}
    public void watering(){}
    public void grow(){}

    //ini buat kalo misale obj ada banyak variasi gambar
    public ArrayList<String> imagePathList = new ArrayList<>(); // List of soil images
    

    


    public boolean pickUpAble = false; // Flag to check if the entity is pickable
    public ArrayList<BufferedImage> objAnimation = new ArrayList<>(); // List of tool images
    public int objAnimationSpriteCount = 0;
    public int objAnimationSpriteNum = 0;
    public int objAnimationSpriteTotal = 0;
    public boolean objectAnimationOn = false; // Flag to check if the object animation is on
    public boolean harvestable = false; // Flag to check if the entity is harvestable

    public void objAnimationUpdate() {
        if (objectAnimationOn) {
            
            image = objAnimation.get(objAnimationSpriteNum); // Set the current image for the object animation
            objAnimationSpriteCount++;
            if (objAnimationSpriteCount > 10) {
                objAnimationSpriteNum++;
                objAnimationSpriteCount = 0;
            }
            if (objAnimationSpriteNum >= objAnimationSpriteTotal) {
                if(this instanceof OBJ_Rumah){
                    gp.eventHandler.handleMapTransition(3, 25, 25);
                }

                if(this instanceof OBJ_Barn){
                    gp.eventHandler.handleMapTransition(4, 25, 28);
                }

                if(this instanceof OBJ_Market){
                    gp.eventHandler.handleMapTransition(5, 25, 18);
                }
                if(this instanceof OBJ_PostOffice){
                    gp.eventHandler.handleMapTransition(6, 25, 18);
                }

                if(this instanceof OBJ_Transition){
                    gp.eventHandler.handleMapTransition(1, 25, 2);
                }

                objAnimationSpriteNum = 0;
                objectAnimationOn = false; // Turn off the animation after it completes
                image = gp.setImage(path); // Reset the image to the default path
                gp.player.moveDisabled = false; // Enable player movement after the animation

            }
        }
    }




    int thinkSpriteNum = 0;
    int thinkSpriteCount = 0;
    BufferedImage bubble[] = new BufferedImage[4];
    BufferedImage exclamationMark[] = new BufferedImage[2];
    ArrayList<ArrayList<BufferedImage>> emote = new ArrayList<>();
    public boolean emoteOn = false; // apakah emoticon sedang ditampilkan
    boolean goalDirection = false; //set jadi true kalo misalnya ga ngikutin player


    public int currentEmote = 0;
    public boolean isWet = false;
    public GamePanel gp;
    public int worldX, worldY;
    public int speed; // Speed of the entity
    public String name;
    public boolean isChopped = false; // Flag to check if the tree is chopped

    public boolean specialNpc = false;
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1; // Sprite number for animation
    public int currentAnimationIndex = 0;
    public String currentTools = null; // Current tool being used by the entity
    public int actionLockCounter = 0;
    public Rectangle solidArea; // Rectangle for collision detection
    public String path;
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0; // Default position of the solid area
    public boolean collisionOn = false; // Flag for collision detection
    public boolean collision = false;
    public BufferedImage image;
    public int width = 48, height = 48; // Default size of the entity
    public boolean isObj = false;
    public Entity currentLight; // Current light source for the entity, if any
    public int lightRadius; // Radius of the light source, if any
    public boolean stackable = false; // kalo mau item nya stackable declare di obj nya jadi true
    public int amount = 1; // Amount of the entity, used for stackable items
    public ArrayList<Entity> inventory = new ArrayList<>();
    public ArrayList<Item> itemInventory = new ArrayList<>(); // Inventory for storing items
    public int type; // declare tipe nya sendiri2
    public boolean onPath = false;


    // item attributes
    public String description = " "; // Description of the item
    public int daysToMature;

    // type item
    // 0 = item, 1 = tool, 2 = seed, 3 = crop, 4 = food, 5 = fish, 6 = material, 7 = furniture
    public int type_item = 0;   // type item nya
    public final int type_tool = 1;
    public final int type_seed = 2;
    public final int type_crop = 3;
    public final int type_food = 4;
    public final int type_fish = 5;
    public final int type_material = 6;
    public final int type_furniture = 7;
    
    public int multiplier = 1;
    public String seedCrop = null; // Seed crop name, used for seeds that grow into crops
    public int dayCount = 0;
    public int wateredCount = 0;

    public void dayPassed(){}

    // animation
    Animation walk;
    Animation idle;
    public ArrayList<Animation> animationList = new ArrayList<>(); // 0: walk, 1:idle
    public ArrayList<ToolsAnimation> toolsAnimationList = new ArrayList<>();

    public boolean isAnimal = false; // Flag to check if the entity is an animal
    
    // dialogue
    public ArrayList<String> dialogues = new ArrayList<>();
    int dialogueIndex = 0; // Index for the current dialogue

    public int energyGiven = 0; // Energy given by the item, used for food and drinks
    public int quality = 0; // Quality of the item, used for crops and seeds
    public int buyPrice = 0; // Price of the item
    public int sellPrice = 0; // Price of the item

    public boolean isMatured = false;
    
    public boolean exitDialogueDisable = false;



    public boolean isRotten = false;

    public void speak() {
        if (dialogues.size() - 1 < dialogueIndex) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues.get(dialogueIndex);
        gp.ui.currentDialogueName = this.name;
        gp.ui.currentEntityDialogue = this;
        dialogueIndex++;

        // biar npc pas ngomong hadap player
        switch (gp.player.direction) {
            case "up":
                this.direction = "down";
                break;
            case "down":
                this.direction = "up";
                break;
            case "left":
                this.direction = "right";
                break;
            case "right":
                this.direction = "left";
                break;

        }
    }

    public void interact() {
        // ini buat interaksi per obj
    }

    public void setDialog() {
    }

    public Entity(GamePanel gp) {
        this.gp = gp;

        solidArea = new Rectangle(); // Set the size of the solid area for collision detection

        solidArea.x = 0;
        solidArea.y = 0; // Adjust based on your NPC sprites
        solidArea.width = 48;
        solidArea.height = 48; // Adjust based on your NPC sprites

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;





        //add emote
        bubble[0] = gp.setImage("/assets/ui/think/bubble_0.png");
        bubble[1] = gp.setImage("/assets/ui/think/bubble_1.png");
        bubble[2] = gp.setImage("/assets/ui/think/bubble_2.png");
        bubble[3] = gp.setImage("/assets/ui/think/bubble_3.png");

        ArrayList<BufferedImage> exclamationMark = new ArrayList<>();
        exclamationMark.add(gp.setImage("/assets/ui/think/exclamation/0.png"));
        exclamationMark.add(gp.setImage("/assets/ui/think/exclamation/1.png"));

        emote.add(exclamationMark);
    }


    public void turnOffEmote(){
        emoteOn = false; // Turn off the emote
        thinkSpriteNum = 0; // Reset the think sprite number
        thinkSpriteCount = 0; // Reset the think sprite count
    }

    public void drawEmote(Graphics2D g2, int screenX, int screenY) {
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


      

        if(objectAnimationOn) {
            objAnimationUpdate(); // Update the object animation if it's on
        }

        int screenX = worldX - gp.player.worldX + gp.player.screenX; // Calculate the screen X position
        int screenY = worldY - gp.player.worldY + gp.player.screenY; // Calculate the screen Y position

        if(emoteOn){
            drawEmote(g2, screenX, screenY); // Draw the emote if it's on
        }
        

        if (!isObj) {
            BufferedImage image = null;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // If the tile is within the screen
                                                                               // bounds
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                switch (direction) {
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

                if (gp.showDebugHitboxes) {
                    // Draw the solid area for debugging
                    g2.setColor(Color.RED);
                    g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
                }
                g2.drawImage(image, screenX, screenY, width, height, null);

            }
        } else {
            if (gp.showDebugHitboxes) {
                // Draw the solid area for debugging
                g2.setColor(Color.RED);
                g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            }
            g2.drawImage(image, screenX, screenY, width, height, null);

        }
    }

    public void setAction() {
    } // npc action

    public void checkCollison()
    {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npcs);
        boolean contactPlayer = gp.cChecker.checkPlayer1(this);
    }
    int spriteCounterMax = 10;

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false); // Check for collision with objects
        gp.cChecker.checkPlayer(this); // Check for collision with NPCs

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

        spriteCounter++;
        if (spriteCounter > spriteCounterMax) {
            spriteNum++;

            if (spriteNum > animationList.get(currentAnimationIndex).spriteTotal - 1) // If the sprite number exceeds
                                                                                      // the number of images
                spriteNum = 0; // Reset the sprite number to 0

            spriteCounter = 0; // Reset the sprite counter to 0
        }

    }

    public void setAnimation(String animation) {

        int i = 0;
        
        for (Animation x : animationList) {
            if (animation.equalsIgnoreCase(x.name)) {
                if (this.currentAnimationIndex != i) {
                    gp.stopMusic(gp.sfx);
                    // set animation
                    currentAnimationIndex = i;
                    // reset animation
                    spriteCounter = 0;
                    spriteNum = 0;
                    // play sfx
                    if (x.soundFX != -1) {
                        gp.playMusic(gp.sfx, x.soundFX);
                    }

                }
                break;
            }
            i++;
        }
    }

    public void searchPath(int goalCol, int goalRow)
    {
        int startCol = (worldX + solidArea.x) / gp.tileSize;
        int startRow = (worldY + solidArea.y) / gp.tileSize;

        gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow, this);

        if(gp.pFinder.search() == true)
        {
            // next x and y 
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            //entity solid area position
            int enLeftX = worldX + solidArea.x;
            int enRightX = worldX + solidArea.x + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = worldY + solidArea.y + solidArea.width; 

            if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize)
            {
                direction = "up";
            }
            else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize)
            {
                direction = "down";
            }
            else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize)
            {
                if(enLeftX > nextX)
                {
                    direction = "left";
                }
                if(enLeftX < nextX)
                {
                    direction = "right";
                }
            }
            else if(enTopY > nextY && enLeftX > nextX)
            {
                direction = "up";
                checkCollison();
                if(collisionOn == true)
                {
                    direction = "left";
                }
            }
            else if(enTopY > nextY && enLeftX < nextX)
            {
                direction = "up";
                checkCollison();
                if(collisionOn == true)
                {
                    direction = "right";
                }
            }
            else if(enTopY < nextY && enLeftX > nextX)
            {
                direction = "down";
                checkCollison();
                if(collisionOn == true)
                {
                    direction = "left";
                }
            }
            else if(enTopY < nextY && enLeftX < nextX)
            {
                direction = "down";
                checkCollison();
                if(collisionOn == true)
                {
                    direction = "right";
                }
            }

            if(this.goalDirection = true)
            {
                int nextCol = gp.pFinder.pathList.get(0).col;
                int nextRow = gp.pFinder.pathList.get(0).row;
                if(nextCol == goalCol && nextRow == goalRow)
                {
                    onPath = false;
                }
            }
        }
    }
    public BufferedImage getImage() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getImage'");
    }

    // Item type constants
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_TOOL = 1;
    public static final int TYPE_SEED = 2;
    public static final int TYPE_CROP = 3;
    public static final int TYPE_FOOD = 4;
    public static final int TYPE_FISH = 5;
    public static final int TYPE_MATERIAL = 6;
    public static final int TYPE_FURNITURE = 7;
}
