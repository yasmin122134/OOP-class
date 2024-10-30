// 329233472 yasmin haddad

package abstractShapes;

import biuoop.DrawSurface;
import utils.CommonUtilities;

import java.awt.Color;
import java.util.List;

/**
 * describes a line in the plane.
 */
public class Line {
    // instance variables
    private Point start;
    private Point end;
    private double slope = 0;
    private boolean isPerToY = false;


    /**
     * constructor -- create a line from 2 points.
     *
     * @param start the start point of the line
     * @param end  the end point of the line
     */
    public Line(Point start, Point end) {
        createLine(start, end);
    }

    /**
     * constructor -- create a line from x and y values.
     *
     * @param x1 the x value of the start point of the line
     * @param y1 the y value of the start point of the line
     * @param x2 the x value of the end point of the line
     * @param y2 the y value of the end point of the line
     */
    public Line(double x1, double y1, double x2, double y2) {
        createLine(new Point(x1, y1), new Point(x2, y2));
    }

    private void createLine(Point start, Point end) {
        this.start = start;
        this.end = end;
        // if the line is parallel to the y-axis.
        if (CommonUtilities.doubleEquals(start.getX(), end.getX())) {
            isPerToY = true;
        } else if (CommonUtilities.doubleEquals(start.getY(), end.getY())) {
            this.slope = 0;
        } else {
            this.slope = (end.getY() - start.getY())
                    / (end.getX() - start.getX());
        }
    }
    /**
     * calculates the length of this line.
     * @return the length of this line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * calculates the middle point of this line.
     * @return the middle point of this line
     */
    public Point middle() {
        return new Point((this.start.getX() + this.end.getX()) / 2,
                (this.start.getY() + this.end.getY()) / 2);
    }

    /**
     * Returns the start point of the line.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the slope of the line.
     * @return the slope of the line
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * get is parallel to y-axis.
     * @return true if the line is parallel to the y-axis, false otherwise
     */
    public boolean getIsPerToY() {
        return isPerToY;
    }

