package Main;
import javax.swing.JPanel;

import Tiles.TileManager;
import Entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
 
    public final int originalTileSize = 16; //16x16 tiles
    public final int scale = 5; //-> ini skala nya

    public final int tileSize = originalTileSize*scale; //jadi 48x48 tile
    public final int maxScreenCol = 16; // ini nentuin kolom di screen
    public final int maxScreenRow = 12;

    //nentuin ukuran gamescreen
    public final int screenWidth = tileSize * maxScreenCol; //768 pixel
    public final int screenHeight = tileSize * maxScreenRow; //576 pixel
    
    //WORLD SETTINGS    
    public final int maxWorldCol = 50; //ini nentuin kolom di world
    public final int maxWorldRow = 50; 
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;


    //set FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CollisionChecker cChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyH);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        //ini loopingan game nya
        while(gameThread != null)
        {
            //set FPS nya
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;

            if(delta >= 1){

                //update data ->movement
                update();
                //draw -> screen
                repaint();

                delta--;

            }


        }

    }

    public void update(){
        //movement
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        
        tileM.draw(g2); //draw tile

        //draw player
        player.draw(g2);

        g2.dispose();
    }

}
