/**
 * @author Naveh Marchoom
 */
public class Velocity {

    private double dX, dY;
    /**
     * Construct a Velocity object built from the change in the 'x' and 'y' axis.
     * @param dx the change in the 'x' axis.
     * @param dy the change in the 'y' axis.
     */
    public Velocity(double dx, double dy) {
        this.dX = dx;
        this.dY = dy;
    }

    /**
     * Get a point with the position (x,y) and add this velocity to the the 'x' and 'y' axis
     * of the point.
     * @param p the original point
     * @return a new point with the values of (x+dx,y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dX, p.getY() + this.dY);
    }

    /**
     * Create a Velocity object using angle and speed.
     * @param angle the movement angle.
     * @param speed the movement speed.
     * @return a new velocity object created out of the angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Get the dx value.
     * @return dx
     */
    public double getDx() {
        return this.dX;
    }

    /**
     * Get the dy value.
     * @return dy
     */
    public double getDy() {
        return this.dY;
    }

    /**
     * Swap the x velocity to the opposite direction.
     */
    public void swapXDirection() {
        this.dX = this.dX * (-1);
    }

    /**
     * Swap the y velocity to the opposite direction.
     */
    public void swapYDirection() {
        this.dY = this.dY * (-1);
    }

    /**
     * Get the current movement speed.
     * @return speed
     */
    public double getSpeed() {
        Point p = new Point(1, 1);
        return p.distance(this.applyToPoint(p));
    }
}