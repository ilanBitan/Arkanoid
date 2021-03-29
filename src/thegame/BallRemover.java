package thegame;

import gameobjects.Ball;
import gameobjects.Block;
import listener.HitListener;

/**
 * The BallRemover is in charge of removing balls from the game, as well as
 * keeping count of the number the remaining balls.
 * @author Ilan Bitan
 *
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    private int modify = 1;

    /**
     * a constructor.
     * @param currentGame - the current game.
     * @param removedBalls - a counter that defines the remaining balls.
     */
    public BallRemover(GameLevel currentGame, Counter removedBalls) {
        this.game = currentGame;
        this.remainingBalls = removedBalls;
    }


    /**
     * when a ball hit the ground, it should be removes from the game.
     * @param beingHit - the ground.
     * @param hitter - the hitting ball that should be removed.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHit() > 0) {
            hitter.removeFromGame(game);
            this.remainingBalls.decrease(this.modify);
        }
    }

    /**
     * a getter for the remainingBalls counter.
     * @return the remainingBalls counter.
     */
    public Counter getCounter() {
        return this.remainingBalls;
    }

}