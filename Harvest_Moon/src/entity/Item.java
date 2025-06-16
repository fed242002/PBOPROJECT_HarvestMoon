package entity;

import Main.Crop;
import Main.GamePanel;

public class Item extends Entity {

    int type; // 0 = item, 1 = tool, 2 = seed, 3 = crop, 4 = food, 5 = fish, 6 = material, 7 = furniture
    public Item(GamePanel gp, String name, int type, String description) {
        super(gp);
        this.name = name;
        this.gp = gp;
        type_item = type;
        this.description = description;
        

        width = 32;
        height = 32;

        path = "/assets/item/" + name + ".png";
        image = gp.setImage(path);

        if(image == null) {
            System.out.println("Image not found: " + path);
        }
    }

        public Item(GamePanel gp, String name, int type, String description, String cropName, int daysToMature) {
        super(gp);
        this.name = name;
        this.gp = gp;
        type_item = type;
        this.seedCrop = cropName;
        this.daysToMature = daysToMature;
        this.description = description;
        

        width = 32;
        height = 32;

        path = "/assets/item/" + name + ".png";
        image = gp.setImage(path);

        if(image == null) {
            System.out.println("Image not found: " + path);
        }

        if(type ==2){
            Crop.getCrop(cropName).daysToMature = daysToMature; // Set the days to mature for the crop
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
