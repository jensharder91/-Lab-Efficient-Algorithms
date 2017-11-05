package sheet01.task_01;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		if (scanner.hasNextInt()) {

			int a;
			int b;
			int counter = scanner.nextInt();
			//check for invalid input
			if (counter > 100)
				counter = 100;
			for (int c = counter; c> 0; c--) {

				a = scanner.nextInt();
				b = scanner.nextInt();
			//check for invalid input 
				if ( ( a< -1000 || a > 1000) || ( b < -1000 || b > 1000))
					System.out.println("invalid input");
				else {
					if (a < b) {
						System.out.println("less than");
					} else if (a > b) {
						System.out.println("greater than");
					} else {
						System.out.println("equal to");
					}
				}
			}
		}

		scanner.close();
	}
}
