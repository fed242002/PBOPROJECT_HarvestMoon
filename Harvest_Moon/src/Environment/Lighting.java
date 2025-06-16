package Environment;

import Main.GamePanel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.image.BufferedImage;

public class Lighting {
    GamePanel gp;
    public BufferedImage imageLighting;
    public String path = "";
    BufferedImage darknessFilter;
    float filterAlpha = 0f;
    long lastUpdate = System.currentTimeMillis();

    final int day = 0;
    final int dusk = 1;
    final int night = 2;
    final int dawn = 3;
    public int dayState = day; // Default to day state

    public Lighting(GamePanel gp) {
        this.gp = gp;
        setLightSource();
    }

    public void setLightSource() {
        // bufferedimage
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        if (gp.player.currentLight == null) {
            // Use filterAlpha to control the darkness level
            g2.setColor(new Color(0, 0, 0, 0.65f));
        }

        else {
            // untuk nyari center x dan y player
            int centerX = gp.player.screenX + (gp.player.width / 2);
            int centerY = gp.player.screenY + (gp.player.width / 2);

            // crate a gradation effect
            Color color[] = new Color[12];
            float fraction[] = new float[12];

            color[0] = new Color(0, 0, 0, 0.1f); // almost black
            color[1] = new Color(0, 0, 0, 0.42f); // dark gray
            color[2] = new Color(0, 0, 0, 0.52f); // gray
            color[3] = new Color(0, 0, 0, 0.61f); // light gray
            color[4] = new Color(0, 0, 0, 0.69f); // very light gray
            color[5] = new Color(0, 0, 0, 0.76f); // almost black
            color[6] = new Color(0, 0, 0, 0.82f); // dark gray
            color[7] = new Color(0, 0, 0, 0.87f); // gray
            color[8] = new Color(0, 0, 0, 0.91f); // light gray
            color[9] = new Color(0, 0, 0, 0.94f); // very light gray
            color[10] = new Color(0, 0, 0, 0.96f); // almost black
            color[11] = new Color(0, 0, 0, 0.98f); // dark gray

            fraction[0] = 0f;
            fraction[1] = 0.4f;
            fraction[2] = 0.5f;
            fraction[3] = 0.6f;
            fraction[4] = 0.65f;
            fraction[5] = 0.7f;
            fraction[6] = 0.75f;
            fraction[7] = 0.8f;
            fraction[8] = 0.85f;
            fraction[9] = 0.9f;
            fraction[10] = 0.95f;
            fraction[11] = 1f;

            // create a radial gradient paint
            RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, gp.player.currentLight.lightRadius,
                    fraction, color);

            // set gradient paint to graphics
            g2.setPaint(gPaint);
        }

        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g2.dispose();
    }

    public void update() {
        if (gp.player.lightUpdated == true) {
            setLightSource();
            gp.player.lightUpdated = false; // Reset the flag after updating the light source
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdate >= 1000) { // setiap 1 detik
            lastUpdate = currentTime;
            // Fixed day state logic
            if(gp.hour >= 6 && gp.hour < 13)  // 6 AM to 1 PM
            {
                dayState = day;
            }
            else if(gp.hour >= 13 && gp.hour < 18) // 1 PM to 6 PM
            {
                dayState = dusk;
            }
            else if (gp.hour >= 18 || gp.hour < 2) // 6 PM to 2 AM (spans midnight)
            {
                dayState = night;
                filterAlpha = 1f; // Ensure filterAlpha is set to 1 for night transition
            }
            else if(gp.hour >= 2 && gp.hour < 6) // 2 AM to 6 AM
            {
                dayState = dawn;
            }
            
            if(gp.currentMap == 1 || gp.currentMap == 2) { 
                switch (dayState) {
                case day -> filterAlpha = 0f; // No filter during day
                case dusk -> {
                    filterAlpha += 0.003f; // Gradually darken
                    filterAlpha = Math.min(filterAlpha, 1f); // Clamp to max 1
                }
                case night -> filterAlpha = 1f; // Full darkness
                case dawn -> {
                    filterAlpha -= 0.004f; // Gradually lighten
                    filterAlpha = Math.max(filterAlpha, 0f); // Clamp to min 0
                }
                default -> {
                }
            }
            }
            
        }
    }

    public void draw(Graphics2D g2) {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, filterAlpha));
        g2.drawImage(darknessFilter, 0, 0, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // Reset alpha to 1 for other drawing
    }

}
