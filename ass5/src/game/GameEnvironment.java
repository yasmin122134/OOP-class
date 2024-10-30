// 329233472 yasmin haddad

package game;

import abstractShapes.Line;
import abstractShapes.Point;
import objects.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * describes a game environment.
 */
public class GameEnvironment {
    private List<Collidable> collidables = new ArrayList<>();




    // add the given collidable to the environment.

    /**
     * adds a collidable to the game environment.
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * removes a collidable from the game environment.
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    // Assume an object moving from line.start() to line.end().
    // If this object will not collide with any of the collidables
    // in this collection, return null. Else, return the information
    // about the closest collision that is going to occur.

    /**
     * <p> gets the closest collision of a trajectory with the collidables.
     * in the game environment. </p>
     * @param trajectory the trajectory to check
     * @return the closest collision of a trajectory with the collidables.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<Point> collisionPoints = new ArrayList<Point>();
        List<Collidable> collidedWith = new ArrayList<Collidable>();
        for (Collidable coll : this.collidables) {
            List<Point> points = coll.getCollisionRectangle().intersectionPoints(trajectory);
            for (Point p : points) {
                if (p != null) {
                    collisionPoints.add(p);
                    collidedWith.add(coll);
                }
            }
        }
        if (collisionPoints.isEmpty()) {
            return null;
        }
        int index = trajectory.closestPointIndToStartPoint(collisionPoints);
        Point p = collisionPoints.get(index);
        Collidable c = collidedWith.get(index);
        return new CollisionInfo(p, c);
    }

    /**
     * gets the collidables in the game environment.
     * @return the collidables in the game environment
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }
}