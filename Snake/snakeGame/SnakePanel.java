package snakeGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class SnakePanel extends JPanel {
	JLabel maxLength;
	Snake snake;
	private Timer timer;
	Color snakeHead, snakeBody;
	Color background;
	JButton addBlocks;
	JButton pauseButton;
	public SnakePanel() {
		pauseButton = new JButton("pause");
		add(pauseButton);
		pauseButton.addActionListener(new TimerListener());
		addBlocks = new JButton("Add wall when eat");
		add(addBlocks);
		addBlocks.addActionListener(new TimerListener());
		background = new Color(70,150,50);
		setBackground(background);
		snakeBody = new Color(30, 150, 0);
		snakeHead = new Color(10, 200, 0);
		snake = new Snake();
		timer = new Timer(100, new TimerListener());
		timer.start();
		addKeyListener(new DirectionListener());
		setPreferredSize(new Dimension(1000, 800));
		setFocusable(true);
		maxLength = new JLabel(snake.maxLength + "");
		add(maxLength);
	}

	public void changeSpeed(int speed) {
		timer.setDelay(speed);
	}

	@Override
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		page.setColor(Color.black);
		page.drawLine(1000, 0, 1000, 1000);
		page.setColor(Color.red);
		page.fillOval(snake.foodPoint.x, snake.foodPoint.y, 50, 50);
		page.setColor(snakeBody);
		drawSnake(page);
		drawWalls(page);
	}
	
	
	public void drawWalls(Graphics page) {
		page.setColor(Color.BLACK);
		
		for(int i = 0; i<snake.walls.size();i++) {
			page.fillRect(snake.walls.get(i).x, snake.walls.get(i).y, 50, 50);
			System.out.println("looping paint");
		}
	}
	
	public void drawSnake(Graphics page) {
		int green = 1;
		for (int i = 0; i < snake.pointsList.size(); i++) {
			if (green < 240) {
				int increment = (int) Math.pow(Math.E, -i) + 10;
				green = green + increment;
			}
			if(snake.colorPosition==0) {
				Color snakeColor = new Color(0,green, 100);
				page.setColor(snakeColor);
			}
			if(snake.colorPosition==1) {
				Color snakeColor = new Color(100, 0, green);
				page.setColor(snakeColor);
			}
			if(snake.colorPosition==2) {
				Color snakeColor = new Color(255, 255-green, 0);
				page.setColor(snakeColor);
			}
			
			drawSnakeSection(page,i);
		}
	}
	
	public void drawSnakeSection(Graphics page, int position) {
		page.fillRect(snake.pointsList.get(position).x, snake.pointsList.get(position).y, 50, 50);
		
		if (position == snake.listLength - 1) {
			page.setColor(Color.black);
			if (snake.direction.equals("down") || snake.direction.equals("up")) {
				page.fillRect(snake.pointsList.get(snake.listLength - 1).x + 10,
						snake.pointsList.get(snake.listLength - 1).y + 25, 5, 5);
				page.fillRect(snake.pointsList.get(snake.listLength - 1).x + 40,
						snake.pointsList.get(snake.listLength - 1).y + 25, 5, 5);
			} else {
				page.fillRect(snake.pointsList.get(snake.listLength - 1).x + 25,
						snake.pointsList.get(snake.listLength - 1).y + 10, 5, 5);
				page.fillRect(snake.pointsList.get(snake.listLength - 1).x + 25,
						snake.pointsList.get(snake.listLength - 1).y + 40, 5, 5);

			}
		}
		
	}

	private class TimerListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(timer)) {
				snake.move();
				repaint();
				maxLength.setText(Snake.maxLength + "");
			} 
			if(e.getSource().equals(pauseButton)) {
				pauseUnpause();
			}
			if(e.getSource().equals(addBlocks)) {
				if(snake.addWalls) {
					snake.addWalls = false;
				}
				else {
					snake.addWalls = true;
				}
				requestFocus();
			}
		}
	}
	
	public void pauseUnpause() {
		if (timer.isRunning())
			timer.stop();
		else
			timer.start();
		requestFocus();
	}

	private class DirectionListener implements KeyListener {
		// 37:left 38:up 39:right 40:down
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 37)
				snake.setDirection("left");
			if (e.getKeyCode() == 38)
				snake.setDirection("up");
			if (e.getKeyCode() == 39)
				snake.setDirection("right");
			if (e.getKeyCode() == 40)
				snake.setDirection("down");
			if(e.getKeyCode()==80)
				pauseUnpause();
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}
}