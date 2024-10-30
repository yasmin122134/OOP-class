// 329233472 yasmin haddad


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
        double dx = speed * Math.cos(angle);
        double dy = speed * Math.sin(angle);
        return new Velocity(dx, dy);
    }

    /**
     * applies the velocity to a point and moves it inside board.
     * @param p the point to move
     * @return the new point after the velocity is applied
     */
    public Point applyToPoint(Point p) {
        return applyToPoint(p, 0);
    }

    /**
     * applies the velocity to a point and moves it inside board.
     * @param p the point to move
     * @param r the radius of the ball
     * @return the new point after the velocity is applied
     */
    public Point applyToPoint(Point p, int r) {
        Rectangle[] board = {new Rectangle(new Point(0, 0),
                Board.BOUND_X, Board.BOUND_Y)};

        return applyToPointInRects(p, r, board);
    }


    /**
     * applies the velocity to a point and moves it inside rects.
     * @param p the point to move
     * @param r its radius
     * @param rects the rectangles to move in
     * @return the new point after the velocity is applied
     */
    public Point applyToPointInRects(Point p, int r, Rectangle[] rects) {
        double nextX = p.getX() + this.dx, nextY = p.getY() + this.dy, x, y;
        for (Rectangle rect : rects) {
            if (rect.compIn(p, r)) {
                if (!rect.compIn(new Point(nextX, p.getY()), r)) {
                    this.dx = -this.dx;
                }
                if (!rect.compIn(new Point(p.getX(), nextY), r)) {
                    this.dy = -this.dy;
                }
            }
        }
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
    /**
     * applies the velocity to a point and moves it outside rects.
     * @param p the point to move
     * @param r its radius
     * @param rects the rectangles to move in
     * @return the new point after the velocity is applied
     */
    public Point applyToPointOutRects(Point p, int r, Rectangle[] rects) {
        double nextX = p.getX() + this.dx, nextY = p.getY() + this.dy;
        for (Rectangle rect : rects) {
            if (rect.compOut(p, r)) {
                if (!rect.compOut(new Point(nextX, p.getY()), r)) {
                    this.dx = -this.dx;
                }
                if (!rect.compOut(new Point(p.getX(), nextY), r)) {
                    this.dy = -this.dy;
                }
            }
        }
        return applyToPoint(p, r);
    }
}