package entity;

import java.util.ArrayList;
import java.util.Random;

import Main.GamePanel;
import animation.Animation;

public class Npc_Merchant extends Entity {

    public Npc_Merchant(GamePanel gp, String name, int x, int y) {
        super(gp);
        this.name = name;
        this.gp = gp;
        this.worldX = x;
        this.worldY = y;
        this.solidArea.y = 48;
        idle = new Animation("idle", 6, "/assets/npc/" + name + "/IDLE/");
        animationList.add(idle);
        currentAnimationIndex = 1; // default idle
        // specialNpc = true;
        final ArrayList<Entity> inventoryNPC = new ArrayList<>(); // inventory e punya npc

        direction = "down";
        setAnimation("walk");
        speed = 1;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        // set dialog
        setDialogue();

    }

    public void setDialogue() {
        dialogues.add("He he, so you found me. \n I have some good stuff. \n Do you want to trade?");
        dialogues.add("How are you?");
        dialogues.add("Nice to meet you!");
        dialogues.add("Have a great day!");
        dialogues.add("See you later!");
    }

    public void speak() {
        super.speak();

        gp.player.gold += 100;
        // gp.gameState = gp.tradeState;
        // gp.ui.npc = this;
    }

    public void sellItem(){ // ini nanti isi objek apa aja yang mau dijual nanti sama si tukang trade
        // inventory.add(new OBJ_Rumah(gp));
        // inventory.add(new OBJ_soil(gp));
    }


}