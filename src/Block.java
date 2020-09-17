import biuoop.DrawSurface;
/**
 * Block object.
 * @author Naveh Marchoom
 */
public class Block implements Collidable, Sprite {

    private Point topLeftCorner;
    private double width;
    private double height;
    private int hitpoints = 0;
    private java.awt.Color color = java.awt.Color.BLACK;

    /**
     * Create a new block using the top left corner, the width and the height of the block.
     * @param topLeftCorner point.
     * @param width double.
     * @param height double.
     */
    public Block(Point topLeftCorner, double width, double height) {
        this.topLeftCorner = topLeftCorner;
        this.width = width;
        this.height = height;
    }

    /**
     * Get the collision rectangle of the block.
     * @return the collision rectangle of the block.
     */
    public Rectangle getCollisionRectangle() {
        return new Rectangle(this.topLeftCorner, this.width, this.height);
    }

    /**
     * Notify the block it has been hit, and return the new velocity should be set to the ball.
     * @param collisionPoint the point of collision.
     * @param currentVelocity the velocity it's been hit by.
     * @return the new velocity should be set to the ball.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Velocity newVelocity = currentVelocity;

        if (this.hitpoints > 0) {
            this.hitpoints--;
        }

        if ((int) collisionPoint.getX() == this.topLeftCorner.getX()
                || (int) collisionPoint.getX() == this.topLeftCorner.getX() + width) {
            newVelocity.swapXDirection();
        }
        if ((int) collisionPoint.getY() == this.topLeftCorner.getY()
                || (int) collisionPoint.getY() == this.topLeftCorner.getY() + height) {
            newVelocity.swapYDirection();
        }
        return newVelocity;
    }

    /**
     * Draw the block on the surface.
     * @param surface the Draw Surface.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillRectangle((int) this.topLeftCorner.getX(), (int) this.topLeftCorner.getY(),
                (int) this.width, (int) this.height);
        surface.setColor(java.awt.Color.BLACK);
        surface.drawRectangle((int) this.topLeftCorner.getX(), (int) this.topLeftCorner.getY(),
                (int) this.width, (int) this.height);
        if (this.hitpoints > 0) {
            surface.drawText((int) this.topLeftCorner.getX() + (int) this.width / 2 - 2,
                    (int) this.topLeftCorner.getY() + 14, "" + this.hitpoints, 11);
        } else if (this.hitpoints == 0) {
            surface.drawText((int) this.topLeftCorner.getX() + (int) this.width / 2 - 2,
                    (int) this.topLeftCorner.getY() + 14, "X", 11);
        }
    }

    /**
     * Time has passed.
     */
    public void timePassed() {
        // Does nothing; It's a block! :D
    }

    /**
     * Add this block to the game.
     * @param game the game
     */
    public void addToGame(Game game) {
        game.addToEnvirnoment(this);
        game.addToSprites(this);
    }

    /**
     * Set the hit points of the block. By default 0;
     * @param hp the hit points should be set for the block.
     */
    public void setHitPoints(int hp) {
        this.hitpoints = hp;
    }

    /**
     * Set the block color to a new color.
     * @param c the color the block should set.
     */
    public void setColor(java.awt.Color c) {
        this.color = c;
    }
}
