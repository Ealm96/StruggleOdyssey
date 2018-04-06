import java.awt.*;

public class SpaceShip {
  
  //MEMBER VARIABLES
	double x;
	double y;
	double A;

	static final int[] bodyx = { 10, 10, -10, -10 };
	static final int[] bodyy = { -20, 20, 20, -20 };

	static final int[] gunx = { 25, 25, 10, 10 };
	static final int[] guny = { -5, 5, 5, -5 };

	static final int[] leftWingx = { 10, -10, -10, -10 };
	static final int[] leftWingy = { 20, 35, 35, 20 };

	static final int[] rightWingx = { 10, -10, -10, -10 };
	static final int[] rightWingy = { -20, -35, -35, -20 };

  //CONSTRUCTOR
	public SpaceShip(int x, int y) {

		this.x = x;
		this.y = y;
	}

  //FUNCTIONS
	public boolean isFiring(boolean b) {
		boolean fire = b;
		return b;
	}

	public void draw(Graphics g) {

		int[] xp = new int[4];
		int[] yp = new int[4];

		double radians = A * Math.PI / 180;

		double sinA = Math.sin(radians);
		double cosA = Math.cos(radians);

		for (int i = 0; i < 4; i++) {
			xp[i] = (int) (bodyx[i] * cosA - bodyy[i] * sinA + x);
			yp[i] = (int) (bodyx[i] * sinA + bodyy[i] * cosA + y);
		}
		g.drawPolygon(xp, yp, 4);
		for (int i = 0; i < 4; i++) {
			xp[i] = (int) (gunx[i] * cosA - guny[i] * sinA + x);
			yp[i] = (int) (gunx[i] * sinA + guny[i] * cosA + y);
		}
		g.drawPolygon(xp, yp, 4);
		for (int i = 0; i < 4; i++) {
			xp[i] = (int) (leftWingx[i] * cosA - leftWingy[i] * sinA + x);
			yp[i] = (int) (leftWingx[i] * sinA + leftWingy[i] * cosA + y);
		}
		g.drawPolygon(xp, yp, 4);
		for (int i = 0; i < 4; i++) {
			xp[i] = (int) (rightWingx[i] * cosA - rightWingy[i] * sinA + x);
			yp[i] = (int) (rightWingx[i] * sinA + rightWingy[i] * cosA + y);
		}
		g.drawPolygon(xp, yp, 4);
	}

	// moves points in the tank equally
	public void flyBy(int dx, int dy) {

		x += dx;
		y += dy;

	}

	/*
	 * distance of a line is (cosA,sinA) and to get it to travel in the exact
	 * same direction you do (direction*cosA,direction*sinA)
	 */

}