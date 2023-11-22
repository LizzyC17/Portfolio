package pingPong;
import java.io.FileNotFoundException;

import javax.swing.JFrame;
public class Driver {
	public static void main(String[] args)  {
		JFrame frame = new JFrame("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new PingPongPanel());
		frame.pack();
		frame.setVisible(true);
	}
}