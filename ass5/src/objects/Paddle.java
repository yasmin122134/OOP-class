// 329233472 yasmin haddad

package objects;


import abstractShapes.Line;
import abstractShapes.Point;
import abstractShapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Game;
import utils.BoardInfo;
import utils.CommonUtilities;
import utils.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * describes a paddle in the plane.
 */
public class Paddle implements Sprite, Collidable {
    private final KeyboardSensor keyboard;
    private Rectangle paddleRect;
    private int speed = 4;
    private Color paddleColor = CommonUtilities.generateRandomColor();
    private Line[] linesPerX = new Line[2];
    private Line[] linesPerY = new Line[2];
    private final int paddleWidth = 175;
    private final int paddleHeight = 15;
    private int regSize = this.paddleWidth / 5;
    private List<Integer> regList = new ArrayList<Integer>();
    private final List<Double> regAngleList = new ArrayList<Double>();


    // Constructor
    /**
     * constructor -- create a paddle from a rectangle and a color.
     * @param keyboard the keyboard sensor
     * @param paddleRect the rectangle of the paddle
     */
    public Paddle(biuoop.KeyboardSensor keyboard, Rectangle paddleRect) {
        this.keyboard = keyboard;
        this.paddleRect = paddleRect;
        rectInit();
    }

    private void initLines() {
        int k = 0, j = 0;
        for (Line line : this.paddleRect.getLines()) {
            if (line.getIsPerToY()) {
                this.linesPerY[j] = line;
                j++;
            } else {
                this.linesPerX[k] = line;
                k++;
            }
        }
    }

    /**
     * constructor -- create a paddle from a rectangle and a color.
     * @param keyboard the keyboard sensor
     * @param speed the speed of the paddle
     * @param c the color of the paddle
     */
    public Paddle(KeyboardSensor keyboard, int speed, Color c) {
        this.keyboard = keyboard;
        this.speed = speed;
        this.paddleColor = c;
        rectInit();
    }
    private void rectInit() {
        this.paddleRect = new Rectangle(new Point(BoardInfo.BOUND_X / 2 - this.paddleWidth / 2,
                BoardInfo.BOUND_Y - this.paddleHeight - 20), this.paddleWidth, this.paddleHeight);
        initLines();
        initReg();
    }

    private void initReg() {
        this.regList.clear();
        this.regAngleList.clear();
        for (int i = 0; i < 5; ++i) {
            this.regList.add(i * this.regSize + (int) this.paddleRect.getUpperLeft().getX());
            this.regAngleList.add(300.0 + i * 30);
        }
    }

    /**
     * sets the speed of the paddle.
     * @param speed the speed of the paddle to set
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * moves the paddle to the left.
     */
    public void moveLeft() {
        this.paddleRect = this.paddleRect.moveLeft(this.speed);
        initLines();
        initReg();
    }

    /**
     * moves the paddle to the right.
     */
    public void moveRight() {
        this.paddleRect = this.paddleRect.moveRight(this.speed);
        initLines();
        initReg();
    }

    // objects.Sprite
    /**
     * moves left or right according to the key pressed, when the time passed.
     */
    public void timePassed() {
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * draws the paddle on a given draw surface.
     * @param d the draw surface to draw on
     */
    public void drawOn(DrawSurface d) {
        this.paddleRect.drawOn(d, this.paddleColor);
    }

    // objects.Collidable
    /**
     * gets the collision rectangle of the paddle. (the rectangle of the paddle, when it is hit)
     * @return the rectangle of the paddle
     */
    public Rectangle getCollisionRectangle() {
        return this.paddleRect;
    }

    private int getReg(Point collisionPoint) {
        for (int i = 0; i < this.regList.size() - 1; ++i) {
            if (collisionPoint.getX() > this.regList.get(i) && collisionPoint.getX() < this.regList.get(i + 1)) {
                return i;
            }
        }
        return this.regList.size() - 1;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(),
                currentVelocity.getDy());
        for (Line line : this.linesPerY) {
            if (line.pointOnLine(collisionPoint)) {
                newVelocity.setDx(-newVelocity.getDx());
            }
        }
        for (Line line : this.linesPerX) {
            if (line.pointOnLine(collisionPoint)) {
                newVelocity = Velocity.fromAngleAndSpeed(this.regAngleList.get(getReg(collisionPoint)),
                        Math.sqrt(Math.pow(currentVelocity.getDx(), 2) + Math.pow(currentVelocity.getDy(), 2)));
//                newVelocity.setDy(-newVelocity.getDy());
            }

        }
        return newVelocity;
    }

    // Add this paddle to the game.

    /**
     * adds the paddle to the game.
     * @param g the game to add the paddle to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}