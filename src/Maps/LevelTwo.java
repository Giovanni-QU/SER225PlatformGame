package Maps;

import Level.Map;

import Tilesets.LevelTwoTileset;

import Utils.Point;

public class LevelTwo extends Map {

    public LevelTwo() {

        super("level_two.txt", new LevelTwoTileset(), new Point(1, 11));


    }
}
