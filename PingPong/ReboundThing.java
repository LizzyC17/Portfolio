package pingPong;

import java.awt.Point;
import java.util.ArrayList;

public class ReboundThing {
	
	ArrayList<Point> points;
	
	public ReboundThing(ArrayList<Point>points) {
		this.points = points;
		
	}
	
	public ArrayList<Point> getCoordinates() {
		return points;
	}

	public void move(int xdirection,int ydirection) {
		
		for(int i = 0;i<points.size();i++) {
			points.get(i).x = points.get(i).x+xdirection;
			points.get(i).y = points.get(i).y+ydirection;

		}
		
	}	
}