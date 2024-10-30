//329233472 yasmin haddad
package objects;

import biuoop.DrawSurface;

/**
 * describes a sprite.
 */
public interface Sprite {
    // draw the sprite to the screen
    /**
     * draws the sprite on the surface.
     * @param d the surface to draw on
     */
    void drawOn(DrawSurface d);
    // notify the sprite that time has passed

    /**
     * notifies the sprite that time has passed.
     */
    void timePassed();
}