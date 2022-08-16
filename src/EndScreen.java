// ID: 322805029
import biuoop.DrawSurface;

/**
 * Once the game is over, we will display the final score.
 * The end screen displays message when the player won or lost.
 */
public class EndScreen implements Animation {
    private String endGame;
    private Counter score;

    /**
     * constructor.
     * @param endGame string representing the end message - whether the player won or lost.
     * @param score the score the player achieved during the game.
     */
    public EndScreen(String endGame, Counter score) {
        this.endGame = endGame;
        this.score = score;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(200, d.getHeight() / 2, endGame + "Your score is " + score.getValue(), 32);

    }
    @Override
    public boolean shouldStop() {
        return false;
    }


}
