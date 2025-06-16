package Main;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
    int eventRectDefaultX = 0;
    int eventRectDefaultY = 0;
    int worldX, worldY;
    int previousEventX, previousEventY;
    boolean canTouchEvent = true;
    
    // Transition animation variables
    private float alpha = 0f;
    private boolean transitioning = false;
    private boolean fadingOut = false; // True when fading to black, false when fading from black
    private int pendingTargetMap = -1;
    private int pendingX = -1;
    private int pendingY = -1;
    private final float FADE_SPEED = 0.05f; // Adjust this to control fade speed

    public EventHandler(GamePanel gp) {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow];
        int col = 0, row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23;
            eventRect[col][row].y = 23;
            eventRect[col][row].width = 2;
            eventRect[col][row].height = 2;
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x;
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

        worldX = 27 * gp.tileSize;
        worldY = 14 * gp.tileSize;
    }

    public void update() {
        // Handle transition animation
        if (transitioning) {
            if (fadingOut) {
                // Fade to black
                alpha += FADE_SPEED;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    // Perform the actual map change when screen is fully black
                    performMapTransition();
                    fadingOut = false; // Start fading back in
                }
            } else {
                // Fade from black
                alpha -= FADE_SPEED;
                if (alpha <= 0.0f) {
                    alpha = 0.0f;
                    transitioning = false;
                    canTouchEvent = false; // Prevent immediate re-triggering
                }
            }
        }
    }

    public void draw(Graphics2D g2) {
        // Draw transition overlay
        if (transitioning && alpha > 0) {
            // Save the original composite
            AlphaComposite originalComposite = (AlphaComposite) g2.getComposite();
            
            // Set alpha composite for transparency
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
            
            // Restore original composite
            g2.setComposite(originalComposite);
        }
    }

    private void startMapTransition(int targetMap, int newX, int newY) {
        if (!transitioning) { // Prevent starting multiple transitions
            transitioning = true;
            fadingOut = true;
            alpha = 0f;
            pendingTargetMap = targetMap;
            pendingX = newX;
            pendingY = newY;
        }
    }

    private void performMapTransition() {
        // This is called when the screen is fully black
        gp.changeMap(pendingTargetMap);
        gp.player.worldX = gp.tileSize * pendingX;
        gp.player.worldY = gp.tileSize * pendingY;
        
        // Reset pending values
        pendingTargetMap = -1;
        pendingX = -1;
        pendingY = -1;
    }

    // Old method - now calls the new transition method
    public void handleMapTransition(int targetMap, int newX, int newY) {
        startMapTransition(targetMap, newX, newY);
    }

    public void checkEvent() {
        
        if (!canTouchEvent) {
            int distance = Math.abs(gp.player.worldX - previousEventX) + Math.abs(gp.player.worldY - previousEventY);
            if (distance > gp.tileSize * 2) {
                canTouchEvent = true;
            }
        }

        if (!canTouchEvent || transitioning) return; // Don't check events during transition

        // Map transition events
        switch (gp.currentMap) {
            case 0 -> { // Home
                if (gp.player.worldY >= gp.tileSize * 47 && gp.player.worldY <= gp.tileSize * 48 &&
                    gp.player.worldX >= gp.tileSize * 22 && gp.player.worldX <= gp.tileSize * 26) {
                    handleMapTransition(1, 25, 2);
                }
            }
            case 1 -> { // Forest
                if (gp.player.worldX >= gp.tileSize * 24 && gp.player.worldX <= gp.tileSize * 26 && 
                    gp.player.worldY >= gp.tileSize * 0 && gp.player.worldY <= gp.tileSize * 1) {
                    handleMapTransition(0, 24, 46);
                }
                
                else if (gp.player.worldX >= gp.tileSize * 47 && gp.player.worldX <= gp.tileSize * 48 && 
                    gp.player.worldY >= gp.tileSize * 23 && gp.player.worldY <= gp.tileSize * 27) {
                    handleMapTransition(2, 4, 37);
                }
            }
            case 2 -> { // Town Hall
                if (gp.player.worldX >= gp.tileSize * 0 && gp.player.worldX <= gp.tileSize * 1 && 
                    gp.player.worldY >= gp.tileSize * 35 && gp.player.worldY <= gp.tileSize * 39) {
                    handleMapTransition(1, 46, 25);
                }
            }
            case 3 -> { // Inside the House
                if (gp.player.worldX >= gp.tileSize * 24 && gp.player.worldX <= gp.tileSize * 26 && 
                    gp.player.worldY >= gp.tileSize * 27 && gp.player.worldY <= gp.tileSize * 28) {
                    handleMapTransition(0, 37, 17);
                }
            }


        }
        
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if (distance > gp.tileSize) {
            canTouchEvent = true;
        }

        if (canTouchEvent) {
            if (hit(27, 14, null) == true && eventRect[27][14].eventDone == false) {
                damagePit(27, 14, gp.eventFoundState);
            }
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[eventCol][eventRow].x = eventCol * gp.tileSize + eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y = eventRow * gp.tileSize + eventRect[eventCol][eventRow].y;

        if (gp.player.solidArea.intersects(eventRect[eventCol][eventRow])) {
            if (reqDirection == null || gp.player.direction.equals(reqDirection)) {
                hit = true;

                previousEventX = gp.player.worldX;
                previousEventY = gp.player.worldY;
            }
        }

        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect[eventCol][eventRow].x = eventRect[eventCol][eventRow].eventRectDefaultX;
        eventRect[eventCol][eventRow].y = eventRect[eventCol][eventRow].eventRectDefaultY;

        return hit;
    }

    public void damagePit(int col, int row, int gameState) {
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        eventRect[col][row].eventDone = true;
        canTouchEvent = false;
    }
}