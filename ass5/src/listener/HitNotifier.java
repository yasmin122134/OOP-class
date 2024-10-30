// 329233472 yasmin haddad

package listener;


/**
 * listener.HitNotifier interface.
 */
public interface HitNotifier {
    // Add hl as a listener to hit events.
    /**
     * adds a hit listener.
     * @param hl the hit listener to add
     */
    void addHitListener(HitListener hl);
    // Remove hl from the list of listeners to hit events.
    /**
     * removes a hit listener.
     * @param hl the hit listener to remove
     */
    void removeHitListener(HitListener hl);
}