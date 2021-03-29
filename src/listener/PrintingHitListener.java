package listener;

import gameobjects.Ball;
import gameobjects.Block;

/**
 * the class prints the information of the block that was hit.
 * @author Ilan Bitan
 *
 */
public class PrintingHitListener implements HitListener {

    /**
     * the method prints the information of points of a current block.
     * @param beingHit - the block.
     * @param hitter - the ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       System.out.println("A Block with " + Integer.toString(beingHit.getNumOfHit()) + " points was hit.");
    }
 }