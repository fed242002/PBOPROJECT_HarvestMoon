package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.ArrayList;

import Main.GamePanel;

public class TileManager {
    GamePanel gp;
    ArrayList<Tile> tile = new ArrayList<>(); 
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp; 
        
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Initialize the mapTileNum array with the maximum screen columns and rows

        getTileImage(); 
        

    }

    public void getTileImage(){
        tile.clear();

        tile.add(new Tile("/Assets/grass.png", false));
        tile.add(new Tile("/Assets/brick.jpg", false));
        
    }

    public void loadMap(){

        try {
            InputStream is = getClass().getResourceAsStream("/Assets/map.txt"); // Load the map file
            BufferedReader br = new BufferedReader(new InputStreamReader(is)); // Create a BufferedReader to read the file

            int col = 0;
            int row = 0;

            while(col < gp.maxWorldCol && row < gp.maxWorldRow) { // Loop through the columns and rows of the map
                String line = br.readLine(); // Read a line from the file
                while(col < gp.maxWorldCol) { // Loop through the columns of the map
                    String numbers[] = line.split(" "); // Split the line into numbers
                    int num = Integer.parseInt(numbers[col]); // Parse the number from the string
                    mapTileNum[col][row] = num; // Assign the number to the mapTileNum array
                    col++; // Move to the next column
                }
                if(col == gp.maxWorldCol) { // If the end of the row is reached
                    col = 0; // Reset the column to 0
                    row++; // Move to the next row
                }
            }
            br.close();

        } catch (Exception e) {
            System.out.println("error di loadMap()"); // Print an error message if there is an exception
            e.printStackTrace();
        }

    }




    public void draw(Graphics2D g2){

        int worldCol = 0;
        int worldRow = 0;


        while(worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
          
            int tileNum = mapTileNum[worldCol][worldRow]; // Get the tile number from the mapTileNum array

            int worldX = worldCol * gp.tileSize; // Calculate the world X position
            int worldY = worldRow * gp.tileSize; // Calculate the world Y position
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // Calculate the screen X position
            int screenY = worldY - gp.player.worldY + gp.player.screenY; // Calculate the screen Y position

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && // If the tile is within the screen bounds
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) 
               { 
                    g2.drawImage(tile.get(tileNum).image, screenX, screenY, gp.tileSize, gp.tileSize,null);
               }

            worldCol++;
            
            if(worldCol == gp.maxWorldCol){ // If the end of the row is reached
                worldCol = 0; // Reset the column to 0
                worldRow++; // Move to the next row
            }
        }

    }
}
