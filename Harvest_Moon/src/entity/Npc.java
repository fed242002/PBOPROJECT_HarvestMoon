package entity;

import Main.GamePanel;
import animation.Animation;

import java.util.ArrayList;
import java.util.Random;

public class Npc extends Entity {

    public Npc(GamePanel gp, String name, int x, int y) {
        super(gp);
        width = gp.playerSizeX;
        height = gp.playerSizeY; // Adjust based on your NPC sprites

        this.name = name;
        this.gp = gp;
        this.worldX = x;
        this.worldY = y;
        this.solidArea.y = 48;
        idle = new Animation("idle", 6, "/assets/npc/" + name + "/IDLE/");
        animationList.add(idle);
        walk = new Animation("walk", 6, "/assets/npc/" + name + "/WALK/");
        animationList.add(walk);
        currentAnimationIndex = 1; // default idle
        // specialNpc = true;

        direction = "down";
        setAnimation("walk");
        speed = 1;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        // set dialog
        setDialogue();

    }

    public void setDialogue() {
        ArrayList<String> temp = new ArrayList<>();
        temp.add("Hello, I'm " + name + "!");
        temp.add("How are you?");
        temp.add("Nice to meet you!");
        temp.add("Have a great day!");
        temp.add("See you later!");
        temp.add("nuub");
        temp.add("im not eddy");
        temp.add("idiot");
    
        dialogues.add(temp);
        ArrayList<String> temp1 = new ArrayList<>();
        temp1.add("I am a simple NPC, \n I don't have much to say.");
        temp1.add("I can help you with some basic tasks.");
        dialogues.add(temp1);
    }

    public void speak() {
        super.speak();
        gp.player.gold +=100;
        //onPath = true; // di path
    }

    public void setAction() {
        actionLockCounter++;

        if(onPath == true)
        {
            //ini contoh pake goal 
            int goalRow = 17; // masukin row
            int goalCol = 39; // masukin col
            goalDirection = true; // true in kalo mau sesuai goal direction


            //ini contoh pake following player
            // int goalCol = (gp.player.worldX + gp.player.solidArea.x)/gp.tileSize; 
            // int goalRow = (gp.player.worldY + gp.player.solidArea.y)/gp.tileSize; 

            searchPath(goalCol,goalRow);
            
        }

        else {
            if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else if (i <= 100) {
                direction = "right";
            }

            actionLockCounter = 0;
        }
        }

    }
}