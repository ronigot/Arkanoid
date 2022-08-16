import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is implementing the LevelInformation interface, and describes the first level of the game.
 */
public class Level1 implements LevelInformation {
    /**
     * constructor.
     */
    public Level1() {
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 5));
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 10;
    }
    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "Level 1";
    }

    @Override
    public Sprite getBackground() {
        Color color = new Color(102, 0, 120).brighter();
        return new Block(color, GameFlow.SCREEN);
    }

    @Override
    public List<Block> blocks() {
        int blockSize = 40;
        List<Block> blocks = new ArrayList<>();
        blocks.add(new Block(Color.CYAN, new Rectangle(new Point(380, 220), blockSize, blockSize)));
        return blocks;


    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }
}
