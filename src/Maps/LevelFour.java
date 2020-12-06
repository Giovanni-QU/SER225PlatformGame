package Maps;

import Enemies.BugEnemy;
import Enemies.DinosaurEnemy;
import Engine.ImageLoader;
import EnhancedMapTiles.EndLevelBox;
import EnhancedMapTiles.HorizontalMovingPlatform;
import GameObject.Rectangle;
import Level.*;

import NPCs.Human;
import NPCs.Walrus;
import Tilesets.LevelTwoTileset;

import Tilesets.MasterMapTileset;
import Utils.Direction;
import Utils.Point;

import java.util.ArrayList;

public class LevelFour extends Map {

    public LevelFour() { super("level_four.txt", new MasterMapTileset(), new Point(2,18)); }

    @Override
    public ArrayList<Enemy> loadEnemies() {
        ArrayList<Enemy> enemies = new ArrayList<>();
        // bug enemies
        enemies.add(new BugEnemy(getPositionByTileIndex(10, 18), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(42, 11), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(62, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(63, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(65, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(66, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(68, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(69, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(71, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(72, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(89, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(91, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(93, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(104, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(106, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(108, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(110, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(112, 23), Direction.LEFT));
        enemies.add(new BugEnemy(getPositionByTileIndex(114, 23), Direction.LEFT));

        enemies.add(new DinosaurEnemy(getPositionByTileIndex(14, 18).addY(2), getPositionByTileIndex(18, 18).addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(19, 18).addY(2), getPositionByTileIndex(24, 18).addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(30, 14).addY(2), getPositionByTileIndex(34, 14).addY(2), Direction.RIGHT));
        enemies.add(new DinosaurEnemy(getPositionByTileIndex(45, 14).addY(2), getPositionByTileIndex(52, 14).addY(2), Direction.RIGHT));

        return enemies;
    }

    @Override
    public ArrayList<EnhancedMapTile> loadEnhancedMapTiles() {
        ArrayList<EnhancedMapTile> enhancedMapTiles = new ArrayList<>();

        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(89, 15),
                getPositionByTileIndex(93, 15),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));
        enhancedMapTiles.add(new HorizontalMovingPlatform(
                ImageLoader.load("GreenPlatform.png"),
                getPositionByTileIndex(107, 15),
                getPositionByTileIndex(114, 15),
                TileType.JUMP_THROUGH_PLATFORM,
                3,
                new Rectangle(0, 6,16,4),
                Direction.RIGHT
        ));

        enhancedMapTiles.add(new EndLevelBox(
                getPositionByTileIndex(129, 13)
        ));

        return enhancedMapTiles;
    }

    @Override
    public ArrayList<NPC> loadNPCs() {
        ArrayList<NPC> npcs = new ArrayList<>();

        npcs.add(new Human(getPositionByTileIndex(125, 14), this));

        return npcs;
    }
}
