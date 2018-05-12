package com.SideScroller;

import java.awt.*;

public class Bullet extends Rect {

	// CONSTRUCTOR
	public Bullet(int x, int y, int w, int h) {

		super(x, y, w, h);

	}

	public boolean dmgDealt(EnemyCruiser[] enemy, SpaceCruiser sc) {

		if (this.x == 1000) {
			this.x = -1000;
			this.setVelocity(0);
		}
		for (int i = 0; i < enemy.length; i++) {
			if (this.overlaps(enemy[i]) && enemy[i].inRange) {
				enemy[i].hitBB = true;
				enemy[i].dCount -= 1;
				enemy[i].x = 1500;
				System.out.println("hit by bullet");
				this.x = -1000;
				this.setVelocity(0);
				sc.points += 1;
				return enemy[i].hitBB;
			}
		}
		return false;
	}

	// OVERRIDDEN FUNCTIONS
	public void draw(Graphics g) {
		g.fillOval((int) x, (int) y, w, h);
		g.setColor(Color.GREEN);
	}

}
