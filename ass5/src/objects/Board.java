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
import utils.BoardInfo;

/**
 * describes a block in the plane.
 */
public class Board implements Sprite, HitNotifier {
    // instance variables
    private List<Rectangle> rect = new ArrayList<>();
    private List<Block> borders = new ArrayList<>();
    private final int width = 20;

    private Color color;
    private List<Line> linesPerX = new ArrayList<>();
    private List<Line> linesPerY = new ArrayList<>();
    private List<HitListener> hitListeners = new ArrayList<>();
    private final int borderWidth = BoardInfo.BORDER_WIDTH;


    //initialize lines corectly from the block list
    /**
     * constructor -- create a block from a rectangle and a color.
     * @param color the blocks color
     * @param deathListener
     */
    public Board(Color color, HitListener deathListener) {
        Border left = new Border(new Rectangle(new Point(0, 0), borderWidth,
                BoardInfo.BOUND_Y), color);
        Border up = new Border(new Rectangle(new Point(0, 20), BoardInfo.BOUND_X,
                borderWidth), color);
        Border right = new Border(new Rectangle(new Point(BoardInfo.BOUND_X - borderWidth, 0), 20,
                BoardInfo.BOUND_Y), color);
        DeathRegion down = new DeathRegion(new Rectangle(new Point(0, BoardInfo.BOUND_Y - borderWidth),
                    BoardInfo.BOUND_X, borderWidth), color);
        borders.add(left); borders.add(up); borders.add(right); borders.add(down);
        rect.add(left.getCollisionRectangle());
        rect.add(up.getCollisionRectangle());
        rect.add(right.getCollisionRectangle());
        rect.add(down.getCollisionRectangle());
        for (Rectangle r : rect) {
            initLines(r);
        }
        this.color = color;

        down.addHitListener(deathListener);
    }

    private void initLines(Rectangle rect) {
        Line[] lines = rect.getLines();

        for (int i = 0; i < 4; ++i) {
            Line line = lines[i];
            if (line.getIsPerToY()) {
                linesPerY.add(line);
            } else {
                linesPerX.add(line);
            }
        }
    }


    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        for (Block b : this.borders) {
            b.drawOn(d);
        }
    }


    /**
     * adds the block to a game - to collideables and sprites.
     * @param g the game to add the block to
     */
    public void addToGame(Game g) {
        for (Block block : borders) {
            g.addCollidable(block);
            g.addSprite(block);
        }
    }


    /**
     * removes the block from the game.
     * @param game the game to remove the block from
     */
    public void removeFromGame(Game game) {
        for (Block block : borders) {
            game.removeCollidable(block);
            game.removeCollidable(block);
        }
    }

    // find the block that we hit based on the hitter location
//    public void notifyHit(objects.Ball hitter) {
//        List<listener.HitListener> listeners = new ArrayList<listener.HitListener>(this.hitListeners);
//
//        for (listener.HitListener hl : listeners) {
//            hl.hitEvent(this, hitter);
//        }
//    }

//    private objects.Block hitting(objects.Ball hitter) {
//        for (objects.Block b : blocks) {
//            if (b.getCollisionRectangle().)
//        }
//    }

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
