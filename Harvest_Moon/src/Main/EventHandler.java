package Main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class EventHandler {
    GamePanel gp;
    EventRect eventRect[][];
    int eventRectDefaultX = 0; // Default X position of the event rectangle
    int eventRectDefaultY = 0; // Default Y position of the event rectangle
    int worldX, worldY; // World coordinates for the event
    int previousEventX, previousEventY; // Previous event coordinates
    boolean canTouchEvent = true; // Flag to check if the event is done

    public EventHandler(GamePanel gp) 
    {
        this.gp = gp;

        eventRect = new EventRect[gp.maxWorldCol][gp.maxWorldRow]; // Initialize the event rectangle array
        int col =0 , row =0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            eventRect[col][row] = new EventRect();
            eventRect[col][row].x = 23; // Set default X position
            eventRect[col][row].y = 23; // Set default Y position
            eventRect[col][row].width = 2; // Set width to tile size
            eventRect[col][row].height = 2; // Set height to tile size
            eventRect[col][row].eventRectDefaultX = eventRect[col][row].x; // Store default X position
            eventRect[col][row].eventRectDefaultY = eventRect[col][row].y; // Store default Y position  

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }

        worldX = 27 * gp.tileSize; // Set the world X coordinate for the event
        worldY = 14 * gp.tileSize; // Set the world Y coordinate for the event
    }

    public void draw(Graphics2D g2) {
        // g2.setColor(Color.BLUE);
        // g2.fillRect(worldX, worldY, eventRect[].width, eventRect.height);
    }

    public void checkEvent(){
        int xDistance = Math.abs(gp.player.worldX - previousEventX);
        int yDistance = Math.abs(gp.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if(distance > gp.tileSize) {
            canTouchEvent = true; // Reset the flag if the player has moved far enough
        }

        if(canTouchEvent){
            if(hit(27,14, null) == true && eventRect[27][14].eventDone == false) {
                //event
                damagePit(27,14,gp.eventFoundState);
            }

        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection){
        boolean hit = false;

        gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
        eventRect[eventCol][eventRow].x = eventCol * gp.tileSize + eventRect[eventCol][eventRow].x;
        eventRect[eventCol][eventRow].y = eventRow * gp.tileSize + eventRect[eventCol][eventRow].y;

        if(gp.player.solidArea.intersects(eventRect[eventCol][eventRow])) {
            if(reqDirection == null || gp.player.direction.equals(reqDirection)) {
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


    public void damagePit(int col, int row,int gameState ){
       
        gp.gameState = gameState;
        gp.ui.currentDialogue = "You fell into a pit!";
        eventRect[col][row].eventDone = true; // Mark the event as done
        canTouchEvent = false; // Prevent further interaction until the player moves away
    }
}
