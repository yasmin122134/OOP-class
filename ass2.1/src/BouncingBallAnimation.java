// 329233472 yasmin haddad

// imports
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * handles a bouncing ball animation.
 */
public class BouncingBallAnimation {

    /**
     * draws a bouncing ball animation.
     * @param start the starting point of the ball
     * @param dx the change in x
     * @param dy the change in y
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("drawAnimation", Board.BOUND_X, Board.BOUND_Y);
        Sleeper sleeper = new Sleeper();
        int r = 3;
        Ball ball = new Ball(start.getX(), start.getY(), r, Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(Board.SLEEP_MILLISECONDS);
        }
    }


    /**
     * handles ball animation.
     * @param args command line arguments: x, y, angle, speed
     */
    public static void main(String[] args) {
        drawAnimation(new Point(Double.parseDouble(args[0]),
                Double.parseDouble(args[1])), Double.parseDouble(args[2]),
                Double.parseDouble(args[3]));

    }
}
