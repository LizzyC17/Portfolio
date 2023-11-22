package crossyRoad;
import javax.swing.JFrame;
public class Driver {
	public static void main(String[] args)  {
		JFrame frame = new JFrame("Lizzy's Crossy Road");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new CrossyPanel());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}