package entity;

import java.util.Random;

import Main.GamePanel;
import animation.Animation;

public class Npc extends Entity {

    public Npc(GamePanel gp, String name, int x, int y) {
        super(gp);
        this.name = name;
        this.gp = gp;
        this.worldX = x;
        this.worldY = y;
        this.solidArea.y = 48;
        walk = new Animation("walk",6, "/assets/npc/"+name+"/WALK/");
        animationList.add(walk);
        idle = new Animation("walk",6, "/assets/npc/"+name+"/IDLE/");
        animationList.add(idle);
        currentAnimationIndex = 1; // default idle

        direction = "down";
        setAnimation("walk");
        speed = 1;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;


        //set dialog
        setDialogue();    

    }

     public void setDialogue(){
        dialogues.add("Hello, I'm " + name + "!");
        dialogues.add("How are you?");
        dialogues.add("Nice to meet you!");
        dialogues.add("Have a great day!");
        dialogues.add("See you later!");
    }

    public void speak(){
        super.speak();
    }

    public void setAction(){
        actionLockCounter++;

        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            else if(i <= 50){
                direction = "down";
            }
            else if(i <= 75){
                direction = "left";
            }
            else if(i <= 100){
                direction = "right";
            }

            actionLockCounter = 0;
        }

    }
}