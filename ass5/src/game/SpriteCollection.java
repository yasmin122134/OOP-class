// 329233472 yasmin haddad
package game;

import biuoop.DrawSurface;
import objects.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * describes a sprite collection.
 */
public class SpriteCollection {
    // instance variables
    private java.util.List<Sprite> sprites = new ArrayList<Sprite>();

    /**
     * adds a sprite to the sprite collection.
     * @param s the sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);

    }

    /**
     * removes a sprite from the sprite collection.
     * @param s the sprite to remove
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }


    // call timePassed() on all sprites.

    /**
     * calls timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> sprites = new ArrayList<>(this.sprites);
        for (Sprite s : sprites) {
            s.timePassed();
        }
    }

    // call drawOn(d) on all sprites.

    /**
     * calls drawOn(d) on all sprites.
     * @param d the surface to draw on
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> sprites = new ArrayList<>(this.sprites);
        for (Sprite s : sprites) {
            s.drawOn(d);
            s.timePassed();
        }
    }
}