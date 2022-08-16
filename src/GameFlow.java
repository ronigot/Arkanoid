// ID: 322805029
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * This class is in charge of creating the different levels, and moving from one level to the next.
 */
public class GameFlow {
    private KeyboardSensor ks;
    private AnimationRunner ar;
    private Counter score;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final Rectangle SCREEN = new Rectangle(new Point(0, 0), WIDTH, HEIGHT);

    /**
     * constructor.
     * @param ar animation runner
     * @param ks keyboard sensor
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.ar = ar;
        this.ks = ks;
        this.score = new Counter();
    }

    /**
     * This function is running the levels, and moving from one level to the next.
     * @param levels list of levels to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(this.ar, this.ks, levelInfo, this.score);

            level.initialize();

            // level has more blocks and balls
            while (level.getBallsRemaining().getValue() != 0 && level.getBlocksRemaining().getValue() != 0) {
                level.run();
            }

            // no more balls
            if (level.getBallsRemaining().getValue() == 0) {
                this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                        new EndScreen("Game Over. ", this.score)));
                this.ar.getGui().close();
            }
        }
        LevelInformation end = levels.get(levels.size() - 1);
        GameLevel endLevel = new GameLevel(this.ar, this.ks, end, this.score);
        if (endLevel.getBlocksRemaining().getValue() == 0) {
            this.ar.run(new KeyPressStoppableAnimation(this.ks, KeyboardSensor.SPACE_KEY,
                    new EndScreen("You Win! ", this.score)));

            this.ar.getGui().close();
        }
    }


}
