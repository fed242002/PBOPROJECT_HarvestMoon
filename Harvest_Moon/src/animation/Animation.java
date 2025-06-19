package animation;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animation {
    public int spriteTotal;
    public BufferedImage up[];
    public BufferedImage down[];
    public BufferedImage left[];
    public BufferedImage right[];

    //body
    public BufferedImage body_up[];
    public BufferedImage body_down[];
    public BufferedImage body_left[];
    public BufferedImage body_right[];

    //eye
    public BufferedImage eye_up[];
    public BufferedImage eye_down[];
    public BufferedImage eye_left[];
    public BufferedImage eye_right[];

    //hair
    public BufferedImage hair_up[];
    public BufferedImage hair_down[];
    public BufferedImage hair_left[];
    public BufferedImage hair_right[];

    //outfit
    public BufferedImage outfit_up[];
    public BufferedImage outfit_down[];
    public BufferedImage outfit_left[];
    public BufferedImage outfit_right[];

    public String path;
    public String name;
    public String body;
    public String eye;
    public String hair;
    public String outfit;

    public int currentSprite=0;
    public int soundFX = -1;
    public boolean oneD = false; // true if the animation is one direction only (kek sleep)

    public Animation(String name1,int spriteTotal, String path, String body, String eye, String hair, String outfit) {
        this.name = name1.toUpperCase();
        this.spriteTotal = spriteTotal;
        this.path = path;
        this.body = body;
        this.eye = eye;
        this.hair = hair;
        this.outfit = outfit;

        //body
        body_up = new BufferedImage[spriteTotal];
        body_down = new BufferedImage[spriteTotal];
        body_left = new BufferedImage[spriteTotal];
        body_right = new BufferedImage[spriteTotal];
        //eye
        eye_up = new BufferedImage[spriteTotal];
        eye_down = new BufferedImage[spriteTotal];
        eye_left = new BufferedImage[spriteTotal];
        eye_right = new BufferedImage[spriteTotal];
        //hair
        hair_up = new BufferedImage[spriteTotal];
        hair_down = new BufferedImage[spriteTotal];
        hair_left = new BufferedImage[spriteTotal];
        hair_right = new BufferedImage[spriteTotal];
        //outfit
        outfit_up = new BufferedImage[spriteTotal];
        outfit_down = new BufferedImage[spriteTotal];
        outfit_left = new BufferedImage[spriteTotal];
        outfit_right = new BufferedImage[spriteTotal];

        setSpritePlayer();
        }

        public void setSpritePlayer(){
        try{
            for (int i = 0; i < spriteTotal; i++) {
                if(!name.equalsIgnoreCase("sit")&&!name.equalsIgnoreCase("sit2")){

                    body_up[i] = ImageIO.read(getClass().getResourceAsStream(path + "body/"+body+"/"+ name+"/up/" + i + ".png"));
                    body_down[i] = ImageIO.read(getClass().getResourceAsStream(path + "body/"+body+"/"+ name+"/down/" + i + ".png"));
                    body_left[i] =ImageIO.read(getClass().getResourceAsStream(path + "body/"+body+"/"+ name+"/left/" + i + ".png"));
                    body_right[i] = ImageIO.read(getClass().getResourceAsStream(path + "body/"+body+"/"+ name+"/right/" + i + ".png"));
    
                    eye_up[i] = ImageIO.read(getClass().getResourceAsStream(path + "eye/"+eye+"/"+ name+"/up/" + i + ".png"));
                    eye_down[i] = ImageIO.read(getClass().getResourceAsStream(path + "eye/"+eye+"/"+ name+"/down/" + i + ".png"));
                    eye_left[i] =ImageIO.read(getClass().getResourceAsStream(path + "eye/"+eye+"/"+ name+"/left/" + i + ".png"));
                    eye_right[i] = ImageIO.read(getClass().getResourceAsStream(path + "eye/"+eye+"/"+ name+"/right/" + i + ".png"));
    
                    hair_up[i] = ImageIO.read(getClass().getResourceAsStream(path + "hair/"+hair+"/"+ name+"/up/" + i + ".png"));
                    hair_down[i] = ImageIO.read(getClass().getResourceAsStream(path + "hair/"+hair+"/"+ name+"/down/" + i + ".png"));
                    hair_left[i] =ImageIO.read(getClass().getResourceAsStream(path + "hair/"+hair+"/"+ name+"/left/" + i + ".png"));
                    hair_right[i] = ImageIO.read(getClass().getResourceAsStream(path + "hair/"+hair+"/"+ name+"/right/" + i + ".png"));

                    outfit_up[i] = ImageIO.read(getClass().getResourceAsStream(path + "outfit/"+outfit+"/"+ name+"/up/" + i + ".png"));
                    outfit_down[i] = ImageIO.read(getClass().getResourceAsStream(path + "outfit/"+outfit+"/"+ name+"/down/" + i + ".png"));
                    outfit_left[i] =ImageIO.read(getClass().getResourceAsStream(path + "outfit/"+outfit+"/"+ name+"/left/" + i + ".png"));
                    outfit_right[i] = ImageIO.read(getClass().getResourceAsStream(path + "outfit/"+outfit+"/"+ name+"/right/" + i + ".png"));
                }else{
                    body_left[i] =ImageIO.read(getClass().getResourceAsStream(path + "body/"+body+"/"+ name+"/left/" + i + ".png"));
                    body_right[i] = ImageIO.read(getClass().getResourceAsStream(path + "body/"+body+"/"+ name+"/right/" + i + ".png"));
    
                    eye_left[i] =ImageIO.read(getClass().getResourceAsStream(path + "eye/"+eye+"/"+ name+"/left/" + i + ".png"));
                    eye_right[i] = ImageIO.read(getClass().getResourceAsStream(path + "eye/"+eye+"/"+ name+"/right/" + i + ".png"));
    
                    hair_left[i] =ImageIO.read(getClass().getResourceAsStream(path + "hair/"+hair+"/"+ name+"/left/" + i + ".png"));
                    hair_right[i] = ImageIO.read(getClass().getResourceAsStream(path + "hair/"+hair+"/"+ name+"/right/" + i + ".png"));

                    outfit_left[i] =ImageIO.read(getClass().getResourceAsStream(path + "outfit/"+outfit+"/"+ name+"/left/" + i + ".png"));
                    outfit_right[i] = ImageIO.read(getClass().getResourceAsStream(path + "outfit/"+outfit+"/"+ name+"/right/" + i + ".png"));

                }

            }
            System.out.println("setSprite("+path+name+") di class Animation berhasil");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        



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

        public Animation(String name1,int spriteTotal, String path, boolean oneD,boolean isAnimal) {
        this.name = name1.toUpperCase();
        this.spriteTotal = spriteTotal;
        this.path = path;
        up = new BufferedImage[spriteTotal];
        down = new BufferedImage[spriteTotal];
        left = new BufferedImage[spriteTotal];
        right = new BufferedImage[spriteTotal];

        setSprit1();
           
            
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

    public void setSprit1() {
        
        try{
            for (int i = 0; i < spriteTotal; i++) {
                up[i] = ImageIO.read(getClass().getResourceAsStream(path + "up/" + i + ".png"));
                down[i] = ImageIO.read(getClass().getResourceAsStream(path + "down/" + i + ".png"));
                left[i] =ImageIO.read(getClass().getResourceAsStream(path + "left/" + i + ".png"));
                right[i] = ImageIO.read(getClass().getResourceAsStream(path + "right/" + i + ".png"));
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
