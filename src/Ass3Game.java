/**
 * The main class running the game.
 * @author Naveh Marchoom
 *
 */
public class Ass3Game {
    /**
     * Run the game.
     * @param args unused.
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize(500, 400);
        game.run();
    }
}
