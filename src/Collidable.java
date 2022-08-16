/**
 * The Collidable interface used by things that can be collided with.
 */
public interface Collidable {

    /**
     * Returns the "collision shape" of the object.
     * @return a rectangle - "collision shape" of the object
     */
    Rectangle getCollisionRectangle();


    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity, and change the velocity.
     * @param collisionPoint collision Point
     * @param currentVelocity current velocity - velocity before the collision.
     * @param hitter the ball that collides.
     * @return new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
