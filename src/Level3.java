// ID: 322805029
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class is implementing the LevelInformation interface, and describes the third level of the game.
 */
public class Level3 implements LevelInformation {
    /**
     * constructor.
     */
    public Level3() {
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            velocities.add(Velocity.fromAngleAndSpeed(-10 + 20 * i, 7));
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 9;
    }

    @Override
    public int paddleWidth() {
        return 85;
    }

    @Override
    public String levelName() {
        return "Level 3";
    }

    @Override
    public Sprite getBackground() {
        return new Block(Color.LIGHT_GRAY, GameFlow.SCREEN);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        int blockWidth = 50;
        int blockHeight = 20;

        final int numOfRows = 6;
        // number of blocks in a row
        int numOfBlocks = 12;
        // right block location
        int startX = GameFlow.WIDTH - 15 - blockWidth;
        // different color for each row
        Color[] colors = {Color.MAGENTA, Color.BLUE, Color.CYAN, Color.GREEN, Color.YELLOW, Color.ORANGE};
        for (int i = 0, y = 120; i < numOfRows; i++, numOfBlocks--, y += blockHeight) {
            for (int j = 0; j < numOfBlocks; j++) {
                int x = startX - j * blockWidth;
                Block block = new Block(colors[i], new Rectangle(new Point(x, y), blockWidth, blockHeight));
                blocks.add(block);

            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 57;
    }
}
