import biuoop.DrawSurface;

/**
 * Animation contains the template-methods.
 */
public interface Animation {
    /**
     * This function is in charge of the logic.
     * @param d surface to draw.
     */
    void doOneFrame(DrawSurface d);

    /**
     * This function is in charge of stopping condition.
     * @return true - in order to stop the animation, false - otherwise.
     */
    boolean shouldStop();
}
