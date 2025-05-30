package Environment;

import Main.GamePanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Rectangle;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.Shape;

public class Lighting {
    GamePanel gp;
    BufferedImage darknessFilter;

    public Lighting(GamePanel gp, int circleSize) {
        // bufferedimage
        darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = (Graphics2D) darknessFilter.getGraphics();

        // untuk screen sized rectangle
        Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));

        // untuk nyari center x dan y player
        int centerX = gp.player.screenX + (gp.player.width / 2);
        int centerY = gp.player.screenY + (gp.player.width / 2);

        // get the top left x and y of the circle
        double x = centerX - (circleSize / 2);
        double y = centerY - (circleSize / 2);

        // create a circle area
        Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);

        // create light circle
        Area lightCircle = new Area(circleShape);

        // subtract the light circle from the screen area
        screenArea.subtract(lightCircle);

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
        RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize / 2), fraction, color);

        // set gradient paint to graphics
        g2.setPaint(gPaint);

        // draw the light circle
        g2.fill(lightCircle);

        // draw the screen rectangle without light circle
        g2.fill(screenArea);
        g2.dispose();
    }

    public void draw(Graphics2D g2) {
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
