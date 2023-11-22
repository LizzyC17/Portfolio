package crossyRoad;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Road implements CrossySection {
	public boolean hasCars;
	int width = 20;
	int y = -20;
	Car car1, car2, car3, car4;
	Color color = Color.GRAY;
	String direction;
Random random;
	public Road(double speed, int density, int car1, int car2, int car3, int car4, int y, String direction) {
		hasCars = true;
		random = new Random();
		this.y = y;
		this.direction = direction;
		this.car1 = new Car(car1, y, speed, density, random.nextInt(20)+20, Color.RED, direction);
		this.car2 = new Car(car2, y, speed, density, random.nextInt(20)+20, Color.BLUE, direction);
		this.car3 = new Car(car3, y, speed, density, random.nextInt(20)+20, Color.BLACK, direction);
		this.car4 = new Car(car4, y, speed, density, random.nextInt(20)+20, Color.YELLOW, direction);
	}

	public boolean withinCar(Car car1, Chicken chicken) {
		int carx = car1.x;
		int backcar = car1.backx;
		if (chicken.topy == car1.y) {
			if (chicken.centerx > backcar && chicken.centerx < carx) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hitCar(Chicken chicken) {
		if(withinCar(car1,chicken)||withinCar(car2,chicken)||withinCar(car3,chicken)||withinCar(car4,chicken)) {
			return true;		
		}
		return false;
	}

	public void move() {
		y = y + 20;
		car1.y = car1.y + 20;
		car1.backy = car1.backy + 20;
		car2.y = car2.y + 20;
		car2.backy = car2.backy + 20;
		car3.y = car3.y + 20;
		car3.backy = car3.backy + 20;
		car4.y = car4.y + 20;
		car4.backy = car4.backy + 20;

	}

	public void moveCars() {
		car1.move();
		car2.move();
		car3.move();
		car4.move();
	}

	public void paint(Graphics page) {
		page.setColor(color);
		page.fillRect(0, y, 300, 20);
		car1.paint(page);
		car2.paint(page);
		car3.paint(page);
		car4.paint(page);
	}
}