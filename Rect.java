import java.awt.*;

public class Rect {

	//MEMBER VARIABLES
	int x;
	int y;
	int w;
	int h;
	int dx = 0;
	int dy = 0;

	boolean held = false;

	//CONSTRUCTOR
	public Rect(int x, int y, int w, int h) {

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

	}
	
	
	//FUNCTIONS
	public boolean overlaps(Rect r) { // checks to see if a rect is touching or inside another rect.

		return (x < r.x + r.w) && (x + w > r.x) && (y < r.y + r.h) && (y + h > r.y);

	}

	public boolean contains(int mx, int my) {

		return mx > x && mx < x + w && my > y && my < y + h;

	}

	public void grab() {

		held = true;

	}

	public void drop() {
		held = false;
	}

	public void draw(Graphics g) {

		g.drawRect(x, y, w, h);
		if(x == 1000)
			g.dispose();

	}

	public void moveBy(int dx, int dy) {

		x += dx;
		y += dy;

	}

	public void setLocation(double x, double y) {
		this.x = (int) x;
		this.y = (int) y;
	}

	public void setVelocity(double vx) {
		dx = (int) vx;
	}

	public void move() {
		x += dx;
	}

}