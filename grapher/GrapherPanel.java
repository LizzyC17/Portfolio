package grapher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JSlider;

public class GrapherPanel extends JPanel {
	double a, b, c;
	int x;
	int y;
	boolean parabola = false, cos = false, sin = false, ln = false;
	double[][] coefficients;

	int currentgraph = 0;

	public GrapherPanel(double a, double b, double c) {
		setPreferredSize(new Dimension(200, 200));
		setBackground(Color.white);
		this.a = a;
		this.b = b;
		this.c = c;
		coefficients = new double[4][3];

	}

	public void recieveNewValues(double a, double b, double c) {
		coefficients[currentgraph][0] = a;
		coefficients[currentgraph][1] = b;
		coefficients[currentgraph][2] = c;

		this.a = a;
		this.b = b;
		this.c = c;
	}

	public void setCoefficientSelector(int graph) {
		currentgraph = graph;
	}

	public void paintComponent(Graphics page) {

		super.paintComponent(page);

		int previousx, previousy;

		page.drawLine(100, 0, 100, 200);
		page.drawLine(0, 100, 200, 100);

		if (sin == true) {
			a = coefficients[0][0];
			b = coefficients[0][1];
			c = coefficients[0][2];
			
			previousy = (int) Math.sin(-150);
			previousx = -150;

			x = previousx;
			while (x < 150) {
				x++;
				y = (int) ((10.0 * a * Math.sin((x + b) / 5.0) + c));
				page.drawLine(previousx + 100, 100 - previousy, 100 + x, 100 - y);
				previousx = x;
				previousy = y;
			}
		}

		if (cos == true) {
			a = coefficients[1][0];
			b = coefficients[1][1];
			c = coefficients[1][2];
			
			previousy = (int) Math.cos(-150);
			previousx = -150;

			x = previousx;
			while (x < 150) {
				x++;
				y = (int) ((10.0 * a * Math.cos((x + b) / 5.0) + c));
				page.drawLine(previousx + 100, 100 - previousy, 100 + x, 100 - y);
				previousx = x;
				previousy = y;
			}

		}

		if (ln == true) {
			a = coefficients[2][0];
			b = coefficients[2][1];
			c = coefficients[2][2];
			
			previousx = (int) -b;
			previousy = (int) (a * Math.log(0.000000000000001) + c);

			x = previousx;
			while (x < 200) {
				x++;
				y = (int) (a * Math.log(x + b + 0.000000000000000000000001) + c);
				page.drawLine(100 + previousx, 100 - previousy, 100 + x, 100 - y);
				previousy = y;
				previousx = x;

			}

		}

		if (parabola == true) {
			a = coefficients[3][0];
			b = coefficients[3][1];
			c = coefficients[3][2];
			x = -300;

			y = (int) c;

			while (x < 300) {
				previousx = x;
				x = x + 1;
				previousy = y;

				y = (int) (a * Math.pow(x, 2) + b * x + c);

				page.drawLine(100 + previousx, 100 - previousy, 100 + x, 100 - y);

			}


		}
	}

	public void setGraphType(String graphType) {
		if (graphType.equals("parabola")) {
			parabola = true;
		}
		if (graphType.equals("ln")) {
			ln = true;
		}
		if (graphType.equals("sin")) {
			sin = true;
		}
		if (graphType.equals("cos")) {
			cos = true;
		}
	}

	public void removeGraphType(String graphType) {
		if (graphType.equals("parabola")) {
			parabola = false;
		}
		if (graphType.equals("ln")) {
			ln = false;
		}
		if (graphType.equals("sin")) {
			sin = false;
		}
		if (graphType.equals("cos")) {
			cos = false;
		}
	}

}
