package com.SideScroller;

import java.awt.event.*;
import javax.swing.JFrame;
import java.awt.*;
import java.util.LinkedList;

import com.SideScroller.ImageLayer;
import com.SideScroller.*;

public class SideScroller extends Canvas implements KeyListener, Runnable {

	// MEMBER VARIABLES
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	public int halfScreen = (dim.height / 2);

	Image off_screen;
	Graphics off_g;

	String[] action = { "straight", "up", "down", "attack", "back" };
	Player player;
	SpaceCruiser spriteG = new SpaceCruiser(0, 200, "player_plane_", action, 2, 10);
	ImageLayer il;

	EnemyCruiser[] ec = new EnemyCruiser[6];

	Music m = new Music("Mercury.wav");

	Bullet[] bullets = new Bullet[50];
	private int b = 0;

	Thread t;
	GameOver go;

	boolean lt_Pressed = false;
	boolean rt_Pressed = false;
	boolean up_Pressed = false;
	boolean dn_Pressed = false;
	boolean sp_Pressed = false;
	boolean a_Pressed = false;
	boolean d_Pressed = false;
	boolean esc_Pressed = false;

	// FUNCTIONS
	public void init() { // Thread is used to let the game function outside of the user input and run
							// continuously
		off_screen = this.createImage(dim.width, dim.height);
		off_g = off_screen.getGraphics();

		il = new ImageLayer("space_BkGrnd.png", 0, 0, 1);
		player = new Player(0, 260, "z_", action, 18, 4);

		for (int i = 0; i < bullets.length; i++) {// initializes the game with the array of bullets
			bullets[i] = new Bullet(-15, -15, 10, 5);
		}

		for (int i = 0; i < ec.length; i++) {
			ec[i] = new EnemyCruiser(1510, 30, "en_", action, 2, 10, 55, 30);
		}

		requestFocus();
		addKeyListener(this);
		t = new Thread(this);
		t.start();

	}

	// makes the game run and let's input react
	public void run() {

		while (spriteG.hp != 0) {

			if (sp_Pressed) {
				spriteG.shoot(bullets[b]);
				b++;
				if (b == bullets.length)
					b = 0;
			}

			for (int i = 0; i < bullets.length; i++) {

				bullets[i].move();
				bullets[i].dmgDealt(ec, spriteG);
			}
			il.scroll();
			spriteG.CoolDown();
			if (lt_Pressed)
				spriteG.moveLeftBy(4);
			if (rt_Pressed)
				spriteG.moveRightBy(4);
			if (up_Pressed)
				spriteG.moveUpBy(4);
			if (dn_Pressed)
				spriteG.moveDownBy(4);
			if (d_Pressed)
				player.moveRightBy(3);
			if (a_Pressed)
				player.moveLeftBy(3);
			if (esc_Pressed)
				System.exit(0);
			spriteG.increase();
			spriteG.invFrame();
			for (int i = 0; i < ec.length; i++) {
				ec[i].approach(spriteG, bullets[b]);
			}

			repaint();

			try {
				t.sleep(15);// gives the OS some time to re draw the objects.
			} catch (Exception x) {

			}
		}

		if (spriteG.hp == 0) {
			m.stop();
			go = new GameOver();
		}

	}

	public void update(Graphics g) {

		off_g.clearRect(0, 0, 1000, 400);

		paint(off_g);

		g.drawImage(off_screen, 0, 0, null);
		

	}

	// draws objects to the screen
	public void paint(Graphics g) {

		il.draw(g);
		spriteG.draw(g);// draws our player when in their spaceship.
		for (int i = 0; i < ec.length; i++) {
			ec[i].draw(g);
		}
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].draw(g);
		} // draws the bullets that the spaceship can use.

		// g.drawString("Points: " + spriteG.amount , 0, 10);

	}

	// setups what happens when a key is pressed
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		if (code == e.VK_LEFT)
			lt_Pressed = true;
		if (code == e.VK_RIGHT)
			rt_Pressed = true;
		if (code == e.VK_UP)
			up_Pressed = true;
		if (code == e.VK_DOWN)
			dn_Pressed = true;
		if (code == e.VK_D)
			d_Pressed = true;
		if (code == e.VK_SPACE) {
			sp_Pressed = true;
		}
		if (code == e.VK_A) {
			a_Pressed = true;
		}
		if (code == e.VK_ESCAPE)
			esc_Pressed = true;
	}

	// setups what will happen when a key is released
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();
		if (code == e.VK_LEFT)
			lt_Pressed = false;
		if (code == e.VK_RIGHT)
			rt_Pressed = false;
		if (code == e.VK_UP)
			up_Pressed = false;
		if (code == e.VK_DOWN)
			dn_Pressed = false;
		if (code == e.VK_D)
			d_Pressed = false;
		if (code == e.VK_SPACE) {
			sp_Pressed = false;
		}
		if (code == e.VK_A)
			a_Pressed = false;
		if (code == e.VK_ESCAPE)
			esc_Pressed = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	public static void main(String[] args) {

		SideScroller game = new SideScroller();
		JFrame frame = new JFrame("Side Scroller");
		frame.setUndecorated(true);
		frame.requestFocus();
		frame.setSize(1000, 400);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setResizable(false);
		frame.setVisible(true);
		game.init();

	}

}
