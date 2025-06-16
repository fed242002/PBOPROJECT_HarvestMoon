package Main;

import object.OBJ_Crop;

public class Crop {

    GamePanel gp;
    static OBJ_Crop berry;
    static OBJ_Crop lettuce;
    static OBJ_Crop carrot;
    static OBJ_Crop cauliflower;
    static OBJ_Crop chili;
    static OBJ_Crop corn;
    static OBJ_Crop cotton;
    static OBJ_Crop grain;
    static OBJ_Crop grapes;
    static OBJ_Crop onion;
    static OBJ_Crop pear;
    static OBJ_Crop pineapple;
    static OBJ_Crop pumpkin;
    static OBJ_Crop radish;
    static OBJ_Crop strawberry;
    static OBJ_Crop tomato;
    static OBJ_Crop turnip;
    static OBJ_Crop watermelon;
    static OBJ_Crop zucchini;

    
    static OBJ_Crop cropList[];

    
    Crop(GamePanel gp){
        this.gp = gp;

        berry = new OBJ_Crop(gp, "berry", 4); 
        lettuce = new OBJ_Crop(gp, "lettuce", 3);
        carrot = new OBJ_Crop(gp, "carrot", 6);
        cauliflower = new OBJ_Crop(gp, "cauliflower", 4);
        chili = new OBJ_Crop(gp, "chili", 5);
        corn = new OBJ_Crop(gp, "corn", 4);
        cotton = new OBJ_Crop(gp, "cotton", 4);
        grain = new OBJ_Crop(gp, "grain", 4);
        grapes = new OBJ_Crop(gp, "grapes", 6);
        onion = new OBJ_Crop(gp, "onion", 3);
        pear = new OBJ_Crop(gp, "pear", 4);
        pineapple = new OBJ_Crop(gp, "pineapple", 4);
        pumpkin = new OBJ_Crop(gp, "pumpkin", 3);
        radish = new OBJ_Crop(gp, "radish", 6);
        strawberry = new OBJ_Crop(gp, "strawberry", 5);
        tomato = new OBJ_Crop(gp, "tomato", 4);
        turnip = new OBJ_Crop(gp, "turnip", 3);
        watermelon = new OBJ_Crop(gp, "watermelon", 4);
        zucchini = new OBJ_Crop(gp, "zucchini", 5);


        cropList = new OBJ_Crop[]{berry, lettuce, carrot, cauliflower, chili, corn, cotton, grain, grapes, onion, pear, pineapple, pumpkin, radish, strawberry, tomato, turnip, watermelon, zucchini};


    }


    static public OBJ_Crop getCrop(String name, int x,int y) {
        for (OBJ_Crop crop : cropList) {
            if (crop.name.equals(name)) {
                return crop.clone(x, y); // Clone with default position
            }
        }
        return null; // Crop not found
    }



}
