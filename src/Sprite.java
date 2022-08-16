// ID: 322805029
import biuoop.DrawSurface;

/**
 *  Sprite is a game object that can be drawn to the screen,
 *  and can be notified that time has passed.
 */
public interface Sprite {

    /**
     * Draw the sprite to the screen.
     * @param d Surface to draw.
     */
    void drawOn(DrawSurface d);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();

    /**
     * This function is in charge of adding the ball and the block to the game,
     * calling the appropriate game methods.
     * @param g A game to add to it
     */
    void addToGame(GameLevel g);
}
