// 329233472 yasmin haddad

package objects;

import abstractShapes.Point;
import abstractShapes.Rectangle;
import utils.Velocity;

import java.awt.Color;


/**
 * describes the area of death for the ball.
 */
public class DeathRegion extends Block {
    /**
     * constructor -- create a block from a rectangle and a color.
     *
     * @param rect  the rectangle of the block
     * @param color the blocks color
     */
    public DeathRegion(Rectangle rect, Color color) {
        super(rect, color);
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        return new Velocity(0, 0);
    }

//    @Override
//    public void notifyHit(objects.Ball hitter) {
//        super.notifyHit(hitter);
//    }
}
