package game2048;
import javax.swing.JFrame;
public class Driver {

	public static void main(String[] args)  {
		JFrame frame = new JFrame("Lizzy's 2048");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Panel2048());
		frame.pack();
		frame.setVisible(true);
	}
}