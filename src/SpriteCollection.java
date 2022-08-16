import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds a collection of sprites.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /***
     * returns the list of the sprites.
     * @return list of sprites
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * The function adds Sprite to the SpriteCollection.
     * @param s Sprite to add
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite s : new ArrayList<>(this.sprites)) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d surface to draw
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : this.sprites) {
            s.drawOn(d);
        }
    }
}
