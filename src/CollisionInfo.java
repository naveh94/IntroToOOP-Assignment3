/**
 * A collision information object.
 * @author Naveh Marchoom
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable collisionObject;

    /**
     * Create a new collision object.
     * @param collisionPoint the point in which the collision occurs.
     * @param collisionObject the collidable object involved in the collision
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * @return the collision point.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }

    /**
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collisionObject;
    }

    /**
     * Check if the collision point is on a horizontal border of the collidable object.
     * @return true if the collision is horizontal, else return false.
     */
    public boolean isHorizontalCollision() {
        if (collisionObject.getCollisionRectangle().getUpperBorder().isOnLine(this.collisionPoint)) {
            return true;
        }
        if (collisionObject.getCollisionRectangle().getLowerBorder().isOnLine(this.collisionPoint)) {
            return true;
        }
        return false;
    }

    /**
     * Check if the collision point is on a vertical border of the collidable object.
     * @return true if the collision is vertical, else return flase.
     */
    public boolean isVerticalCollision() {
        if (this.collisionObject.getCollisionRectangle().getLeftBorder().isOnLine(this.collisionPoint)) {
            return true;
        }
        if (this.collisionObject.getCollisionRectangle().getRightBorder().isOnLine(this.collisionPoint)) {
            return true;
        }
        return false;
    }
}