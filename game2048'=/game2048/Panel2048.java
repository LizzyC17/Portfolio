package game2048;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel2048 extends JPanel {

	public int[][] values;
	Random random;
	public JLabel[][] labels;

	public Panel2048() {
		setPreferredSize(new Dimension(300, 300));
		labels = new JLabel[4][4];
		values = new int[4][4];
		random = new Random();
		setLayout(new GridLayout(5, 16));
		fillZeroes();
		addNew2();
		addNew2();

		assignLabels();
		slideListener listener = new slideListener();
		addKeyListener(listener);
		setFocusable(true);
		Random random = new Random();
		System.out.println((int)(random.nextDouble()*10));
		System.out.println((int)random.nextDouble()*10);


	}

	public void assignLabels() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				labels[x][y] = new JLabel(values[x][y] + "");
				add(labels[x][y]);
				labels[x][y].setOpaque(true);
			}
		}
		reassignLabels();

	}

	public void reassignLabels() {
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++) {

				if (values[x][y] == 0)
					labels[x][y].setText("");
				else
					labels[x][y].setText("          " + values[x][y] + "");
				if (values[x][y] == 0)
					labels[x][y].setBackground(Color.white);
				if (values[x][y] > 0)
					labels[x][y].setBackground(Color.LIGHT_GRAY);
				if (values[x][y] > 2)
					labels[x][y].setBackground(Color.GRAY);
				if (values[x][y] > 4)
					labels[x][y].setBackground(Color.DARK_GRAY);
				if (values[x][y] > 8)
					labels[x][y].setBackground(Color.cyan);
				if (values[x][y] > 16)
					labels[x][y].setBackground(Color.BLUE);
				if (values[x][y] > 32)
					labels[x][y].setBackground(Color.magenta);
				if (values[x][y] > 64)
					labels[x][y].setBackground(Color.green);
				if (values[x][y] > 128)
					labels[x][y].setBackground(Color.yellow);
				if (values[x][y] > 256)
					labels[x][y].setBackground(Color.orange);
				if (values[x][y] > 512)
					labels[x][y].setBackground(Color.red);
				if (values[x][y] > 1024)
					labels[x][y].setBackground(Color.pink);

			}
	}

	public void fillZeroes() {
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++)
				values[x][y] = 0;
	}

	private class slideListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
		}

		@Override
		public void keyPressed(KeyEvent e) {
			boolean movedRight = false, movedLeft = false, movedUp = false, movedDown = false;
			if (e.getKeyCode() == 37)
				movedLeft = moveLeft();
			if (e.getKeyCode() == 38)
				movedUp = moveUp();
			if (e.getKeyCode() == 39)
				movedRight = moveRight();
			if (e.getKeyCode() == 40)
				movedDown = moveDown();

			if (movedRight || movedLeft || movedUp || movedDown)
				addNew2();

			if (!canMakeMove() && !canAddTwo())
				die();
			
			reassignLabels();
		}

		@Override
		public void keyReleased(KeyEvent e) {
		}

	}

	public boolean moveUp() {// up
		boolean movedUp = false;

		for (int y = 0; y < 4; y++) {
			if (values[2][y] == 0) {
				movedUp = true;
				values[2][y] = values[3][y];
				values[3][y] = 0;
			}
			if (values[1][y] == 0) {
				movedUp = true;
				values[1][y] = values[2][y];
				values[2][y] = values[3][y];
				values[3][y] = 0;
			}

			if (values[0][y] == 0) {
				movedUp = true;
				values[0][y] = values[1][y];
				values[1][y] = values[2][y];
				values[2][y] = values[3][y];
				values[3][y] = 0;
			}

			if (values[0][y] == values[1][y] && values[0][y] != 0) {
				movedUp = true;
				values[0][y] = 2 * values[0][y];
				values[1][y] = values[2][y];
				values[2][y] = values[3][y];
				values[3][y] = 0;
			} else if (values[1][y] == values[2][y] && values[1][y] != 0) {
				movedUp = true;
				values[1][y] = 2 * values[2][y];
				values[2][y] = values[3][y];
				values[3][y] = 0;
			} else if (values[2][y] == values[3][y] && values[1][y] != 0) {
				movedUp = true;
				values[2][y] = 2 * values[3][y];
				values[3][y] = 0;
			}

		}
		return movedUp;
	}

	public boolean moveDown() {// down
		boolean movedDown = false;

		for (int y = 0; y < 4; y++) {
			if (values[1][y] == 0) {
				movedDown = true;
				values[1][y] = values[0][y];
				values[0][y] = 0;
			}
			if (values[2][y] == 0) {
				movedDown = true;
				values[2][y] = values[1][y];
				values[1][y] = values[0][y];
				values[0][y] = 0;
			}
			if (values[3][y] == 0) {
				movedDown = true;
				values[3][y] = values[2][y];
				values[2][y] = values[1][y];
				values[1][y] = values[0][y];
				values[0][y] = 0;
			}

			if (values[3][y] == values[2][y] && values[3][y] != 0) {
				movedDown = true;
				values[3][y] = values[3][y] * 2;
				values[2][y] = values[1][y];
				values[1][y] = values[0][y];
				values[0][y] = 0;
			} else if (values[2][y] == values[1][y] && values[2][y] != 0) {
				movedDown = true;
				values[2][y] = 2 * values[1][y];
				values[1][y] = values[0][y];
				values[0][y] = 0;
			} else if (values[2][y] == values[3][y] && values[1][y] != 0) {
				movedDown = true;
				values[1][y] = 2 * values[0][y];
				values[0][y] = 0;
			}

		}
		return movedDown;

	}

	public boolean moveLeft() {// left
		boolean movedLeft = false;
		for (int x = 0; x < 4; x++) {
			if (values[x][2] == 0) {
				movedLeft = true;
				values[x][2] = values[x][3];
				values[x][3] = 0;
			}
			if (values[x][1] == 0) {
				movedLeft = true;
				values[x][1] = values[x][2];
				values[x][2] = values[x][3];
				values[x][3] = 0;
			}
			if (values[x][0] == 0) {
				movedLeft = true;
				values[x][0] = values[x][1];
				values[x][1] = values[x][2];
				values[x][2] = values[x][3];
				values[x][3] = 0;
			}

			if (values[x][0] == values[x][1] && values[x][0] != 0) {
				movedLeft = true;
				values[x][0] = 2 * values[x][0];
				values[x][1] = values[x][2];
				values[x][2] = values[x][3];
				values[x][3] = 0;
			} else if (values[x][1] == values[x][2] && values[x][1] != 0) {
				movedLeft = true;
				values[x][1] = 2 * values[x][1];
				values[x][2] = values[x][3];
				values[x][3] = 0;
			} else if (values[x][2] == values[x][3] && values[x][2] != 0) {
				movedLeft = true;
				values[x][2] = 2 * values[x][3];
				values[x][3] = 0;
			}
		}
		return movedLeft;
	}

	public boolean moveRight() {// right
		boolean movedRight = false;
		for (int x = 0; x < 4; x++) {
			if (values[x][1] == 0) {
				movedRight = true;
				values[x][1] = values[x][0];
				values[x][0] = 0;
			}
			if (values[x][2] == 0) {
				movedRight = true;
				values[x][2] = values[x][1];
				values[x][1] = values[x][0];
				values[x][0] = 0;
			}
			if (values[x][3] == 0) {
				movedRight = true;
				values[x][3] = values[x][2];
				values[x][2] = values[x][1];
				values[x][1] = values[x][0];
				values[x][0] = 0;
			}

			if (values[x][3] == values[x][2] && values[x][3] != 0) {
				movedRight = true;
				values[x][3] = 2 * values[x][2];
				values[x][2] = values[x][1];
				values[x][1] = values[x][0];
				values[x][0] = 0;
			} else if (values[x][2] == values[x][1] && values[x][2] != 0) {
				movedRight = true;
				values[x][2] = 2 * values[x][1];
				values[x][1] = values[x][0];
				values[x][0] = 0;
			} else if (values[x][1] == values[x][0] && values[x][1] != 0) {
				movedRight = true;
				values[x][1] = 2 * values[x][0];
				values[x][0] = 0;
			}
		}
		return movedRight;
	}

	public void die() {
		fillZeroes();
		addNew2();
		repaint();
	}

	public void addNew2() {
		int x = (int) (random.nextDouble() * 4);
		int y = (int) (random.nextDouble() * 4);

		if (canAddTwo()) {
			while (values[x][y] != 0) {
				x = (int) (random.nextDouble() * 4);
				y = (int) (random.nextDouble() * 4);
			}
			values[x][y] = 2;
		}

	}

	public boolean canAddTwo() {
		boolean canAddTwo = false;
		for (int a = 0; a < 4; a++)
			for (int i = 0; i < 4; i++)
				if (values[a][i] == 0)
					canAddTwo = true;
		return canAddTwo;

	}

	public boolean canMakeMove() {
		boolean canMove = false;
		for (int a = 0; a < 4; a++)
			for (int i = 0; i < 4; i++)
				if (hasSameAdjacent(a, i))
					canMove = true;
		return canMove;

	}

	public boolean hasSameAdjacent(int x, int y) {
		if (x > 0) {
			if (values[x - 1][y] == values[x][y])
				return true;
		}
		if (x < 3) {
			if (values[x + 1][y] == values[x][y])
				return true;
		}
		if (y > 0) {
			if (values[x][y - 1] == values[x][y])
				return true;
		}
		if (y < 3) {
			if (values[x][y + 1] == values[x][y])
				return true;
		}
		return false;

	}

}