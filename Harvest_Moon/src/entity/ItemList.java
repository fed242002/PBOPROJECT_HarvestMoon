package entity;

import java.util.ArrayList;

import Main.GamePanel;

public class ItemList {
    // 0 = item, 1 = tool, 2 = seed, 3 = crop, 4 = food, 5 = fish, 6 = material, 7 = furniture
   public static Item apple, axe, baguette, banana, carrot, carrotSeedBag, cauliflower, cauliflowerSeedBag, cheese, chicken, chili, chiliSeedBag, coffeeBean;
    public static Item  croissant, egg, emptySeedBag, fishRod, grain, grainBag, grainSeedBag, grapes, grapesSeedBag, lemon, lettuce, lettuceSeedBag, milkBucket, onion, onionSeedBag, orange, peach, pear, pearSeedBag, pineapple, pineappleSeedBag, pumpkin, pumpkinSeedBag, radish, radishSeedBag, rawChicken, rawSteak, shear, shovel, steak, strawberry, strawberrySeedBag, tomato, tomatoSeedBag, toolsBag, oakTrunk, spruceTrunk, appleWoodTrunk, pineTrunk, turnip, turnipSeedBag, wateringCan, watermelon, watermelonSeedBag, WatermelonSlice, whoolBrown, WhoolGray, WhoolWhite, WhoolYellow, zucchini, zucchiniSeedBag;
    GamePanel gp;
    static ArrayList<Item> item;


    public ItemList(GamePanel gp) {
        this.gp = gp;

        initializeItems();
    }

