//329233472 yasmin haddad
package game;


import abstractShapes.Point;
import abstractShapes.Rectangle;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import biuoop.Sleeper;
import listener.BlockRemover;
import listener.BallRemover;

import objects.Ball;
import objects.Block;
import objects.Paddle;
import objects.Sprite;
import listener.ScoreTrackingListener;
import objects.Board;

import objects.Collidable;
import listener.ScoreIndicator;

import utils.BoardInfo;
import utils.CommonUtilities;
import utils.Counter;
import utils.Velocity;

import java.awt.Color;
import java.util.List;

/**
 * game.Game class. handles the game.
 */
public class Game {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui = new GUI("title", BoardInfo.BOUND_X, BoardInfo.BOUND_Y);
    private Sleeper sleeper = new Sleeper();
    private KeyboardSensor keyboard = gui.getKeyboardSensor();
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;

    /**
     * add a collidable to the game.
     * @param c the collidable to add
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add a sprite to the game.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    // Initialize a new game: create the Blocks and objects.Ball (and objects.Paddle)
    // and add them to the game.

    /**
     * initialize the game and add the sprites.
     */
    public void initialize() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();

        Rectangle rec = new Rectangle(new Point(0, 0), BoardInfo.BOUND_X, BoardInfo.BOUND_Y);
        Block bl = new Block(rec, CommonUtilities.generateRandomColor());
        bl.addToGame(this);


        this.blockCounter = new Counter();
        BlockRemover blockRemover = new BlockRemover(this, blockCounter);

        this.ballCounter = new Counter();
        BallRemover ballRemover = new BallRemover(this, this.ballCounter);

        Board board = new Board(CommonUtilities.generateRandomColor(), ballRemover);
        board.addToGame(this);

        this.scoreCounter = new Counter();
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(scoreCounter);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCounter);
        scoreIndicator.addToGame(this);


        // create the balls
        Point start = new Point(400, 550);
        int r = 6;
        Color color = Color.BLACK;
        Ball ball = new Ball(start, r, color, this.environment);



        for (int i = 0; i < 3; ++i) {
            ball = new Ball(ball.getCenter(), r, color, this.environment);
            ball.setVelocity(Velocity.fromAngleAndSpeed(30, 3));
            ball.addToGame(this);
            ballCounter.increase(1);
            ball.timePassed();
            ball.timePassed();
            ball.timePassed();
            ball.timePassed();
        }

        // create the blocks
        List<Color> colors = new java.util.ArrayList<Color>();
        while (colors.size() < 10) {
            Color c = CommonUtilities.generateRandomColor();
            if (!colors.contains(c)) {
                colors.add(c);
            }
        }
        int xStart, y, width = 60, height = 20;

        // init blocks.
        for (int i = 1; i <= 6; ++i) {
            for (int j = 0; j < i * 2; ++j) {
                xStart = (BoardInfo.BOUND_X - width * 2 * i) / 2;
                y = 160 - (i - 1) * height;
                Block b = new Block(new Rectangle(new Point(xStart + j * width, y), width, height), colors.get(i - 1));
                b.addToGame(this);
                b.addHitListener(blockRemover);
                b.addHitListener(scoreTrackingListener);
                blockCounter.increase(1);
            }
        }
//        for (int i = 1; i <= 5; ++i) {
//            for (int j = 1; j <= i + 5; ++j) {
//                xStart = BoardInfo.BOUND_X - BoardInfo.BORDER_WIDTH - width * j;
//                y = 140 - (i - 1) * height;
//                Block b = new Block(new Rectangle(new Point(xStart, y), width, height), colors.get(i - 1));
//                b.addToGame(this);
//                b.addHitListener(blockRemover);
//                b.addHitListener(scoreTrackingListener);
//                blockCounter.increase(1);
//            }
//        }

        Paddle paddle = new Paddle(this.keyboard,  5, CommonUtilities.generateRandomColor());
        paddle.addToGame(this);

    }

    // Run the game -- start the animation loop.

    /**
     * run the game.
     */
    public void run() {
        while (true) {
            long startTime = System.currentTimeMillis(); // timing

            DrawSurface d = this.gui.getDrawSurface();
            this.sprites.drawAllOn(d);
            this.gui.show(d);
            this.sprites.notifyAllTimePassed();


            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = BoardInfo.MILLISECONDS_PER_FRAME - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }

            if (blockCounter.getValue() <= 0) {
                scoreCounter.increase(100);
                System.out.println("You Win!");
                System.out.println("Your score is: " + scoreCounter.getValue());
                gui.close();
                return;
            }
            if (ballCounter.getValue() <= 0) {
                System.out.println("Game Over.");
                System.out.println("Your score is: " + scoreCounter.getValue());
                gui.close();
                return;
            }
        }
    }


    /**
     * remove a collidable from the game.
     * @param c the collidable to remove
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a sprite from the game.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

//    public void removeFromGame()

}