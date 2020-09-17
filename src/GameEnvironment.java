/**
 * Contains a list of all the collidable objects on the board.
 * @author Naveh Marchoom
 */
public class GameEnvironment {

    private java.util.ArrayList<Collidable> collidables = new java.util.ArrayList<Collidable>();

    /**
     * Add a specific collidable object into the game envirnoment.
     * @param c the collidable added.
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Check which objects will collide with the given trajectory and returns the closest one
     *  to the trajectory start.
     * @param trajectory the given trajectory.
     * @return the collision info of the closest collision.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // Make a list of all the objects that collide with the trajectory:
        java.util.ArrayList<Collidable> inters = new java.util.ArrayList<Collidable>();
        for (int i = 0; i < this.collidables.size(); i++) {
            if (trajectory.closestIntersectionToStartOfLine(collidables.get(i).getCollisionRectangle()) != null) {
                inters.add(collidables.get(i));
            }
        }

        // If no objects collide with the trajectory:
        if (inters.isEmpty()) {
            return null;
        }

        // Find which collision point is closest to the start of the trajectory line:
        Collidable closest = inters.get(0);
        Point collision = trajectory.closestIntersectionToStartOfLine(inters.get(0).getCollisionRectangle());
        for (int i = 1; i < inters.size(); i++) {
            Point collision2 = trajectory.closestIntersectionToStartOfLine(inters.get(i).getCollisionRectangle());
            if (trajectory.start().distance(collision2) < trajectory.start().distance(collision)) {
                closest = inters.get(i);
                collision = collision2;
            }
        }

        // return the CollisionInfo of the closest collision.
        return new CollisionInfo(collision, closest);
    }
}