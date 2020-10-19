package Maps;

import Level.Map;
import Tilesets.CommonTileset;
import Tilesets.TestTileset;
import Utils.Point;

public class LevelTwo extends Map {

    public LevelTwo() {
        super("level_one.txt", new TestTileset(), new Point(1, 11));
    }

}
