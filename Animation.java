package com.SideScroller;

import java.awt.Image;
import java.awt.Toolkit;

public class Animation {

	Image[] image;

	private int current = 1;
	private int delay;
	private int duration;
	private int atkDuration;

	public Animation(String file, int count, int duration) {

		image = new Image[count];

		for (int i = 0; i < count; i++) {

			image[i] = Toolkit.getDefaultToolkit().getImage(file + i + ".png");

		}

		this.duration = duration;
		atkDuration = 4;
		delay = duration;

	}

	public Image StillImage() {

		return image[0];
	}

	public Image nextFlight() {
		if (delay == 0) {

			current++;
			if (current == image.length) {
				current = 1;
			}
			delay = duration;
		} else {
			delay--;
		}
		return image[current];

	}

	public Image nextAttack() {
		if (delay == 0) {

			current++;
			if (current == image.length) {
				current = 0;
			}
			delay = atkDuration;
		} else {
			delay--;
		}

		return image[0];
	}

	public Image nextImage() {

		if (delay == 0) {

			current++;
			if (current == image.length) {
				current = 3;
			}
			delay = duration;
		} else {
			delay--;
		}
		return image[current];
	}

}
