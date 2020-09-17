/**
 * Rectangle class.
 * @author Naveh Marchoom
 */
class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Create a new rectangle using a corner, width and height.
     * @param upperLeftCorner the upper left corner of the rectangle.
     * @param wid the width of the rectangle.
     * @param hei the height of the rectangle.
     */
    Rectangle(Point upperLeftCorner, double wid, double hei) {
        this.upperLeft = upperLeftCorner;
        this.width = wid;
        this.height = hei;
    }

    /**
     * Create a possible empty list of intersection points.
     * @param line the line it check intersection with.
     * @return a list of all the intersections with the line.
     */
    public java.util.ArrayList<Point> intersectionPoints(Line line) {
        java.util.ArrayList<Point> intersections = new java.util.ArrayList<Point>();
        if (line.isIntersecting(this.getUpperBorder())) {
            intersections.add(line.intersectionWith(this.getUpperBorder()));
        }
        if (line.isIntersecting(this.getLeftBorder())) {
            intersections.add(line.intersectionWith(this.getLeftBorder()));
        }
        if (line.isIntersecting(this.getLowerBorder())) {
            intersections.add(line.intersectionWith(this.getLowerBorder()));
        }
        if (line.isIntersecting(this.getRightBorder())) {
            intersections.add(line.intersectionWith(this.getRightBorder()));
        }
        return intersections;
    }

    /**
     * Get the width of the rectangle.
     * @return width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get the height of the rectangle.
     * @return height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Get the upper left corner point of the rectangle.
     * @return upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Get the top border of the rectangle.
     * @return Line, top border.
     */
    public Line getUpperBorder() {
        Point start = this.upperLeft;
        Point end = new Point(upperLeft.getX() + this.width, upperLeft.getY());
        return new Line(start, end);
    }

    /**
     * Return the bottom border of the rectangle.
     * @return Line, bottom border.
     */
    public Line getLowerBorder() {
        Point start = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        Point end = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(start, end);
    }

    /**
     * Get the left border of the rectangle.
     * @return Line, left border of the rectangle.
     */
    public Line getLeftBorder() {
        Point start = this.upperLeft;
        Point end = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        return new Line(start, end);
    }

    /**
     * Get the right border of the rectangle.
     * @return Line, the right border of the rectangle.
     */
    public Line getRightBorder() {
        Point start = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        Point end = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        return new Line(start, end);
    }
}