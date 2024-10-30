// 329233472 yasmin haddad

package objects;


import abstractShapes.Point;
import abstractShapes.Rectangle;

import utils.BoardInfo;
import utils.Velocity;

import java.awt.Color;

/**
 * describes a border block for the board.
 */
public class Border extends Block {
    private final int borderWidth = BoardInfo.BORDER_WIDTH;

    /**
     * constructor -- create a block from a rectangle and a color.
     *
     * @param rect  the rectangle of the block
     * @param color the blocks color
     */
    public Border(Rectangle rect, Color color) {
        super(rect, color);
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Color ballColor = hitter.getColor();
        Velocity newV = super.hit(hitter, collisionPoint, currentVelocity);
        hitter.setColor(ballColor);
        return newV;

    }
}
