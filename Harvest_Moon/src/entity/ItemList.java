package entity;

import java.util.ArrayList;

import Main.GamePanel;

public class ItemList {
    // 0 = item, 1 = tool, 2 = seed, 3 = crop, 4 = food, 5 = fish, 6 = material, 7 = furniture
   public static Item apple, axe, baguette, banana, carrot, carrotSeedBag, cauliflower, cauliflowerSeedBag, cheese, chicken, chili, chiliSeedBag, coffee, coffeeBean;
    public static Item coffeeBeanBag, croissant, egg, emptySeedBag, fishRod, grain, grainBag, grainSeedBag, grape, grapeSeedBag, lemon, lettuce, lettuceSeedBag, milkBucket, onion, onionSeedBag, orange, peach, pear, pearSeedBag, pineapple, pineappleSeedBag, pumpkin, pumpkinSeedBag, radish, radishSeedBag, rawChicken, rawSteak, shear, shovel, steak, strawberry, strawberrySeedBag, tomato, tomatoSeedBag, toolsBag, oakTrunk, spruceTrunk, appleWoodTrunk, pineTree, turnip, turnipSeedBag, wateringCan, watermelon, watermelonSeedBag, WatermelonSlice, whoolBrown, WhoolGray, WhoolWhite, WhoolYellow, zucchini, zucchiniSeedBag;
    GamePanel gp;
    static ArrayList<Item> item;


