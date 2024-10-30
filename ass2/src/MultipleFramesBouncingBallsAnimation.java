// 329233472 yasmin haddad

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * handles multiple bouncing balls animation.
 */
public class MultipleFramesBouncingBallsAnimation {
    private static final int GX = 50, GY = 50, YX = 450, YY = 450, GW = 450,
            GH = 450, YW = 150, YH = 150;
    /**
     * draws a bouncing ball animation.
     * @param balls the balls to draw
     */
    public static void drawAnimation(Ball[] balls) {

        GUI gui = new GUI("multi balls", Board.BOUND_X, Board.BOUND_Y);
        Sleeper sleeper = new Sleeper();

        Rectangle grayRect = new Rectangle(new Point(GX, GY), GW, GH);
        Rectangle yellowRect = new Rectangle(new Point(YX, YY), YW, YH);
        Rectangle board = new Rectangle(new Point(0, 0), Board.BOUND_X,
                Board.BOUND_Y);
        Rectangle[] rectsIn = {grayRect, board};
        Rectangle[] rectsOut = {yellowRect, grayRect};


        while (true) {
            DrawSurface d = gui.getDrawSurface();
            grayRect.drawOn(d, Color.GRAY);

            for (int i = 0; i < balls.length; i += 2) {
//                balls[i].setRadius(Math.min(balls[i].getSize(),
//                        grayRect.getMinWidthHeight() / 2));
                balls[i].moveOneStepIn(rectsIn);
                balls[i].drawOn(d, balls[i].getColor());
            }
            yellowRect.drawOn(d, Color.YELLOW);
            for (int i = 1; i < balls.length; i += 2) {
//                balls[i].setRadius(Math.min(balls[i].getSize(),
//                        yellowRect.getMinWidthHeight() / 2));
                balls[i].moveOneStepOut(rectsOut);
                balls[i].drawOn(d, balls[i].getColor());
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
        int length = args.length;
        Ball[] balls = new Ball[length];
        int maxRadius = Math.min(GW, Math.min(GH, Math.min(YW, YH))) / 2;
        // handle case when size is too big
        for (int i = 0; i < length; ++i) {
            double angle = 0;
            int size = Integer.parseInt(args[i]);
            size = Math.min(size, maxRadius);
            balls[i] = Ball.getRandomBall(Board.BOUND_X, Board.BOUND_Y, size);
            balls[i].setRadius(size);
            while (angle % 90 == 0) {
                angle = rand.nextDouble() * 360;
            }
            Velocity v = Velocity.fromAngleAndSpeed(angle,
                    Math.max(6, 50.0 / balls[i].getSize()));
            balls[i].setVelocity(v);
        }
        drawAnimation(balls);

    }
}
