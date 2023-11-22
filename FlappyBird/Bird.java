
package flappyBird;

import java.awt.Point;

public class Bird {

	private Point position;
	private int timerFiresAfterReset = 0;
	private double height = 200;

	public Bird() {
		position = new Point(50, 200);
	}

	public void resetUp() {
		timerFiresAfterReset = 0;
	}

	public void die() {
		position = new Point(50, 200);
		timerFiresAfterReset = 0;
		height = 200;
	}

	public void upwardFlap() {
		double yIncrement = -1 * (-2 * timerFiresAfterReset + 8);
		height = height + yIncrement;
		position.y = (int) height;
		timerFiresAfterReset++;
	}

	public int getX() {
		return position.x;
	}

	public int getY() {
		return position.y;
	}
}