package Maps;

import Level.Map;
import Level.Tileset;
import Tilesets.MasterMapTileset;
import Utils.Point;

public class LevelThree extends Map {
    public LevelThree() {
        super("level_three.txt", new MasterMapTileset(), new Point(1, 11));
    }
}
