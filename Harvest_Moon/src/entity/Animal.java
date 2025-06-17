package entity;

import Main.GamePanel;
import animation.Animation;

public class Animal extends Entity{
    GamePanel gp;
    public int age = 0; // Age of the animal in days
    String hasil;
    boolean readyToHarverst = false;
    int xStart, yStart;
    int xEnd, yEnd;
    boolean walkToDestination = true;
    boolean walkBack = false;

    public Animal(GamePanel gp,String name ,int x, int y) {
        super(gp);
        this.gp = gp;
        this.worldX = x;
        this.worldY = y;
        this.name = name;
        this.speed = 1;
        xStart = x;
        yStart = y;
        spriteCounterMax = 30;
        xEnd = x ; // Example end position, adjust as needed
        yEnd = y + 144; // Example end position, adjust as needed
        
        if(name.equalsIgnoreCase("cow")){
            
            width = 144;
            height = 144;
            this.solidArea.width = 40; // Adjust solid area for animal
            this.solidArea.height = 110; // Adjust solid area for animal
            
            this.solidArea.x = 50; // Adjust solid area for animal
            this.solidArea.y = 28; // Adjust solid area for animal
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }
        
        
        if(name.equalsIgnoreCase("brownsheep")||name.equalsIgnoreCase("graysheep")
        ||name.equalsIgnoreCase("whitesheep")||name.equalsIgnoreCase("yellowsheep")){
            
            width = 96;
            height = 96;
            this.solidArea.width = 45; // Adjust solid area for animal
            this.solidArea.height = 60; // Adjust solid area for animal
            
            this.solidArea.x = 25; // Adjust solid area for animal
            this.solidArea.y = 40; // Adjust solid area for animal
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }
        
        if(name.equalsIgnoreCase("chicken")){
            
            width = 48;
            height = 48;
            this.solidArea.width = 24; // Adjust solid area for animal
            this.solidArea.height = 24; // Adjust solid area for animal
            
            this.solidArea.x = 12; // Adjust solid area for animal
            this.solidArea.y = 12; // Adjust solid area for animal
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

        isAnimal = true;
    }

    @Override
    public void interact() {
        System.out.println("Interacting with "+name+" at (" + worldX + ", " + worldY + ")");

        // if(name.equalsIgnosreCase("cow")){

        // }

    }

    @Override
    public void setAction() {
        if(walkToDestination){
            if(worldX >= xEnd && worldY >= yEnd){
                walkToDestination = false; // Stop walking to destination
                walkBack = true; // Start walking back
                direction = "up"; // Set direction to up
            }
        }
        if(walkBack){
            if(worldX <= xStart && worldY <= yStart){
                walkBack = false; // Stop walking back
                walkToDestination = true; // Start walking to destination
                direction = "down"; // Set direction to down
            }
        }
    }
    
}
