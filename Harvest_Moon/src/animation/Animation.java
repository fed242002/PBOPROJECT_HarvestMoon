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


        public Animation(String name,int spriteTotal, String path) {
        this.name = name;
        this.spriteTotal = spriteTotal;
        this.path = path;
        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];

        setSprite();
        }

    

    public void setSprite() {
        
        try{
            for (int i = 0; i < spriteTotal; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream(path + "atas-" + i + ".png"));
                down[i] = ImageIO.read(getClass().getResourceAsStream(path + "bawah-" + i + ".png"));
                left[i] =ImageIO.read(getClass().getResourceAsStream(path + "kiri-" + i + ".png"));
                right[i] = ImageIO.read(getClass().getResourceAsStream(path + "kanan-" + i + ".png"));
            }

        }catch (Exception e) {
            System.out.println("error di setSprite("+path+") di class Animation"); 
            e.printStackTrace(); 
        }
    }



    public void setPath(String path) {
        this.path = path;
        setSprite();
    }


}
