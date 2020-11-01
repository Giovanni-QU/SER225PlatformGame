package Players;

import Builders.FrameBuilder;
import Engine.ImageLoader;
import GameObject.Frame;
import GameObject.SpriteSheet;
import Level.Enemy;
import Level.MapEntityStatus;
import Level.Player;
import Utils.Direction;
import Utils.Point;
import Utils.Stopwatch;

import java.util.HashMap;

public class Hairball extends Enemy {
    private float movementSpeed;
    private Stopwatch existenceTimer = new Stopwatch();

    public Hairball(Point location, float movementSpeed, int existenceTime) {
        super(location.x, location.y, new SpriteSheet(ImageLoader.load("Hairball.png"), 7, 7), "DEFAULT");
        this.movementSpeed = movementSpeed;
        existenceTimer.setWaitTime(existenceTime);
        isRespawnable = false;

        initialize();
    }

    @Override
    public void update(Player player) {
        // if timer is up, set map entity status to REMOVED
        // the camera class will see this next frame and remove it permanently from the map
        if (existenceTimer.isTimeUp()) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        } else {
            // move fireball forward
            moveXHandleCollision(movementSpeed);
            super.update(player);
        }
    }

    @Override
    public void onEndCollisionCheckX(boolean hasCollided, Direction direction) {
        // if fireball collides with anything solid on the x axis, it is removed
        if (hasCollided) {
            this.mapEntityStatus = MapEntityStatus.REMOVED;
        }
    }

    @Override
    public void touchedPlayer(Player player) {
//        // if fireball touches player, it disappears
//        super.touchedPlayer(player);
//        this.mapEntityStatus = MapEntityStatus.REMOVED;

    }

    @Override
    public void touchedEnemy(Enemy enemy) {
        super.hurtEnemy(enemy);
        this.mapEntityStatus = MapEntityStatus.REMOVED;
    }

    @Override
    public HashMap<String, Frame[]> getAnimations(SpriteSheet spriteSheet) {
        return new HashMap<String, Frame[]>() {{
            put("DEFAULT", new Frame[]{
                    new FrameBuilder(spriteSheet.getSprite(0, 0), 0)
                            .withScale(3)
                            .withBounds(1, 1, 5, 5)
                            .build()
            });
        }};
    }
}
