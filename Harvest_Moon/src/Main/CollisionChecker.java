package Main;

import entity.Entity;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;

public class CollisionChecker {

    GamePanel gp;

    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity) {
        // Calculate entity boundaries
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
        
        // Variables for predicted position based on movement direction
        int entityLeftCol, entityRightCol, entityTopRow, entityBottomRow;
        
        // Determine which tiles to check based on direction
        switch (entity.direction) {
            case "up":
                // Check the row above
                entityTopRow = (entityTopWorldY - entity.speed) / gp.tileSize;
                entityLeftCol = entityLeftWorldX / gp.tileSize;
                entityRightCol = entityRightWorldX / gp.tileSize;
                
                // Boundary check
                if (entityTopRow < 0 || entityLeftCol < 0 || entityRightCol >= gp.maxWorldCol) {
                    entity.collisionOn = true;
                    return;
                }
                
                // Check collision with tiles
                if (isTileCollidable(entityLeftCol, entityTopRow) || 
                    isTileCollidable(entityRightCol, entityTopRow)) {
                    entity.collisionOn = true;
                }
                break;
                
            case "down":
                // Check the row below
                entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
                entityLeftCol = entityLeftWorldX / gp.tileSize;
                entityRightCol = entityRightWorldX / gp.tileSize;
                
                // Boundary check
                if (entityBottomRow >= gp.maxWorldRow || entityLeftCol < 0 || entityRightCol >= gp.maxWorldCol) {
                    entity.collisionOn = true;
                    return;
                }
                
                // Check collision with tiles
                if (isTileCollidable(entityLeftCol, entityBottomRow) || 
                    isTileCollidable(entityRightCol, entityBottomRow)) {
                    entity.collisionOn = true;
                }
                break;
                
            case "left":
                // Check the column to the left
                entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
                entityTopRow = entityTopWorldY / gp.tileSize;
                entityBottomRow = entityBottomWorldY / gp.tileSize;
                
                // Boundary check
                if (entityLeftCol < 0 || entityTopRow < 0 || entityBottomRow >= gp.maxWorldRow) {
                    entity.collisionOn = true;
                    return;
                }
                
                // Check collision with tiles
                if (isTileCollidable(entityLeftCol, entityTopRow) || 
                    isTileCollidable(entityLeftCol, entityBottomRow)) {
                    entity.collisionOn = true;
                }
                break;
                
            case "right":
                // Check the column to the right
                entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
                entityTopRow = entityTopWorldY / gp.tileSize;
                entityBottomRow = entityBottomWorldY / gp.tileSize;
                
                // Boundary check
                if (entityRightCol >= gp.maxWorldCol || entityTopRow < 0 || entityBottomRow >= gp.maxWorldRow) {
                    entity.collisionOn = true;
                    return;
                }
                
                // Check collision with tiles
                if (isTileCollidable(entityRightCol, entityTopRow) || 
                    isTileCollidable(entityRightCol, entityBottomRow)) {
                    entity.collisionOn = true;
                }
                break;
        }
    }

    // Helper method to check if a tile is collidable
    private boolean isTileCollidable(int col, int row) {
        int tileNum = gp.tileM.mapTileNum[col][row];
        return gp.tileM.tile.get(tileNum).collision;
    }

    /**
     * Draws hitboxes for the player and all entities for debugging purposes
     * @param g2 Graphics2D object for drawing
     */
    public void drawHitboxes(Graphics2D g2) {
        // Save original settings
        Color originalColor = g2.getColor();
        float originalAlpha = 1.0f;
        
        // Draw player hitbox with bright green color for better visibility
        g2.setColor(new Color(0, 255, 0, 128)); // Semi-transparent green for player
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        
        int playerScreenX = gp.player.screenX;
        int playerScreenY = gp.player.screenY;
        
        // Draw the actual player hitbox (solid area)
        g2.drawRect(
            playerScreenX + gp.player.solidArea.x,
            playerScreenY + gp.player.solidArea.y,
            gp.player.solidArea.width,
            gp.player.solidArea.height
        );
        
        // Draw tile hitboxes with blue color
        drawTileHitboxes(g2);
        
        // Restore original settings
        g2.setColor(originalColor);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, originalAlpha));
    }
    
    /**
     * Draw hitboxes for all collidable tiles visible on screen
     * @param g2 Graphics2D object for drawing
     */
    private void drawTileHitboxes(Graphics2D g2) {
        // Set different color for tile hitboxes
        g2.setColor(new Color(0, 0, 255, 128)); // Semi-transparent blue
        
        // Get the player's position to determine what tiles are on screen
        int playerWorldCol = gp.player.worldX / gp.tileSize;
        int playerWorldRow = gp.player.worldY / gp.tileSize;
        
        // Calculate range of tiles to draw (visible on screen)
        int startCol = Math.max(0, playerWorldCol - (gp.screenWidth/(2*gp.tileSize)) - 1);
        int endCol = Math.min(gp.maxWorldCol - 1, playerWorldCol + (gp.screenWidth/(2*gp.tileSize)) + 1);
        int startRow = Math.max(0, playerWorldRow - (gp.screenHeight/(2*gp.tileSize)) - 1);
        int endRow = Math.min(gp.maxWorldRow - 1, playerWorldRow + (gp.screenHeight/(2*gp.tileSize)) + 1);
        
        // Loop through visible tiles
        for (int col = startCol; col <= endCol; col++) {
            for (int row = startRow; row <= endRow; row++) {
                int tileNum = gp.tileM.mapTileNum[col][row];
                
                // Only draw hitbox if tile has collision
                if (tileNum >= 0 && tileNum < gp.tileM.tile.size() && gp.tileM.tile.get(tileNum).collision) {
                    // Calculate screen coordinates
                    int worldX = col * gp.tileSize;
                    int worldY = row * gp.tileSize;
                    int screenX = worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = worldY - gp.player.worldY + gp.player.screenY;
                    
                    // Draw tile hitbox
                    g2.drawRect(screenX, screenY, gp.tileSize, gp.tileSize);
                }
            }
        }
    }
    
    /**
     * Helper method to draw a hitbox for a single entity
     * @param g2 Graphics2D object for drawing
     * @param entity The entity whose hitbox to draw
     */
    private void drawEntityHitbox(Graphics2D g2, Entity entity) {
        int screenX = entity.worldX - gp.player.screenX;
        int screenY = entity.worldY - gp.player.screenY;
        
        // Only draw if on screen
        if (screenX + gp.tileSize > 0 && 
            screenX - gp.tileSize < gp.screenWidth && 
            screenY + gp.tileSize > 0 && 
            screenY - gp.tileSize < gp.screenHeight) {
            
            g2.drawRect(
                screenX + entity.solidArea.x,
                screenY + entity.solidArea.y,
                entity.solidArea.width,
                entity.solidArea.height
            );
        }
    }
}

