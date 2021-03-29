package thegame;

import gameobjects.Ball;
import gameobjects.Block;
import listener.HitListener;

/**
 * The BlockRemover is in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 * @author Ilan Bitan
 *
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    private int modify = 1;

    /**
     * a constructor.
     * @param currentGame - the current game.
     * @param removedBlocks - a counter that defines the remaining blocks
     */
    public BlockRemover(GameLevel currentGame, Counter removedBlocks) {
        this.game = currentGame;
        this.remainingBlocks = removedBlocks;

    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed
     * from the game.
     * @param beingHit - the block that was hit.
     * @param hitter - the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHit() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(this.modify);
        }
    }

    /**
     * a getter for the remainingBalls counter.
     * @return the remainingBalls counter.
     */
    public Counter getCounter() {
        return this.remainingBlocks;
    }

}