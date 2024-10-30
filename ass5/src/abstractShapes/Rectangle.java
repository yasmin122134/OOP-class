// 329233472 yasmin haddad
package abstractShapes;

import biuoop.DrawSurface;
import utils.BoardInfo;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * describes a rectangle in the plane.
 */
public class Rectangle {
    private Point leftUpPoint;
    private double width;
    private double height;
    private double minX, maxX, minY, maxY;
    private Line[] lines = new Line[4];


    /**
     * constructor -- create a rectangle from a point, width and height.
     * @param upperLeft the upper left point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.leftUpPoint = upperLeft;
        this.width = width;
        this.height = height;
        this.minX = upperLeft.getX();
        this.maxX = upperLeft.getX() + width;
        this.minY = upperLeft.getY();
        this.maxY = upperLeft.getY() + height;
        this.lines[0] = new Line(upperLeft, new Point(maxX, minY));
        this.lines[1] = new Line(new Point(maxX, minY), new Point(maxX, maxY));
        this.lines[2] = new Line(new Point(maxX, maxY), new Point(minX, maxY));
        this.lines[3] = new Line(new Point(minX, maxY), upperLeft);
    }

    /**
     * draws the rectangle on a given surface.
     * @param d the surface to draw on
     * @param c the color to draw with
     */
    public void drawOn(DrawSurface d, Color c) {
        d.setColor(c);
        d.fillRectangle((int) this.leftUpPoint.getX(),
                (int) this.leftUpPoint.getY(), (int) this.width,
                (int) this.height);
    }

    /**
     * <p>Return a List of intersection points with the specified line.
     * it never adds null to list. </p>
     * @param line the line to check intersection with
     * @return a List of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> retList = new ArrayList<Point>();
        for (Line l : this.lines) {

            if (line.isIntersecting(l)) {
                Point intersection = l.intersectionWith(line);
                if (intersection != null) {
                    retList.add(intersection);
                } else {
                    if (l.start().distance(line.start()) < l.end().distance(line.start())) {
                        retList.add(l.start());
                    } else {
                        retList.add(l.end());
                    }
                }
            }
        }
        return retList;
    }

    /**
     * gets the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getMaxX() {
        return maxX;
    }

    /**
     * gets the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getMaxY() {
        return maxY;
    }

    /**
     * gets the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getMinX() {
        return minX;
    }
    /**
     * gets the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getMinY() {
        return minY;
    }

    /**
     * gets the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * gets the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * gets the upper left point of the rectangle.
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.leftUpPoint;
    }

    /**
     * gets the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getMinWidthHeight() {
        return Math.min(this.height, this.width);
    }
    /**
     * get line array.
     * @return the lines array
     */
    public Line[] getLines() {
        return lines;
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

    /**
     * checks if a point collides with the rectangle.
     * @param center the center of the ball
     * @param step the radius of the ball
     * @return true if the ball collides with the rectangle, false otherwise
     */
    public boolean collideWith(Point center, int step) {
        boolean flag = false;
        for (Line line : this.lines) {
            if (line.collideWith(center, step)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * moves the rectangle to the left.
     * @param speed the speed to move with
     * @return the new rectangle
     */
    public Rectangle moveLeft(double speed) {
        Rectangle newRect = new Rectangle(new Point(this.leftUpPoint.getX() - speed, this.leftUpPoint.getY()),
                this.width, this.height);
        if (newRect.getMinX() < 0 - this.width / 2) {
            newRect = new Rectangle(new Point(BoardInfo.BOUND_X - this.width, this.leftUpPoint.getY()),
                    this.width, this.height);
        }
        return newRect;
    }

    /**
     * moves the rectangle to the right.
     * @param speed the speed to move with
     * @return the new rectangle
     */
    public Rectangle moveRight(double speed) {
        Rectangle newRect = new Rectangle(new Point(this.leftUpPoint.getX() + speed, this.leftUpPoint.getY()),
                this.width, this.height);
        if (newRect.getMaxX() > BoardInfo.BOUND_X + this.width / 2) {
            newRect = new Rectangle(new Point(0, this.leftUpPoint.getY()),
                    this.width, this.height);
        }
        return newRect;
    }
}

