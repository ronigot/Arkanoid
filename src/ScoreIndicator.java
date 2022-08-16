// ID: 322805029

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * ScoreIndicator is in charge of displaying the current score.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * constructor.
     * @param score score counter
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(380, 10, "Score: " + this.score.getValue(), 13);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
