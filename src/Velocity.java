// ID: 322805029

/**
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * Velocity allows us to give our ball some speed and direction.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * constructor.
     * @param dx movement in the x axis
     * @param dy movement in the y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor by angle and speed.
     * @param angle the angle of the movement vector
     * @param speed the size of the movement vector
     * @return a new Velocity object
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = (-1) * Math.cos(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * The function sets the movement in x.
     * @param newDx movement in x
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * The function sets the movement in y.
     * @param newDy movement in y
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * The function returns the movement in x.
     * @return movement in x
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * The function returns the movement in y.
     * @return movement in y
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * The function changes the position of the ball.
     * @param p point with position (x,y)
     * @return a new point with position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
