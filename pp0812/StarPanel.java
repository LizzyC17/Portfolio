package pp0812;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JPanel;

public class StarPanel extends JPanel {

	private int[] XOnStarFromCenter = { -4, -10, -2, 0, 2, 10, 4, 6, 0, -6 };
	private int[] YOnStarFromCenter = { 0, 4, 4, 10, 4, 4, 0, -10, -4, -10 };

	Random generator;
	int starnumber;

	public StarPanel(int starnumber,int starsize) {
		this.starnumber = starnumber;
		setBackground(Color.black);
		setPreferredSize(new Dimension(400, 400));
		generator = new Random();
		
		for(int a = 0; a<10;a++) {
			XOnStarFromCenter[a] =XOnStarFromCenter[a]*starsize; 
			YOnStarFromCenter[a] = YOnStarFromCenter[a]*starsize;
		}
	}

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		int[] xonStar = new int[10];
		int[] yonStar = new int[10];
		super.paintComponent(page);
		page.setColor(Color.yellow);

		for (int a = 0; a < starnumber; a++) {
			int x = generator.nextInt(400);
			int y = generator.nextInt(400);
			for (int i = 0; i < 10; i++) {
				xonStar[i] = x + XOnStarFromCenter[i];
				yonStar[i] = y + YOnStarFromCenter[i];
			}
			page.fillPolygon(xonStar, yonStar, xonStar.length);
		}

	}

}
