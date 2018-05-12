package com.SideScroller;

import java.awt.Frame;
import java.awt.Graphics;

public class Player extends Sprite {

	// MEMBER VARIABLES
	static int w = 50;
	static int h = 46;
	static int imw = -50;
	// int pose;

	static final int STRAIGHT = 0;
	static final int BACK = 4;
	static final int JUMP = 2;
	static final int ATTACK = 3;
	
	static final double GRAVITY = .3;
	
	double ax = 0;
	double ay = GRAVITY;
	double vy = 0;

	Animation[] animation;
	Animation atkAnim;

	boolean moving = false;
	boolean attack = false;

	int pose = STRAIGHT;

	// CONSTRUCTOR
	public Player(int x, int y, String file, String[] action, int count, int duration) {
		super(x, y, file, action, count, duration, w, h);

		animation = new Animation[action.length];

		for (int i = 0; i < action.length; i++) {
			animation[i] = new Animation(file + action[i] + "_", count, duration);
		}

	}

	// FUNCTIONS
	public void jump() {

		//*y -= dy;
		if (y >= 0)
			y = 0;//*/
		moving = true;
		pose = JUMP;

	}

	public void moveLeftBy(int dx) {

		x -= dx;
		if (x <= 0)
			x = 0;
		moving = true;
		pose = BACK;
	}

	public void setAcceleration(double ay) {
		this.ay += ay;
	}

	public void setVelocity(double vy) {
		this.vy = vy;
	}
	
	public void moveRightBy(int dx) {

		x += dx;
		if (x >= 945)
			x = 945;
		moving = true;
		pose = STRAIGHT;
	}

	public void isAtkng(boolean a) {
		attack = a;
		pose = ATTACK;
	}

	public void draw(Graphics g) {
		if (moving) {
			g.drawImage(animation[pose].nextImage(), (int) x, (int) y, null);
		} // *
		else {
			g.drawImage(animation[pose].StillImage(), (int) x, (int) y, null);
		} // */

		/*
		 * if (attack || (attack && moving)) { System.out.println("zero attacking.");
		 * g.drawImage(animation[pose].nextAttack(), x, y, null); } else {
		 * g.drawImage(animation[pose].StillImage(), x, y, null); }//
		 */

		attack = false;
		moving = false;

		super.draw(g);
	}

}
