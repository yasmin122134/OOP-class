// 329233472 yasmin haddad

package listener;



import objects.Block;
import objects.Ball;
import utils.Counter;

/**
 * describes a score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * constructor -- create a score tracking listener from a counter.
     * @param scoreCounter the counter to create the score tracking listener from
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}