package othello;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OthelloPanel extends JPanel {

	private Color turn = Color.BLACK;
	private JLabel player;
	private JLabel credits;
	private boolean sameAbove, sameBelow, sameLeft, sameRight;
	private boolean sameUpR, sameUpL, sameDownL, sameDownR;
	private final int WIDTH = 100, numBoxes = 8;
	private int left, right, up, down, upL, upR, downL, downR;
	private int x, y;
	private JButton restart, cantMove;
	private OthelloPiece[][] pieces;

	public OthelloPanel() {

		cantMove = new JButton("Can't move");
		cantMove.addActionListener(new ButtonListener());
		add(cantMove);
		restart = new JButton("Restart");
		restart.addActionListener(new ButtonListener());
		add(restart);
		credits = new JLabel("Created by Lizzy Cable.");
		credits.setForeground(Color.red);
		credits.setFont(new Font("Serif", Font.ROMAN_BASELINE, 20));
		add(credits);

		pieces = new OthelloPiece[8][8];

		player = new JLabel("Black's turn.");
		player.setFont(new Font("Serif", Font.ITALIC, 20));
		player.setForeground(Color.red);
		add(player);

		Color background = new Color(10, 100, 0);
		setBackground(background);

		initializeBoard();
		
		setPreferredSize(new Dimension(800, 800));

		clickListener listen = new clickListener();
		addMouseListener(listen);
	}

	@Override
	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		for (int i = 0; i < 8; i++) {
			for (int a = 0; a < 8; a++) {
				if (pieces[i][a] != null) {
					page.setColor(pieces[i][a].color);
					page.fillOval(pieces[i][a].x, pieces[i][a].y, WIDTH, WIDTH);
				}
			}
		}
	}
	
	public void initializeBoard() {
		pieces[3][3] = new OthelloPiece(Color.WHITE, 300, 300);
		pieces[3][4] = new OthelloPiece(Color.BLACK, 300, 400);
		pieces[4][3] = new OthelloPiece(Color.BLACK, 400, 300);
		pieces[4][4] = new OthelloPiece(Color.WHITE, 400, 400);	
	}

	public class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource().equals(restart)) {
				for (int i = 0; i < 8; i++) {
					for (int a = 0; a < 8; a++) {
						pieces[i][a] = null;

					}
				}
				initializeBoard();
				repaint();
				turn = Color.black;
			}
			if (e.getSource().equals(cantMove)) {
				if (turn.equals(Color.black)) {
					turn = Color.white;
					player.setText("White's turn");
				} else {
					turn = Color.black;
					player.setText("Black's turn ");
				}
			}
		}
	}

	public class clickListener implements MouseListener {

		@Override
		public void mousePressed(MouseEvent e) {

			Point clicked = e.getPoint();
			x = clicked.x / WIDTH;
			y = clicked.y / WIDTH;
			y = y * WIDTH;
			x = x * WIDTH;

			clicked = new Point(x, y);
			boolean touchesShape = false;
			boolean sameAsOther = false;

			touchesShape = touchesShape(x, y);
			sameAsOther = isAnotherPiece(x, y);

			if (touchesShape == true && sameAsOther == false) {

				sameAbove = false;
				sameBelow = false;
				sameLeft = false;
				sameRight = false;
				sameUpR = false;
				sameUpL = false;
				sameDownR = false;
				sameDownL = false;
				left = x / WIDTH;
				right = x / WIDTH;
				up = y / WIDTH;
				down = y / WIDTH;
				upR = x / WIDTH;
				upL = x / WIDTH;
				downR = x / WIDTH;
				downL = x / WIDTH;

				canFlipWhat();

				if (sameAbove == true || sameBelow == true || sameRight == true || sameLeft == true || sameUpL == true
						|| sameDownR == true || sameUpR == true || sameDownL == true) {

					pieces[x / WIDTH][y / WIDTH] = new OthelloPiece(turn, x, y);
					repaint();

					flipPieces();

					if (turn.equals(Color.WHITE)) {
						turn = Color.BLACK;
						player.setText("Black's turn.");
					} else if (turn.equals(Color.BLACK)) {
						turn = Color.WHITE;
						player.setText("White's turn.");

					}

				}
				gameFinish();

			}

		}

		@Override
		public void mouseClicked(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		}

		@Override
		public void mouseExited(MouseEvent e) {
		}

	}

	public boolean isAnotherPiece(int x, int y) {
		boolean sameAsOther = false;
		for (int i = 0; i < 8; i++) {
			for (int a = 0; a < 8; a++) {
				if (pieces[i][a] != null) {
					Point current = new Point(pieces[i][a].x, pieces[i][a].y);
					if (x == current.x && y == current.y) {
						sameAsOther = true;
					}
				}
			}
		}
		return sameAsOther;
	}

	public void gameFinish() {
		int black = 0, white = 0;
		for (int i = 0; i < 8; i++) {
			for (int a = 0; a < 8; a++) {
				if (pieces[i][a] != null) {
					if (pieces[i][a].color.equals(Color.black))
						black++;
					else
						white++;
				}
			}
		}
		if (turn == Color.black)
			player.setText("Black's turn " + black + " black and " + white + " white");
		else
			player.setText("White's turn " + black + " black and " + white + " white");

	}

	public void flipPieces() {

		if (sameAbove == true)
			for (int i = up; i < y / WIDTH; i++)
				if (pieces[x / WIDTH][i] != null)
					pieces[x / WIDTH][i].color = turn;

		if (sameBelow == true)
			for (int i = down; i > y / WIDTH; i--)
				if (pieces[x / WIDTH][i] != null)
					pieces[x / WIDTH][i].color = turn;

		if (sameRight == true)
			for (int i = right; i > x / WIDTH; i--)
				if (pieces[i][y / WIDTH] != null)
					pieces[i][y / WIDTH].color = turn;

		if (sameLeft == true)
			for (int i = left; i < x / WIDTH; i++)
				if (pieces[i][y / WIDTH] != null)
					pieces[i][y / WIDTH].color = turn;

		if (sameUpL == true)
			for (int a = x / WIDTH, b = y / WIDTH; a > upL; a--, b--)
				if (pieces[a][b] != null)
					pieces[a][b].color = turn;

		if (sameDownR == true)
			for (int a = x / WIDTH, b = y / WIDTH; a < downR; a++, b++)
				if (pieces[a][b] != null)
					pieces[a][b].color = turn;

		if (sameUpR == true)
			for (int a = x / WIDTH, b = y / WIDTH; a < upR; a++, b--)
				if (pieces[a][b] != null)
					pieces[a][b].color = turn;

		if (sameDownL == true)
			for (int a = x / WIDTH, b = y / WIDTH; a > downL; a--, b++)
				if (pieces[a][b] != null)
					pieces[a][b].color = turn;

		repaint();
	}

	public void canFlipWhat() {

		for (int i = x / WIDTH - 1, b = y / WIDTH + 1; i > -1 && b < numBoxes; i--, b++) {
			if (pieces[i][b] == null)
				break;
			else if (pieces[i][b].color.equals(turn)) {
				downL = i;
				break;
			}
		}

		for (int a = x / WIDTH, b = y / WIDTH; a > downL; a--, b++)
			if (pieces[a][b] != null)
				if (pieces[a][b].color != turn)
					sameDownL = true;

		for (int i = x / WIDTH + 1, b = y / WIDTH - 1; i < numBoxes && b > -1; i++, b--) {
			if (pieces[i][b] == null)
				break;
			else if (pieces[i][b].color.equals(turn)) {
				upR = i;
				break;
			}
		}

		for (int a = x / WIDTH, b = y / WIDTH; a < upR; a++, b--)
			if (pieces[a][b] != null)
				if (pieces[a][b].color != turn)
					sameUpR = true;

		for (int i = x / WIDTH - 1, b = y / WIDTH - 1; i > -1 && b > -1; i--, b--)
			if (pieces[i][b] == null)
				break;
			else if (pieces[i][b].color.equals(turn)) {
				upL = i;
				break;
			}

		for (int a = x / WIDTH, b = y / WIDTH; a > upL; a--, b--)
			if (pieces[a][b] != null)
				if (pieces[a][b].color != turn)
					sameUpL = true;

		for (int i = x / WIDTH + 1, b = y / WIDTH + 1; i < numBoxes && b < numBoxes; i++, b++)
			if (pieces[i][b] == null)
				break;
			else if (pieces[i][b].color.equals(turn)) {
				downR = i;
				break;
			}

		for (int a = x / WIDTH, b = y / WIDTH; a < downR; a++, b++)
			if (pieces[a][b] != null)
				if (pieces[a][b].color != turn)
					sameDownR = true;

		for (int i = y / WIDTH - 1; i > -1; i--)
			if (pieces[x / WIDTH][i] == null)
				break;
			else if (pieces[x / WIDTH][i].color.equals(turn)) {
				up = i;
				break;
			}

		for (int a = up; a < y / WIDTH; a++)
			if (pieces[x / WIDTH][a] != null)
				if (pieces[x / WIDTH][a].color != turn)
					sameAbove = true;

		for (int i = y / WIDTH + 1; i < numBoxes; i++)
			if (pieces[x / WIDTH][i] == null)
				break;
			else if (pieces[x / WIDTH][i].color.equals(turn)) {
				down = i;
				break;
			}

		for (int a = down; a > y / WIDTH; a--)
			if (pieces[x / WIDTH][a] != null)
				if (pieces[x / WIDTH][a].color != turn)
					sameBelow = true;

		for (int i = x / WIDTH - 1; i > -1; i--)
			if (pieces[i][y / WIDTH] == null)
				break;
			else if (pieces[i][y / WIDTH].color.equals(turn)) {
				left = i;
				break;
			}

		for (int i = left; i < x / WIDTH; i++)
			if (pieces[i][y / WIDTH] != null)
				if (pieces[i][y / WIDTH].color != turn)
					sameLeft = true;

		for (int i = x / WIDTH + 1; i < numBoxes; i++)
			if (pieces[i][y / WIDTH] == null)
				break;
			else if (pieces[i][y / WIDTH].color.equals(turn)) {
				right = i;
				break;
			}

		for (int i = right; i > x / WIDTH; i--)
			if (pieces[i][y / WIDTH] != null)
				if (pieces[i][y / WIDTH].color != turn)
					sameRight = true;

	}

	public boolean touchesShape(int x, int y) {
		boolean touchesShape = false;
		for (int i = 0; i < 8; i++) {
			for (int a = 0; a < 8; a++) {
				if (pieces[i][a] != null) {
					Point current = new Point(pieces[i][a].x, pieces[i][a].y);
					if ((x + WIDTH == current.x && (y == current.y || y + WIDTH == current.y || y - WIDTH == current.y))
							|| (x - WIDTH == current.x
									&& (y == current.y || y + WIDTH == current.y || y - WIDTH == current.y))
							|| (x == current.x && (y == current.y || y + WIDTH == current.y || y - WIDTH == current.y)))
						touchesShape = true;
				}
			}
		}
		return touchesShape;
	}
}