// 329233472 yasmin haddad

package objects;

import abstractShapes.Point;
import abstractShapes.Rectangle;
import utils.Velocity;

/**
 *
 */
public interface Collidable {
    // Return the "collision shape" of the object.

    /**
     * returns the rectangle of the collided object.
     * @return the rectangle of the collided object
     */
    Rectangle getCollisionRectangle();

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).

    /**
     *      * Notify the object that we collided with it at collisionPoint.
     * @param hitter the ball that hit
     * @param collisionPoint the point of collision
     * @param currentVelocity the velocity of the ball before the collision
     * @return the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}