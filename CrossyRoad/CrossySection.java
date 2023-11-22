package crossyRoad;

import java.awt.Color;
import java.awt.Graphics;

public interface CrossySection {

	public int width = 20;	
	int y = -20;
	public  Car car3 = new Car(500,0,0,0,0,Color.red,"hi");
	public boolean hasCars = false;
	public  Car car2 = new Car(500,0,0,0,0,Color.red,"hi");

	public  Car car1 = new Car(500,0,0,0,0,Color.red,"hi");
	public  Car car4 = new Car(500,0,0,0,0,Color.red,"hi");

	public static Color color = null;
	public void move();

	public void moveCars();
	public boolean hitCar(Chicken chicken);
	
	public void paint(Graphics page);
		
}