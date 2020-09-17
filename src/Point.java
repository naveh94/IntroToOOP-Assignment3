/**
 *
 * @author dell
 *
 */
public class Point {

    private double x, y;

    /**
     * Create a new point using given x and y parameters.
     * @param x - the point's x parameter.
     * @param y - the point's y parameter.
     */
    public Point(double x, double y) {
        this.x = Math.round(x * 10000.0) / 10000.0;
        this.y = Math.round(y * 10000.0) / 10000.0;
    }

    /**
     * Create a new point using the parameters from an existing one.
     * @param p an existing point.
     */
    public Point(Point p) {
        this.x = Math.round(p.getX() * 10000.0) / 10000.0;
        this.y = Math.round(p.getY() * 10000.0) / 10000.0;
    }

    /**
     * Calculated the distance between this point and the other point.
     * @param other - the other point.
     * @return The distance between the points.
     */
    public double distance(Point other) {
        double x1 = this.x, x2 = other.getX(), y1 = this.y, y2 = other.getY();
        double distance = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
        return distance;
    }

    /**
     * Check if the two points are equal to each other.
     * @param other - the other point
     * @return True if both points have same coordinates, else returns false.
     */
    public boolean equals(Point other) {
        return (this.x == other.getX() && this.y == other.getY());
    }

    /**
     * Get the x coordinate of the point.
     * @return The x coordinate.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Get the y coordinate of the point.
     * @return The y coordinate.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Get bound parameters and check if the point given is in those binds.
     * @param xMin the lowest x can get
     * @param yMin the lowest y can get
     * @param xMax the highest x can get
     * @param yMax the highest y can get
     * @return true if the point is in the bounds. else returns false.
     */
    public boolean inBounds(double xMin, double yMin, double xMax, double yMax) {
        if (this.x > xMin && this.x < xMax && this.y > yMin && this.y < yMax) {
            return true;
        }
        return false;
    }
}
