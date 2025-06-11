package object;

import Main.GamePanel;
import entity.Entity;

public class OBJ_Tree extends Entity {

    String pathtrunk = "/assets/object/tree/trunk.png"; // Path to the tree trunk image
    int treeX, treeY;
    int trunkX, trunkY;

    public OBJ_Tree(GamePanel gp, int x, int y) {
        super(gp);
        collision = true;
        treeX = x;
        treeY = y;
        trunkX = x + gp.tileSize * 2 - 24;
        trunkY = y + gp.tileSize * 3;

        
        this.gp = gp;
        name = "Tree";
        path = "/assets/object/tree/tree.png"; // Path to the tree image

        this.worldX = x;
        this.worldY = y;

        this.width = 48 * 5;
        this.height = 48 * 5; 


        reset();
        isObj = true; 
    }
    
    public void chop(){
        isChopped = true; // Set the flag to indicate the tree has been chopped

        image = gp.setImage(pathtrunk);

        this.worldX = trunkX;
        this.worldY = trunkY;

        
        this.width = 48 * 2;
        this.height = 48 * 2; 

        solidArea.x = 25;
        solidArea.y = 48;
        solidArea.width = 48;
        solidArea.height = 48;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;


    }

    public void reset(){
        isChopped = false; // Reset the flag to indicate the tree is not chopped

        this.worldX = treeX;
        this.worldY = treeY;

        this.width = 48 * 5;
        this.height = 48 * 5; 

        image = gp.setImage(path);
        solidArea.x = 100;
        solidArea.y = gp.tileSize * 4;
        solidArea.width = 48;
        solidArea.height = 48;
        this.solidAreaDefaultX = this.solidArea.x;
        this.solidAreaDefaultY = this.solidArea.y;

    }
    
}