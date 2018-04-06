import javax.swing.JFrame;
import java.awt.*;

public class Bullet extends Rect {

	// CONSTRUCTOR
	public Bullet(int x, int y, int w, int h) {

		super(x, y, w, h);

	}
	// OVERRIDDEN FUNCTIONS
	public void draw(Graphics g) {
		g.fillOval(x, y, w, h);
		g.setColor(Color.BLUE);

	}

}