// 329233472 yasmin haddad

// imports
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;


/**
 * describes a ball in the plane.
 */
public class Ball {
    // instance variables
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);

    /**
     * constructor -- create a ball from a center point, radius and color.
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;

    }

    /**
     * constructor -- create a ball from x and y values, radius and color.
     * @param x the x value of the center point of the ball
     * @param y the y value of the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     */
    public Ball(double x, double y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    // accessors

    /**
     * gets x of center.
     * @return the x value of the center point of the ball
     */
    public int getX() {
        return (int) this.center.getX();
    }
    /**
     * gets y of center.
     * @return the y value of the center point of the ball
     */
    public int getY() {
        return (int) this.center.getY();
    }
    /**
     * gets radius.
     * @return the radius of the ball
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * gets color.
     * @return the color of the ball
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * sets velocity.
     * @param v the velocity to set
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * sets velocity.
     * @param dx the dx of the velocity to set
     * @param dy the dy of the velocity to set
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);

    }

    /**
     * sets the radius.
     * @param radius the radius to set
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }
    /**
     * gets velocity.
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * moves the ball one step.
     */
    public void moveOneStep() {
//        this.center = this.getVelocity().applyToPoint(this.center);
        this.center = this.velocity.applyToPoint(this.center, this.radius);

    }

    /**
     * moves the ball one step inside a rect.
     * @param rects the rectangles to move in
     */
    public void moveOneStepIn(Rectangle[] rects) {
        this.center = this.velocity.applyToPointInRects(this.center,
                this.radius, rects);
    }

    /**
     * moves the ball one step outside a rect.
     * @param rects the rectangles to move out of
     */
    public void moveOneStepOut(Rectangle[] rects) {
        this.center = this.velocity.applyToPointOutRects(this.center,
                this.radius, rects);
    }
    /**
     * draw the ball on the given DrawSurface.
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        surface.fillCircle(getX(), getY(), getSize());
    }

    /**
     * draw the ball on the given DrawSurface with a given color.
     * @param surface the surface to draw on
     * @param c the color to draw with
     */
    public void drawOn(DrawSurface surface, Color c) {
        surface.setColor(c);
        drawOn(surface);
    }

    /**
     * generates a random ball.
     *
     * @param boundX the upper bound of the x value
     * @param boundY the upper bound of the y value
     * @param c      the color of the ball
     * @param maxR   the maximum radius
     * @param minR   the minimum radius
     * @return a random ball
     */
    public static Ball getRandomBall(int boundX, int boundY,
                                     Color c, int maxR, int minR) {
        Random rand = new Random();
        return new Ball(Point.getRandomPoint(boundX, boundY),
                (int) (rand.nextDouble() * (maxR - minR) + minR), c);
    }

    /**
     * generates a random ball.
     * @param boundX the upper bound of the x value
     * @param boundY the upper bound of the y value
     * @param r the radius of the ball
     * @return a random ball completely inside the bounds
     */
    public static Ball getRandomBall(int boundX, int boundY, int r) {
        Random rand = new Random();
        return new Ball(Point.getRandomPoint(boundX, boundY, r),
                rand.nextInt(Math.min(boundX, boundY) / 2),
                CommonUtilities.generateRandomColor());
    }
}