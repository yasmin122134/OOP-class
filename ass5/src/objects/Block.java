// 329233472 yasmin haddad
package objects;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import abstractShapes.Line;
import abstractShapes.Point;
import abstractShapes.Rectangle;
import biuoop.DrawSurface;
import game.Game;
import listener.HitListener;
import listener.HitNotifier;
import utils.Velocity;

/**
 * describes a block in the plane.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    // instance variables
    private Rectangle rect;
    private Color color;
    private boolean isColliding = false;
    private Line[] linesPerX = new Line[2];
    private Line[] linesPerY = new Line[2];
    private List<HitListener> hitListeners = new ArrayList<>();


    /**
     * constructor -- create a block from a rectangle and a color.
     * @param rect the rectangle of the block
     * @param color the blocks color
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        Line[] lines = rect.getLines();
        int k = 0, j = 0;
        for (int i = 0; i < 4; ++i) {
            Line line = lines[i];
            if (line.getIsPerToY()) {
                linesPerY[j] = line;
                j++;
            } else {
                linesPerX[k] = line;
                k++;
            }
        }

    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = new Velocity(currentVelocity.getDx(),
                currentVelocity.getDy());
        for (Line line : linesPerY) {
            if (line.pointOnLine(collisionPoint)) {
                newVelocity.setDx(-newVelocity.getDx());
            }
        }
        for (Line line : linesPerX) {
            if (line.pointOnLine(collisionPoint)) {
                newVelocity.setDy(-newVelocity.getDy());
            }

        }
        if (!ballColorMatch(hitter)) {
            this.notifyHit(hitter);
            hitter.setColor(this.color);

        }
        return newVelocity;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
    }


    /**
     * adds the block to a game - to collideables and sprites.
     * @param g the game to add the block to
     */
    public void addToGame(Game g) {
        g.addCollidable(this);
        g.addSprite(this);
    }


    /**
     * checks if the ball and the block have the same color.
     * @param ball the ball to check
     * @return true if the ball and the block have the same color, false otherwise
     */
    private boolean ballColorMatch(Ball ball) {
        return ball.getColor().equals(this.color);
    }

    /**
     * removes this block from the game.
     * @param game the game to remove the block from
     */
    public void removeFromGame(Game game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    /**
     * notify the block that time has passed.
     * @param hitter the ball that hit the block
     */
    public void notifyHit(Ball hitter) {
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    // Add hl as a listener to hit events.
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    // Remove hl from the list of listeners to hit events.
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


    @Override
    public void timePassed() { }
}
