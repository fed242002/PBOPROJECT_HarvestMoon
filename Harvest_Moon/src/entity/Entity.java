package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Main.GamePanel;
import animation.Animation;

public class Entity extends SuperEntity {
    GamePanel gp;
    public int worldX, worldY;
    public int speed; // Speed of the entity
    public String name;

    public boolean specialNpc = false; 
    public String direction = "down";
    public int spriteCounter = 0;
    public int spriteNum = 1; // Sprite number for animation
    public int currentAnimationIndex = 0;
    public int actionLockCounter = 0;
    public Rectangle solidArea; // Rectangle for collision detection
    public String path;
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0; // Default position of the solid area
    public boolean collisionOn = false; // Flag for collision detection
    public boolean collision = false;
    public BufferedImage image;
    public int width = 48, height = 48; // Default size of the entity
    public boolean isObj = false;
    public Entity currentLight; // Current light source for the entity, if any
    public int lightRadius = 0; // Radius of the light source, if any
    public final int type_light = 9;

    // animation
    Animation walk;
    Animation idle;
    public ArrayList<Animation> animationList = new ArrayList<>(); // 0: walk, 1:idle

    // dialogue
    public ArrayList<String> dialogues = new ArrayList<>();
    int dialogueIndex = 0; // Index for the current dialogue

    public void speak() {
        if (dialogues.size() - 1 < dialogueIndex) {
            dialogueIndex = 0;
        }
        gp.ui.currentDialogue = dialogues.get(dialogueIndex);
        gp.ui.currentDialogueName = this.name;
        gp.ui.currentEntityDialogue = this;
        dialogueIndex++;

        // biar npc pas ngomong hadap player
        switch (gp.player.direction) {
            case "up":
                this.direction = "down";
                break;
            case "down":
                this.direction = "up";
                break;
            case "left":
                this.direction = "right";
                break;
            case "right":
                this.direction = "left";
                break;

        }
    }

    public void interact() {
        // ini buat interaksi per obj
    }

    public void setDialog() {
    }

    public Entity(GamePanel gp) {
        this.gp = gp;

        solidArea = new Rectangle(); // Set the size of the solid area for collision detection

        solidArea.x = 0;
        solidArea.y = 0; // Adjust based on your NPC sprites
        solidArea.width = 48;
        solidArea.height = 48;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

    }

    public void draw(Graphics2D g2) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX; // Calculate the screen X position
        int screenY = worldY - gp.player.worldY + gp.player.screenY; // Calculate the screen Y position

        if (!isObj) {
            BufferedImage image = null;

            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // If the tile is within the screen
                                                                               // bounds
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                switch (direction) {
                    case "up":
                        image = animationList.get(currentAnimationIndex).up[spriteNum]; // Get the idle up image
                        break;
                    case "down":
                        image = animationList.get(currentAnimationIndex).down[spriteNum]; // Get the idle up image
                        break;
                    case "left":
                        image = animationList.get(currentAnimationIndex).left[spriteNum]; // Get the idle up image
                        break;
                    case "right":
                        image = animationList.get(currentAnimationIndex).right[spriteNum]; // Get the idle up image
                        break;

                }

                if (gp.showDebugHitboxes) {
                    // Draw the solid area for debugging
                    g2.setColor(Color.RED);
                    g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
                }
                g2.drawImage(image, screenX, screenY, gp.playerSizeX, gp.playerSizeY, null);

            }
        } else {
            if (gp.showDebugHitboxes) {
                // Draw the solid area for debugging
                g2.setColor(Color.RED);
                g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
            }
            g2.drawImage(image, screenX, screenY, width, height, null);

        }
    }

    public void setAction() {
    } // npc action

    public void update() {
        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false); // Check for collision with objects
        gp.cChecker.checkPlayer(this); // Check for collision with NPCs

        if (collisionOn == false) {
            switch (direction) {
                case "up":
                    worldY -= speed; // Move the player up
                    break;
                case "down":
                    worldY += speed; // Move the player down
                    break;
                case "left":
                    worldX -= speed; // Move the pldddddayer left
                    break;
                case "right":
                    worldX += speed; // Move the player right
                    break;

            }
        }

        spriteCounter++;
        if (spriteCounter > 10) {
            spriteNum++;

            if (spriteNum > animationList.get(currentAnimationIndex).spriteTotal - 1) // If the sprite number exceeds
                                                                                      // the number of images
                spriteNum = 0; // Reset the sprite number to 0

            spriteCounter = 0; // Reset the sprite counter to 0
        }

    }

    public void setAnimation(String animation) {

        int i = 0;
        for (Animation x : animationList) {
            if (animation.equalsIgnoreCase(x.name)) {
                if (this.currentAnimationIndex != i) {
                    gp.stopMusic(gp.sfx);
                    // set animation
                    currentAnimationIndex = i;
                    // reset animation
                    spriteCounter = 0;
                    spriteCounter = 0;
                    // play sfx
                    if (x.soundFX != -1) {
                        gp.playMusic(gp.sfx, x.soundFX);
                    }

                }
                break;
            }
            i++;
        }
    }

}
