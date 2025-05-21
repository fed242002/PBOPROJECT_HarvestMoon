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
        walk = new Animation("walk",6, "/assets/npc/"+name+"/WALK/");
        animationList.add(walk);
        idle = new Animation("walk",6, "/assets/npc/"+name+"/IDLE/");
        animationList.add(idle);
        currentAnimationIndex = 1; // default idle

        direction = "down";
        setAnimation("walk");
        speed = 1;

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