package thegame;

import gameobjects.Ball;
import gameobjects.Block;
import listener.HitListener;
/**
 * This class is used to update the score counter when
 * blocks are being hit and removed.
 * @author Ilan Bitan
 *
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    private int destroyedBlock = 10;
    private int hitBlock = 5;

    /**
     * This is the constructor, for the ScoreTrackingListener class.
     * @param scoreCounter - the counter for scores.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
       this.currentScore = scoreCounter;
    }

    /**
     * the method increases or decreases the score
     * if there was or wasn't a hit.
     * @param beingHit - the block.
     * @param hitter - the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getNumOfHit() == 0) {
            this.currentScore.increase(destroyedBlock);
        } else {
            this.currentScore.increase(hitBlock);
        }
    }

 }