package entity;

import Main.GamePanel;
import animation.Animation;

public class Animal extends Entity{
    GamePanel gp;
    public int age = 0; // Age of the animal in days
    String hasil;
    boolean readyToHarverst = false;

    public Animal(GamePanel gp,String name ,int x, int y) {
        super(gp);
        this.gp = gp;
        this.worldX = x;
        this.worldY = y;
        this.solidArea.y = 48; // Adjust solid area for animal
        walk = new Animation("walk", 6, "/assets/animal/" + name + "/WALK/",false,true);
        animationList.add(walk);
        currentAnimationIndex = 0; // default walk

        direction = "down";
        setAnimation("walk");
        speed = 1;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }
    
}
