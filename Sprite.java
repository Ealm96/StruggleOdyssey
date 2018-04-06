package com.SideScroller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;

import com.SideScroller.Animation;

public abstract class Sprite {

	//MEMBER VARIABLES
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	int x;
	int y;
	int dx = 0;
	int dy = 0;

	static final int STRAIGHT = 0;
	static final int UP = 1;
	static final int DOWN = 2;
	static final int ATTACK = 3;

	Animation[] animation;
	Animation atkAnim;

	boolean moving = false;

	int pose = STRAIGHT;

	//CONSTRUCTOR
	public Sprite(int x, int y, String file, String[] action, int count, int duration) {

		this.x = x;
		this.y = y;

		animation = new Animation[action.length];

		for (int i = 0; i < action.length; i++) {
			animation[i] = new Animation(file + action[i], count, duration);
		}
	}
	
	
	//FUNCTIONS
	public void move() {
		x += dx;
		y += dy;
	}

	public void moveUpBy(int dy) {

		y -= dy;
		if (y <= 0)
			y = 0;
		moving = true;
		pose = UP;
	}

	public void moveDownBy(int dy) {

		y += dy;
		if (y >= 370)
			y = 370;
		moving = true;
		pose = DOWN;
	}

	public void moveLeftBy(int dx) {

		x -= dx;
		if (x <= 0)
			x = 0;
		moving = true;
		pose = STRAIGHT;
	}

	public void moveRightBy(int dx) {

		x += dx;
		if (x >= 945)
			x = 945;
		moving = true;
		pose = STRAIGHT;
	}

	public void checkBounds() {

		if (x <= 0)
			x = 0;
		if (y <= 0)
			y = 0;
		if (x >= dim.width)
			x = dim.width;
		if (y >= dim.height)
			y = dim.height;

	}

	public void draw(Graphics g) {

		if (moving && pose == UP || pose == DOWN) {
			g.drawImage(animation[pose].nextFlight(), x, y, null);
		} else {
			g.drawImage(animation[pose].StillImage(), x, y, null);
		}

		moving = false;
	}
	// */
}