package Main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;

import java.awt.Color;


public class GamePanel extends JPanel implements Runnable{
    
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3; // Scale the tile size by 3x
    
    public final int tileSize = originalTileSize * scale; // 48x48 tile size (ini size yang bakal muncul di screen)
    final int maxScreenCol = 16; // 16 tiles in a row
    final int maxScreenRow = 12; // 12 tiles in a column
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    //FPS
    int FPS = 60; // Frames per second

    KeyHandler keyH = new KeyHandler(); // Create a new KeyHandler object
    Thread gameThread; // Thread for the game loop
    Player player = new Player(this, keyH); // Create a new Player object

    //player default position
    int playerX = 100; // Player's x position
    int playerY = 100; // Player's y position
    int playerSpeed = 4; // Player's speed


    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); // Double buffering to reduce flickering
        this.addKeyListener(keyH); 
        this.setFocusable(true); // Make the panel focusable to receive key events;


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

    public void paintComponent(Graphics g){
        super.paintComponent(g); 

        Graphics2D g2 = (Graphics2D) g; 

        player.draw(g2); // Draw the player

        g2.dispose(); // Dispose of the graphics object to free up resources


    }


}
