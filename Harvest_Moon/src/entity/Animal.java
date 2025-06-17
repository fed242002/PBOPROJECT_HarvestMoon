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
        width = 144;
        height = 144;

        if(name.equalsIgnoreCase("cow")){

            this.solidArea.width = 48; // Adjust solid area for animal
            this.solidArea.height = 96; // Adjust solid area for animal
            
            this.solidArea.x = 14; // Adjust solid area for animal
            this.solidArea.y = 15; // Adjust solid area for animal
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }

        walk = new Animation("walk", 6, "/assets/animal/" + name + "/WALK/",false,true);
        animationList.add(walk);
        currentAnimationIndex = 0; // default walk

        direction = "down";
        setAnimation("walk");
        speed = 1;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;
    }

    @Override
    public void interact() {
        System.out.println("Interacting with animal at (" + worldX + ", " + worldY + ")");
    }
    
}
