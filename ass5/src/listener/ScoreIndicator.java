// 329233472 yasmin haddad

package listener;

import abstractShapes.Point;
import abstractShapes.Rectangle;
import biuoop.DrawSurface;
import game.Game;
import objects.Sprite;
import utils.BoardInfo;
import utils.CommonUtilities;
import utils.Counter;

import java.awt.Color;

/**
 * describes a score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCounter;
    private Rectangle rectangle = new Rectangle(new Point(0, 0), BoardInfo.BOUND_X, 20);
    private Color color = CommonUtilities.generateRandomColor();


    /**
     * constructor -- create a score indicator from a counter.
     * @param scoreCounter the counter to create the score indicator from
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreCounter = scoreCounter;
    }

    @Override
    public void drawOn(DrawSurface d) {
        rectangle.drawOn(d, this.color);
        d.setColor(Color.BLACK);
        d.drawText((int) rectangle.getUpperLeft().getX() + BoardInfo.BOUND_X / 2,
                (int) rectangle.getUpperLeft().getY() + 15,
                "Score: " + scoreCounter.getValue(), 15);

    }

    @Override
    public void timePassed() {

    }

    /**
     * add the score indicator to a game.
     * @param g the game to add the score indicator to
     */
    public void addToGame(Game g) {
        g.addSprite(this);
    }
}
