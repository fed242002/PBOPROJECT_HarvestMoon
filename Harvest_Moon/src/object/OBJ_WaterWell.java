package object;

import java.io.BufferedReader;

import Main.EnergyIntake;
import Main.GamePanel;
import entity.Entity;

public class OBJ_WaterWell extends Entity{

    public OBJ_WaterWell(GamePanel gp, int x, int y) {
        super(gp);
        collision = true; // Enable collision for the water well
        name = "Water Well";
        path = "/assets/tile/HomeObjects/Well_Usable_Bucket_Empty_48x48.png"; // Path to the soil image
        this.worldX = x;
        this.worldY = y;
        this.width = 144;
        this.height = 96; 
        isObj = true; 
        image = gp.setImage(path);
        this.solidArea.y = 45;
        this.solidArea.x = 8;
        this.solidArea.width = 79;
        this.solidArea.height = 48;

        solidAreaDefaultY = this.solidArea.y;
        solidAreaDefaultX = this.solidArea.x;
        objAnimationSpriteTotal = 16;


    
        for(int i = 0; i < objAnimationSpriteTotal; i++) {
            objAnimation.add(gp.setImage("/assets/objAnimation/well/" + i + ".png"));
        }


    }

    @Override
    public void interact() {
        if(gp.keyH.interactPressed && gp.player.currentTools!= null){
            gp.keyH.interactPressed = false; // Reset the interact key
            if(gp.player.currentTools.equalsIgnoreCase("wateringCan")) {
                gp.player.energy -= EnergyIntake.takingWater;
                objectAnimationOn = true; // Enable object animation
                gp.player.moveDisabled = true;
                gp.player.setAnimation("idle");
                gp.playMusic(gp.sfx, 9);
            }
        }
    }
    
}
