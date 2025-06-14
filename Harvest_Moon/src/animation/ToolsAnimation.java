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
        System.out.println("trying to load sprite: " + tools +"/" + AnimationName);
        setSprite();

    }

     public ToolsAnimation(GamePanel gp,String tools, String Animationname, int spriteTotal, boolean special) {
        this.gp = gp;
        this.width = 240;
        this.height = 240;
        x = (gp.screenWidth / 2 - (gp.tileSize / 2) - gp.tileSize) - 49;
        y = (gp.screenHeight / 2 - (gp.tileSize / 2)) -54;
        this.AnimationName = Animationname.toUpperCase();
        this.tools = tools.toUpperCase();
        this.spriteTotal = spriteTotal;


        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];
        System.out.println("trying to load sprite: " + tools +"/" + AnimationName);

        setSprite();
        System.out.println("loaded successfully: " + tools +"/" + AnimationName);
    }

    void setSprite(){
        for (int i = 0; i < spriteTotal; i++) {
            try {
                up[i] = ImageIO.read(getClass().getResourceAsStream("/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/up/" + i + ".png"));
                down[i] = ImageIO.read(getClass().getResourceAsStream("/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/down/" + i + ".png"));
                left[i] = ImageIO.read(getClass().getResourceAsStream("/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/left/" + i + ".png"));
                right[i] = ImageIO.read(getClass().getResourceAsStream("/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/right/" + i + ".png"));
            
                System.out.println("Loaded sprite: " + "/assets/player/TOOLS/"+ tools +"/" + AnimationName+ "/up/" + i + ".png");
            } catch (IOException e) {
                System.out.println("Error loading sprite: " + tools +"/" + AnimationName );
                e.printStackTrace();
            }
        }
    }

}