    public ItemList(GamePanel gp) {
        this.gp = gp;


        // Type 0: General items
        emptySeedBag = new Item(gp, "emptySeedBag", 0);
        
        // Type 1: Tools
        axe = new Item(gp, "axe", 1);
        fishRod = new Item(gp, "fishRod", 1);
        milkBucket = new Item(gp, "milkBucket", 1);
        shear = new Item(gp, "shear", 1);
        shovel = new Item(gp, "shovel", 1);
        toolsBag = new Item(gp, "toolsBag", 1);
        wateringCan = new Item(gp, "wateringCan", 1);

        // Type 2: Seeds
        carrotSeedBag = new Item(gp, "carrotSeedBag", 2);
        cauliflowerSeedBag = new Item(gp, "cauliflowerSeedBag", 2);
        chiliSeedBag = new Item(gp, "chiliSeedBag", 2);
        coffeeBeanBag = new Item(gp, "coffeeBeanBag", 2);
        grainBag = new Item(gp, "grainBag", 2);
        grainSeedBag = new Item(gp, "grainSeedBag", 2);
        grapeSeedBag = new Item(gp, "grapeSeedBag", 2);
        lettuceSeedBag = new Item(gp, "lettuceSeedBag", 2);
        onionSeedBag = new Item(gp, "onionSeedBag", 2);
        pearSeedBag = new Item(gp, "pearSeedBag", 2);
        pineappleSeedBag = new Item(gp, "pineappleSeedBag", 2);
        pumpkinSeedBag = new Item(gp, "pumpkinSeedBag", 2);
        radishSeedBag = new Item(gp, "radishSeedBag", 2);
        strawberrySeedBag = new Item(gp, "strawberrySeedBag", 2);
        tomatoSeedBag = new Item(gp, "tomatoSeedBag", 2);
        turnipSeedBag = new Item(gp, "turnipSeedBag", 2);
        watermelonSeedBag = new Item(gp, "watermelonSeedBag", 2);
        zucchiniSeedBag = new Item(gp, "zucchiniSeedBag", 2);

        // Type 3: Crops
        pear = new Item(gp, "pear", 3);
        carrot = new Item(gp, "carrot", 3);
        cauliflower = new Item(gp, "cauliflower", 3);
        chili = new Item(gp, "chili", 3);
        coffeeBean = new Item(gp, "coffeeBean", 3);
        grain = new Item(gp, "grain", 3);
        grape = new Item(gp, "grape", 3);
        lettuce = new Item(gp, "lettuce", 3);
        onion = new Item(gp, "onion", 3);
        pineapple = new Item(gp, "pineapple", 3);
        pumpkin = new Item(gp, "pumpkin", 3);
        radish = new Item(gp, "radish", 3);
        strawberry = new Item(gp, "strawberry", 3);
        tomato = new Item(gp, "tomato", 3);
        turnip = new Item(gp, "turnip", 3);
        watermelon = new Item(gp, "watermelon", 3);
        zucchini = new Item(gp, "zucchini", 3);

        // Type 4: Food
        apple = new Item(gp, "apple", 4);
        banana = new Item(gp, "banana", 4);
        lemon = new Item(gp, "lemon", 4);
        orange = new Item(gp, "orange", 4);
        peach = new Item(gp, "peach", 4);
        WatermelonSlice = new Item(gp, "WatermelonSlice", 4);
        baguette = new Item(gp, "baguette", 4);
        cheese = new Item(gp, "cheese", 4);
        chicken = new Item(gp, "chicken", 4);
        coffee = new Item(gp, "coffee", 4);
        croissant = new Item(gp, "croissant", 4);
        egg = new Item(gp, "egg", 4);
        steak = new Item(gp, "steak", 4);
        
        // Type 6: Materials
        rawChicken = new Item(gp, "rawChicken", 6);
        rawSteak = new Item(gp, "rawSteak", 6);
        oakTrunk = new Item(gp, "oakTrunk", 6);
        spruceTrunk = new Item(gp, "spruceTrunk", 6);
        appleWoodTrunk = new Item(gp, "appleWoodTrunk", 6);
        pineTree = new Item(gp, "pineTree", 6);
        whoolBrown = new Item(gp, "whoolBrown", 6);
        WhoolGray = new Item(gp, "WhoolGray", 6);
        WhoolWhite = new Item(gp, "WhoolWhite", 6);
        WhoolYellow = new Item(gp, "WhoolYellow", 6);

        item = new ArrayList<>();
        item.add(apple);
        item.add(axe);
        item.add(baguette);
        item.add(banana);
        item.add(carrot);
        item.add(carrotSeedBag);
        item.add(cauliflower);
        item.add(cauliflowerSeedBag);
        item.add(cheese);
        item.add(chicken);
        item.add(chili);
        item.add(chiliSeedBag);
        item.add(coffee);
        item.add(coffeeBean);
        item.add(coffeeBeanBag);
        item.add(croissant);
        item.add(egg);
        item.add(emptySeedBag);
        item.add(fishRod);
        item.add(grain);
        item.add(grainBag);
        item.add(grainSeedBag);
        item.add(grape);
        item.add(grapeSeedBag);
        item.add(lemon);
        item.add(lettuce);
        item.add(lettuceSeedBag);
        item.add(milkBucket);
        item.add(onion);
        item.add(onionSeedBag);
        item.add(orange);
        item.add(peach);
        item.add(pear);
        item.add(pearSeedBag);
        item.add(pineapple);
        item.add(pineappleSeedBag);
        item.add(pumpkin);
        item.add(pumpkinSeedBag);
        item.add(radish);
        item.add(radishSeedBag);
        item.add(rawChicken);
        item.add(rawSteak);
        item.add(shear);
        item.add(shovel);
        item.add(steak);
        item.add(strawberry);
        item.add(strawberrySeedBag);
        item.add(tomato);
        item.add(tomatoSeedBag);
        item.add(toolsBag);
        item.add(oakTrunk);
        item.add(spruceTrunk);
        item.add(appleWoodTrunk);
        item.add(pineTree);
        item.add(turnip);
        item.add(turnipSeedBag);
        item.add(wateringCan);
        item.add(watermelon);
        item.add(watermelonSeedBag);
        item.add(WatermelonSlice);
        item.add(whoolBrown);
        item.add(WhoolGray);
        item.add(WhoolWhite);
        item.add(WhoolYellow);
        item.add(zucchini);
        item.add(zucchiniSeedBag);

    }


    static public Item getItem(String name){
            for(Item x : item){
                if(x.name.equalsIgnoreCase(name)){
                    return x;
                }
            }
        return null;
    }

}
