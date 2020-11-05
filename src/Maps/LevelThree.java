package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;
import Tilesets.MasterMapTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

public class LevelThree extends Map {
    public LevelThree() {
        super("level_three.txt", new MasterMapTileset(), new Point(1, 44)); //change this point location to change were you start on the map
    }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        //TODO: add enemies here. Make sure there are enemies all over the map or a bug with the powerups will happen
        //EXAMPLE CODE bug enemy: enemies.add(new BugEnemy(getPositionByTileIndex(8, 18), Direction.LEFT));
        //EXAMPLE CODE dinosaur enemy: enemies.add(new DinosaurEnemy(getPositionByTileIndex(21, 12).addY(2), getPositionByTileIndex(24, 12).addY(2), Direction.RIGHT));

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();
        //TODO: add moving plateforms here. You will need to add some for the jumping part between the two trees.
        /* EXAMPLE CODE moving plateform
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(34, 21),  //only need to change this starting point
                getPositionByTileIndex(35, 21),  // and this end point
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
         */

        return enhancedMapTiles;
    }
}
