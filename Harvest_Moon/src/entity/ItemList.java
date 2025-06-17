package entity;

import java.util.ArrayList;
import java.util.Random;

import Main.Crop;
import Main.GamePanel;

public class ItemList {
    // 0 = item, 1 = tool, 2 = seed, 3 = crop, 4 = food, 6 = material, 7 = furniture
   public static Item apple, axe, baguette, banana, carrot, carrotSeedBag, cauliflower, cauliflowerSeedBag, cheese, chicken, chili, chiliSeedBag, coffeeBean;
    public static Item  blueFish, redFish, orangeFish, greenFish,boots,croissant, egg, emptySeedBag, fishRod, grain, grainSeedBag, grapes, grapesSeedBag, lemon, lettuce, lettuceSeedBag, milkBucket, onion, onionSeedBag, orange, peach, pear, pearSeedBag, pineapple, pineappleSeedBag, pumpkin, pumpkinSeedBag, radish, radishSeedBag, rawChicken, rawSteak, shear, shovel, steak, strawberry, strawberrySeedBag, tomato, tomatoSeedBag, toolsBag, oakTrunk, spruceTrunk, appleWoodTrunk, pineTrunk, turnip, turnipSeedBag, wateringCan, watermelon, watermelonSeedBag, WatermelonSlice, whoolBrown, WhoolGray, WhoolWhite, WhoolYellow, zucchini, zucchiniSeedBag;
    GamePanel gp;
    static ArrayList<Item> item;

    public static Item carrotSeedBag2, cauliflowerSeedBag2, chiliSeedBag2, grainSeedBag2, grapesSeedBag2, lettuceSeedBag2, onionSeedBag2, pearSeedBag2, pineappleSeedBag2, pumpkinSeedBag2, radishSeedBag2, strawberrySeedBag2, tomatoSeedBag2, turnipSeedBag2, watermelonSeedBag2, zucchiniSeedBag2;
    public static Item carrotSeedBag3, cauliflowerSeedBag3, chiliSeedBag3, grainSeedBag3, grapesSeedBag3, lettuceSeedBag3, onionSeedBag3, pearSeedBag3, pineappleSeedBag3, pumpkinSeedBag3, radishSeedBag3, strawberrySeedBag3, tomatoSeedBag3, turnipSeedBag3, watermelonSeedBag3, zucchiniSeedBag3;


    public ItemList(GamePanel gp) {
        this.gp = gp;

        initializeItems();
    }

