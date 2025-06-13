package Main;


import java.util.ArrayList;

import entity.Entity;

public class MapData{

    public String name;
    public String path;
    public String tileDataPath;
    public String tilePath;
    public int mapNum;
    static int totalMaps = 0;
    public ArrayList<Entity> obj = new ArrayList<>(); // List of objects in the game
    public ArrayList<Entity> farmObj = new ArrayList<>(); // List of objects in the game
    public ArrayList<Entity> npcs = new ArrayList<>(); // List of NPCs in the game
    public ArrayList<Entity> entityList = new ArrayList<>(); // List of all entities in the game



    public MapData  (String name, String path, String tileDataPath, String tilePath) {
        mapNum = totalMaps;
        totalMaps++;
        this.name = name;
        this.path = path;
        this.tileDataPath = tileDataPath;
        this.tilePath = tilePath;
        

    }
    
}

