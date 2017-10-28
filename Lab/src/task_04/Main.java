package task_04;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int numberOfTestCases;
		int centerX;
		int centerY;

		int testCaseX;
		int testCaseY;

		while (true) {

			numberOfTestCases = scanner.nextInt();

			// end
			if (numberOfTestCases == 0) {
				break;
			}

			// center coordinates
			centerX = scanner.nextInt();
			centerY = scanner.nextInt();

			for (int coutner = numberOfTestCases; coutner > 0; coutner--) {

				testCaseX = scanner.nextInt();
				testCaseY = scanner.nextInt();

				// axes
				if (testCaseX == centerX || testCaseY == centerY) {
					System.out.println("axes");
				} else {
					if (testCaseX < centerX) {//left
						if (testCaseY < centerY) {//bottom
							System.out.println("BL");
						} else {//top
							System.out.println("TL");
						}
					} else {//right
						if (testCaseY < centerY) {//bottom
							System.out.println("BR");
						} else {//top
							System.out.println("TR");
						}
					}
				}
			}

		}

		scanner.close();
	}
}
