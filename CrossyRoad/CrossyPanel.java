package crossyRoad;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class CrossyPanel extends JPanel {

	private ArrayList<CrossySection> sections;
	private Chicken chicken;
	private Random random;
	private Timer timer;
	private MoveListener listener;
	private int points = 0;
	private JLabel pointsLabel;
	
	public CrossyPanel() {
		
		pointsLabel = new JLabel(points+" points");
		setPreferredSize(new Dimension(300, 300));
		sections = new ArrayList<CrossySection>();
		chicken = new Chicken();
		random = new Random();
		initialRoads();

		timer = new Timer(1, new TimerListener());
		timer.start();

		listener = new MoveListener();
		addKeyListener(listener);
		setFocusable(true);
		add(pointsLabel);
	}

	public void initialRoads() {
		for (int i = 0; i < 300; i = i + 20) {
			if (i > 200)
				sections.add(new Grass(i));
			else
				sections.add(createSection(i));
		}
	}

	@Override
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		for (int i = 0; i < sections.size(); i++) {
			CrossySection section = sections.get(i);
			section.paint(page);
		}
		chicken.paint(page);
		pointsLabel.setText(points+" points");
	}

	public CrossySection createSection(int y) {
		int n = random.nextInt(2);
		int density, car1, car2, car3, car4;
		double speed;
		if (n == 0) {
			speed = random.nextDouble(0.2) + 0.1;
			density = random.nextInt(600) + 300;
			car1 = random.nextInt(900);
			car2 = random.nextInt(900);
			car3 = random.nextInt(900);
			car4 = random.nextInt(900);
			int dir = random.nextInt(2);
			String direction;
			if (dir == 0)
				direction = "right";
			else
				direction = "left";
			direction = "right";

			return new Road(speed, density, car1, car2, car3, car4, y, direction);
		} else {
			return new Grass(y);
		}
	}

	public class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean hitCar = false;
			;
			for (int i = 0; i < sections.size(); i++) {
				sections.get(i).moveCars();

				if (sections.get(i).hitCar(chicken)) {
					hitCar = true;
				}
			}

			if (hitCar) {
				die();
			}
			repaint();
		}
	}

	public void die() {
		sections.clear();
		initialRoads();
		chicken = new Chicken();
		timer.stop();
		points = 0;
	}

	public class MoveListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 10) {//enter
				timer.start();
			}
			if (timer.isRunning()) {

				if (e.getKeyCode() == 37) {// left
					chicken.move("left");
				}
				if (e.getKeyCode() == 38) {// up
					for (int i = 0; i < sections.size(); i++) {
						sections.get(i).move();
					}
					sections.add(createSection(0));
					points++;
				}

				if (e.getKeyCode() == 39) {// right
					chicken.move("right");

				}

			}
			repaint();

		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

}
