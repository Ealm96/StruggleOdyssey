import java.awt.Graphics;

public class Player extends Sprite {

	int x;
	int y;

	// int pose;

	static final int STRAIGHT = 0;
	static final int JUMP = 2;
	static final int ATTACK = 3;

	Animation[] animation;
	Animation atkAnim;

	boolean moving = false;
	boolean attack = false;

	int pose = STRAIGHT;

	public Player(int x, int y, String file, String[] action, int count, int duration) {
		super(x, y, file, action, count, duration);
		this.x = x;
		this.y = y;

		animation = new Animation[action.length];

		for (int i = 0; i < action.length; i++) {
			animation[i] = new Animation(file + action[i] + "_", count, duration);
		}

	}

	public void moveUpBy(int dy) {

		y -= dy;
		moving = true;
		pose = JUMP;
	}

	public void moveLeftBy(int dx) {

		x -= dx;
		moving = true;
		pose = STRAIGHT;
	}

	public void moveRightBy(int dx) {

		x += dx;
		moving = true;
		pose = STRAIGHT;
	}

	public void isAtkng(boolean a) {
		attack = a;
		pose = ATTACK;
	}

	public void draw(Graphics g) {
		if (moving) {
			g.drawImage(animation[pose].nextImage(), x, y, null);
		} /*
			 * else { g.drawImage(animation[pose].StillImage(), x, y, null); }
			 */
		// *
		if (attack || (attack && moving)) {
			System.out.println("zero attacking.");
			g.drawImage(animation[pose].nextAttack(), x, y, null);
		} // */
		if (!attack && !moving) {
			g.drawImage(animation[STRAIGHT].StillImage(), x, y, null);
		}

		attack = false;
		moving = false;
	}

}