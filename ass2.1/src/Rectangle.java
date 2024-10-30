// 329233472 yasmin haddad

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * describes a rectangle in the plane.
 */
public class Rectangle {
    private Point leftUpPoint;
    private int width;
    private int height;
    private int minX, maxX, minY, maxY;

    Rectangle(Point leftUpPoint, int width, int height) {
        this.leftUpPoint = leftUpPoint;
        this.width = width;
        this.height = height;
        this.minX = (int) leftUpPoint.getX();
        this.maxX = (int) leftUpPoint.getX() + width;
        this.minY = (int) leftUpPoint.getY();
        this.maxY = (int) leftUpPoint.getY() + height;

    }

    /**
     * draws the rectangle on a given surface.
     * @param d the surface to draw on
     * @param c the color to draw with
     */
    public void drawOn(DrawSurface d, Color c) {
        d.setColor(c);
        d.fillRectangle((int) this.leftUpPoint.getX(),
                (int) this.leftUpPoint.getY(), this.width, this.height);
    }

    /**
     * gets the width of the rectangle.
     * @return the width of the rectangle
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * gets the height of the rectangle.
     * @return the height of the rectangle
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * gets the width of the rectangle.
     * @return the width of the rectangle
     */
    public int getMinX() {
        return minX;
    }
    /**
     * gets the height of the rectangle.
     * @return the height of the rectangle
     */
    public int getMinY() {
        return minY;
    }

    /**
     * gets the width of the rectangle.
     * @return the width of the rectangle
     */
    public int getMinWidthHeight() {
        return Math.min(this.height, this.width);
    }
    /**
     * checks if a point is completely outside the rectangle.
     * @param p the point to check
     * @param r the radius of the ball
     * @return true if the point is outside the rectangle, false otherwise
     */
    public boolean compOut(Point p, int r) {
        return (p.getX() - r >= this.maxX || p.getX() + r <= this.minX)
                || (p.getY() - r >= this.maxY || p.getY() + r <= this.minY);
    }

    /**
     * checks if a point is completely in the rectangle.
     * @param p the point to check
     * @param r the radius of the ball
     * @return true if the point is in the rectangle, false otherwise
     */
    public boolean compIn(Point p, int r) {
        return p.getX() + r <= this.maxX && p.getX() - r >= this.minX
                && p.getY() + r <= this.maxY && p.getY() - r >= this.minY;
    }
}

