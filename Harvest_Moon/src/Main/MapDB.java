package Main;

import java.util.ArrayList;

public class MapDB {
    static public ArrayList<MapData> mapList = new ArrayList<>(); 

    public MapDB() {
        mapList.add(new MapData("Home", "/assets/map/Map_Home_Map", "/assets/map/Map_Home_TileData", "/assets/tile/HomeTiles/")); // Add the Home map
        mapList.add(new MapData("Forest", "/assets/map/Map_Forest_Map", "/assets/map/Map_Home_TileData", "/assets/tile/HomeTiles/")); // Add the Forest map
        mapList.add(new MapData("Town Hall", "/assets/map/mapTownHall", "/assets/map/TileData_TownHall", "/assets/tile/TownHallTiles/"));
        
    }
}
