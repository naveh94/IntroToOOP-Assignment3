import biuoop.DrawSurface;
import biuoop.GUI;

/**
 * A collection of all the sprites and collidable on the current game.
 * @author Naveh MArchoom
 */
public class Game {
    private SpriteCollection sprites = new SpriteCollection();
    private GameEnvironment environment = new GameEnvironment();
    private GUI gui;


    /**
     * Initialize a new game, creating the blocks, the ball and the paddle.
     * @param width The GUI width.
     * @param height The GUI height.
     */
    public void initialize(int width, int height) {
        gui = new GUI("Arkanoid", width, height);
        java.util.Random rand = new java.util.Random();

        // Create the GUI borders.
        this.createBorders(gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());

        // Create a grid of blocks.
        for (int i = 0; i < 10; i++) {
            java.awt.Color color = new java.awt.Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
            for (int j = (width - 35); j > (4 + 30 * i); j -= 30) {
                Block b = new Block(new Point(j, i * 20 + height / 10), 30, 20);
                if (i == 0) {
                    b.setHitPoints(2);
                } else {
                    b.setHitPoints(1);
                }
                b.setColor(color);
                b.addToGame(this);
            }
        }

        // Create and initialize the ball.
        Ball ball = new Ball(width / 2 - 5, height - 30, 3, java.awt.Color.yellow);
        ball.setVelocity(Velocity.fromAngleAndSpeed(270, 2));
        ball.setEnvironment(environment);
        ball.addToGame(this);
        Ball ball2 = new Ball(width / 2 + 5, height - 30, 3, java.awt.Color.yellow);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(270, 2));
        ball2.setEnvironment(environment);
        ball2.addToGame(this);

        // Create the paddle:
        Paddle p = new Paddle(new Point(width / 2 - 25, height - 14), 50, this.gui.getKeyboardSensor());
        p.setBorders(5, width - 5);
        p.setColor(new java.awt.Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
        p.addToGame(this);
    }

    /**
     * Run the game, starting the animated loop.
     */
    public void run() {
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        int framesPerSecond = 60;
        int milliSecondsPerFrame = 1000 / framesPerSecond;

        java.util.Random rand = new java.util.Random();
        // Create a background:
        java.util.ArrayList<Point> stars = new java.util.ArrayList<Point>();
        for (int i = 0; i < 30; i++) {
            stars.add(new Point(rand.nextDouble() * this.gui.getDrawSurface().getWidth() + 1,
                    rand.nextDouble() * (this.gui.getDrawSurface().getHeight() - 40) + 1));
        }


        while (true) {
            long startTime = System.currentTimeMillis(); // Timing
            DrawSurface board = gui.getDrawSurface();

            // Draw Background:
            board.setColor(java.awt.Color.BLUE.darker().darker().darker().darker());
            board.fillRectangle(0, 0, board.getWidth(), board.getHeight());
            board.setColor(java.awt.Color.WHITE);
            for (int i = 0; i < stars.size(); i++) {
                board.fillCircle((int) stars.get(i).getX(), (int) stars.get(i).getY(), 1);
            }

            this.sprites.drawOnAll(board);
            gui.show(board);
            this.sprites.notifyAllTimePassed();

            // Timing:
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondsLeftToSleep = milliSecondsPerFrame - usedTime;
            if (milliSecondsLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondsLeftToSleep);
            }
        }
    }

    /**
     * Create the borders of the game.
     * @param width The width of the GUI
     * @param height The height of the GUI
     */
    private void createBorders(int width, int height) {
        Block top = new Block(new Point(-1, 0), width + 2, 5);
        Block left = new Block(new Point(0, 4), 5, height - 3);
        Block right = new Block(new Point(width - 5, 4), 5, height - 3);
        Block bottom = new Block(new Point(-1, height - 5), width + 2, 5);
        // Set hit points to -1 so it won't appear on block.
        top.setHitPoints(-1);
        left.setHitPoints(-1);
        right.setHitPoints(-1);
        bottom.setHitPoints(-1);
        top.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);
        bottom.addToGame(this);
    }

    /**
     * Add a collidable to the game environment.
     * @param c collidable
     */
    public void addToEnvirnoment(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Add a sprite to the sprites collection.
     * @param s sprite.
     */
    public void addToSprites(Sprite s) {
        sprites.addSprite(s);
    }
}
