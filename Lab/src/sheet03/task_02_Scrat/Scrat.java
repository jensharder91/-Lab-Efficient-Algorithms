package sheet03.task_02_Scrat;

import java.util.Scanner;

public class Scrat {
	public static void main(String[] args) {

		// Testing
		// 3 36 s 4 b 8 b 21 --> 15
		// 3 25 s 6 s 7 s 10 --> 18
		// 17 34 s 2 s 4 s 6 s 7 s 9 b 10 s 11 s 13 b 14 s 15 b 17 s 20 s 26 s 27 s 28 b 29 b 32 --> 9

		Scanner scanner = new Scanner(System.in);
		int numberIceFloes = scanner.nextInt();
		int distanceBothShores = scanner.nextInt();

		int lastStepPath1 = 0;
		int lastStepPath2 = 0;

		int maxJump = 0;

		for (int i = 0; i < numberIceFloes; i++) {
			String floesType = scanner.next();
			int distance = scanner.nextInt();

			if (floesType.equals("s")) {// short floe
				if (i % 2 == 0) {
					if (distance - lastStepPath1 > maxJump) {
						maxJump = distance - lastStepPath1;
					}
					lastStepPath1 = distance;
				} else {
					if (distance - lastStepPath2 > maxJump) {
						maxJump = distance - lastStepPath2;
					}
					lastStepPath2 = distance;
				}

			} // big floes
			else {

				if (distance - lastStepPath1 > maxJump) {
					maxJump = distance - lastStepPath1;
				}
				if (distance - lastStepPath2 > maxJump) {
					maxJump = distance - lastStepPath2;
				}

				// both paths use this floe
				lastStepPath1 = distance;
				lastStepPath2 = distance;
			}
		}
		if (distanceBothShores - lastStepPath1 > maxJump) {
			maxJump = distanceBothShores - lastStepPath1;
		}
		if (distanceBothShores - lastStepPath2 > maxJump) {
			maxJump = distanceBothShores - lastStepPath2;
		}

		System.out.println(maxJump);
		scanner.close();
	}

}
