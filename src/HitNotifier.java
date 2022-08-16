/**
 * The HitNotifier interface indicate that objects that implement it,
 * send notifications when they are being hit.
 */
public interface HitNotifier {

    /**
     * Adds hl as a listener to hit events.
     * @param hl hitListener to add.
     */
    void addHitListener(HitListener hl);


    /**
     * Removes hl from the list of listeners to hit events.
     * @param hl hitListener to remove.
     */
    void removeHitListener(HitListener hl);
}
