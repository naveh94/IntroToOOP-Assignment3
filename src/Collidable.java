/**
 * Collidable interface.
 * @author Naveh Marchoom
 */
public interface Collidable {

   /**
    * Get the collision rectangle of the collidable object.
    * @return rectangle.
    */
    Rectangle getCollisionRectangle();

   /**
    * Notify the object it got hit, and get a new velocity for the ball movement.
    * @param collisionPoint The point of the collision.
    * @param currentVelocity The collision velocity.
    * @return a new velocity for the ball.
    */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);
}