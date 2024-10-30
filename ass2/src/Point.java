// 329233472 yasmin haddad

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * describes a point in the plane.
 */
public class Point {
    // instance variables
    private double x;
    private double y;

    /**
     * constructor -- create a point from x and y values.
     * @param x x value of the point
     * @param y y value of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * calculates the distance between this point and another point.
     * @param other the other point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.x, 2)
                + Math.pow(this.y - other.y, 2));
    }

    // equals -- return true is the points are equal, false otherwise

    /**
     * checks if this point is equal to another point.
     * @param other the other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return CommonUtilities.doubleEquals(this.x, other.x)
                && CommonUtilities.doubleEquals(this.y, other.y);
    }

    /**
     * returns the x value of this point.
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * returns the y value of this point.
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * sets the x value of this point.
     * @param newX the new x value
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * sets the y value of this point.
     * @param newY the new y value
     */
    public void setY(double newY) {
        this.y = newY;
    }

    /**
     * generates a random point.
     * @param xBound the upper bound of the x value
     * @param yBound the upper bound of the y value
     * @return a random point
     */
    public static Point getRandomPoint(int xBound, int yBound) {
        Random rand = new Random();
        return new Point(rand.nextDouble() * xBound,
                rand.nextDouble() * yBound);
    }

    /**
     * generates a random point.
     * @param xBound the upper bound of the x value
     * @param yBound the upper bound of the y value
     * @param r the radius of the ball using this
     * @return a random point with a distance of at least r from the edges
     */
    public static Point getRandomPoint(int xBound, int yBound, int r) {
        Random rand = new Random();
        return new Point(rand.nextDouble() * (xBound - 2 * r) + r,
                rand.nextDouble() * (yBound - 2 * r) + r);
    }

    /**
     * draws a dot on the given DrawSurface.
     * @param d the surface to draw on
     * @param c the color of the dot
     * @param radius the radius of the dot
     */
    public void drawDot(DrawSurface d, Color c, int radius) {
        d.setColor(c);
        d.fillCircle((int) this.x, (int) this.y, radius);
    }
}