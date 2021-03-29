package listener;
import gameobjects.Ball;
import gameobjects.Block;

/**
 * The interface of the Hit Listeners.
 * @author Ilan Bitan
 *
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit - the block being hit.
     * @param hitter - the hitting ball.
     */
   void hitEvent(Block beingHit, Ball hitter);
}