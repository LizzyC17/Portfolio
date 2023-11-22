package othello;

import javax.swing.JFrame;
public class Driver {
	public static void main(String[] args)  {
		JFrame frame = new JFrame("Othello");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new OthelloPanel());
		frame.pack();
		frame.setVisible(true);
	}
}
