package entity;

    import Main.GamePanel;
    import animation.Animation;

    public class Animal extends Entity{
        GamePanel gp;
        public int age = 0; // Age of the animal in days
        String hasil;
        boolean readyToHarverst = true;
    int xStart, yStart;
    int xEnd, yEnd;
    boolean walkToDestination = true;
    boolean walkBack = false;

    public Animal(GamePanel gp,String name ,int x, int y) {
        super(gp);
        this.gp = gp;
        this.worldX = x;
        this.worldY = y;
        this.name = name;
        this.speed = 1;
        xStart = x;
        yStart = y;
        spriteCounterMax = 30;
        xEnd = x ; // Example end position, adjust as needed
        yEnd = y + 144; // Example end position, adjust as needed
        
        if(name.equalsIgnoreCase("cow")){
            
            width = 144;
            height = 144;
            this.solidArea.width = 40; // Adjust solid area for animal
            this.solidArea.height = 110; // Adjust solid area for animal
            
            this.solidArea.x = 50; // Adjust solid area for animal
            this.solidArea.y = 28; // Adjust solid area for animal
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }
        
        
        if(name.equalsIgnoreCase("brownsheep")||name.equalsIgnoreCase("graysheep")
        ||name.equalsIgnoreCase("whitesheep")||name.equalsIgnoreCase("yellowsheep")){
            
            width = 96;
            height = 96;
            this.solidArea.width = 45; // Adjust solid area for animal
            this.solidArea.height = 60; // Adjust solid area for animal
            
            this.solidArea.x = 25; // Adjust solid area for animal
            this.solidArea.y = 40; // Adjust solid area for animal
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }
        
        if(name.equalsIgnoreCase("chicken")){
            
            width = 48;
            height = 48;
            this.solidArea.width = 24; // Adjust solid area for animal
            this.solidArea.height = 24; // Adjust solid area for animal
            
            this.solidArea.x = 12; // Adjust solid area for animal
            this.solidArea.y = 12; // Adjust solid area for animal
            this.solidAreaDefaultX = this.solidArea.x;
            this.solidAreaDefaultY = this.solidArea.y;
        }


        walk = new Animation("walk", 6, "/assets/animal/" + name + "/WALK/",false,true);
        animationList.add(walk);
        currentAnimationIndex = 0; // default walk

        direction = "down";
        setAnimation("walk");
        speed = 1;

        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

        isAnimal = true;
    }


    @Override
    public void reset()
    {
        readyToHarverst = true;
    }

    @Override
    public void interact() {
        if(readyToHarverst)
        {
            if(name.equalsIgnoreCase("cow")){
                hasil = "milkBucket";
                readyToHarverst = false;
                gp.player.inventory.add(ItemList.getItem(hasil));
                System.out.println("You got " + hasil + " from " + name);
            }

            else if(name.equalsIgnoreCase("chicken")){
                hasil = "egg";
                readyToHarverst = false;
                gp.player.inventory.add(ItemList.getItem(hasil));
                System.out.println("You got " + hasil + " from " + name);
            }
            else if(name.equalsIgnoreCase("brownsheep") && gp.player.currentTools.equalsIgnoreCase("shear"))
            {
                hasil = "whoolBrown";
                readyToHarverst = false;
                gp.player.inventory.add(ItemList.getItem(hasil));
                System.out.println("You got " + hasil + " from " + name);
            }
            else if(name.equalsIgnoreCase("graysheep") && gp.player.currentTools.equalsIgnoreCase("shear"))
            {
                hasil = "whoolGray";
                readyToHarverst = false;
                gp.player.inventory.add(ItemList.getItem(hasil));
                System.out.println("You got " + hasil + " from " + name);
            }
            else if(name.equalsIgnoreCase("whitesheep") && gp.player.currentTools.equalsIgnoreCase("shear"))
            {
                hasil = "whoolWhite";
                readyToHarverst = false;
                gp.player.inventory.add(ItemList.getItem(hasil));
                System.out.println("You got " + hasil + " from " + name);
            }
            else if(name.equalsIgnoreCase("yellowsheep") && gp.player.currentTools.equalsIgnoreCase("shears"))
            {
                hasil = "whoolYellow";
                readyToHarverst = false;
                gp.player.inventory.add(ItemList.getItem(hasil));
                System.out.println("You got " + hasil + " from " + name);
            }
        }
        else
        {
            System.out.println("You can't harvest " + name + " right now.");
        }
    
}
}
