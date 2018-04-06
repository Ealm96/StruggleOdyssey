import java.awt.event.*;
import java.util.*;
//import java.applet.Applet;
import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.SideScroller.Animation;
import com.SideScroller.Sprite;

public class SideScroller extends Canvas implements KeyListener, Runnable {

	// MEMBER VARIABLES
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	public int halfScreen = (dim.height / 2);

	Image off_screen;
	Graphics off_g;

	String[] action = { "straight", "up", "down", "attack" };
	Player player = new Player(0, 0, "z_", action, 16, 5);
	SpaceCruiser spriteG = new SpaceCruiser(0, 200, "player_plane_", action, 2, 10);

	SpaceShip sp = new SpaceShip(100, 360);
	Bullet r = new Bullet(-15, 5, 10, 5);
	Bullet[] bullets = new Bullet[4];
	private int b = 0;

	Thread t;

	boolean lt_Pressed = false;
	boolean rt_Pressed = false;
	boolean up_Pressed = false;
	boolean dn_Pressed = false;
	boolean sp_Pressed = false;
	boolean a_Pressed = false;
	boolean d_Pressed = false;
	boolean c_Pressed = false;

	// FUNCTIONS
	public void init() { // Thread is used to let the game function outside of the user input and run
							// continuously
		off_screen = this.createImage(dim.width, dim.height);
		off_g = off_screen.getGraphics();

		for (int i = 0; i < bullets.length; i++) {// initializes the game with the array of bullets
			bullets[i] = r;
		}

		requestFocus();
		addKeyListener(this);
		t = new Thread(this);
		t.start();

	}

	// makes the game run and let's input react
	public void run() {

		while (true) {

			if (sp_Pressed) {
				spriteG.shoot(bullets[b]);
				b++;
				if (b == bullets.length)
					b = 0;
			}
			bullets[b].move();
			if (lt_Pressed)
				spriteG.moveLeftBy(4);
			if (rt_Pressed)
				spriteG.moveRightBy(4);
			if (up_Pressed)
				spriteG.moveUpBy(4);
			if (dn_Pressed)
				spriteG.moveDownBy(4);
			if (d_Pressed)
				player.moveRightBy(2);

			repaint();

			try {
				t.sleep(15);// gives the OS some time to re draw the objects.
			} catch (Exception x) {

			}
		}
	}

	public void update(Graphics g) {

		off_g.clearRect(0, 0, dim.width, dim.height);

		paint(off_g);

		g.drawImage(off_screen, 0, 0, null);

	}

	// draws objects to the screen
	public void paint(Graphics g) {

		spriteG.draw(g);// draws our player when in their spaceship.
		for (int i = 0; i < bullets.length; i++) {
			bullets[i].draw(g);
		} // draws the bullets that the spaceship can use.

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
			// spriteG.isFiring = true;
		}
		if (code == e.VK_A) {
			a_Pressed = true;
			player.isAtkng(true);
		}
		if (code == e.VK_C)
			c_Pressed = true;
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
			// spriteG.isFiring = false;
		}
		if (code == e.VK_A)
			a_Pressed = false;
		if (code == e.VK_C)
			c_Pressed = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	public static void main(String[] args) {

		JFrame frame = new JFrame("Side Scroller");
		SideScroller game = new SideScroller();
		frame.getContentPane().setBackground(Color.BLACK);

		// frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setSize(1000, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.setResizable(true);
		frame.setUndecorated(true);
		frame.setVisible(true);
		game.init();
	}

}