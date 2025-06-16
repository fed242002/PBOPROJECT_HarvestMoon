package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


import animation.*;
import Data.*;
import entity.*;
import Environment.*;
import object.*;
import Main.*;
import tile.*;

public class GamePanel extends JPanel implements Runnable, MouseMotionListener {

    final int originalTileSize = 48; // 48x48 tile
    final int scale = 1; // Scale the tile size by 1x

    public final int tileSize = originalTileSize * scale; // 48x48 tile size (ini size yang bakal muncul di screen)
    public final int maxScreenCol = 16; // 16 tiles in a row
    public final int maxScreenRow = 12; // 12 tiles in a column
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public int currentMap = 0; // Current map index -> 0: Home
    public int playerSizeX = tileSize;
    public int playerSizeY = tileSize * 2; // tinggi player

    // world settings
    public final int maxWorldCol = 50; // 50 tiles in a row
    public final int maxWorldRow = 50; // 50 tiles in a column
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    public boolean justChangedMap = false; // Flag to indicate if the map has just changed

    // FPS
    int FPS = 60; // Frames per second

    //time
    int hour;
    int minute;
    int timeCounter = 0;

    // system
    public TileManager tileM = new TileManager(this); // Create a new TileManager object
    public KeyHandler keyH = new KeyHandler(this); // Create a new KeyHandler object
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this); // Create a new AssetSetter object
    public SFX sfx = new SFX();
    public MasterMusic masterMusic = new MasterMusic();
    public EventHandler eventHandler = new EventHandler(this); // Create a new EventHandler object
    Thread gameThread; // Thread for the game loop
    public boolean fullScreen = false; // Fullscreen mode toggle

    // entity and object

    public Player player = new Player(this, keyH); // Create a new Player object
    public ArrayList<Entity> obj = new ArrayList<>(MapDB.mapList.get(currentMap).obj); // List of objects in the game
    public ArrayList<Entity> farmObj = new ArrayList<>(MapDB.mapList.get(currentMap).farmObj); // List of objects in the game
    public ArrayList<Entity> npcs = new ArrayList<>(MapDB.mapList.get(currentMap).npcs); // List of NPCs in the game
    public ArrayList<Entity> entityList = new ArrayList<>(MapDB.mapList.get(currentMap).entityList); // List of all entities in the game
    saveLoad saveLoad1 = new saveLoad(this);

    Crop crop = new Crop(this); // Create a new Crop object
    


    public void changeMap(int mapNum){

        //balikin current obj ke mabDB
        MapDB.mapList.get(currentMap).obj = new ArrayList<>(obj);
        MapDB.mapList.get(currentMap).farmObj = new ArrayList<>(farmObj);
        MapDB.mapList.get(currentMap).npcs = new ArrayList<>(npcs);
        MapDB.mapList.get(currentMap).entityList = new ArrayList<>(entityList);
        // Clear the current lists
        obj.clear();
        farmObj.clear();
        npcs.clear();
        entityList.clear();
        

        player.setAnimation("idle");
        stopMusic(masterMusic);
        if(mapNum < 0 || mapNum >= MapDB.mapList.size()) {
            System.out.println("Invalid map number: " + mapNum);
            return; // Invalid map number
        }
        currentMap = mapNum;

        MapData current = MapDB.mapList.get(currentMap);
        


        tileM.loadTileData(current.tileDataPath); // Load tile data from the specified file
        tileM.getTileImage(current.tilePath);
        tileM.loadMap(current.path); // Load map data from the specified file


        System.out.println("Map " + mapNum + " loaded with " + tileM.tile.size() + " tiles");

        MapDB.mapList.get(currentMap).needsRefresh = true; // Set the needsRefresh flag to true
        justChangedMap = true; // Set the flag to indicate that the map has just changed
        current.needsRefresh = true; // Set the needsRefresh flag to true

        playMusic(masterMusic, current.music);
    }

    // Game state
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1; // Game is being played
    public final int pauseState = 2; // Game is paused
    public final int dialogueState = 3; // Dialog is being shown
    public final int eventFoundState = 4; // Event found is being shown
    public final int inventoryState = 5; // Inventory is being shown

    // UI
    public UI ui = new UI(this); // Create a new UI object


    // lighting
    EnvironmentManager eManager = new EnvironmentManager(this); // Create a new EnvironmentManager object

    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseWorldX = -1; // Add this for world X coordinate
    private int mouseWorldY = -1; // Add this for world Y coordinate

    public boolean showDebugHitboxes = false; // Toggle for showing hitboxes
    public boolean showGrid = false; // Toggle for showing the grid

    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Double buffering to reduce flickering
        this.addKeyListener(keyH);
        this.setFocusable(true); // Make the panel focusable to receive key events;
        this.addMouseMotionListener(this);


    }

    public void setupGame() {
        eventHandler = new EventHandler(this);
        aSetter.setObject();
        aSetter.setNPC();
        eManager.setup(); // ini untuk setting dalam kegelapan
        playMusic(masterMusic, MapDB.mapList.get(currentMap).music);

        // gameState = titleState;
        gameState = playState; // Set the game state to play
        // changeMap(1);

    }

    public void startGameThread() {
        gameThread = new Thread(this); // Create a new thread for the game loop
        gameThread.start(); // Start the thread
    }

    @Override
    public void run() {


        ;
        double drawInterval = 1000000000 / FPS; // Calculate the draw interval in nanoseconds
        double delta = 0; // Time difference between frames
        long lastTime = System.nanoTime(); // Get the current time in nanoseconds
        long currentTime; // Current time in nanoseconds
        long timer = 0;
        int drawCount = 0; // Count the number of frames drawn

        while (gameThread != null) {

            currentTime = System.nanoTime(); // Get the current time in nanoseconds
            delta += (currentTime - lastTime) / drawInterval; // Calculate the time difference
            timer += (currentTime - lastTime); // Update the timer
            lastTime = currentTime; // Update the last time

            if (delta >= 1) { // If the time difference is greater than or equal to 1
                // Update the game state and repaint the screen
                update(); // Update the game state
                repaint(); // Repaint the screen
                delta--; // Decrease delta by 1
                drawCount++; // Increment the draw count
            }

            if (timer >= 1000000000) { // If 1 second has passed
                // System.out.println("FPS: " + drawCount); // Print the FPS
                drawCount = 0; // Reset the draw count
                timer = 0; // Reset the timer
            }

        }

    }

    public void update() {
    
        MapData current = MapDB.mapList.get(currentMap);

        if(minute > 59)
        {
            hour++;
        }

        timeCounter++;
        if(timeCounter > 59)
        {
            timeCounter = 0;
            minute++;
        }
        System.out.println("Time Counter : " + timeCounter );
        System.out.println("Min Counter : " + minute );
        System.out.println("Hour Counter : " + hour );


        if(justChangedMap|| current.needsRefresh) { // If the map has just changed

            obj = current.obj; // Update the object list
            farmObj = current.farmObj;
            npcs = current.npcs;
            entityList = current.entityList;

            // Reset flags
            current.needsRefresh = false;
            justChangedMap = false;

        }

        eventHandler.update();

        if (gameState == playState) { // If the game is being played
            player.update(); // Update the player

            for (Entity npc : npcs) { // Update all NPCs
                if (npc != null) {
                    npc.update();
                }
            }
            eManager.update();
        }

        if (gameState == pauseState) { // If the game is paused
            // Handle pause state updates here if needed
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        // Calculate world coordinates based on player position and screen coordinates
        mouseWorldX = mouseX - player.screenX + player.worldX;
        mouseWorldY = mouseY - player.screenY + player.worldY;

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used, but required by interface
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            // tile
            tileM.draw(g2);
            // draw farm dkk
            for (Entity x : farmObj) {
                if (x != null) {
                    x.draw(g2); // Draw each farm object
                }
            }

            // tambah entitiy to list
            //special buat animasi turu kalo misal turu digambar diatas bed
            if(player.currentAnimationIndex != 13) { // Only add player to the list if not in animation
                entityList.add(player); // Add player to the entity list    
            }

            for (int i = 0; i < npcs.size(); i++) {
                if (npcs.get(i) != null) {
                    entityList.add(npcs.get(i)); // Add NPCs to the entity list
                }
            }

            for (int i = 0; i < obj.size(); i++) {
                if (obj.get(i) != null) {
                    entityList.add(obj.get(i)); // Add objects to the entity list
                }
            }

            // sort
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    // Compare based on the bottom Y position (worldY + solidArea.y + height)
                    int e1Bottom = e1.worldY + e1.solidArea.y + e1.solidArea.height;
                    int e2Bottom = e2.worldY + e2.solidArea.y + e2.solidArea.height;
                    return Integer.compare(e1Bottom, e2Bottom);
                }
            });
            
            // draw entities
            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).draw(g2); // Draw each entity in the list
            }
            entityList.clear(); // Clear the entity list after drawing
            
            //draw player diatas smua entity k
            if(player.currentAnimationIndex == 13) { // Only add player to the list if not in animation
                player.draw(g2); // Draw player on top of all entities
            }
            

            // enviroment
            eManager.draw(g2); // cara buat setting kegelapan nya disini

            ui.draw(g2); // Draw the UI

            // Add hitbox drawing at the end (on top of everything else)
            if (showDebugHitboxes) {
                // event hitbox
                eventHandler.draw(g2);

                // Draw mouse coordinates
                g2.fillRect(0, 0, 150, 75);
                if (mouseX >= 0 && mouseY >= 0) {
                    g2.setColor(Color.WHITE);
                    g2.drawString("Screen X: " + mouseX + " Y: " + mouseY, 10, 20);
                    g2.drawString("World X: " + mouseWorldX + " Y: " + mouseWorldY, 10, 40);
                    // Optional: Add tile coordinates
                    g2.drawString("Tile Col: " + (mouseWorldX / tileSize) + " Row: " + (mouseWorldY / tileSize), 10,
                            60);
                }
                cChecker.drawHitboxes(g2);
            }

            // Draw the grid if enabled
            if (showGrid) {
                drawGrid(g2);
            }

            eventHandler.draw(g2);
            
            g2.dispose(); // Dispose of the graphics object to free up resources

        }
    }

    public BufferedImage setImage(String imagePath) {
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream(imagePath));
            this.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
            this.repaint();
            return image;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void drawGrid(Graphics2D g2) {
        g2.setColor(new Color(255, 255, 255, 100)); // Semi-transparent white
        g2.setStroke(new BasicStroke(1)); // Thin lines

        // Calculate the starting and ending positions for the grid
        int startWorldCol = player.worldX / tileSize - 9;
        int endWorldCol = player.worldX / tileSize + 10;
        int startWorldRow = player.worldY / tileSize - 7;
        int endWorldRow = player.worldY / tileSize + 8;

        // Make sure we don't go out of bounds
        if (startWorldCol < 0)
            startWorldCol = 0;
        if (endWorldCol > maxWorldCol)
            endWorldCol = maxWorldCol;
        if (startWorldRow < 0)
            startWorldRow = 0;
        if (endWorldRow > maxWorldRow)
            endWorldRow = maxWorldRow;

        // Draw vertical lines (columns)
        for (int col = startWorldCol; col <= endWorldCol; col++) {
            int screenX = col * tileSize - player.worldX + player.screenX;
            g2.drawLine(screenX, 0, screenX, screenHeight);

            // Add column numbers
            if (col % 5 == 0) { // Only show numbers every 5 columns for clarity
                g2.setColor(new Color(255, 255, 0, 200)); // Yellow for numbers
                g2.drawString(String.valueOf(col), screenX + 2, 20);
                g2.setColor(new Color(255, 255, 255, 100)); // Back to white for lines
            }
        }

        // Draw horizontal lines (rows)
        for (int row = startWorldRow; row <= endWorldRow; row++) {
            int screenY = row * tileSize - player.worldY + player.screenY;
            g2.drawLine(0, screenY, screenWidth, screenY);

            // Add row numbers
            if (row % 5 == 0) { // Only show numbers every 5 rows for clarity
                g2.setColor(new Color(255, 255, 0, 200)); // Yellow for numbers
                g2.drawString(String.valueOf(row), 2, screenY + 15);
                g2.setColor(new Color(255, 255, 255, 100)); // Back to white for lines
            }
        }

        // Add current player tile position in a more visible box
        int playerCol = player.worldX / tileSize;
        int playerRow = player.worldY / tileSize;
        g2.setColor(new Color(0, 0, 0, 180));
        g2.fillRect(screenWidth - 150, 0, 150, 40);
        g2.setColor(Color.WHITE);
        g2.drawString("Player Tile: Col " + playerCol + " Row " + playerRow, screenWidth - 145, 25);
    }

    public void playMusic(Sound sound, int i) {
        sound.setFile(i);
        sound.play();
        sound.loop();
    }

    public void stopMusic(Sound sound) {
        if (sound.clip != null) // Check if the clip is not null
            sound.stop();
    }

    public void playSFX(Sound sound, int i) {
        sound.setFile(i);
        sound.play();
    }

}
