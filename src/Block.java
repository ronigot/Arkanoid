// ID: 322805029
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Block can be a block in the middle of the game, or a block in the edge of the screen.
 * Block class implementing the Collidable and Sprite interfaces.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rec;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     * @param color color of the block.
     * @param rec Rectangle - block size and location
     */
    public Block(Color color, Rectangle rec) {
        this.color = color;
        this.rec = rec;
        this.hitListeners = new ArrayList<>();
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rec;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newV = new Velocity(currentVelocity.getDx(), currentVelocity.getDy());

        Line upperEdge = new Line(this.rec.getUpperRight(), this.rec.getUpperLeft());
        Line lowerEdge = new Line(this.rec.getLowerRight(), this.rec.getLowerLeft());
        // change the vertical direction of the velocity
        if (upperEdge.containsPoint(collisionPoint) || lowerEdge.containsPoint(collisionPoint)) {
            newV.setDy((-1) * newV.getDy());
        }

        Line rightEdge = new Line(this.rec.getLowerRight(), this.rec.getUpperRight());
        Line leftEdge = new Line(this.rec.getUpperLeft(), this.rec.getLowerLeft());
        // change the horizontal direction of the velocity
        if (rightEdge.containsPoint(collisionPoint) || leftEdge.containsPoint(collisionPoint)) {
            newV.setDx((-1) * newV.getDx());
        }
        this.notifyHit(hitter);
        return newV;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.rec.getUpperLeft().getX(), (int) this.rec.getUpperLeft().getY(),
                (int) this.rec.getWidth(), (int) this.rec.getHeight());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * The function removes this block from the given game.
     * @param game the game from which we will remove the block.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * The function is called whenever a hit() occurs,
     * and notifiers all of the registered HitListener objects by calling their hitEvent method.
     * @param hitter the ball that collides.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

}
