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
        
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow]; // Initialize the mapTileNum array with the maximum screen columns and rows

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

            while(col < gp.maxScreenCol && row < gp.maxScreenRow) { // Loop through the columns and rows of the map
                String line = br.readLine(); // Read a line from the file
                while(col < gp.maxScreenCol) { // Loop through the columns of the map
                    String numbers[] = line.split(" "); // Split the line into numbers
                    int num = Integer.parseInt(numbers[col]); // Parse the number from the string
                    mapTileNum[col][row] = num; // Assign the number to the mapTileNum array
                    col++; // Move to the next column
                }
                if(col == gp.maxScreenCol) { // If the end of the row is reached
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
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col<gp.maxScreenCol && row<gp.maxScreenRow){
          
            int tileNum = mapTileNum[col][row]; // Get the tile number from the mapTileNum array

            g2.drawImage(tile.get(tileNum).image, x, y, gp.tileSize, gp.tileSize,null);
            col++;
            x += gp.tileSize; // Move to the right by one tile size
            
            if(col == gp.maxScreenCol){ // If the end of the row is reached
                col = 0; // Reset the column to 0
                x = 0; // Reset the x position to 0
                row++; // Move to the next row
                y += gp.tileSize; // Move down by one tile size
            }
        }

    }
}
