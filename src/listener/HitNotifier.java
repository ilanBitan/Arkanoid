package listener;

/**
 * the interface of the Hit Notifier.
 * @author Ilan Bitan
 *
 */
public interface HitNotifier {

    /**
     * the method adds a current listener as a listener to hit events.
     * @param hl - the listener to be added.
     */
   void addHitListener(HitListener hl);

   /**
    * the method removes a current listener from the list of listeners to hit events.
    * @param hl - the listener to be removed.
    */
   void removeHitListener(HitListener hl);
}