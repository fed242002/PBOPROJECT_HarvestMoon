package ai;

public class Node {

    Node parent; // Parent node in the tree
    public int row;
    public int col;
    int gCost;
    int hCost;
    int fCost;
    boolean solid;
    boolean open;
    boolean checked;


    public Node(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
}
