package MapEditor;

import Level.Map;
import Maps.LevelThree;
import Maps.LevelOne;
import Maps.TitleScreenMap;
import Maps.LevelTwo;

import java.util.ArrayList;

public class EditorMaps {
    public static ArrayList<String> getMapNames() {
        return new ArrayList<String>() {{
            add("LevelOne");
            add("TitleScreen");
            add("LevelTwo");
            add("LevelThree");
        }};
    }

    //TODO: Add level 4
    public static Map getMapByName(String mapName) {
        switch(mapName) {
            case "LevelOne":
                return new LevelOne();
            case "TitleScreen":
                return new TitleScreenMap();
            case "LevelTwo":
                return new LevelTwo();
            case "LevelThree":
                return new LevelThree();
            default:
                throw new RuntimeException("Unrecognized map name");
        }
    }
}
