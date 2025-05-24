package Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;

public class EnergyBar {
    private int x, y, width, height, cornerRadius;
    private int maxValue, currentValue;
    private Color backgroundColor = new Color(120, 0, 0);
    private Color fillColor = new Color(0, 180, 220);
    private Color borderColor = Color.WHITE;
    
    public EnergyBar(int x, int y, int width, int height, int cornerRadius, int maxValue) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.cornerRadius = cornerRadius;
        this.maxValue = maxValue;
        this.currentValue = maxValue;
    }
    
    public void setValue(int value) {
        this.currentValue = Math.min(maxValue, Math.max(0, value));
    }
    
    public void draw(Graphics2D g2) {
        // Shadow
        g2.setColor(new Color(40, 40, 40, 150));
        g2.fillRoundRect(x + 3, y + 3, width, height, cornerRadius, cornerRadius);
        
        // Background
        g2.setColor(backgroundColor);
        g2.fillRoundRect(x, y, width, height, cornerRadius, cornerRadius);
        
        // Calculate fill width
        int fillWidth = (int)((width * currentValue) / (double)maxValue);
        
        // Draw fill with 3D effect
        if (fillWidth > 0) {
            // Base fill
            g2.setColor(fillColor);
            if (fillWidth >= width - 5) {
                g2.fillRoundRect(x, y, fillWidth, height, cornerRadius, cornerRadius);
            } else {
                g2.fillRoundRect(x, y, fillWidth, height, cornerRadius, cornerRadius);
                g2.fillRect(x + fillWidth - 5, y, 5, height);
            }
            
            // Top highlight
            g2.setColor(new Color(50, 220, 255));
            g2.fillRect(x, y, fillWidth, height/4);
            
            // Bottom shadow
            g2.setColor(new Color(0, 120, 160));
            g2.fillRect(x, y + 3*height/4, fillWidth, height/4);
            
            // Center highlight
            int highlightWidth = Math.min(fillWidth - 10, width - 20);
            if (highlightWidth > 20) {
                float centerX = x + fillWidth/2;
                float[] dist = {0.0f, 0.5f, 1.0f};
                Color[] colors = {
                    fillColor,
                    new Color(130, 255, 255, 180),
                    fillColor
                };
                
                RadialGradientPaint paint = new RadialGradientPaint(
                    centerX, y + height/2, fillWidth/3, dist, colors);
                g2.setPaint(paint);
                g2.fillRect(x + 5, y + height/3, highlightWidth, height/3);
            }
        }
        
        // Inner bevel
        g2.setColor(new Color(255, 255, 255, 90));
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(x + 1, y + 1, width - 2, height - 2, cornerRadius - 2, cornerRadius - 2);
        
        // Border
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(x, y, width, height, cornerRadius, cornerRadius);
        
        // Text with shadow
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 14F));
        String text = currentValue + "/" + maxValue;
        int textX = x + (width - g2.getFontMetrics().stringWidth(text)) / 2;
        int textY = y + height/2 + 5;
        
        g2.setColor(new Color(0, 0, 0, 120));
        g2.drawString(text, textX + 1, textY + 1);
        
        g2.setColor(Color.WHITE);
        g2.drawString(text, textX, textY);
    }
}

