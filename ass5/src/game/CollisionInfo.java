// 329233472 yasmin haddad

package game;

import abstractShapes.Point;
import objects.Collidable;

/**
 * describes a collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * constructor -- create a collision from a point and a collidable object.
     * @param collisionPoint the point of collision
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    // the point at which the collision occurs.

    /**
     * gets the point of collision.
     * @return the point of collision
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    // the collidable object involved in the collision.

    /**
     * gets the collidable object involved in the collision.
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collisionObject;
    }
}