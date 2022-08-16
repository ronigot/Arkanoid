import biuoop.DrawSurface;

import java.awt.Color;

/**
 * LevelNameIndicator is in charge of displaying the current level name.
 */
public class LevelNameIndicator implements Sprite {
    private String name;

    /**
     * constructor.
     * @param name name of a level
     */
    public LevelNameIndicator(String name) {
        this.name = name;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.drawText(660, 10, "Level Name: " + this.name, 13);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
