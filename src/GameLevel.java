// ID: 322805029
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * Game class holds the sprites and the collidables,
 * and in charge of the animation.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksRemaining;
    private Counter ballsRemaining;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboardSensor;
    private LevelInformation levelInformation;


    /**
     * constructor.
     * @param runner Animation Runner
     * @param keyboardSensor keyboard sensor
     * @param levelInformation information on the current level
     * @param score The accumulated score in the game
     */
    public GameLevel(AnimationRunner runner, KeyboardSensor keyboardSensor,
                     LevelInformation levelInformation, Counter score) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blocksRemaining = new Counter();
        this.ballsRemaining = new Counter();
        this.score = score;
        this.runner = runner;
        this.keyboardSensor = keyboardSensor;
        this.levelInformation = levelInformation;
    }

    /**
     * returns the number of blocks are available in the game.
     * @return remaining blocks.
     */
    public Counter getBlocksRemaining() {
        return this.blocksRemaining;
    }

    /**
     * returns the number of balls are available in the game.
     * @return remaining balls.
     */
    public Counter getBallsRemaining() {
        return this.ballsRemaining;
    }

    /**
     * The function adds a collidable object to the environment.
     * @param c new collidable to the environment
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * The function adds a sprite object to the game.
     * @param s new sprite to the game
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }


    /**
     * Initialize the blocks frame.
     */
    public void initializeFrame() {
        BallRemover ballRemover = new BallRemover(this, this.ballsRemaining);
        // width and height of the surface
        int widthSurface = GameFlow.WIDTH;
        int heightSurface = GameFlow.HEIGHT;

        //initialize the blocks of the frame.
        final int frame = 15;

        // The top block on screen
        Block top = new Block(Color.GRAY, new Rectangle(new Point(0, 0), widthSurface, frame));
        // The right block on screen
        Block right = new Block(Color.GRAY, new Rectangle(
                new Point(widthSurface - frame, frame), frame, heightSurface - frame));
        // The bottom block on screen - the death-region
        Block bottom = new Block(Color.GRAY, new Rectangle(
                new Point(frame, heightSurface - frame), widthSurface - 2 * frame, frame));
        bottom.addHitListener(ballRemover);
        // The left block on screen
        Block left = new Block(Color.GRAY, new Rectangle(new Point(0, frame), frame, heightSurface - frame));

        top.addToGame(this);
        right.addToGame(this);
        bottom.addToGame(this);
        this.removeSprite(bottom);
        left.addToGame(this);
    }

    /**
     * Initialize balls based on the supplied LevelInformation.
     */
    public void initializeBalls() {
        final int r = 7;
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point((int) (GameFlow.WIDTH / 2), 540), r, Color.BLACK, this.environment);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ball.addToGame(this);
            this.ballsRemaining.increase(1);
        }
    }

    /**
     * Initialize blocks based on the supplied LevelInformation.
     */
    public void initializeBlocks() {
        BlockRemover blockRemover = new BlockRemover(this, new Counter());
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);

        for (int i = 0; i < this.levelInformation.numberOfBlocksToRemove(); i++) {
            Block block = this.levelInformation.blocks().get(i);
            block.addToGame(this);
            block.addHitListener(blockRemover);
            this.blocksRemaining.increase(1);
            block.addHitListener(scoreTrackingListener);

        }
    }

    /**
     * Initialize a Paddle based on the supplied LevelInformation.
     */
    public void initializePaddle() {
        final int paddleWidth = this.levelInformation.paddleWidth();
        final int paddleHeight = 20;
        final int frame = 15;
        Rectangle rec = new Rectangle(new Point((int) ((GameFlow.WIDTH - paddleWidth) / 2),
                GameFlow.HEIGHT - (frame + paddleHeight + 2)), paddleWidth, paddleHeight);

        Paddle paddle = new Paddle(rec, Color.DARK_GRAY, this.keyboardSensor,
                GameFlow.WIDTH - frame - 1, frame + 1, this.levelInformation.paddleSpeed());
        paddle.addToGame(this);
    }

    /**
     * Initialize a new game: create the blocks and balls and paddle
     * and add them to the game.
     */
    public void initialize() {
        this.addSprite(this.levelInformation.getBackground());
        this.initializeFrame();
        this.initializePaddle();
        this.initializeBalls();
        this.initializeBlocks();

        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score);
        scoreIndicator.addToGame(this);
        LevelNameIndicator levelNameIndicator = new LevelNameIndicator(this.levelInformation.levelName());
        levelNameIndicator.addToGame(this);
    }

    /**
     * This function runs the game.
     */
    public void run() {
        // countdown before turn starts.
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
    }

    /**
     * removes the collidable from the game's collidables.
     * @param c the collidable we remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getCollidables().remove(c);
    }

    /**
     * removes the sprite from the game's sprites.
     * @param s the sprite we remove.
     */
    public void removeSprite(Sprite s) {
       this.sprites.getSprites().remove(s);

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // game-specific logic
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        // stopping condition
        if (this.ballsRemaining.getValue() == 0) {
            this.running = false;
        }
        if (this.blocksRemaining.getValue() == 0) {
            this.running = false;
        }
        if (this.ballsRemaining.getValue() != 0 && this.blocksRemaining.getValue() == 0) {
            this.score.increase(100);
        }
        if (this.keyboardSensor.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }

    }
    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
