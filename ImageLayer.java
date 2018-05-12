package com.SideScroller;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.LinkedList;
import com.SideScroller.SpaceCruiser;

public class ImageLayer {

	Image image;
	Image[] images = new Image[10];
	protected int scrollSpeed = 1;

	private int x;
	private int y;
	private int z;

	public ImageLayer(String file, int x, int y, int z) {

		image = Toolkit.getDefaultToolkit().getImage(file);

		this.x = x;
		this.y = y;
		this.z = z;

	}

	public void scroll() {

		x -= scrollSpeed;
		if (x == -500)
			x = 0;

	}
	
	public void scrollStop() {
		scrollSpeed = 0;
	}

	public void draw(Graphics g) {

		for (int i = 0; i < 4; i++) {
			g.drawImage(image, x / z + 500 * i, y, 500, 400, null);
		}

	}

}
