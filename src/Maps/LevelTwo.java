package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Utils.Point;

public class LevelTwo extends Map {

    public LevelTwo() {
        super("level_one.txt", new CommonTileset(), new Point(1, 11));
    }
}
