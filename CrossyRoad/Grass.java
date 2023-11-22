package crossyRoad;

import java.awt.Color;
import java.awt.Graphics;

public class Grass  implements CrossySection{
	public boolean hasCars = false;
	int width = 20;	
	int y = -20;
	Color color = Color.GREEN;
	Car car1,car2,car3,car4;
	
	public Grass(int y) {
		this.y = y;
		car1 = new Car(500,0,0,0,0,Color.red,"hi");
		car2 = new Car(500,0,0,0,0,Color.red,"hi");
		car3 = new Car(500,0,0,0,0,Color.red,"hi");
		car4 = new Car(500,0,0,0,0,Color.red,"hi");
		
	}

	public void move() {
		y = y+20;
	}
	
	public void moveCars() {
	}
	
	
	public void paint(Graphics page) {
		page.setColor(Color.green);
		page.fillRect(0, y, 300, 20);
	}

	@Override
	public boolean hitCar(Chicken chicken) {		
		return false;
	}
}
