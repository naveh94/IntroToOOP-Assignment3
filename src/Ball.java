import biuoop.DrawSurface;

/**
 * @author Naveh Marchoom
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity movement = new Velocity(0, 0);
    private GameEnvironment environment = null;

    /**
     * Construct a new ball using an existing point, the ball's radius, and the ball's color.
     * @param center The position of the ball.
     * @param r The size of the ball.
     * @param color The color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
    }

    /**
     * Construct a new ball using a point parameters, the ball's radius, and the ball's color.
     * @param x The position of the ball on the 'x' axis.
     * @param y The position of the ball on the 'y' axis.
     * @param r The size of the ball.
     * @param color The color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = r;
        this.color = color;
    }

    /**
     * Get the position of the ball on the 'x' axis.
     * @return the 'x' value of the ball's center.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Get the position of the ball on the 'y' axis.
     * @return the 'y' value of the ball's center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Get the size of the ball.
     * @return the value of the ball's radius.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Get the color of the ball.
     * @return the color value of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Draw the ball on a given surface.
     * @param surface the surface the ball should be drawn at.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Set the ball's velocity using an existing Velocity object.
     * @param v the ball's new velocity.
     */
    public void setVelocity(Velocity v) {
        this.movement = v;
    }

    /**
     * Set the ball's velocity using dx and dy values.
     * @param dx the ball's new 'x' value's velocity.
     * @param dy the ball's new 'y' value's velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.movement = new Velocity(dx, dy);
    }

    /**
     * Get the ball's velocity.
     * @return the ball's Velocity value.
     */
    public Velocity getVelocity() {
        return movement;
    }

    /**
     * Move the ball one step forward using the ball's velocity values.
     */
    public void moveOneStep() {

        if (environment != null) {
            if (this.environment.getClosestCollision(this.getTrajectory()) != null) {
                Point colP = new Point(this.environment.getClosestCollision(this.getTrajectory()).collisionPoint());
                Collidable colOb = this.environment.getClosestCollision(getTrajectory()).collisionObject();
                this.setVelocity(colOb.hit(colP, this.getVelocity()));
            }
        }
        /**/
        // Move the ball one step forward:
        this.center = this.getVelocity().applyToPoint(this.center);
        this.center = new Point(this.center);
    }

    /**
     * Set the ball's environment to a specific game environment.
     * @param game The environment.
     */
    public void setEnvironment(GameEnvironment game) {
        this.environment = game;
    }

    /**
     * Get the current trajectory line of the ball.
     * @return a trajectory line.
     */
    public Line getTrajectory() {
        Point end = new Point(this.center);
        // Calculate using current ball's velocity where the ball will change direction:
        end = this.getVelocity().applyToPoint(end);
        // Return a line that start at current ball's location and end where it will change velocity;
        return new Line(this.center, end);
    }

    /**
     * TIme has passed. Move the ball ones.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Add this ball to the game.
     * @param game the game.
     */
    public void addToGame(Game game) {
        game.addToSprites(this);
    }
}