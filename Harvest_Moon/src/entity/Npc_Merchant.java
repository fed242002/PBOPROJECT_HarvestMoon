package entity;

import Main.GamePanel;
import animation.Animation;
import java.util.ArrayList;

public class Npc_Merchant extends Entity {
    
    public ArrayList<Entity> inventoryNPC;
    public ArrayList<Integer> itemPrices;
    public int selectedItem;
    public boolean showInventory;

    public Npc_Merchant(GamePanel gp, String name, int x, int y) {
        super(gp);
        this.name = name;
        width = gp.playerSizeX;
        height = gp.playerSizeY; // Adjust based on your NPC sprites

        this.gp = gp;
        this.worldX = x;
        this.worldY = y;
        this.solidArea.y = 48;
        exitDialogueDisable =  true;

        idle = new Animation("idle", 6, "/assets/npc/" + name + "/IDLE/");
        animationList.add(idle);
        currentAnimationIndex = 0; // default idle
        
        direction = "down";
        setAnimation("idle");
        speed = 0; // Set speed to 0 to prevent movement

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        // Initialize trading system
        inventoryNPC = new ArrayList<>();
        itemPrices = new ArrayList<>();
        selectedItem = 0;
        showInventory = false;
        
        // set dialog
        setDialogue();
    }

    public void setDialogue() {
        ArrayList<String> temp = new ArrayList<>();
        ArrayList<String> temp1 = new ArrayList<>();
        ArrayList<String> temp2 = new ArrayList<>();
        temp.add("He he, so you found me. \n I have some good stuff. \n Do you want to trade?");
        temp1.add("How are you?");
        temp2.add("Nice to meet you!");
        
        dialogues.add(temp);
        dialogues.add(temp1);
        dialogues.add(temp2);
    }

    public void speak() {
        super.speak();
    }

    public void setAction() {
        // Override parent setAction to make merchant stay in place
        // No movement logic needed
    }
}