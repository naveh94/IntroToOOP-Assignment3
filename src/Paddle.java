import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
/**
 * The Paddle the user controls.
 * @author Naveh Marchoom
 *
 */
public class Paddle implements Collidable, Sprite {

    private Point topLeftCorner;
    private double width = 20;
    private double height = 8;
    private biuoop.KeyboardSensor keyboard;
    private java.awt.Color color = java.awt.Color.BLACK;
    private int leftBorder = Integer.MIN_VALUE, rightBorder = Integer.MAX_VALUE;

    /**
     * Create a paddle using corner, width and keyboard parameters.
     * @param topLeftCorner The top left corner of the paddle
     * @param width the width of the paddle.
     * @param keyboard the keyboard sensor sensing keyboard clicks.
     */
    public Paddle(Point topLeftCorner, double width, biuoop.KeyboardSensor keyboard) {
        this.topLeftCorner = topLeftCorner;
        this.width = width;
        this.keyboard = keyboard;
    }

    /**
     * Set the paddle's borders.
     * @param left left border.
     * @param right right border.
     */
    public void setBorders(int left, int right) {
        this.leftBorder = left;
        this.rightBorder = right;
    }

    /**
     * Set the color of the paddle.
     * @param c the color.
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }

    /**
     * Move the paddle right.
     */
    public void moveRight() {
        if (this.topLeftCorner.getX() + this.width < this.rightBorder - 5) {
            Point p = new Point(this.topLeftCorner.getX() + 5, this.topLeftCorner.getY());
            this.topLeftCorner = p;
        }
    }

    /**
     * Move the paddle left.
     */
    public void moveLeft() {
        if (this.topLeftCorner.getX() > this.leftBorder + 5) {
            Point p = new Point(this.topLeftCorner.getX() - 5, this.topLeftCorner.getY());
            this.topLeftCorner = p;
        }
    }

    /**
     * Draw the paddle on the surface.
     * @param board The draw surface it would be drawn on.
     */
    public void drawOn(DrawSurface board) {
        board.setColor(color);
        board.fillRectangle((int) topLeftCorner.getX(), (int) topLeftCorner.getY(), (int) width, (int) height);
        board.setColor(java.awt.Color.BLACK);
        board.drawRectangle((int) topLeftCorner.getX(), (int) topLeftCorner.getY(), (int) width, (int) height);
    }

    /**
     * Notify the paddle time has passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Get the paddle's collision rectangle.
     * @return The paddle's collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.topLeftCorner, this.width, this.height);
    }

    /**
     * Notify the paddle it been hit by the ball, and get new velocity to the ball according to the
     * collision point.
     * @param collisionPoint The collision Point.
     * @param currentVelocity The collision velocity.
     * @return new velocity for the ball.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double region = 180 * ((collisionPoint.getX() - this.topLeftCorner.getX()) / width);
        if (this.getCollisionRectangle().getUpperBorder().isOnLine(collisionPoint)) {
            Velocity v = Velocity.fromAngleAndSpeed(region + 180, currentVelocity.getSpeed());
            return v;
            }
        if (this.getCollisionRectangle().getLowerBorder().isOnLine(collisionPoint)) {
            currentVelocity.swapYDirection();
            return currentVelocity;
        }
        if (this.getCollisionRectangle().getLeftBorder().isOnLine(collisionPoint)
                || this.getCollisionRectangle().getRightBorder().isOnLine(collisionPoint)) {
            currentVelocity.swapXDirection();
            return currentVelocity;
        }
        currentVelocity.swapYDirection();
        return currentVelocity;
    }

    /**
     * Add the paddle to an existing game.
     * @param game the game it should be added to.
     */
    public void addToGame(Game game) {
        game.addToEnvirnoment(this);
        game.addToSprites(this);
    }
}
