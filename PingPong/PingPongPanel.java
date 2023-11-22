package pingPong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PingPongPanel extends JPanel{
Ball ball;
ReboundThing thing;

	public PingPongPanel() {
		setPreferredSize(new Dimension(500,500));
		
		ArrayList<Point> pointsList = new ArrayList<Point>();
		pointsList.add(new Point(240,500));
		pointsList.add(new Point(250,480));
		pointsList.add(new Point(260,500));
		thing = new ReboundThing(pointsList);
		
		ArrayList<Point> leftBorder = new ArrayList<Point>();
		leftBorder.add(new Point(0,0));
		leftBorder.add(new Point(10,500));
		ReboundThing left = new ReboundThing(leftBorder);
		
		ArrayList<Point> rightBorder = new ArrayList<Point>();
		rightBorder.add(new Point(499,0));
		rightBorder.add(new Point(500,500));
		ReboundThing right = new ReboundThing(rightBorder);
		
		ArrayList<Point> topBorder = new ArrayList<Point>();
		topBorder.add(new Point(0,1));
		topBorder.add(new Point(500,0));
		ReboundThing top = new ReboundThing(topBorder);
		ArrayList<Point> bottomBorder = new ArrayList<Point>();
		bottomBorder.add(new Point(0,500));
		bottomBorder.add(new Point(500,499));
		ReboundThing bottom = new ReboundThing(bottomBorder);
		
		ArrayList<ReboundThing> reboundList = new ArrayList<ReboundThing>();
		reboundList.add(thing);
		reboundList.add(left);
		reboundList.add(right);
		reboundList.add(top);
		reboundList.add(bottom);

		
		
		ball = new Ball(reboundList,10);
		
		Timer timer = new Timer(50,new TimerListener());
		timer.start();
		
	}
	
	@Override
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		page.fillOval(ball.ballPosition.x, ball.ballPosition.y, ball.width, ball.width);
		int[] x = new int[thing.points.size()];
		int[] y = new int[thing.points.size()];

		for(int i = 0;i<thing.points.size();i++) {
			x[i] = thing.points.get(i).x;
			y[i] = thing.points.get(i).y;
		}
		
		page.fillPolygon(x, y, x.length);
		page.drawLine(0, 0, 10, 500);
		
	}
	
	public class TimerListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ball.move();
			repaint();
			
		}
		
	}
}