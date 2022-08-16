// ID: 322805029
import java.util.List;

/**
 * The LevelInformation interface specifies the information required to fully describe a level.
 */
public interface LevelInformation {
    /**
     * Returns the amount of balls in the game.
     * @return an integer number represents the amount of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * @return list of velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * Return the paddle's speed.
     * @return an integer number represents the paddle's speed.
     */
    int paddleSpeed();

    /**
     * Return the paddle's width.
     * @return  an integer number represents the paddle's width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return string of level name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return sprite of background.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     * @return list of blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * @return an integer number represents the num of blocks be removed.
     */
    int numberOfBlocksToRemove();
}