    public void initializeItems() {
        // Type 0: General items
        emptySeedBag = new Item(gp, "emptySeedBag", 0, "A plain cloth bag that once held seeds. Could be reused for storage.");
        
        // Type 1: Tools
        axe = new Item(gp, "axe", 1, "A sturdy tool for chopping trees and collecting wood.");
        fishRod = new Item(gp, "fishRod", 1, "Used to catch fish from ponds, rivers, and the ocean.");
        milkBucket = new Item(gp, "milkBucket", 1, "Essential for collecting milk from your cows.");
        shear = new Item(gp, "shear", 1, "Used for harvesting wool from sheep. Keep them sharp!");
        shovel = new Item(gp, "shovel", 1, "A versatile tool for digging holes and tilling soil.");
        toolsBag = new Item(gp, "toolsBag", 1, "A durable bag that can hold your essential farming tools.");
        wateringCan = new Item(gp, "wateringCan", 1, "Used to water crops. Remember to refill it regularly!");

        // Type 2: Seeds
        carrotSeedBag = new Item(gp, "carrotSeedBag", 2, "Contains seeds for growing orange root vegetables. Takes 4 days to mature.", "carrot", 4);
        cauliflowerSeedBag = new Item(gp, "cauliflowerSeedBag", 2, "Seeds for growing large white florets. Takes 8 days to mature.", "cauliflower", 8);
        chiliSeedBag = new Item(gp, "chiliSeedBag", 2, "Seeds for growing spicy red peppers. Takes 5 days to mature.", "chili", 5);
        grainBag = new Item(gp, "grainBag", 2, "Contains various cereal grains for planting. Takes 7 days to mature.", "grain", 7);
        grainSeedBag = new Item(gp, "grainSeedBag", 2, "Premium seeds for growing higher quality grains. Takes 7 days.", "grain", 7);
        grapesSeedBag = new Item(gp, "grapesSeedBag", 2, "Seeds for growing purple grape vines. Takes 9 days to mature.", "grape", 9);
        lettuceSeedBag = new Item(gp, "lettuceSeedBag", 2, "Seeds for growing leafy green vegetables. Takes 5 days to mature.", "lettuce", 5);
        onionSeedBag = new Item(gp, "onionSeedBag", 2, "Seeds for growing pungent bulb vegetables. Takes 6 days to mature.", "onion", 6);
        pearSeedBag = new Item(gp, "pearSeedBag", 2, "Seeds to grow pear trees. Takes a season to grow into a sapling.", "pear", 30);
        pineappleSeedBag = new Item(gp, "pineappleSeedBag", 2, "Seeds for growing tropical pineapple plants. Takes 14 days.", "pineapple", 14);
        pumpkinSeedBag = new Item(gp, "pumpkinSeedBag", 2, "Seeds for growing large orange gourds. Takes 13 days to mature.", "pumpkin", 13);
        radishSeedBag = new Item(gp, "radishSeedBag", 2, "Seeds for growing crisp white root vegetables. Takes 3 days.", "radish", 3);
        strawberrySeedBag = new Item(gp, "strawberrySeedBag", 2, "Seeds for growing sweet red berries. Takes 7 days to mature.", "strawberry", 7);
        tomatoSeedBag = new Item(gp, "tomatoSeedBag", 2, "Seeds for growing juicy red vegetables. Takes 6 days to mature.", "tomato", 6);
        turnipSeedBag = new Item(gp, "turnipSeedBag", 2, "Seeds for growing purple and white root vegetables. Takes 4 days.", "turnip", 4);
        watermelonSeedBag = new Item(gp, "watermelonSeedBag", 2, "Seeds for growing large, striped melons. Takes 12 days.", "watermelon", 12);
        zucchiniSeedBag = new Item(gp, "zucchiniSeedBag", 2, "Seeds for growing green summer squash. Takes 5 days to mature.", "zucchini", 5);

        // Type 3: Crops
        carrot = new Item(gp, "carrot", 3, "A crunchy orange root vegetable. Good for vision.");
        cauliflower = new Item(gp, "cauliflower", 3, "A large vegetable with white florets. High in nutrients.");
        chili = new Item(gp, "chili", 3, "A small, spicy red pepper. Adds heat to any dish.");
        coffeeBean = new Item(gp, "coffeeBean", 3, "Aromatic beans used to brew coffee. Can be roasted.");
        grain = new Item(gp, "grain", 3, "Various cereal grains used for baking and cooking.");
        grapes = new Item(gp, "grapes", 3, "Sweet purple fruit growing in clusters. Good fresh or as juice.");
        lettuce = new Item(gp, "lettuce", 3, "Leafy green vegetable, crisp and refreshing in salads.");
        onion = new Item(gp, "onion", 3, "Layered vegetable with a strong, pungent flavor. Essential for cooking.");
        pineapple = new Item(gp, "pineapple", 3, "Sweet tropical fruit with a spiky exterior and yellow flesh.");
        pumpkin = new Item(gp, "pumpkin", 3, "Large orange gourd, good for cooking and decoration.");
        radish = new Item(gp, "radish", 3, "Crisp white root vegetable with a peppery flavor.");
        strawberry = new Item(gp, "strawberry", 3, "Sweet red berries with tiny seeds on the outside.");
        tomato = new Item(gp, "tomato", 3, "Juicy red fruit/vegetable used in many dishes.");
        turnip = new Item(gp, "turnip", 3, "Purple and white root vegetable with a slightly sweet flavor.");
        watermelon = new Item(gp, "watermelon", 3, "Large striped melon with sweet, juicy red flesh.");
        zucchini = new Item(gp, "zucchini", 3, "Green summer squash that's versatile for cooking.");
        pear = new Item(gp, "pear", 3, "A sweet fruit with a distinctive bell shape and grainy texture.");
        
        // Type 4: Food
        apple = new Item(gp, "apple", 4, "A crisp, juicy red fruit. Good for eating or cooking.");
        banana = new Item(gp, "banana", 4, "A sweet yellow fruit with a curved shape. Rich in potassium.");
        lemon = new Item(gp, "lemon", 4, "A sour citrus fruit with bright yellow skin. Used in cooking and beverages.");
        orange = new Item(gp, "orange", 4, "A juicy citrus fruit high in vitamin C. Sweet with a hint of tartness.");
        peach = new Item(gp, "peach", 4, "A soft, fuzzy fruit with sweet pink flesh. Popular in summer.");
        WatermelonSlice = new Item(gp, "WatermelonSlice", 4, "A refreshing slice of watermelon. Sweet, juicy, and perfect for hot days.");
        baguette = new Item(gp, "baguette", 4, "Long, crusty French bread. Perfect with cheese or on its own.");
        cheese = new Item(gp, "cheese", 4, "Dairy product made from fermented milk. Rich and flavorful.");
        chicken = new Item(gp, "chicken", 4, "Prepared poultry meat, ready to eat. High in protein.");
        croissant = new Item(gp, "croissant", 4, "Buttery, flaky pastry of French origin. Delicious for breakfast.");
        egg = new Item(gp, "egg", 4, "Farm-fresh chicken egg. Versatile for cooking or baking.");
        rawChicken = new Item(gp, "rawChicken", 4, "Uncooked poultry meat. Must be cooked before consumption.");
        rawSteak = new Item(gp, "rawSteak", 4, "Uncooked beef cut. Needs to be grilled or cooked.");
        steak = new Item(gp, "steak", 4, "Cooked beef cut. Juicy and protein-rich.");

        // Type 6: Materials
        oakTrunk = new Item(gp, "oakTrunk", 6, "Sturdy wood harvested from oak trees. Good for building.");
        spruceTrunk = new Item(gp, "spruceTrunk", 6, "Lightweight wood from spruce trees. Useful for crafting.");
        appleWoodTrunk = new Item(gp, "appleWoodTrunk", 6, "Wood from apple trees. Has a subtle sweet scent.");
        pineTrunk = new Item(gp, "pineTrunk", 6, "Wood from pine trees. Can be processed for wood and pine needles.");
        whoolBrown = new Item(gp, "whoolBrown", 6, "Soft brown sheep's wool. Used for crafting and textiles.");
        WhoolGray = new Item(gp, "WhoolGray", 6, "Thick gray sheep's wool. Good for warmer clothing and crafts.");
        WhoolWhite = new Item(gp, "WhoolWhite", 6, "Pure white sheep's wool. Premium quality for fine textiles.");
        WhoolYellow = new Item(gp, "WhoolYellow", 6, "Yellow-tinted sheep's wool. Unique color for specialty crafts.");
    
        item = new ArrayList<>(java.util.Arrays.asList(
            apple, axe, baguette, banana, carrot, carrotSeedBag, cauliflower, cauliflowerSeedBag, cheese, chicken, chili, chiliSeedBag, coffeeBean, croissant, egg, emptySeedBag, fishRod, grain, grainBag, grainSeedBag, grapes, grapesSeedBag, lemon, lettuce, lettuceSeedBag, milkBucket, onion, onionSeedBag, orange, peach, pear, pearSeedBag, pineapple, pineappleSeedBag, pumpkin, pumpkinSeedBag, radish, radishSeedBag, rawChicken, rawSteak, shear, shovel, steak, strawberry, strawberrySeedBag, tomato, tomatoSeedBag, toolsBag, oakTrunk, spruceTrunk, appleWoodTrunk, pineTrunk, turnip, turnipSeedBag, wateringCan, watermelon, watermelonSeedBag, WatermelonSlice, whoolBrown, WhoolGray, WhoolWhite, WhoolYellow, zucchini, zucchiniSeedBag
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
