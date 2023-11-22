package pp0812;



import java.util.Scanner;

import javax.swing.JFrame;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter how many stars you want, and what size.");
		int starnumber = scan.nextInt();
		int starsize = scan.nextInt();
		JFrame frame = new JFrame("Stars");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new StarPanel(starnumber,starsize));
		frame.pack();
		frame.setVisible(true);
		
	}

}
