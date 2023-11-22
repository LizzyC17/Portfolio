package snakeGame;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class Snake {

	public Point foodPoint;
	public ArrayList<Point> pointsList;
	Random random;
	String direction = "up";
	int listLength = 1;
	public static int maxLength = 1;
	public int colorPosition = 1;

	public Snake() {
		random = new Random();
		pointsList = new ArrayList<Point>();
		pointsList.add(new Point(random.nextInt(8) * 50, random.nextInt(8) * 50));
		assignFood();
	}

	public void setDirection(String string) {
		if (listLength == 1)
			direction = string;
		if (!direction.equals("up") && string.equals("down"))
			direction = string;
		if (!direction.equals("down") && string.equals("up"))
			direction = string;
		if (!direction.equals("right") && string.equals("left"))
			direction = string;
		if (!direction.equals("left") && string.equals("right"))
			direction = string;
	}

	public void die() {
		randomizeDirection();
		listLength = 1;
		assignFood();
		pointsList.clear();
		colorPosition = random.nextInt(3);
		pointsList.add(new Point(random.nextInt(18) * 50, random.nextInt(18) * 50));
	}
	
	public void randomizeDirection() {
		int n = random.nextInt(4);
		if(n==1)
			direction = "up";
		if(n==2)
			direction = "down";
		if(n==3)
			direction = "left";
		if(n==0)
			direction = "right";
	}

	public void eat() {
		pointsList.add(listLength, foodPoint);
		listLength++;
		if (listLength > maxLength)
			maxLength = listLength;
		assignFood();	
	}
	
	public void assignFood() {
		foodPoint = new Point(random.nextInt(19) * 50, random.nextInt(19) * 50);	
		while(checkOnBody()) {
			foodPoint = new Point(random.nextInt(19) * 50, random.nextInt(19) * 50);
		}
	}
	
	public boolean checkOnBody() {

		for (int i = 0; i < listLength - 1; i++) {
			if (foodPoint.equals(pointsList.get(i))) {
				return true;
			}
		}
		return false;	
	}

	public void move() {
		Point headPoint = null;
		switch (direction) {
		case "down":
			headPoint = new Point(pointsList.get(listLength - 1).x, pointsList.get(listLength - 1).y + 50);
			break;
		case "left":
			headPoint = new Point(pointsList.get(listLength - 1).x - 50, pointsList.get(listLength - 1).y);
			break;
		case "up":
			headPoint = new Point(pointsList.get(listLength - 1).x, pointsList.get(listLength - 1).y - 50);
			break;
		case "right":
			headPoint = new Point(pointsList.get(listLength - 1).x + 50, pointsList.get(listLength - 1).y);
			break;
		}
		if (!didDie(headPoint)) {
			pointsList.add(headPoint);
			pointsList.remove(0);
		}
		if (headPoint.equals(foodPoint) && !didDie(headPoint))
			eat();
	}
	
	public boolean didDie(Point headPoint) {
		if (headPoint.x > 950 || headPoint.x < 0 || headPoint.y < 0 || headPoint.y > 950) {
			die();
			return true;
		}
		if (touchesSelf(headPoint))
			return true;
		return false;
	}

	public boolean touchesSelf(Point headPoint) {
		boolean touchesSelf = false;

		for (int i = 0; i < listLength - 1; i++) {
			if (headPoint.equals(pointsList.get(i))) {
				touchesSelf = true;
				die();
			}
		}
		return touchesSelf;
	}
}