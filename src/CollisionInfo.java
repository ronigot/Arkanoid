/**
 * This class holds the information about the point at which the collision occurs,
 * and the collidable object involved in the collision.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidable;

    /**
     * constructor.
     * @param collisionPoint the point at which the collision occurs
     * @param collidable collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collidable = collidable;
        this.collisionPoint = collisionPoint;
    }

    /**
     * The function returns the point at which the collision occurs.
     * @return collision Point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * The function returns the collidable object involved in the collision.
     * @return collidable object
     */
    public Collidable collisionObject() {
        return this.collidable;
    }

}
