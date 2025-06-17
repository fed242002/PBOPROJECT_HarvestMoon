package object;

import Main.GamePanel;
import entity.Animal;
import entity.Entity;

public class OBJ_Bed extends Entity {

    int type;
    
    //type ini variasi gambar bed ye misal 0 skarang itu bed ungu

    int playerTempX;
    int playerTempY;


    public OBJ_Bed(GamePanel gp, int type,int x, int y) {


        super(gp);
        if(type>imagePathList.size() - 1) {
            type = 0; // Reset to the first image if type exceeds available images
        }
        this.type = type; // Set the type of bed
        imagePathList.add("/assets/object/bed0.png"); // Add the bed image path to the list

        name = "bed";
        path = imagePathList.get(type); // Path to the bed image
        image = gp.setImage(path); // Set the image for the bed
        collision = true; // Set collision to true for this object
        worldX = x;
        worldY = y;
        width = 48; // Width of the bed
        height = 144; // Height of the bed
        isObj = true; // Set this entity as an object

        this.solidArea.x = 0;
        this.solidArea.y = 24;
        this.solidArea.width = width;
        this.solidArea.height = 96;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;


    }

    @Override
    public void interact() {
        if(gp.keyH.interactPressed ){
            playerTempX = gp.player.worldX; // Store the player's current X position
            playerTempY = gp.player.worldY; // Store the player's current Y position

            if(gp.player.currentTools != null) {
                gp.player.currentTools = null; 
            }
            gp.player.duvetImage = gp.setImage("/assets/object/duvet" + type + ".png"); // Set the duvet image for the bed
            
            gp.keyH.interactPressed = false; // Reset the interact key
            gp.player.setAnimation("sleep"); // Set the player's animation to sleep
            gp.player.moveDisabled = true; // Disable player movement
            gp.player.worldX = worldX; // Set player's world position to the bed's position
            gp.player.worldY = worldY; // Set player's world position to the bed's position
            
            //ganti hari implement sini

            int sleepTimeInSecond = 5; // Sleep time in milliseconds

            try {
                int sleepCounter = sleepTimeInSecond * 3; // Total iterations for the animation
                for(int i = 0; i < sleepCounter; i++) { 
                    Thread.sleep(320);
                    gp.player.spriteNum++;
                    if(gp.player.spriteNum >= gp.player.animationList.get(gp.player.currentAnimationIndex).spriteTotal) {
                        gp.player.spriteNum = 0; // Reset sprite number after reaching the total
                    }
                    gp.repaint(); // Repaint the game panel to update the player's animation

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } // Sleep for 1 second to simulate the sleeping process


            //reset semua pas ganti hari
                resetBed();
                //reset semua Entity farm, wet soil -> not wet, pohon -> not cut, etc.
                for(Entity e : gp.farmObj) {
                    e.reset();
                }

                for(Entity e : gp.cropObj) {
                    e.dayPassed();
                }

                //reset Energy
                gp.player.energy = gp.player.maxEnergy; // Reset player's energy to maximum
            
                











            //pas bangun            
            gp.player.setAnimation("idle");
            gp.player.moveDisabled = false; // Enable player movement
            gp.player.worldX = playerTempX; // Restore player's X position
            gp.player.worldY = playerTempY; // Restore player's Y position
            gp.player.duvetImage = null; // Reset duvet image
        }
    }

    public void resetBed()
    {
        gp.hour = 8; // Reset hour to 8 AM
                gp.minute = 0; // Reset minute to 0
                gp.timeCounter = 0; // Reset time counter to 0
                gp.currDay++;
                gp.currDate++;
                for(int i = 0; i < gp.npcs.size(); i++) {
                    if(gp.npcs.get(i) instanceof Animal) {
                        gp.npcs.get(i).reset(); // Reset all animals
                }
    }

}
