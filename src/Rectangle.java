// ID: 322805029

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle with location and width, height.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle with location and width-height.
     * @param upperLeft the upperLeft point of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * The function returns the width of the rectangle.
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * The function returns the height of the rectangle.
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * The function returns the upper-left point of the rectangle.
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * The function returns the upper-right point of the rectangle.
     * @return the upper-right point of the rectangle
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * The function returns the lower-left point of the rectangle.
     * @return the lower-left point of the rectangle
     */
    public Point getLowerLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * The function updates the top left point.
     * @param newPoint new top left point.
     */
    public void setUpperLeft(Point newPoint) {
        this.upperLeft = newPoint;
    }

    /**
     * The function returns the lower-right point of the rectangle.
     * @return the lower-right point of the rectangle
     */
    public Point getLowerRight() {
        return new Point(getUpperRight().getX(), getLowerLeft().getY());
    }

    /**
     * The function gets a list and a point and checks if the point is in the list.
     * @param p point to check
     * @param points a list of points
     * @return true - if the point is in the list, false - otherwise
     */
    private boolean contains(Point p, List<Point> points) {
        for (Point other : points) {
            if (other.equals(p)) {
                return true;
            }
        }
        return false;
    }
    /**
     * The function adds to the list the intersectionPoint between the line and the edge.
     * @param points List of points
     * @param line line to check intersection
     * @param edge edge of the rectangle to check intersection
     */
    private void addToList(java.util.List<Point> points, Line line, Line edge) {
        Point intersectionPoint = line.intersectionWith(edge);
        if (intersectionPoint != null && !contains(intersectionPoint, points)) {
            points.add(intersectionPoint);
        }
    }

    /**
     * The function returns a (possibly empty) List of intersection points
     * with the specified line.
     * @param line line to check intersection
     * @return List of intersection points
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> points = new ArrayList<>();

        // check intersectionPoint (and insert list if any) for each of the rectangle edges.
        addToList(points, line, new Line(this.upperLeft, this.getUpperRight()));
        addToList(points, line, new Line(this.upperLeft, this.getLowerLeft()));
        addToList(points, line, new Line(this.getLowerLeft(), this.getLowerRight()));
        addToList(points, line, new Line(this.getUpperRight(), this.getLowerRight()));

        return points;
    }
}
