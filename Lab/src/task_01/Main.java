package task_01;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		if (scanner.hasNextInt()) {

			int a;
			int b;

			for (int coutner = scanner.nextInt(); coutner > 0; coutner--) {

				a = scanner.nextInt();
				b = scanner.nextInt();

				if (a < b) {
					System.out.println("less than");
				} else if (a > b) {
					System.out.println("greater than");
				} else {
					System.out.println("equal to");
				}
			}
		}

		scanner.close();
	}
}