    public void initializeItems() {
        // Type 0: Miscellaneous
        emptySeedBag = new Item(gp, "emptySeedBag", 0, "A plain cloth bag that once held seeds. Could be reused for storage.", 5, 2);
        boots = new Item(gp, "boots", 0, "Sturdy footwear for protecting your feet while working.", 120, 60);

        // Type 1: Tools
        axe = new Item(gp, "axe", 1, "A sturdy tool for chopping trees and collecting wood.", 250, 125);
        fishRod = new Item(gp, "fishRod", 1, "Used to catch fish from ponds, rivers, and the ocean.", 300, 150);
        milkBucket = new Item(gp, "milkBucket", 1, "Essential for collecting milk from your cows.", 200, 100);
        shear = new Item(gp, "shear", 1, "Used for harvesting wool from sheep. Keep them sharp!", 150, 75);
        shovel = new Item(gp, "shovel", 1, "A versatile tool for digging holes and tilling soil.", 200, 100);
        toolsBag = new Item(gp, "toolsBag", 1, "A durable bag that can hold your essential farming tools.", 100, 50);
        wateringCan = new Item(gp, "wateringCan", 1, "Used to water crops. Remember to refill it regularly!", 180, 90);

        // Type 2: Seeds (Quality 1)
        carrotSeedBag = new Item(gp, "carrotSeedBag", 2, "Contains seeds for growing orange root vegetables.", 6, 1, 20, 10);
        cauliflowerSeedBag = new Item(gp, "cauliflowerSeedBag", 2, "Seeds for growing large white florets.", 4, 1, 30, 15);
        chiliSeedBag = new Item(gp, "chiliSeedBag", 2, "Seeds for growing spicy red peppers.", 5, 1, 25, 12);
        grainSeedBag = new Item(gp, "grainSeedBag", 2, "Premium seeds for growing higher quality grains.", 4, 1, 15, 7);
        grapesSeedBag = new Item(gp, "grapesSeedBag", 2, "Seeds for growing purple grape vines.", 6, 1, 45, 22);
        lettuceSeedBag = new Item(gp, "lettuceSeedBag", 2, "Seeds for growing leafy green vegetables.", 3, 1, 25, 12);
        onionSeedBag = new Item(gp, "onionSeedBag", 2, "Seeds for growing pungent bulb vegetables.", 3, 1, 20, 10);
        pearSeedBag = new Item(gp, "pearSeedBag", 2, "Seeds to grow pear trees.", 4, 1, 35, 17);
        pineappleSeedBag = new Item(gp, "pineappleSeedBag", 2, "Seeds for growing tropical pineapple plants.", 4, 1, 60, 30);
        pumpkinSeedBag = new Item(gp, "pumpkinSeedBag", 2, "Seeds for growing large orange gourds.", 3, 1, 40, 20);
        radishSeedBag = new Item(gp, "radishSeedBag", 2, "Seeds for growing crisp white root vegetables.", 6, 1, 20, 10);
        strawberrySeedBag = new Item(gp, "strawberrySeedBag", 2, "Seeds for growing sweet red berries.", 5, 1, 30, 15);
        tomatoSeedBag = new Item(gp, "tomatoSeedBag", 2, "Seeds for growing juicy red vegetables.", 4, 1, 25, 12);
        turnipSeedBag = new Item(gp, "turnipSeedBag", 2, "Seeds for growing purple and white root vegetables.", 3, 1, 15, 7);
        watermelonSeedBag = new Item(gp, "watermelonSeedBag", 2, "Seeds for growing large, striped melons.", 4, 1, 50, 25);
        zucchiniSeedBag = new Item(gp, "zucchiniSeedBag", 2, "Seeds for growing green summer squash.", 5, 1, 25, 12);

        // Type 2: Seeds (Quality 2)
        carrotSeedBag2 = new Item(gp, "carrotSeedBag", 2, "Contains seeds for growing orange root vegetables.", 6, 2, 30, 15);
        cauliflowerSeedBag2 = new Item(gp, "cauliflowerSeedBag", 2, "Seeds for growing large white florets.", 4, 2, 45, 22);
        chiliSeedBag2 = new Item(gp, "chiliSeedBag", 2, "Seeds for growing spicy red peppers.", 5, 2, 38, 19);
        grainSeedBag2 = new Item(gp, "grainSeedBag", 2, "Premium seeds for growing higher quality grains.", 4, 2, 23, 11);
        grapesSeedBag2 = new Item(gp, "grapesSeedBag", 2, "Seeds for growing purple grape vines.", 6, 2, 68, 34);
        lettuceSeedBag2 = new Item(gp, "lettuceSeedBag", 2, "Seeds for growing leafy green vegetables.", 3, 2, 38, 19);
        onionSeedBag2 = new Item(gp, "onionSeedBag", 2, "Seeds for growing pungent bulb vegetables.", 3, 2, 30, 15);
        pearSeedBag2 = new Item(gp, "pearSeedBag", 2, "Seeds to grow pear trees.", 4, 2, 53, 26);
        pineappleSeedBag2 = new Item(gp, "pineappleSeedBag", 2, "Seeds for growing tropical pineapple plants.", 4, 2, 90, 45);
        pumpkinSeedBag2 = new Item(gp, "pumpkinSeedBag", 2, "Seeds for growing large orange gourds.", 3, 2, 60, 30);
        radishSeedBag2 = new Item(gp, "radishSeedBag", 2, "Seeds for growing crisp white root vegetables.", 6, 2, 30, 15);
        strawberrySeedBag2 = new Item(gp, "strawberrySeedBag", 2, "Seeds for growing sweet red berries.", 5, 2, 45, 22);
        tomatoSeedBag2 = new Item(gp, "tomatoSeedBag", 2, "Seeds for growing juicy red vegetables.", 4, 2, 38, 19);
        turnipSeedBag2 = new Item(gp, "turnipSeedBag", 2, "Seeds for growing purple and white root vegetables.", 3, 2, 23, 11);
        watermelonSeedBag2 = new Item(gp, "watermelonSeedBag", 2, "Seeds for growing large, striped melons.", 4, 2, 75, 37);
        zucchiniSeedBag2 = new Item(gp, "zucchiniSeedBag", 2, "Seeds for growing green summer squash.", 5, 2, 38, 19);

        // Type 2: Seeds (Quality 3)
        carrotSeedBag3 = new Item(gp, "carrotSeedBag", 2, "Contains seeds for growing orange root vegetables.", 6, 3, 45, 22);
        cauliflowerSeedBag3 = new Item(gp, "cauliflowerSeedBag", 2, "Seeds for growing large white florets.", 4, 3, 68, 34);
        chiliSeedBag3 = new Item(gp, "chiliSeedBag", 2, "Seeds for growing spicy red peppers.", 5, 3, 56, 28);
        grainSeedBag3 = new Item(gp, "grainSeedBag", 2, "Premium seeds for growing higher quality grains.", 4, 3, 34, 17);
        grapesSeedBag3 = new Item(gp, "grapesSeedBag", 2, "Seeds for growing purple grape vines.", 6, 3, 102, 51);
        lettuceSeedBag3 = new Item(gp, "lettuceSeedBag", 2, "Seeds for growing leafy green vegetables.", 3, 3, 56, 28);
        onionSeedBag3 = new Item(gp, "onionSeedBag", 2, "Seeds for growing pungent bulb vegetables.", 3, 3, 45, 22);
        pearSeedBag3 = new Item(gp, "pearSeedBag", 2, "Seeds to grow pear trees.", 4, 3, 79, 39);
        pineappleSeedBag3 = new Item(gp, "pineappleSeedBag", 2, "Seeds for growing tropical pineapple plants.", 4, 3, 135, 67);
        pumpkinSeedBag3 = new Item(gp, "pumpkinSeedBag", 2, "Seeds for growing large orange gourds.", 3, 3, 90, 45);
        radishSeedBag3 = new Item(gp, "radishSeedBag", 2, "Seeds for growing crisp white root vegetables.", 6, 3, 45, 22);
        strawberrySeedBag3 = new Item(gp, "strawberrySeedBag", 2, "Seeds for growing sweet red berries.", 5, 3, 68, 34);
        tomatoSeedBag3 = new Item(gp, "tomatoSeedBag", 2, "Seeds for growing juicy red vegetables.", 4, 3, 56, 28);
        turnipSeedBag3 = new Item(gp, "turnipSeedBag", 2, "Seeds for growing purple and white root vegetables.", 3, 3, 34, 17);
        watermelonSeedBag3 = new Item(gp, "watermelonSeedBag", 2, "Seeds for growing large, striped melons.", 4, 3, 113, 56);
        zucchiniSeedBag3 = new Item(gp, "zucchiniSeedBag", 2, "Seeds for growing green summer squash.", 5, 3, 56, 28);


        // Type 3: Crops
        carrot = new Item(gp, "carrot", 3, "A crunchy orange root vegetable. Good for vision.", 40, 30);
        cauliflower = new Item(gp, "cauliflower", 3, "A large vegetable with white florets. High in nutrients.", 60, 45);
        chili = new Item(gp, "chili", 3, "A small, spicy red pepper. Adds heat to any dish.", 50, 38);
        coffeeBean = new Item(gp, "coffeeBean", 3, "Aromatic beans used to brew coffee. Can be roasted.", 45, 35);
        grain = new Item(gp, "grain", 3, "Various cereal grains used for baking and cooking.", 30, 22);
        grapes = new Item(gp, "grapes", 3, "Sweet purple fruit growing in clusters. Good fresh or as juice.", 90, 70);
        lettuce = new Item(gp, "lettuce", 3, "Leafy green vegetable, crisp and refreshing in salads.", 50, 38);
        onion = new Item(gp, "onion", 3, "Layered vegetable with a strong, pungent flavor. Essential for cooking.", 40, 30);
        pineapple = new Item(gp, "pineapple", 3, "Sweet tropical fruit with a spiky exterior and yellow flesh.", 120, 90);
        pumpkin = new Item(gp, "pumpkin", 3, "Large orange gourd, good for cooking and decoration.", 80, 60);
        radish = new Item(gp, "radish", 3, "Crisp white root vegetable with a peppery flavor.", 40, 30);
        strawberry = new Item(gp, "strawberry", 3, "Sweet red berries with tiny seeds on the outside.", 60, 45);
        tomato = new Item(gp, "tomato", 3, "Juicy red fruit/vegetable used in many dishes.", 50, 38);
        turnip = new Item(gp, "turnip", 3, "Purple and white root vegetable with a slightly sweet flavor.", 30, 22);
        watermelon = new Item(gp, "watermelon", 3, "Large striped melon with sweet, juicy red flesh.", 100, 75);
        zucchini = new Item(gp, "zucchini", 3, "Green summer squash that's versatile for cooking.", 50, 38);
        pear = new Item(gp, "pear", 3, "A sweet fruit with a distinctive bell shape and grainy texture.", 70, 55);
        
        // Type 4: Food - with energy restoration values
        apple = new Item(gp, "apple", 4, "A crisp, juicy red fruit. Good for eating or cooking.", 30, 20, 15);
        banana = new Item(gp, "banana", 4, "A sweet yellow fruit with a curved shape. Rich in potassium.", 35, 25, 20);
        lemon = new Item(gp, "lemon", 4, "A sour citrus fruit with bright yellow skin. Used in cooking and beverages.", 40, 30, 10);
        orange = new Item(gp, "orange", 4, "A juicy citrus fruit high in vitamin C. Sweet with a hint of tartness.", 35, 25, 18);
        peach = new Item(gp, "peach", 4, "A soft, fuzzy fruit with sweet pink flesh. Popular in summer.", 45, 35, 25);
        WatermelonSlice = new Item(gp, "WatermelonSlice", 4, "A refreshing slice of watermelon. Sweet, juicy, and perfect for hot days.", 25, 15, 12);
        baguette = new Item(gp, "baguette", 4, "Long, crusty French bread. Perfect with cheese or on its own.", 70, 50, 35);
        cheese = new Item(gp, "cheese", 4, "Dairy product made from fermented milk. Rich and flavorful.", 150, 120, 30);
        chicken = new Item(gp, "chicken", 4, "Prepared poultry meat, ready to eat. High in protein.", 250, 180, 60);
        croissant = new Item(gp, "croissant", 4, "Buttery, flaky pastry of French origin. Delicious for breakfast.", 60, 45, 25);
        egg = new Item(gp, "egg", 4, "Farm-fresh chicken egg. Versatile for cooking or baking.", 40, 30, 15);
        rawChicken = new Item(gp, "rawChicken", 4, "Uncooked poultry meat. Must be cooked before consumption.", 80, 60, 5); // Raw food: low energy, risk of sickness
        rawSteak = new Item(gp, "rawSteak", 4, "Uncooked beef cut. Needs to be grilled or cooked.", 120, 90, 8); // Raw food: low energy, risk of sickness
        steak = new Item(gp, "steak", 4, "Cooked beef cut. Juicy and protein-rich.", 180, 135, 75);
       
        // Type 6: Materials
        oakTrunk = new Item(gp, "oakTrunk", 6, "Sturdy wood harvested from oak trees. Good for building.", 40, 30);
        spruceTrunk = new Item(gp, "spruceTrunk", 6, "Lightweight wood from spruce trees. Useful for crafting.", 50, 40);
        appleWoodTrunk = new Item(gp, "appleWoodTrunk", 6, "Wood from apple trees. Has a subtle sweet scent.", 60, 45);
        pineTrunk = new Item(gp, "pineTrunk", 6, "Wood from pine trees. Can be processed for wood and pine needles.", 45, 35);
        whoolBrown = new Item(gp, "whoolBrown", 6, "Soft brown sheep's wool. Used for crafting and textiles.", 90, 70);
        WhoolGray = new Item(gp, "WhoolGray", 6, "Thick gray sheep's wool. Good for warmer clothing and crafts.", 90, 70);
        WhoolWhite = new Item(gp, "WhoolWhite", 6, "Pure white sheep's wool. Premium quality for fine textiles.", 100, 80);
        WhoolYellow = new Item(gp, "WhoolYellow", 6, "Yellow-tinted sheep's wool. Unique color for specialty crafts.", 100, 80);
        redFish = new Item(gp, "redFish", 6, "A vibrant red fish. Fresh and rich in flavor.", 80, 60);
        blueFish = new Item(gp, "blueFish", 6, "A rare blue fish. Known for its delicate taste.", 110, 85);
        orangeFish = new Item(gp, "orangeFish", 6, "A bright orange fish. Popular in local markets.", 75, 55);
        greenFish = new Item(gp, "greenFish", 6, "A unique green fish. Adds color to your catch.", 95, 70);
        

        item = new ArrayList<>(java.util.Arrays.asList(
            apple, axe, baguette, banana, carrot, carrotSeedBag, cauliflower, cauliflowerSeedBag, cheese, chicken, chili, chiliSeedBag, coffeeBean, croissant, egg, emptySeedBag, fishRod, grain, grainSeedBag, grainSeedBag, grapes, grapesSeedBag, lemon, lettuce, lettuceSeedBag, milkBucket, onion, onionSeedBag, orange, peach, pear, pearSeedBag, pineapple, pineappleSeedBag, pumpkin, pumpkinSeedBag, radish, radishSeedBag, rawChicken, rawSteak, shear, shovel, steak, strawberry, strawberrySeedBag, tomato, tomatoSeedBag, toolsBag, oakTrunk, spruceTrunk, appleWoodTrunk, pineTrunk, turnip, turnipSeedBag, wateringCan, watermelon, watermelonSeedBag, WatermelonSlice, whoolBrown, WhoolGray, WhoolWhite, WhoolYellow, zucchini, zucchiniSeedBag,
            carrotSeedBag2, cauliflowerSeedBag2, chiliSeedBag2, grainSeedBag2, grapesSeedBag2, lettuceSeedBag2, onionSeedBag2, pearSeedBag2, pineappleSeedBag2, pumpkinSeedBag2, radishSeedBag2, strawberrySeedBag2, tomatoSeedBag2, turnipSeedBag2, watermelonSeedBag2, zucchiniSeedBag2,
            carrotSeedBag3, cauliflowerSeedBag3, chiliSeedBag3, grainSeedBag3, grapesSeedBag3, lettuceSeedBag3, onionSeedBag3, pearSeedBag3, pineappleSeedBag3, pumpkinSeedBag3, radishSeedBag3, strawberrySeedBag3, tomatoSeedBag3, turnipSeedBag3, watermelonSeedBag3, zucchiniSeedBag3
            ));



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
