// ID: 322805029
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation decorator-class that wraps an existing animation,
 * and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * constructor.
     * @param keyboard Keyboard sensor
     * @param key the key that was pressed
     * @param animation animation that runs
     */
    public KeyPressStoppableAnimation(KeyboardSensor keyboard, String key, Animation animation) {
        this.keyboard = keyboard;
        this.stop = false;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.animation.doOneFrame(d);

        if (this.keyboard.isPressed(this.key)) {
            this.stop = !this.isAlreadyPressed;
        } else {
            this.isAlreadyPressed = false;
        }


    }
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
