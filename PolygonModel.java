import java.awt.Graphics;

import com.SideScroller.Lookup;

public abstract class PolygonModel {

  //MEMBER VARIABLES
	double x;
	double y;
	double A;

	final int[][] struct_x = { { 35, 35, -35, -35 }, { 5, 5, -5, -5 }, { 30, 30, 5, 5 }, { 28, 28, -28, -28 },
			{ 28, 28, -28, -28 } };
	final int[][] struct_y = { { -25, 25, 25, -25 }, { -13, 13, 13, -13 }, { -3, 3, 3, -3 }, { 25, 33, 33, 25 },
			{ -25, -33, -33, -25 } };

	static final double radius = 35;

  //CONSTRUCTOR
	public PolygonModel(int x, int y, int A) {

		this.x = x;
		this.y = y;
		this.A = A;

	}

  //FUNCTIONS
	public void draw(Graphics g) {

		int[] xp = new int[4];
		int[] yp = new int[4];

		double sinA = Lookup.sinA[(int) A];
		double cosA = Lookup.cosA[(int) A];
		//Loop to decide the amount of structures you have to draw.
		for (int poly = 0; poly < struct_x.length; poly++) {// this sets which polygon you want to draw in your array.

			for (int vert = 0; vert < struct_x[poly].length; vert++) {//picks the values to draw the polygons with.
				xp[vert] = (int) (struct_x[poly][vert] * cosA - struct_y[poly][vert] * sinA + x);
				yp[vert] = (int) (struct_x[poly][vert] * sinA + struct_y[poly][vert] * cosA + y);
			}
			g.drawPolygon(xp, yp, 4);
		}

	}

	// Makes the tank able to move in the direction it is facing.
	public void moveForwardBy(int direction) {

		x += (direction * Math.cos(A * Math.PI / 180));
		y += (direction * Math.sin(A * Math.PI / 180));

	}

	public void moveBackwardBy(int direction) {

		x -= (direction * Math.cos(A * Math.PI / 180));
		y -= (direction * Math.sin(A * Math.PI / 180));

	}

	public boolean contains(int mx, int my) {

		int dist2 = (mx - (int) x) * (mx - (int) x) + (my - (int) y) * (my - (int) y);

		return dist2 < radius * radius;

	}

	// moves points in the tank equally
	public void moveBy(int dx, int dy) {

		x += dx;
		y += dy;

	}

	// rotates object by delta A
	public void rotateBy(int dA) {

		A += dA;

		if (A >= 360)
			A -= 360;
		if (A < 0)
			A += 360;

	}

	public void rotateLeftBy(int dA) {

		A -= dA;
		if (A < 0)
			A += 360;

	}

	public void rotateRightBy(int dA) {

		A += dA;

		if (A >= 360)
			A -= 360;

	}

}