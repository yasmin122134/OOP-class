// 329233472 yasmin haddad
package objects;

// imports
import abstractShapes.Point;
import biuoop.DrawSurface;
import game.Game;
import game.GameEnvironment;
import utils.CommonUtilities;
import utils.Velocity;

import java.awt.Color;
import java.util.Random;


/**
 * describes a ball in the plane.
 */
public class Ball implements Sprite {
    // instance variables
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity = new Velocity(0, 0);
    private GameEnvironment environment;

    /**
     * constructor -- create a ball from a center point, radius and color.
     * @param center the center point of the ball
     * @param r the radius of the ball
     * @param color the color of the ball
     * @param gameEnvironment the game environment of the ball
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.environment = gameEnvironment;
    }
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
     * sets x of center.
     * @param x the x value of the center point to set
     */
    public void setX(double x) {
        this.center.setX(x);
    }

    /**
     * sets y of center.
     * @param y the y value of the center point to set
     */
    public void setY(double y) {
        this.center.setY(y);
    }

    /**
     * gets center.
     * @return the center point of the ball
     */
    public Point getCenter() {
        return this.center;
    }

    /**
     * sets center.
     * @param center the center point to set
     */
    public void setCenter(Point center) {
        this.center = center;
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
     * @param gameEnvironment the game environment of the ball
     */
    public void moveOneStep(GameEnvironment gameEnvironment) {
        this.center = this.velocity.applyToPoint(this, this.radius, gameEnvironment);
    }
    /**
     * draw the ball on the given DrawSurface.
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        drawOn(surface, this.color);
    }

    /**
     * draw the ball on the given DrawSurface with a given color.
     * @param surface the surface to draw on
     * @param c the color to draw with
     */
    public void drawOn(DrawSurface surface, Color c) {
        surface.setColor(c);
    surface.fillCircle(getX(), getY(), this.radius);
    }

    /**
     * generates a random ball.
     * @param boundX the upper bound of the x value
     * @param boundY the upper bound of the y value
     * @param r the radius of the ball
     * @param gameEnvironment the game environment of the ball
     * @return a random ball completely inside the bounds
     */
    public static Ball getRandomBall(int boundX, int boundY, int r, GameEnvironment gameEnvironment) {
        Random rand = new Random();
        return new Ball(Point.getRandomPoint(boundX, boundY, r),
                r, CommonUtilities.generateRandomColor(), gameEnvironment);
    }

    /**
     * adds the ball to the game.
     * @param g the game to add the ball to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
        this.moveOneStep(this.environment);
    }

    /**
     * sets the color of the ball.
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * removes the ball from the game.
     * @param g the game to remove the ball from
     */
    public void removeFromGame(Game g) {
        g.removeSprite(this);
    }
}