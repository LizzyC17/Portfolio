package crossyRoad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Chicken {

	int backx = 140;
	double bottomy = 260;
	int topy = 240;
	int width = 20;
	int frontx = 160;
	int centerx = (frontx+backx)/2;
		public Chicken() {
		
		}
	
		public void move (String direction) {
			if(direction.equals("right")) {
				backx = backx+20;
				frontx = frontx+20;
			
			}
			if(direction.equals("left")) {
				backx = backx-20;
				frontx = frontx-20;
			}		
			centerx = (frontx+backx)/2;
		}
		
		public void paint(Graphics page) {
			page.setColor(new Color(150,60,0));
			page.fillOval(backx, topy, 20, 20);
		}
		
		public boolean hitCar(Road road) {
			Car car1 = road.car1;
			Car car2 = road.car2;
			Car car3 = road.car3;
			Car car4 = road.car4;
			if(withinCar(car1)||withinCar(car2)||withinCar(car3)||withinCar(car4)) {
				die();
				return true;
			
			}
			return false;
		}
		
		public boolean withinCar(Car car1) {
			int carx = car1.x;
			int backcar = car1.backx;
			if(backx>backcar&&backx<carx) 
				return true;
			if(frontx>backcar&&backx<carx) 
				return true;
			return false;
		}
		
		public void die() {
			backx = 140;
			frontx = 160;
		}
		
}