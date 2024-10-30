// 329233472 yasmin haddad

package listener;

import objects.Block;
import objects.Ball;

/**
 * describes a hit listener.
 */
public interface HitListener {
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the objects.Ball that's doing the hitting.

    /**
     * this method is called whenever the beingHit object is hit.
     * @param beingHit the block that was hit
     * @param hitter the ball that hit
     */
    void hitEvent(Block beingHit, Ball hitter);
}