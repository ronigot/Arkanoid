// ID: 322805029
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * The AnimationRunner takes an Animation object and runs it.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor.
     * @param gui a GUI for the Animation Runner.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        // 60 frames per second
        this.framesPerSecond = 60;
        this.sleeper = new Sleeper();
    }

    /**
     * This function is the animation loop, includes only the gui and frame-management code.
     * @param animation animation to run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * Returns the gui of this object.
     * @return gui
     */
    public GUI getGui() {
        return this.gui;
    }
}
