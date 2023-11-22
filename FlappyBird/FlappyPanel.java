package flappyBird;

import java.awt.Color;
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

public class FlappyPanel extends JPanel {
	private Timer timer;
	private Bird bird;
	private int timerFires = 0;
	private Random random;
	private ArrayList<Box> boxes;
	private int points = 0;
	private JLabel pointsLabel;

	public FlappyPanel() {

		pointsLabel = new JLabel(points + "");
		add(pointsLabel);
		Color background = new Color(50, 150, 50);
		setBackground(background);
		setFocusable(true);
		setPreferredSize(new Dimension(300, 300));

		timer = new Timer(100, new TimerListener());
		timer.start();

		addKeyListener(new ArrowListener());

		bird = new Bird();
		random = new Random();
		boxes = new ArrayList<>();
	}

	@Override
	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		paintSky(page);
		paintHills(page);
		paintBird(page);
		paintBoxes(page);

	}

	public void paintBoxes(Graphics page) {
		points = 0;
		for (int i = boxes.size() - 1; i > -1; i--) {
			page.fillRect(300 - 3 * (timerFires - boxes.get(i).getTimerFires()), 0, 10, boxes.get(i).getHeight());
			page.fillRect(300 - 3 * (timerFires - boxes.get(i).getTimerFires()), boxes.get(i).getHeight() + 50, 10,
					300);
			if (bird.getX() + 10 > 300 - 3 * (timerFires - boxes.get(i).getTimerFires())) {
				points++;
				pointsLabel.setText(points + "");
				if (bird.getX() < 310 - 3 * (timerFires - boxes.get(i).getTimerFires())) {
					if (bird.getY() + 10 > boxes.get(i).getHeight() + 50 || bird.getY() < boxes.get(i).getHeight()) {
						die();
						break;
					}
				}
			}
		}
	}

	public void paintBird(Graphics page) {
		page.setColor(Color.blue);
		page.fillOval(bird.getX(), (int) bird.getY(), 10, 10);
		page.setColor(Color.black);
		page.fillOval(bird.getX() + 5, (int) bird.getY() + 4, 5, 5);
		page.setColor(Color.black);
		page.setColor(Color.red);
	}

	public void paintHills(Graphics page) {
		page.setColor(new Color(50, 150, 50));
		page.fillOval((320 - 2 * (timerFires) % 500), 170, 200, 100);
		page.fillOval((320 - 2 * (timerFires) % 600) + 100, 180, 150, 100);
		page.fillOval((320 - 2 * (timerFires) % 800) + 300, 180, 150, 100);
		page.fillOval((320 - 2 * (timerFires) % 1020) + 400, 160, 300, 100);
	}

	public void paintSky(Graphics page) {

		Color sky = new Color(135, 206, 235);
		page.setColor(sky);
		page.fillRect(0, 0, 300, 200);

		page.setColor(Color.white);
		page.fillRect((300 - 2 * timerFires % 360), 75, 50, 10);
		page.fillRect((310 - 2 * timerFires % 360), 70, 50, 10);
		page.fillRect((320 - 2 * timerFires % 360), 65, 30, 10);
		page.fillRect((300 - 2 * (timerFires) % 500) + 100, 85, 50, 10);
		page.fillRect((310 - 2 * (timerFires) % 500) + 100, 95, 50, 10);
		page.fillRect((320 - 2 * (timerFires) % 500) + 100, 90, 40, 10);
	}

	public void die() {

		boxes.clear();
		timerFires = 0;
		bird.die();
		points = 0;
		pointsLabel.setText(points + "");
	}

	public class TimerListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			timerFires++;
			bird.upwardFlap();
			repaint();

			if (timerFires % 30 == 0) {
				boxes.add(new Box(timerFires, random.nextInt(250)));
				repaint();
			}

			if (bird.getY() < 0 || bird.getY() > 300) {
				die();
			}
		}
	}

	public class ArrowListener implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 38)
				bird.resetUp();
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}
	}
}