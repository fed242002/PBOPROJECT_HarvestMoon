package entity;

import java.util.Random;

import Main.Crop;
import Main.GamePanel;
import object.OBJ_Crop;

public class Item extends Entity implements Cloneable {

    public int price;
    int stages;
    int type; // 0 = item, 1 = tool, 2 = seed, 3 = crop, 4 = food, 5 = fish, 6 = material, 7 = furniture
    public Item(GamePanel gp, String name, int type, String description) {
        this(gp, name, type, description, 0);
    }
    
    // public Item(GamePanel gp, String name, int type, String description, int price) {
    //     super(gp);
    //     this.name = name;
    //     this.gp = gp;
    //     type_item = type;
    //     this.description = description;
    //     this.price = price;
        
    //     width = 32;
    //     height = 32;
        
    //     path = "/assets/item/" + name + ".png";
    //     image = gp.setImage(path);
        
    //     if(image == null) {
    //         System.out.println("Image not found: " + path);
    //     }


    // }


    public Item(GamePanel gp, String name, int type, String description, int stages) {
        super(gp);
        this.name = name;
        this.gp = gp;
        type_item = type;
        this.description = description;
        this.stages = stages;


        width = 32;
        height = 32;

        path = "/assets/item/" + name + ".png";
        image = gp.setImage(path);

        if(image == null) {
            System.out.println("Image not found: " + path);
        }

        if(type == type_seed) { // If it's a crop
            Random random = new Random();
            seedCrop = name.substring(0, name.length() - 7); // Removes "SeedBag" (7 characters)

            int randomQuality = random.nextInt(1, 4); // Random quality between 1 and 3
            if(randomQuality == 1){
                this.name += " (High Quality)";
            }
            if(randomQuality == 2){
                this.name += " (Medium Quality)";
            }
            if(randomQuality == 3){
                this.name += " (Low Quality)";
            }
            this.daysToMature = randomQuality * stages;
            this.description += "\nIt takes " + this.daysToMature + " days to mature.";

        }

        

       

    }





    
        
    

    @Override
    public void interact() {
    }

    public Item clone() {
        try {
            Item cloned = (Item) super.clone();
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Cloning failed", e);
        }
    }
    

    
}
