// 329233472 yasmin haddad

package listener;

import objects.Block;
import game.Game;
import objects.Ball;
import utils.Counter;

/**
 * listener.BallRemover class.
 */
public class BallRemover implements HitListener {

    private Counter remainingBalls;
    private Game game;

    /**
     * constructor.
     * @param game the game
     * @param remainingBalls the remaining balls
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.remainingBalls.getValue() > 0) {
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
    }
}
