package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;

import java.awt.Color;


public class GamePanel extends JPanel implements Runnable, MouseMotionListener {
    
    final int originalTileSize = 48; // 16x16 tile
    final int scale = 1; // Scale the tile size by 3x
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile size (ini size yang bakal muncul di screen)
    public final int maxScreenCol = 16; // 16 tiles in a row
    public final int maxScreenRow = 12; // 12 tiles in a column
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    public int playerSizeX=tileSize; 
    public int playerSizeY=tileSize*2; // tinggi player

    //world settings
    public final int maxWorldCol = 50; // 50 tiles in a row
    public final int maxWorldRow = 50; // 50 tiles in a column
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow; 


    //FPS
    int FPS = 60; // Frames per second

    TileManager tileM = new TileManager(this); // Create a new TileManager object
    KeyHandler keyH = new KeyHandler(this); // Create a new KeyHandler object
    Thread gameThread; // Thread for the game loop
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH); // Create a new Player object

    private int mouseX = -1;
    private int mouseY = -1;

    public boolean showDebugHitboxes = false; // Toggle for showing hitboxes

    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Double buffering to reduce flickering
        this.addKeyListener(keyH); 
        this.setFocusable(true); // Make the panel focusable to receive key events;
        this.addMouseMotionListener(this);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // Create a new thread for the game loop
        gameThread.start(); // Start the thread
    }

    @Override
    public void run() {
      
        double drawInterval = 1000000000/FPS; // Calculate the draw interval in nanoseconds
        double delta = 0; // Time difference between frames
        long lastTime = System.nanoTime(); // Get the current time in nanoseconds
        long currentTime; // Current time in nanoseconds  
        long timer = 0;
        int drawCount = 0; // Count the number of frames drawn

        while(gameThread != null){

            currentTime = System.nanoTime(); // Get the current time in nanoseconds
            delta += (currentTime - lastTime) / drawInterval; // Calculate the time difference
            timer += (currentTime - lastTime); // Update the timer
            lastTime = currentTime; // Update the last time

            if(delta >= 1){ // If the time difference is greater than or equal to 1
                // Update the game state and repaint the screen
                update(); // Update the game state
                repaint(); // Repaint the screen
                delta--; // Decrease delta by 1
                drawCount++; // Increment the draw count
            }

            if(timer >= 1000000000){ // If 1 second has passed
                System.out.println("FPS: " + drawCount); // Print the FPS
                drawCount = 0; // Reset the draw count
                timer = 0; // Reset the timer
            }

        }

    }

    public void update(){

        player.update();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not used, but required by interface
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g); 

        Graphics2D g2 = (Graphics2D) g; 

        tileM.draw(g2); // Draw the tiles
        player.draw(g2); // Draw the player
        
        // Draw mouse coordinates
        if(mouseX >= 0 && mouseY >= 0) {
            g2.setColor(Color.WHITE);
            g2.drawString("Mouse X: " + mouseX + " Y: " + mouseY, 10, 20);
        }

        // Add hitbox drawing at the end (on top of everything else)
        if(showDebugHitboxes) {
            cChecker.drawHitboxes(g2);
        }

        g2.dispose(); // Dispose of the graphics object to free up resources
    }


}
