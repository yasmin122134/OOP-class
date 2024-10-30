package listener;


// a listener.BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.

import objects.Block;
import game.Game;
import objects.Ball;
import utils.Counter;

/**
 * listener.BlockRemover class.
 */
public class BlockRemover implements HitListener {
    private Game game;
    private Counter remainingBlocks;

    /**
     * constructor.
     * @param game the game
     * @param remainingBlocks the remaining blocks
     */
    public BlockRemover(Game game, Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
        this.game = game;

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.remainingBlocks.getValue() > 0) {
            this.game.removeCollidable(beingHit);
            this.game.removeSprite(beingHit);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        }
    }
}