package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UI {
 
    Graphics2D g2;
    GamePanel gp;
    Font arial_40;
    BufferedImage image;
    public boolean messageOn = false;
    public String message = "";
    public int messageCounter = 0;

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
    }

    public void drawSubWindow(int x, int y, int width, int height){
        g2.setColor(new Color(0,0,0, 200));
        g2.fillRoundRect(x, y, width, height,35,35);

        g2.setColor(Color.WHITE);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10,25,25);
    }
}
