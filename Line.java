package com.SideScroller;

import java.awt.*;

public class Line {

	// MEMBER VARIABLES
	int x0;
	int y0;

	int x1;
	int y1;

	// CONSTRUCTOR
	public Line(int x0, int y0, int x1, int y1) {

		this.x0 = x0;
		this.y0 = y0;

		this.x1 = x1;
		this.y1 = y1;

		double dx = x1 - x0;
		double dy = y1 - y0;

		double mag = Math.sqrt(dx * dx + dy * dy);

		double xu = dx / mag;
		double yu = dy / mag;

	}

	// FUNCTIONS

}
