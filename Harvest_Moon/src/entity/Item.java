package entity;

import Main.GamePanel;

public class Item extends Entity {

    int type; // 0 = item, 1 = tool, 2 = seed, 3 = crop, 4 = food, 5 = fish, 6 = material, 7 = furniture
    public Item(GamePanel gp, String name, int type) {
        super(gp);
        this.name = name;
        this.gp = gp;
        type_item = type;
        
        

        width = 32;
        height = 32;

        path = "/assets/item/" + name + ".png";
        image = gp.setImage(path);
    }

    @Override
    public void interact() {
    }
    
}
