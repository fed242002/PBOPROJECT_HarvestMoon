package object;

import java.util.Random;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Crop extends Entity{
    public int stages;
    int currentStage = 0;
    boolean isRotten = false;
    int dayToGrow;
    Random rand = new Random();
    int additionalDays = 0; // Additional days for growth
    int growTracker =0;
    boolean isMatured = false;
    boolean fixRotten = false; // Fix rotten crop
    

    public OBJ_Crop(GamePanel gp,String name,int stages) {
        super(gp);
        this.stages = stages;
        this.name = name;
        path = "/assets/crop/" + name + "/seed.png";
        image = gp.setImage(path);
        width = 48;
        height = 48;
        isObj = true;
        dayToGrow = daysToMature / stages; // Days to grow per stage


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
        if(isRotten)
            return; // If the crop is rotten, it cannot grow

        if (currentStage < stages-1) {
            currentStage++;
            path = "/assets/crop/" + name + "/" + currentStage + ".png";
            image = gp.setImage(path);
        }else if(currentStage == stages-1){
            // ada chances buat crop gagal tumbuh
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



    public void dayPassed(){
        dayCount++;
        growTracker++;
        if (growTracker >= dayToGrow) {
            grow();
            growTracker = 0; // Reset grow tracker after growing
        }
        if (dayCount >= daysToMature + 5) { //5 day after mature, crop will rot
            if(!isRotten){
                fixRotten = rand.nextBoolean();
            }
        }
        if(wateredCount < dayCount - 5) {
            fixRotten = rand.nextBoolean();
        }
    }
}
