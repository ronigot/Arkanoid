/**
 * A line (actually a line-segment) connects two points -- a start point and an end point.
 * Lines have lengths, and may intersect with other lines.
 * It can also tell if it is the same as another line segment.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * constructor.
     * @param start one endpoint
     * @param end another endpoint
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     * @param x1 the x value of one endpoint
     * @param y1 the y value of one endpoint
     * @param x2 the x value of another endpoint
     * @param y2 the y value of another endpoint
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * The function calculates the length of the line.
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * The function calculates the midpoint of the line.
     * @return the midpoint of the line
     */
    public Point middle() {
        double middleX = ((this.start.getX() + this.end.getX()) / 2);
        double middleY = ((this.start.getY() + this.end.getY()) / 2);
        return new Point(middleX, middleY);
    }

    /**
     * A query.
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * A query.
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * The function checks if there is an intersection point between vertical and notVertical and finds it.
     * @param vertical vertical line
     * @param notVertical notVertical line
     * @return the insertion point between the segments (null if there is no)
     */
    private Point intersectionWithVertical(Line vertical, Line notVertical) {
        double startX = Math.min(notVertical.start.getX(), notVertical.end.getX());
        double endX = Math.max(notVertical.start.getX(), notVertical.end.getX());
        double xVertical = vertical.start.getX();
        if (xVertical < startX || xVertical > endX) {
            return null;
        }
        // finding the equation of notVertical
        double dx = notVertical.start.getX() - notVertical.end.getX();
        double dy = notVertical.start.getY() - notVertical.end.getY();
        double m = dy / dx;
        double b = notVertical.start.getY() - m * notVertical.start.getX();
        // Finding the y value in the notVertical segment for the x value of the vertical
        double yVertical = m * xVertical + b;
        double minY = Math.min(vertical.start.getY(), vertical.end.getY());
        double maxY = Math.max(vertical.start.getY(), vertical.end.getY());
        // Check if the point is on the vertical
        if (yVertical >= minY && yVertical <= maxY) {
            return new Point(xVertical, yVertical);
        }
        return null;
    }

    /**
     * The function checks which point is the minimum-
     * which point is closer to the origin.
     * @param a point to check
     * @param b point to check
     * @return the min point
     */
    private Point minPoint(Point a, Point b) {
        // point closest to 0
        Point origin = new Point(0, 0);
        if (a.distance(origin) < b.distance(origin)) {
            return a;
        }
        return b;
    }

    /**
     * The function checks which point is the maximum-
     * which point is farther then the origin.
     * @param a point to check
     * @param b point to check
     * @return the max point
     */
    private Point maxPoint(Point a, Point b) {
        // point farthest from 0
        Point origin = new Point(0, 0);
        if (a.distance(origin) > b.distance(origin)) {
            return a;
        }
        return b;
    }

    /**
     * The function checks if two lines are intersect at the endpoints and finds it.
     * @param line1 line to check
     * @param line2 line to check
     * @return the intersection point if the lines are intersect, and null otherwise
     */
    private Point linesIntersectAtEndpoint(Line line1, Line line2) {
        // minPoint is the minimum of the minimum point of each segment
        // maxPoint is the maximum of the maximum point of each segment
        Point minPoint = minPoint(minPoint(line1.start, line1.end), minPoint(line2.start, line2.end));
        Point maxPoint = maxPoint(maxPoint(line1.start, line1.end), maxPoint(line2.start, line2.end));
        // Check if the sum of the segments lengths equals the distance
        // between the top of the top segment and the bottom of the bottom segment
        final double epsilon = Math.pow(10, -14);
        if (Math.abs(minPoint.distance(maxPoint) - line1.length() - line2.length()) < epsilon) {
            Point intersection = maxPoint(minPoint(line1.start, line1.end), minPoint(line2.start, line2.end));
            return intersection;
        }
        return null;
    }

    /**
     * The function checks if there is an insertion point and finds it (if there is).
     * @param other other line to check
     * @return the intersection point if the lines are intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        double dx1 = this.start.getX() - this.end.getX();
        double dx2 = other.start.getX() - other.end.getX();
        // Both segments are vertical
        if (dx1 == 0 && dx2 == 0) {
            if (this.start.getX() != other.start.getX()) {
                return null;
            }
            return linesIntersectAtEndpoint(this, other);
        }
        // One of the segments is vertical
        if (dx1 == 0 || dx2 == 0) {
            Line verticalLine;
            Line notVerticalLine;
            if (dx1 == 0) {
                verticalLine = this;
                notVerticalLine = other;
            } else {
                verticalLine = other;
                notVerticalLine = this;
            }
            return intersectionWithVertical(verticalLine, notVerticalLine);
        }
        // Constructing the Equations y = mx + b
        double dy1 = this.start.getY() - this.end.getY();
        double dy2 = other.start.getY() - other.end.getY();
        double m1 = dy1 / dx1;
        double m2 = dy2 / dx2;
        double b1 = this.start.getY() - m1 * this.start.getX();
        double b2 = other.start.getY() - m2 * other.start.getX();
        // The inclines are equal
        if (m1 == m2) {
            if (b1 != b2) {
                return null;
            }
            return linesIntersectAtEndpoint(this, other);
        }
        // Finding the x value of the intersection point
        double x = (b2 - b1) / (m1 - m2);
        // Check if the point is within the range of the two segments
        double minX1 = Math.min(this.start.getX(), this.end.getX()),
                minX2 = Math.min(other.start.getX(), other.end.getX()),
                maxX1 = Math.max(this.start.getX(), this.end.getX()),
                maxX2 = Math.max(other.start.getX(), other.end.getX());
        if (x <= Math.min(maxX1, maxX2) && x >= Math.max(minX1, minX2)) {
            // Finding the y value
            double y = m1 * x + b1;
            return new Point(x, y);
        }
        return null;

    }

    /**
     * The function checks if two lines are intersect.
     * @param other line to check
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return intersectionWith(other) != null;
    }

    /**
     * The function checks if the point is on this line.
     * @param p Point to check
     * @return true - if p is on the line. false - otherwise.
     */
    public boolean containsPoint(Point p) {
        double startToP = p.distance(this.start);
        double endToP = p.distance(this.end);
        final double epsilon = Math.pow(10, -12);
        if (Math.abs(this.length() - (startToP + endToP)) < epsilon) {
            return true;
        }
        return false;
    }


    /**
     * The function checks if two lines are equal.
     * @param other other line to check
     * @return returns true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.start.equals(other.end) && this.end.equals(other.start));
    }


    /**
     * The function returns null if this line does not intersect with the rectangle.
     * Otherwise, return the closest intersection point to the start of the line.
     * @param rect rectangle to check intersection
     * @return return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(this);
        // if this line does not intersect with the rectangle
        if (points.size() == 0) {
            //System.out.println("0");
            return null;
        }
        // if there is only one intersection point
        if (points.size() == 1) {
            //System.out.println("1");
            return points.get(0);
        }
        // if there are two intersection points
        if (points.get(0).distance(this.start) > points.get(1).distance(this.start)) {
            return points.get(1);
        }
        return points.get(0);
    }

}
