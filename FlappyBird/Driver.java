package flappyBird;
import javax.swing.JFrame;
public class Driver {
	public static void main(String[] args)  {
		JFrame frame = new JFrame("Lizzy's Flappy Bird");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new FlappyPanel());
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
}