package entity;

import java.util.ArrayList;

import Main.GamePanel;

public class ItemList {
    // 0 = item, 1 = tool, 2 = seed, 3 = crop, 4 = food, 5 = fish, 6 = material, 7 = furniture
   public static Item apple, axe, baguette, banana, carrot, carrotSeedBag, cauliflower, cauliflowerSeedBag, cheese, chicken, chili, chiliSeedBag, coffee, coffeeBean;

    GamePanel gp;
    ArrayList<Item> item = new ArrayList<>();


    public ItemList(GamePanel gp) {
        this.gp = gp;
        apple = new Item(gp, "apple", 0);

    }

    
}
