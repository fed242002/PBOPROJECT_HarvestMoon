package entity;

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
