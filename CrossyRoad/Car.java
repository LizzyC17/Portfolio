package crossyRoad;
import java.awt.Color;
import java.awt.Graphics;
public class Car {

	int x, y, backx,backy;
	int  density;
	double speed;
	int width, height = 20;
	Color color;
	String direction;
	public Car(int x, int y, double speed,int density,int width,Color color, String direction) {
		
		this.direction = direction;
		this.color = color;
		this.y = y;
		this.x = x;
		this.speed = speed;
		this.density = density;
		this.width = width;
		backx = x-width;
		backy = y;
	}
	
	public void move() {
		if(direction.equals("right")) {
			x = (int)(x + 10*speed)%density;
			backx = x-width;
			
		}
	}
	
	public void paint(Graphics page) {
		page.setColor(color);
		page.fillRect(x-width, y, width, height);
	}
}