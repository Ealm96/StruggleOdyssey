package com.SideScroller;

import java.awt.*;
import java.util.Random;

public class EnemyCruiser extends Rect {

	static final int STRAIGHT = 0;
	static final int UP = 1;
	static final int DOWN = 2;
	static final int ATTACK = 3;
	static final int DEATH = 4;

	Animation[] animation;

	boolean moving = false;
	boolean destroyed = false;
	boolean collide = false;
	boolean hitBB = false;
	boolean inRange = false;

	int pose = STRAIGHT;
	Random ran = new Random();
	private double mvmSpd = 10;
	int ran_y;
	int dCount = 10;

	// CONSTRUCTOR
	public EnemyCruiser(int x, int y, String file, String[] action, int count, int duration, int w, int h) {

		super(x, y, w, h);

		animation = new Animation[action.length];

		for (int i = 0; i < action.length; i++) {
			animation[i] = new Animation(file + action[i], count, duration);
		}
	}

	// FUNCTIONS

	public void approach(SpaceCruiser sc, Bullet b) {

		finalDeath();
		inRange();

		if (hit(sc) && sc.dmgTaken == false) {
			destroyed = true;
			sc.dmgTaken = true;
			sc.hp -= 1;
			this.x = 1500;
			sc.invFrame();
			System.out.println("Damage Taken\n" + "HP = " + sc.hp);
		}

		if (this.x < -20 && this.dCount != 0) {
			this.x = 1500;
		}

		if (this.x == 1500) {
			destroyed = false;
			collide = false;
			hitBB = false;
			inRange = false;
			this.y = Random();
			if (mvmSpd < 20)
				mvmSpd += .5;

		}

		this.x -= (int) mvmSpd;

	}

	public void finalDeath() {
		if (this.dCount == 0) {
			this.x = 1500;
			destroyed = true;
		}
	}
	
	public boolean inRange() {
		if(this.x >= 0 && this.x < 1000) {
			inRange = true;
		}
		return inRange;
	}

	public int Random() {
		ran_y = ran.nextInt(340) + 20;
		return ran_y;
	}

	public boolean hit(SpaceCruiser sc) {

		if (this.overlaps(sc))
			collide = true;
		return collide;
	}

	public void draw(Graphics g) {

		if (moving && pose == UP || pose == DOWN) {
			g.drawImage(animation[pose].nextFlight(), (int) x, (int) y, null);
		} else {
			g.drawImage(animation[pose].StillImage(), (int) x, (int) y, null);
		}

		moving = false;

	}

}
