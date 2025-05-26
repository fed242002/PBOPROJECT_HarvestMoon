package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

import Main.GamePanel;
import entity.SuperEntity;

public class SuperObject extends SuperEntity {

    public BufferedImage image;
    public String name;
    public String path;
    public boolean collision = false;
    public int worldX, worldY, map;
    public Rectangle solidArea = new Rectangle();
    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    int width = 48, height = 48;
    GamePanel gp;

    public SuperObject(GamePanel gp) {
        this.gp = gp; // Initialize the GamePanel instance

    }

    public void interact() {
        // ini buat interaksi per obj
    }

    public void draw(Graphics2D g2, GamePanel gp) {
        int screenX = worldX - gp.player.worldX + gp.player.screenX; // Calculate the screen X position
        int screenY = worldY - gp.player.worldY + gp.player.screenY; // Calculate the screen Y position

        // if(worldX + gp.tileSize * 2> gp.player.worldX - gp.player.screenX && // If
        // the tile is within the screen bounds
        // worldX - gp.tileSize * 2< gp.player.worldX + gp.player.screenX &&
        // worldY + gp.tileSize * 2> gp.player.worldY - gp.player.screenY &&
        // worldY - gp.tileSize * 2 < gp.player.worldY + gp.player.screenY)
        // {
        if (gp.showDebugHitboxes) {
            // Draw the solid area for debugging
            g2.setColor(Color.RED);
            g2.fillRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }

        g2.drawImage(image, screenX, screenY, width, height, null);
        // }
    }
}