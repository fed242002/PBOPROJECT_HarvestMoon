package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class UI {
 
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
        // g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        // g2.drawImage(image,gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        // g2.drawString("Halo bang", 74, 50);


        //message 
        if(messageOn){
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize/2, gp.tileSize/2);
            messageCounter++;

            if(messageCounter > 120){
                messageCounter = 0;
                messageOn = false;
            }
        }
    }
}
