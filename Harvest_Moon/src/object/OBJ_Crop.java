package object;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Crop extends Entity{

    int stages;
    int currentStage = 0;
    boolean isRotten = false;

    public OBJ_Crop(GamePanel gp,String name,int stages) {
        super(gp);
        this.stages = stages;
        this.name = name;
        path = "/assets/crop/" + name + "/seed.png";
        image = gp.setImage(path);
        width = 48;
        height = 48;
        isObj = true;

    }

    public OBJ_Crop clone(int x, int y) {
        try {
            OBJ_Crop cloned = (OBJ_Crop) super.clone();
            cloned.worldX = x;
            cloned.worldY = y;
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed", e);
        }
    }

    @Override
    public void grow() {
        if (currentStage < stages-1) {
            currentStage++;
            path = "/assets/crop/" + name + "/" + currentStage + ".png";
            image = gp.setImage(path);
        }else if(currentStage == stages-1){
            // ada chances buat crop gagal tumbuh
                Random rand = new Random();
                if(rand.nextInt(100) < 5){ // 5% chances crop gagal tumbuh
                    path = "/assets/crop/" + name + "/rotten.png";
                    image = gp.setImage(path);
                    isRotten = true; // tandai crop sebagai busuk
                }
                else{
                    currentStage++;
                    path = "/assets/crop/" + name + "/" + currentStage + ".png";
                    image = gp.setImage(path);
                }

        } else {
            // Crop is fully grown, you can add logic for harvesting here
        }
    }
}
