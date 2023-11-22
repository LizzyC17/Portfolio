package grapher;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderPanel extends JPanel {
	int a1 = 1, b1 = 1, c1 = 1;
	JSlider a, b, c, coefficientselector;
	GrapherPanel grapher;
	JCheckBox ln, sin, cos, parabola;
	boolean onparabola, onsin, oncos, onln;
	int graph = 0;

	public SliderPanel() {
		setLayout(new GridLayout(1, 2));
		SliderListener listener = new SliderListener();

		a = new JSlider(JSlider.VERTICAL, -25, 25, 1);
		a.setMajorTickSpacing(50);
		a.setMinorTickSpacing(10);
		a.setPaintTicks(true);
		a.setPaintLabels(true);
		a.setAlignmentX(Component.LEFT_ALIGNMENT);
		//a.setBackground(Color.gray);
		b = new JSlider(JSlider.VERTICAL, -25, 25, 1);
		b.setMajorTickSpacing(50);
		b.setMinorTickSpacing(10);
		b.setPaintTicks(true);
		b.setPaintLabels(true);
		b.setAlignmentX(Component.LEFT_ALIGNMENT);
		//b.setBackground(Color.gray);
		c = new JSlider(JSlider.VERTICAL, -25, 25, 1);
		c.setMajorTickSpacing(50);
		c.setMinorTickSpacing(10);
		c.setPaintTicks(true);
		c.setPaintLabels(true);
		c.setAlignmentX(Component.LEFT_ALIGNMENT);
		c.addChangeListener(listener);

		//c.setBackground(Color.gray);
		coefficientselector = new JSlider(JSlider.VERTICAL, 0, 3, 1);
		coefficientselector.setMajorTickSpacing(3);
		coefficientselector.setMinorTickSpacing(1);
		coefficientselector.setPaintTicks(true);
		coefficientselector.setPaintLabels(true);
		coefficientselector.setAlignmentX(Component.LEFT_ALIGNMENT);
		//coefficientselector.setBackground(Color.gray);
		a.addChangeListener(listener);
		b.addChangeListener(listener);
		JPanel sliderPanel = new JPanel();
		sliderPanel.add(a);
		sliderPanel.add(b);
		sliderPanel.add(c);
		coefficientselector.addChangeListener(listener);
		sliderPanel.add(coefficientselector);
		sliderPanel.setPreferredSize(new Dimension(250, 300));
		add(sliderPanel);
		grapher = new GrapherPanel(a1, b1, c1);
		add(grapher);
		ln = new JCheckBox("LN graph");
		CheckListener checkListener = new CheckListener();
		ln.addItemListener(checkListener);
		sliderPanel.add(ln);
		parabola = new JCheckBox("parabola graph");
		parabola.addItemListener(checkListener);
		sliderPanel.add(parabola);
		sin = new JCheckBox("sin graph");
		sin.addItemListener(checkListener);
		sliderPanel.add(sin);
		cos = new JCheckBox("cos graph");
		cos.addItemListener(checkListener);
		sliderPanel.add(cos);
		JLabel instructions = new JLabel("0 is sin, 1 cos, 3 ln and 4 parabola");
		sliderPanel.add(instructions);
		//sliderPanel.setBackground(Color.lightGray);
		//ln.setBackground(Color.gray);
		//cos.setBackground(Color.gray);
		//sin.setBackground(Color.gray);
		//parabola.setBackground(Color.gray);
	}

	private class SliderListener implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			a1 = a.getValue();
			b1 = b.getValue();
			c1 = 10 * c.getValue();
			grapher.recieveNewValues(a1 / 10.0, b1, c1);
			grapher.repaint();
			graph = coefficientselector.getValue();

			grapher.setCoefficientSelector(graph);
		}

	}

	private class CheckListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource().equals(ln)) {
				if (ln.isSelected())
					grapher.setGraphType("ln");
				else
					grapher.removeGraphType("ln");
			}
			if (e.getSource().equals(cos)) {
				if (cos.isSelected())
					grapher.setGraphType("cos");
				else
					grapher.removeGraphType("cos");
			}
			if (e.getSource().equals(sin)) {
				if (sin.isSelected())
					grapher.setGraphType("sin");
				else
					grapher.removeGraphType("sin");
			}
			if (e.getSource().equals(parabola)) {
				if (parabola.isSelected())
					grapher.setGraphType("parabola");
				else
					grapher.removeGraphType("parabola");
			}

			repaint();
		}
	}

}
