package flappyBird;

public class Box {
	
	private int height, timerFires;

	public Box(int timerFires, int height) {
		this.height = height;
		this.timerFires = timerFires;
	}

	public int getHeight() {
		return height;
	}

	public int getTimerFires() {
		return timerFires;
	}

}