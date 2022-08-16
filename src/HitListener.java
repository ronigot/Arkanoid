/**
 * Objects that should be notified of hit events are implements this interface.
 */
public interface HitListener {


    /**
     * This method is called whenever the beingHit object is hit by the hitter ball.
     * @param beingHit the block that is hit.
     * @param hitter the Ball that's doing the hitting.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
