package pingPong;

import java.awt.Point;
import java.util.ArrayList;

public class Ball {
	
	int[] XYvelocity;
	Point ballPosition;
	int angle;
	double velocity,XV,YV;
	ArrayList < ReboundThing> obstacles;
	int width;
	public Ball(ArrayList<ReboundThing> obstacles, int width) {
		this.obstacles = obstacles;
		this.width = width;
		XV= 0;
		XV = -5;
		velocity = XV/YV;
		ballPosition = new Point(250,250);
		angle = (int)Math.atan(velocity);

	}
	
	public void move() {
		ballPosition.x = (int)(ballPosition.x+XV);
		ballPosition.y = (int)(ballPosition.y+YV);
		for(int i = 0;i<obstacles.size();i++) {
			checkRebound(obstacles.get(i));
		}
	}
	
	public void rebound(int angle) {
		
		System.out.println("rebounding");
		int reboundAngle = angle;
		int increment = (int)(Math.PI/2.0-angle);
		angle =(int) (angle+increment);
		reboundAngle = (int) (reboundAngle+(Math.PI/2.0-reboundAngle));
		angle = (int)(Math.PI+(Math.PI-angle));
		angle = angle-increment;
		velocity = Math.tan(angle);
		
	}
	
	public void addReboundThing(ReboundThing thing) {
		obstacles.add(thing);
	}
	
	public void checkRebound(ReboundThing thing){
		
			ArrayList<Point> thingPoints = thing.getCoordinates();
			boolean inX = false,inY= false;
			int angleReboundSurface;

			for(int i =0;i< thingPoints.size();i++) {
				int x = thingPoints.get(i).x;
				int x2 = thingPoints.get((i+1)%thingPoints.size()).x;
				if(x>x2) {
					int temp = x;
					x = x2;
					x2 = temp ; 
				}
				int bx = ballPosition.x;
				if(bx>x&&bx<x2) {
					inX = true;
					System.out.println("inx");
				}
				int y = thingPoints.get(i).y;
				int y2 = thingPoints.get((i+1)%thingPoints.size()).y;
				if(y>y2) {
					int temp = y;
					y = y2;
					y2 = temp ; 
				}
				int by = ballPosition.y;
				if(by>y&&by<y2) {
					System.out.println("iny");

					inY = true;
				}
				
				if(inY&&inX) {
					angleReboundSurface =(int)Math.atan((y2-y)/(x2-x));
					rebound(angleReboundSurface);
				}
				
			}
			
		}
	}