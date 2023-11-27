package pp085;

import java.util.Scanner;
whoo changing everything!

public class MeanStandard {

	public static void main(String[] args) {
	String yesno = "";	
	int[] numberlist = new int[50];
	double mean=0.0;
	double stdev=0.0;
	
	Scanner scan = new Scanner(System.in);
	System.out.println("If you want to enter data type yes");
	
	yesno = scan.next();
	int position = 0;
	
	while(yesno.equalsIgnoreCase("yes")) {
		System.out.println("Enter data.");
		numberlist[position] = scan.nextInt();
		position++;
		System.out.println("Do you want to enter more data?");
		yesno = scan.next();
	}
	int numNumbers = position+1;
	while(position>-1) {
		mean = mean+numberlist[position];
		position--;
	}
	position  = numNumbers-1;
	mean = mean/numNumbers;
	
	while(position>-1) {
		stdev =stdev+Math.pow (numberlist[position]-mean,2);
		position--;
	}
	stdev = Math.sqrt(stdev/(numNumbers-1));
	System.out.println("Your mean is "+mean+" and your standard deviation is "+stdev);
	}

}
