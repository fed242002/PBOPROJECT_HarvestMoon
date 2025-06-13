package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Main.GamePanel;
import Main.MapDB;
import Main.MapData;

public class TileManager {
    GamePanel gp;
    public ArrayList<Tile> tile = new ArrayList<>(); 
    public ArrayList<String> fileNames = new ArrayList<>(); 
    public ArrayList<String> collisionStatus = new ArrayList<>(); 
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp; 

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow]; // Initialize the mapTileNum array with the correct size
        loadTileData(MapDB.mapList.get(gp.currentMap).tileDataPath); // Load tile data from the specified path
        loadMap(MapDB.mapList.get(gp.currentMap).path); // Load the map from the specified path
        MapDB.mapList.get(gp.currentMap).needsRefresh = true;


    }
    


    public void loadTileData(String dataPath){
        // clear previous data
        fileNames.clear();
        collisionStatus.clear();

        InputStream is = getClass().getResourceAsStream(dataPath);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line;

        try {
            while((line = br.readLine()) != null) {
                fileNames.add(line); 
                collisionStatus.add(br.readLine()); 
            }
        } catch (IOException e) {
            System.out.println("Error loading tile data: " + e.getMessage());
        }

        getTileImage(MapDB.mapList.get(gp.currentMap).tilePath); // Load tile images using the file names and collision status

    }

    public void getTileImage(String dataPath){
        tile.clear();

        for(int i = 0; i < fileNames.size(); i++){
            String fileName = dataPath +fileNames.get(i);
            boolean collision;

            if(collisionStatus.get(i).equalsIgnoreCase("true")){
                collision = true; 
            } else {
                collision = false; 
            }

            Tile t = new Tile(fileName, collision);
            tile.add(t); 
        }
        

        
    }

    public void loadMap(String mapPath){
        try {
            InputStream is = getClass().getResourceAsStream(mapPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));


            int row = 0;
            
            while(row < gp.maxWorldRow) { // Process one row at a time
                String line = br.readLine();
                if(line == null) {
                    // Reached end of file before filling the map
                    System.out.println("Warning: Map file has fewer rows than expected");
                    break;
                }
                
                String[] numbers = line.split(" ");
                
                if(numbers.length < gp.maxWorldCol) {
                    System.out.println("Warning: Row " + row + " has fewer columns than expected");
                }
                
                // Process each number in the current row
                for(int col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                }
                
                row++;
            }
            
            br.close();
            
        } catch (Exception e) {
            System.out.println("Error in loadMap(): " + e.getMessage());
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

                if(tileNum < 0 || tileNum >= tile.size()) {
                    System.out.println("Warning: Tile number " + tileNum + " is out of bounds for the tile list.");
                    System.out.println("Available tiles: " + tile.size());
                    tileNum = 0; // Default to the first tile if out of bounds
                }
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
