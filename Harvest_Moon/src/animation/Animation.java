package animation;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Animation {
    public int spriteTotal;
    public BufferedImage up[];
    public BufferedImage down[];
    public BufferedImage left[];
    public BufferedImage right[];
    public String path;
    public String name;
    public int currentSprite=0;
    public int soundFX = -1;
    boolean oneD = false; // true if the animation is one direction only (kek sleep)

        public Animation(String name1,int spriteTotal, String path) {
        this.name = name1.toUpperCase();
        this.spriteTotal = spriteTotal;
        this.path = path;
        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];

        setSprite();
        }

        
        public Animation(String name1,int spriteTotal, String path, boolean oneD) {
        this.name = name1.toUpperCase();
        this.spriteTotal = spriteTotal;
        this.path = path;
        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];
        this.oneD = oneD;


            if(!oneD)
                setSprite();
            else
                setSpriteAllD();
                
            
        }

        public Animation(String name1,int spriteTotal, String path, boolean oneD, int soundFX) {
        this.name = name1.toUpperCase();
        this.spriteTotal = spriteTotal;
        this.path = path;
        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];
        this.oneD = oneD;
        this.soundFX = soundFX;

            if(!oneD)
                setSprite();
            else
                setSpriteAllD();
                
            
        }



        public Animation(String name,int spriteTotal, String path, int soundFX) {
        this.soundFX = soundFX;
        this.name = name;
        this.spriteTotal = spriteTotal;
        this.path = path;
        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];

        setSprite();
        }

    public void setSpriteAllD()
    {
        try{
            for (int i = 0; i < spriteTotal; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream(path + i + ".png"));
                down[i] = ImageIO.read(getClass().getResourceAsStream(path + i + ".png"));
                left[i] =ImageIO.read(getClass().getResourceAsStream(path + i + ".png"));
                right[i] = ImageIO.read(getClass().getResourceAsStream(path + i + ".png"));
            }

            // System.out.println("setSprite("+path+") di class Animation berhasil");

        }catch (Exception e) {
            System.out.println("error di setSprite("+path+") di class Animation"); 
            e.printStackTrace(); 
        }

    }
    public void setSprite() {
        
        try{
            for (int i = 0; i < spriteTotal; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream(path + "atas-" + i + ".png"));
                down[i] = ImageIO.read(getClass().getResourceAsStream(path + "bawah-" + i + ".png"));
                left[i] =ImageIO.read(getClass().getResourceAsStream(path + "kiri-" + i + ".png"));
                right[i] = ImageIO.read(getClass().getResourceAsStream(path + "kanan-" + i + ".png"));
            }

            // System.out.println("setSprite("+path+") di class Animation berhasil");

        }catch (Exception e) {
            System.out.println("error di setSprite("+path+") di class Animation"); 
            e.printStackTrace(); 
        }
    }



    public void setPath(String path) {
        this.path = path;
        setSprite();
    }



    public BufferedImage CurrentImage(String direction) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                image = up[currentSprite];
                break;
            case "down":
                image = down[currentSprite];
                break;
            case "left":
                image = left[currentSprite];
                break;
            case "right":
                image = right[currentSprite];
                break;
        }
        return image;
    }

    public void resetAnimation() {
        currentSprite = 0;
    }
}
