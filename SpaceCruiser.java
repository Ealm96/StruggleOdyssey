package com.SideScroller;

import com.SideScroller.Rect;
import java.awt.Graphics;

public class SpaceCruiser extends Sprite {

	// MEMBER VARIABLES
	Rect r;
	int hp = 3;
	int delay = 0;
	int dmgDelay = 100;
	int delayTime = 15;
	int elapsed = 70;

	protected int kCount = 0;

	boolean cooldown = false;
	boolean dmgTaken = false;

	static int w = 55;
	static int h = 30;

	protected int points = 0;
	protected long tpoints;

	// CONSTRUCTOR
	public SpaceCruiser(int x, int y, String file, String[] action, int count, int duration) {
		super(x, y, file, action, count, duration, w, h);
	}

	// FUNCTIONS
	public void shoot(Rect Shell) {
		r = Shell;

		if (cooldown) {
			r.setLocation(this.x + 30, this.y + 15);
			double vx = 7;
			r.setVelocity(vx);
			System.out.println("Shooting");
			cooldown = false;
			delay = delayTime;
		}
	}

	public void CoolDown() {
		if (delay == 0) {
			cooldown = true;
		} else {
			delay--;
		}
	}

	public void invFrame() {

		if (dmgDelay == 0) {
			dmgTaken = false;
			dmgDelay = 100;
		} else {
			dmgDelay--;
		}

	}

	public void increase() {
		if (elapsed == 0) {
			points++;
			this.amount = "" + points;
			elapsed = 70;
		} else {
			elapsed--;
		}
	}

}
