package  ai;

import Main.GamePanel;
import entity.Entity;
import java.util.ArrayList;

public class PathFinder {

    GamePanel gp;
    Node[][] node;
    ArrayList<Node> openList = new ArrayList<>();
    public ArrayList <Node> pathList = new ArrayList<>();
    Node startNode,goalNode,currentNode;
    boolean goalReached = false;
    int step = 0;

    public PathFinder(GamePanel gp) {
        this.gp = gp;
        instanstiateNodes(); //should be called explicitly after construction
    }

    public void instanstiateNodes() {
        node = new Node[gp.maxWorldCol][gp.maxWorldRow];

        int row = 0;
        int col = 0;

        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[col][row] = new Node(row, col);

            col++;
            if(col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void resetNode() {
        int row = 0;
        int col = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            node[col][row].open = false; 
            node[col][row].checked= false;
            node[col][row].solid = false;

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
        //reset other settings
        openList.clear();
        pathList.clear();
        goalReached = false;
        step = 0;
    }

    public void setNodes (int startCol, int startRow, int goalCol, int goalRow, Entity entity) {
        
        resetNode();

        //set start and goal
        startNode = node[startCol][startRow];
        currentNode = startNode;
        goalNode = node[goalCol][goalRow];
        openList.add(currentNode);

        int col = 0;
        int row = 0;

        while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
            //set solid node
            //check tiles
            int tileNum = gp.tileM.mapTileNum[col][row];
            if(gp.tileM.tile.get(tileNum).collision == true)
            {
                node[col][row].solid = true; // Set the node as solid if the tile has collision
            } 

            //set cost
            getCost(node[col][row]);

            col++;
            if (col == gp.maxWorldCol) {
                col = 0;
                row++;
            }
        }
    }

    public void getCost(Node node) {
        //gcost
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance; // Manhattan distance for gCost
        //hcost
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        //fcost
        node.fCost = node.gCost + node.hCost;
    }

    public boolean search()
    {
        while(!goalReached && step < 500) {
            
            int col = currentNode.col;
            int row = currentNode.row;

            //check the current node
            currentNode.checked = true;
            openList.remove(currentNode);

            //open the up node 
            if(row - 1 >= 0) {
                openNode(node[col][row-1]);
            }

            if(row + 1 >= 0) {
                openNode(node[col][row+1]);
            }

            if(col - 1 >= 0) {
                openNode(node[col-1][row]);
            }

            if(col - 1 >= 0) {
                openNode(node[col+1][row]);
            }

            //find the best node
            int bestNodeIndex = 0;
            int bestNodefCost = 999;

            for(int i = 0; i < openList.size(); i++)
            {

                // check if this node f cost is better 
                if(openList.get(i).fCost < bestNodefCost)
                {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                }

                // check if equal
                else if(openList.get(i).fCost == bestNodefCost)
                {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost)
                    {
                        bestNodeIndex = i;
                    }
                }
            }
            if(openList.isEmpty())
            {
                break;
            }

            //after the loop 
            currentNode = openList.get(bestNodeIndex);

            if(currentNode == goalNode)
            {
                goalReached = true;
                trackThePath();
            }
            step++;
        }
        return goalReached;
    }

    public void openNode(Node node)
    {

        if(!node.open && !node.checked && !node.solid) {
            node.open = true; // Mark the node as open
            node.parent = currentNode;
            openList.add(node);
        }
    }

    public void trackThePath()
    {
        Node current = goalNode;

        while(current != startNode)
        {
            pathList.add(0,current);
            current = current.parent;
        }
    }
    
}