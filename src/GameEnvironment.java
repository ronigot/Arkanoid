import java.util.ArrayList;
import java.util.List;

/**
 * This class is a collection of many objects a Ball can collide with.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * constructor - initialize the list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * returns the list of collidables.
     * @return list of collidables
     */
    public List<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * add the given collidable to the environment.
     * @param c add c to the environment
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * The function returns the information about the closest collision
     * that is going to occur. (null if this object will not collide with any of the collidables)
     * @param trajectory the object trajectory
     * @return CollisionInfo - information about the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Rectangle rec = null;
        Collidable coll = this.collidables.get(0);
        Point closestPoint = trajectory.closestIntersectionToStartOfLine(
                this.collidables.get(0).getCollisionRectangle());
        Point tempPoint = null;
        // move over the collidables objects to find the closest collision.
        for (Collidable c : this.collidables) {
            rec = c.getCollisionRectangle();
            // if there has been no collision until now, we put tempPoint as the closetPoint
            // (even if it null).
            tempPoint = trajectory.closestIntersectionToStartOfLine(rec);
            if (closestPoint == null) {
                closestPoint = tempPoint;
                coll = c;
            } else {
                // if we find collision, we will check if it is closer than the closestPoint
                if (tempPoint != null && trajectory.start().distance(tempPoint)
                        < trajectory.start().distance(closestPoint)) {
                    closestPoint = tempPoint;
                    coll = c;
                }
            }
        }
        if (closestPoint == null) {
            return null;
        }
        return new CollisionInfo(closestPoint, coll);

    }
}
