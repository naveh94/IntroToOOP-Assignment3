import biuoop.DrawSurface;
import biuoop.GUI;
import java.awt.Color;
/**
 * Tset
 * @author dell
 *
 */
public class LineTest {
	public static void main(String[] args) {
		GUI gui = new GUI("Test",600,300);
		DrawSurface board = gui.getDrawSurface();
		java.util.Random rand = new java.util.Random();
		for (int i = 0; i < 360; i++) {
			Point p = new Point(i , 150);
			Velocity v = Velocity.fromAngleAndSpeed(i, 30);
			Point p2 = v.applyToPoint(p);
			board.setColor(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
			board.drawLine((int)p.getX(), (int)p.getY(), (int)p2.getX(), (int)p2.getY());
		}
		board.setColor(Color.BLACK);
		board.drawLine(180, 60, 180, 300);
		board.drawLine(360, 60, 360, 300);
		gui.show(board);
	}
}
