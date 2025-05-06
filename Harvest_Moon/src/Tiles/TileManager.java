package Tiles;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public ArrayList<Tile> tile = new ArrayList<>(); // ArrayList untuk menyimpan tile
    public int mapTileNum[][];
    boolean drawPath = false;
    ArrayList<String> fileNames = new ArrayList<>(); // ArrayList untuk menyimpan nama file
    ArrayList<String> collisionStatus = new ArrayList<>(); // ArrayList untuk menyimpan status collision
     
    public TileManager(GamePanel gp) {
        this.gp = gp;
        //reading the data file
        // InputStream is = getClass().getResourceAsStream("/maps/House.txt"); // Load map
        // BufferedReader br = new BufferedReader(new InputStreamReader(is));
        
        // getting the tile names and collision status

        // String line;

        // try {
        //     w    hile ((line = br.readLine()) != null) {
                
        //         fileNames.add(line); // Add the file name to the list
        //         collisionStatus.add(br.readLine()); // Add the collision status to the list
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }
        
        tile = new ArrayList<>(fileNames.size()); // Initialize the tile ArrayList with the size of fileNames
        getTileImage();

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
       
        loadMap("/assets/map01.txt");
    }



    public void getTileImage(){

        try {
            tile.clear();
            // Load tile images and add them to the tile list
            Tile tile1 = new Tile("/assets/grass.png");  //cara panjang nya // default gaada collision nya
            tile.add(tile1);

            tile.add(new Tile("/assets/brick.jpg", true)); // Add a tile with a custom image and collision

            tile.add(new Tile("/assets/tile1204.png")); // Add a tile with a custom image and no collision

            // for (int i = 0; i < fileNames.size(); i++) {
            //     Tile tile1 = new Tile("");
            //     boolean collision;
            //      // Check if the collision status is "true"
                
            //      if (collisionStatus.get(i).equals("true")) {
            //         collision = true; 
            //     } else {
            //         collision = false;
            //     }
            //     setup(i, fileName, collision); // Call the setup method to create and add the tile
            // }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public void setup(int index, String imagePath, boolean collision) {
    //     try {
    //         // tile = new ArrayList<>(); // Initialize the tile ArrayList
    //         // tile.get(index) = new Tile(); // Create a new Tile object
    //         // Tile tile = new Tile(imagePath, collision); // Create a new Tile object
    //         // this.tile.add(tile); // Add the tile to the list
    //         // tile[index].image = tile.image; // Set the image
    //         // tile[index].collision = collision; // Set the collision status
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }
    public void loadMap(String mapPath) {
        try {
            InputStream is = getClass().getResourceAsStream(mapPath); // Load map
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
    
            int row = 0;
    
            while (row < gp.maxWorldRow) {
                String line = br.readLine();
                if (line == null) break; // Stop if the file ends unexpectedly
    
                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxWorldCol; col++) {
                    if (col < numbers.length) {
                        int num = Integer.parseInt(numbers[col]);
                        mapTileNum[col][row] = num;
                    } else {
                        mapTileNum[col][row] = 0; // Default to 0 if the map file is incomplete
                    }
                }
                row++;
            }
    
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2){    
        int worldCol = 0;
        int worldRow = 0;


        while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){
            
            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            if(worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
               worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && 
               worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
               worldY - gp.tileSize < gp.player.worldY + gp.player.screenY){
               
                g2.drawImage(tile.get(tileNum).image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            
            }


            worldCol++;            

            if(worldCol == gp.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
