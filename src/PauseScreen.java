// ID: 322805029
import biuoop.DrawSurface;

/**
 * PauseScreen displays a screen with the message paused -- press space to continue until a key is pressed.
 */
public class PauseScreen implements Animation {

    /**
     * constructor.
     */
    public PauseScreen() {
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(160, d.getHeight() / 2, "paused -- press space to continue", 32);


    }
    @Override
    public boolean shouldStop() {
        return false;
    }
}
