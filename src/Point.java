/**
 * A point has an x and a y value, and can measure the distance to other points,
 * and if it is equal to another point.
 */
public class Point {
    private double x;
    private double y;

    /**
     * constructor.
     * @param x decimal number
     * @param y decimal number
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance - the function returns the distance of this point to the other point.
     * @param other other point
     * @return the distance between the two points
     */
    public double distance(Point other) {
        double d = (this.x - other.x) * (this.x - other.x) + (this.y - other.y) * (this.y - other.y);
        return Math.sqrt(d);
    }

    /**
     * The function checks if the points are equal.
     * @param other other point
     * @return true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (this.x == other.x && this.y == other.y) {
            return true;
        }
        return false;
    }

    /**
     * A query.
     * @return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * A query.
     * @return the y value of this point
     */
    public double getY() {
        return this.y;
    }

    /**
     * The function sets the x value of the point.
     * @param newX new x value for the point
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * The function sets the y value of the point.
     * @param newY new y value fo the point
     */
    public void setY(double newY) {
        this.y = newY;
    }
}
