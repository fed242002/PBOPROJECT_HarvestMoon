package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class UI {
 
    Graphics2D g2;
    GamePanel gp;
    Font arial_40;
    BufferedImage image;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;
    public String currentDialogue = "";
    public int commandNum = 0; // 0: new game, 1: load game, 2: exit
    public int titleScreenState = 0;

    public UI(GamePanel gp){
        this.gp = gp;
        // arial_40 = new Font("Arial", Font.PLAIN, 40);
    }

   

    public void showMessage(String text){
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);

        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        if(gp.gameState == gp.playState){
            //do play State
        }

        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }






        // g2.setFont(arial_40);
        // g2.setColor(Color.WHITE);
        // // g2.drawImage(image,gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        // // g2.drawString("Halo bang", 74, 50);



        // //message 
        // if(messageOn){
        //     g2.setFont(g2.getFont().deriveFont(30F));
        //     g2.drawString(message, gp.tileSize/2, gp.tileSize/2);
        //     messageCounter++;

        //     if(messageCounter > 120){
        //         messageCounter = 0;
        //         messageOn = false;
        //     }
        // }
    }

    public void drawTitleScreen(){

        Image mainMenuImage = null, newGameButton = null, loadGameButton = null, exitButton = null, newGameActive = null, loadGameActive = null, exitActive = null;

        if(titleScreenState == 0){
            try {
            mainMenuImage = ImageIO.read(getClass().getResourceAsStream("/assets/ui/mainMenu.png"));
            newGameButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/newGame.png"));
            loadGameButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/loadGame.png"));
            exitButton = ImageIO.read(getClass().getResourceAsStream("/assets/ui/exit.png"));
            newGameActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/newGameActive.png"));
            loadGameActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/loadGameActive.png"));
            exitActive = ImageIO.read(getClass().getResourceAsStream("/assets/ui/exitActive.png"));
            } catch (IOException e) {
                System.out.println("Error loading title screen images: " + e.getMessage());
            }

        if(mainMenuImage != null) {
            g2.drawImage(mainMenuImage, 0, 0, gp.screenWidth, gp.screenHeight, null);

            //new game button
            if(commandNum == 0){
                g2.drawImage(newGameActive, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 5, gp.tileSize * 8, gp.tileSize * 2, null);
            } else {
                g2.drawImage(newGameButton, gp.screenWidth / 2 - (gp.tileSize / 2)- 150, gp.tileSize * 5, gp.tileSize * 8, gp.tileSize * 2, null);
            }
            //load game button
            if(commandNum == 1){
                g2.drawImage(loadGameActive, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 7, gp.tileSize * 8, gp.tileSize * 2, null);
            } else {
                g2.drawImage(loadGameButton, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 7, gp.tileSize * 8, gp.tileSize * 2, null);
            }
            //exit button
            if(commandNum == 2){
                g2.drawImage(exitActive, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 9, gp.tileSize * 8, gp.tileSize * 2, null);
            } else {
                g2.drawImage(exitButton, gp.screenWidth / 2 - (gp.tileSize / 2) - 150, gp.tileSize * 9, gp.tileSize * 8, gp.tileSize * 2, null);
            }
        }
            

            // g2.setColor(new Color(70,120,80));
            // g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    
            // //title name 
            // g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            // String text = "Harvest Moon";
            // int x = getXforCenteredText(text);
            // int y = gp.tileSize * 3;
    
            // //shadow
            // g2.setColor(Color.BLACK);
            // g2.drawString(text, x+5, y+5);
    
            // // text
            // g2.setColor(Color.WHITE);
            // g2.drawString(text, x, y);
    
            // x = gp.screenWidth / 2 - (gp.tileSize / 2);
            // y += gp.tileSize * 2 - 100;
            // g2.drawImage(gp.player.animationList.get(1).down[0], x, y, gp.tileSize * 2, gp.tileSize * 4, null);
       
            // //menu
            // g2.setFont(g2.getFont().deriveFont(Font.BOLD, 48F));
    
            // text = "NEW GAME";
            // x = getXforCenteredText(text);
            // y += gp.tileSize * 5;
            // g2.drawString(text, x, y);
            // if(commandNum == 0){
            //     g2.drawString(">", x - gp.tileSize, y);
            // }
    
            // text = "LOAD GAME";
            // x = getXforCenteredText(text);
            // y += gp.tileSize ;
            // g2.drawString(text, x, y);
            // if(commandNum == 1){
            //     g2.drawString(">", x - gp.tileSize, y);
            // }
    
            // text = "EXIT";
            // x = getXforCenteredText(text);
            // y += gp.tileSize;
            // g2.drawString(text, x, y);
            // if(commandNum == 2){
            //     g2.drawString(">", x - gp.tileSize, y);
            // }  
        }
        if(titleScreenState == 1){
            g2.setColor(new Color(70,120,80));
            g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
    
            //title name 
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
            String text = "LOAD GAME";
            int x = getXforCenteredText(text);
            int y = gp.tileSize * 3;
    
            //shadow
            g2.setColor(Color.BLACK);
            g2.drawString(text, x+5, y+5);
    
            // text
            g2.setColor(Color.WHITE);
            g2.drawString(text, x, y);
        }
    }

    public void drawPauseScreen(){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD, 80F));
            String text = "PAUSED";
            int x = getXforCenteredText(text);
            int y = gp.screenHeight / 2;

            g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text){
            int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth / 2 - length / 2; 
            return x;
    }

    public void drawDialogueScreen(){
        // window
        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - (gp.tileSize * 4);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);


        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;
        
        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height){
        g2.setColor(new Color(0,0,0, 200));
        g2.fillRoundRect(x, y, width, height,35,35);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10,25,25);
    }
}
