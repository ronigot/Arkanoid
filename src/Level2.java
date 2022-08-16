import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The class is implementing the LevelInformation interface, and describes the second level of the game.
 */

public class Level2 implements LevelInformation {
    /**
     * constructor.
     */
    public Level2() {
    }

    @Override
    public int numberOfBalls() {
        return 10;
    }
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-45 + 10 * i, 6));
        }
        return velocities;
    }
    @Override
    public int paddleSpeed() {
        return 10;
    }
    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Level 2";
    }

    @Override
    public Sprite getBackground() {
        return new Block(Color.WHITE, GameFlow.SCREEN);
    }

    @Override
    public List<Block> blocks() {
        int blockWidth = 55;
        int blockHeight = 25;
        // the colors of the blocks.
        Color[] colors = {Color.MAGENTA, Color.MAGENTA, Color.BLUE, Color.BLUE,
                Color.CYAN, Color.CYAN, Color.GREEN, Color.GREEN, Color.YELLOW, Color.YELLOW,
                Color.ORANGE, Color.ORANGE, Color.RED, Color.RED};

        List<Block> blocks = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            blocks.add(new Block(colors[i], new Rectangle(new Point(15 + i * blockWidth, 220),
                    blockWidth, blockHeight)));
        }
        return blocks;


    }

    @Override
    public int numberOfBlocksToRemove() {
        return 14;
    }
}
