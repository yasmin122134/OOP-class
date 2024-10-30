// 329233472 yasmin haddad

// imports
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;

/**
 * handles multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * draws a bouncing ball animation.
     * @param balls the balls to draw
     */
    public static void drawAnimation(Ball[] balls) {
        GUI gui = new GUI("multi balls", Board.BOUND_X, Board.BOUND_Y);
        Sleeper sleeper = new Sleeper();
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            // Move and draw each ball
            for (Ball ball : balls) {
                ball.moveOneStep();
                ball.drawOn(d, ball.getColor());
            }

            gui.show(d);
            sleeper.sleepFor(Board.SLEEP_MILLISECONDS);
        }
    }
    /**
     * main -- handles multiple bouncing balls animation.
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Random rand = new Random();
        int size, maxRadius = Math.min(Board.BOUND_X, Board.BOUND_Y) / 2 - 5;
        Ball[] balls = new Ball[args.length];
        // create balls
        for (int i = 0; i < args.length; ++i) {
            size = Integer.parseInt(args[i]);
            size = Math.min(Math.abs(size), maxRadius);
            balls[i] = Ball.getRandomBall(Board.BOUND_X, Board.BOUND_Y, size);
            Velocity v = Velocity.fromAngleAndSpeed(rand.nextDouble()
                            * 360,
                    Math.max(1, 50.0 / size));
            balls[i].setVelocity(v);
        }

        drawAnimation(balls);
    }
}