    /**
     * checks if this line is intersecting with another line.
     * @param other the other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        if (this.slope == other.getSlope() && (this.pointOnLine(other.start()) || other.pointOnLine(this.start))) {
            return true;
        }
        Point intersection = this.intersectionWith(other);
        return intersection != null;
    }

    // Returns true if this 2 lines intersect with this line, false otherwise
    /**
     * checks if this line is intersecting with 2 other lines.
     * @param other1 the first other line
     * @param other2 the second other line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other1, Line other2) {
        return this.isIntersecting(other1) && this.isIntersecting(other2);
    }

    /**
     * calculates the intersection point of this line with another line.
     * @param other the other line
     * @return the intersection point if the lines intersect, null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (this.end.equals(other.start())) {
            return this.end;
        }
        if (this.start.equals(other.end())) {
            return this.start;
        }
        // define the variables
        double x1, y1, x2, y2, x, y;
        double slopeOther = other.getSlope();
        x1 = this.start.getX();
        y1 = this.start.getY();
        x2 = other.start().getX();
        y2 = other.start().getY();
        // if this line is parallel to the y-axis
        if (this.isPerToY && !other.getIsPerToY()) {
            x = x1;
            y = slopeOther * (x - x2) + y2;
        } else if (other.getIsPerToY() && !this.isPerToY) {
            x = x2;
            y = this.slope * (x - x1) + y1;

        } /*if the lines are parallel*/ else
            if (CommonUtilities.doubleEquals(this.slope, slopeOther)) {
            return null;
        } else if (CommonUtilities.doubleEquals(0, this.slope)) {
            x = x2 + (y1 - y2) / slopeOther;
            y = y1;
        } else if (CommonUtilities.doubleEquals(0, slopeOther)) {
            x = x1 + (y2 - y1) / this.slope;
            y = y2;
            } else {
            // calculate the intersection point
            x = (this.slope * x1 - slopeOther * x2 + y2 - y1)
                    / (this.slope - slopeOther);
            y = (this.slope * slopeOther * (x2 - x1)
                    - this.slope * y2 + slopeOther * y1)
                    / (slopeOther - this.slope);
        }
        // check if the intersection point is in the range of both lines
        Point p = new Point(x, y);
        if (pointOnLine(p) && other.pointOnLine(p)) {
            return new Point(x, y);
        }
        return null;
    }

    /**
     * checks if this line is equal to another line.
     * @param other the other line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                ||
                (this.start.equals(other.end) && this.end.equals(other.start));
    }

    /**
     * calculates the distance between this line and a point.
     * @param p the point
     * @return the distance between the line and the point
     */
    public double distance(Point p) {
        if (!this.pointOnLine(p)) {
            return Math.min(this.start.distance(p), this.end.distance(p));
        }
        double distance = Math.abs(this.slope * p.getX() - p.getY()
                + this.start.getY() - this.slope * this.start.getX())
                / Math.sqrt(Math.pow(this.slope, 2) + 1);
        if (this.inRangeX(p.getX())) {
            return distance;
        }
        if (this.inRangeY(p.getY())) {
            // if the line is parallel to the y-axis
            if (this.isPerToY) {
                return Math.abs(this.start.getX() - p.getX());
            }
            return distance;
        }
        return distance;
    }
    /**
     * checks if a point is in the range of this line.
     * @param p the point
     * @return true if the point is in the range of this line, false otherwise
     */
    public boolean pointOnLine(Point p) {
        double x = p.getX(), y = p.getY();
        return inRangeX(x) && inRangeY(y);
    }

    /**
     * checks if a point is in the range of this line.
     * @param x the x value of the point
     * @return true if the point is in the range of this line, false otherwise
     */
    public boolean inRangeX(double x) {
        return ((x >= this.start.getX() && x <= this.end.getX()) || (x >= this.end.getX() && x <= this.start.getX()));
    }

    /**
     * checks if a point is in the range of this line.
     * @param y the y value of the point
     * @return true if the point is in the range of this line, false otherwise
     */
    public boolean inRangeY(double y) {
        return ((y >= this.start.getY() && y <= this.end.getY() || (y >= this.end.getY() && y <= this.start.getY())));
    }

    /**
     * generates a random line.
     * @param xBound the x bound of the line
     * @param yBound the y bound of the line
     * @return a random line
     */
    public static Line getRandomLine(int xBound, int yBound) {
        return new Line(Point.getRandomPoint(xBound, yBound),
                Point.getRandomPoint(xBound, yBound));
    }
    /**
     * draws this line on a given surface.
     * @param d the surface to draw on
     * @param c the color of the line
     */
    public void drawLine(DrawSurface d, Color c) {
        d.setColor(c);
        d.drawLine((int) this.start.getX(), (int) this.start.getY(),
                (int) this.end.getX(), (int) this.end.getY());
    }
    /**
     * draws a line on a given surface.
     * @param d the surface to draw on
     * @param c the color of the line
     * @param l the line to draw
     */
    public static void drawLine(DrawSurface d, Color c, Line l) {
        l.drawLine(d, c);
    }

    /**
     * checks if this line is intersecting with a ball.
     * @param center the center of the ball
     * @param step the step of the ball
     * @return true if the line is intersecting with the ball, false otherwise
     */
    public boolean collideWith(Point center, int step) {
        return this.isIntersecting(new Line(center, new Point(center.getX() + step,
                center.getY() + step)));
    }

    // If this line does not intersect with the rectangle, return null.
    // Otherwise, return the closest intersection point to the
    // start of the line.
    /**
     * finds the closest intersection point to the start of the line.
     * @param rect the rectangle to check intersection with
     * @return the closest intersection point to the start of the line
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        int retIndex = closestPointIndToStartPoint(points);
        return points.get(retIndex);
    }

    /**
     * finds the closest intersection point to the start of the line.
     * @param points the points to check intersection with
     * @return the closest intersection point to the start of the line
     */
    public int closestPointIndToStartPoint(List<Point> points) {
        int retIndex = 0;
        double dist = points.get(0).distance(this.start), tempDist;
        for (int i = 1; i < points.size(); ++i) {
            tempDist = points.get(i).distance(this.start);
            if (tempDist < dist) {
                dist = tempDist;
                retIndex = i;
            }
        }
        return retIndex;
    }

}



