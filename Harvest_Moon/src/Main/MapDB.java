package Main;

import java.util.ArrayList;

public class MapDB {
    static public ArrayList<MapData> mapList = new ArrayList<>(); 

    public MapDB() {
        mapList.add(new MapData("Home", "/assets/map/Map_Home_Map", "/assets/map/Map_Home_TileData", "/assets/tile/HomeTiles/",0)); // Add the Home map
        mapList.add(new MapData("Forest", "/assets/map/Map_Forest_Map", "/assets/map/Map_Home_TileData", "/assets/tile/HomeTiles/",1)); // Add the Forest map
        mapList.add(new MapData("Town Hall", "/assets/map/Map_TownHall_Map", "/assets/map/Map_TownHall_TileData", "/assets/tile/TownHallTiles/",2));
        mapList.add(new MapData("Inside the House", "/assets/map/Inside_Home_Map", "/assets/map/Inside_Home_TileData", "/assets/tile/Interiors/",3));
        mapList.add(new MapData("Inside the Barn", "/assets/map/Inside_Barn_Map", "/assets/map/Inside_Barn_TileData", "/assets/tile/Interior_Barn/",4));
        mapList.add(new MapData("Inside the Market", "/assets/map/Inside_TownHall_Market_Map", "/assets/map/Inside_TownHall_Market_TileData", "/assets/tile/Interiors_TownHall_Office/",5));
    }
}
