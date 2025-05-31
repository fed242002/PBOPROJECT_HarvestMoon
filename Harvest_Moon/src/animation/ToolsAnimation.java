package animation;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.GamePanel;

public class ToolsAnimation {
    GamePanel gp;
    public int spriteTotal;
    public BufferedImage up[];
    public BufferedImage down[];
    public BufferedImage left[];
    public BufferedImage right[];
    public String AnimationName;
    public String tools;
    public int width = 144; // Default width of the sprite
    public int height = 144; // Default height of the sprite
    public int x; // Default x position of the sprite
    public int y; // Default y position of the sprite

    public ToolsAnimation(GamePanel gp, String tools, String Animationname, int spriteTotal) {
        this.gp = gp;
        this.AnimationName = Animationname.toUpperCase();
        this.tools = tools.toUpperCase();
        this.spriteTotal = spriteTotal;
        x = gp.screenWidth / 2 - (gp.tileSize / 2) - gp.tileSize;
        y = gp.screenHeight / 2 - (gp.tileSize / 2);
        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];
        setSprite();
    }

     public ToolsAnimation(GamePanel gp,String tools, String Animationname, int spriteTotal, int width, int height, int x, int y) {
        this.gp = gp;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.AnimationName = Animationname.toUpperCase();
        this.tools = tools.toUpperCase();
        this.spriteTotal = spriteTotal;


        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];
        setSprite();
    }

    void setSprite(){
        for (int i = 0; i < spriteTotal; i++) {
            try {
                up[i] = ImageIO.read(getClass().getResourceAsStream("/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/up/" + i + ".png"));
                down[i] = ImageIO.read(getClass().getResourceAsStream("/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/down/" + i + ".png"));
                left[i] = ImageIO.read(getClass().getResourceAsStream("/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/left/" + i + ".png"));
                right[i] = ImageIO.read(getClass().getResourceAsStream("/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/right/" + i + ".png"));
            
                System.out.println("Loaded sprite: " + tools +"/" + AnimationName + " - Frame: " + i);
            } catch (IOException e) {
                System.out.println("Error loading sprite: " + tools +"/" + AnimationName );
                e.printStackTrace();
            }
        }
    }

}
