// 329233472 yasmin haddad

package utils;


import abstractShapes.Line;
import abstractShapes.Point;
import game.CollisionInfo;
import game.GameEnvironment;
import objects.Ball;
import objects.Collidable;

/**
 * describes a velocity in the plane.
 */
public class Velocity {
    // instance variables
    private double dx = 0;
    private double dy = 0;

    // constructor
    /**
     * constructor -- create a velocity from dx and dy values.
     * @param dx the dx value of the velocity
     * @param dy the dy value of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * creates a velocity from angle and speed.
     * @param angle the angle of the velocity
     * @param speed the speed of the velocity
     * @return the velocity based on the angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = angle % 360;
        int xMultiplier = 1, yMultiplier = 1;
        if (angle < 90 || angle > 270) {
            yMultiplier = -1;
        }
        if (angle > 180 && angle <= 360 || angle == 0) {
            yMultiplier = -1;
        }
        angle = Math.toRadians(angle);
        double dx = speed * Math.sin(angle) * xMultiplier;
        double dy = speed * Math.cos(angle) * yMultiplier;
        return new Velocity(dx, dy);
    }

    /**
     * applies the velocity to a point and moves it inside board.
     * @param p the point to move
     * @return the new point after the velocity is applied
     */
    public Point applyToPoint(Point p) {
//        return applyToPoint(p, 0);
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * gets dx.
     * @return the dx value of the velocity
     */
    public double getDx() {
        return dx;
    }

    /**
     * gets dy.
     * @return the dy value of the velocity
     */
    public double getDy() {
        return dy;
    }


    /**
     * applies the velocity to a point and moves it inside board.
     * @param b the ball to move
     * @param r the radius of the ball
     * @param gameEnvironment the game environment of the ball
     * @return the new point after the velocity is applied
     */
    public Point applyToPoint(Ball b, int r, GameEnvironment gameEnvironment) {
        Point p = b.getCenter();
        int endX = r * (int) Math.signum(this.dx) + (int) this.dx;
        int endY = r * (int) Math.signum(this.dy) + (int) this.dy;
        Line trajectory = new Line(p, new Point(p.getX() + endX,
                p.getY() + endY));
        CollisionInfo collisionInfo = gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            return new Point(p.getX() + this.dx, p.getY() + this.dy);
        }
        Point collisionPoint = collisionInfo.collisionPoint();

        Collidable collidable = collisionInfo.collisionObject();
        Velocity newVelocity = collidable.hit(b, collisionPoint, this);

        this.dx = newVelocity.getDx();
        this.dy = newVelocity.getDy();
        return p;
    }

    /**
     * sets dx.
     * @param dx the dx value of the velocity
     */
    public void setDx(double dx) {
        this.dx = dx;
    }
    /**
     * sets dy.
     * @param dy the dy value of the velocity
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
}