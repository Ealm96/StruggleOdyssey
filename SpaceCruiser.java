import com.SideScroller.Rect;

public class SpaceCruiser extends Sprite {
	
	//MEMBER VARIABLES
	Rect r;

	// CONSTRUCTOR
	public SpaceCruiser(int x, int y, String file, String[] action, int count, int duration) {
		super(x, y, file, action, count, duration);
	}

	// FUNCTIONS
	public void shoot(Rect Shell) {

		r = Shell;
		r.setLocation(this.x + 30, this.y + 15);
		double vx = (7);
		r.setVelocity(vx);

	}

}