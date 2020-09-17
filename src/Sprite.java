import biuoop.DrawSurface;
/**
 * An animated object with access to timePassed.
 * @author Naveh Marchoom
 */
public interface Sprite {
    /**
     * Draw the sprite on the Drawsurface.
     * @param board the drawsurface.
     */
    void drawOn(DrawSurface board);

    /**
     * Notify the sprite that time has passed.
     */
    void timePassed();
}
