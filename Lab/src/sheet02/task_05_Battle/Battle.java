package sheet02.task_05_Battle;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Battle {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numberTextCases = scanner.nextInt();
		int numberArenas;
		int numberWarriors_1;
		int numberWarriors_2;

		Integer[] warriors_1;
		Integer[] warriors_2;

		// loop for every test case
		for (int i = 0; i < numberTextCases; i++) {

			// meta data for testcase
			numberArenas = scanner.nextInt();
			numberWarriors_1 = scanner.nextInt();
			numberWarriors_2 = scanner.nextInt();

			// if less warriors then arenas, adjust
			if (numberArenas > numberWarriors_1)
				numberArenas = numberWarriors_1;
			if (numberArenas > numberWarriors_2)
				numberArenas = numberWarriors_2;

			warriors_1 = new Integer[numberWarriors_1];
			warriors_2 = new Integer[numberWarriors_2];

			// warriors_1
			for (int j = 0; j < numberWarriors_1; j++) {
				warriors_1[j] = scanner.nextInt();
			}
			Arrays.sort(warriors_1, Collections.reverseOrder());
			// warriors_2
			for (int j = 0; j < numberWarriors_2; j++) {
				warriors_2[j] = scanner.nextInt();
			}
			Arrays.sort(warriors_2, Collections.reverseOrder());

			boolean stillFighting = true;
			while (stillFighting) {

				// loop all arena
				for (int a = 0; a < numberArenas; a++) {

					int warrior_1_value = warriors_1[a];
					int warrior_2_value = warriors_2[a];

					if (warrior_1_value > 0 && warrior_2_value > 0) {
						warriors_1[a] -= warrior_2_value;
						warriors_2[a] -= warrior_1_value;
					} else {
						break;
					}
				}

				// stort arrays
				Arrays.sort(warriors_1, Collections.reverseOrder());
				Arrays.sort(warriors_2, Collections.reverseOrder());

				// if all warriors are dead from (at least) one tribe
				if (warriors_1[0] <= 0 || warriors_2[0] <= 0) {
					stillFighting = false;
				}
			}

			if (warriors_1[0] <= 0 && warriors_2[0] <= 0) {
				System.out.println("Both tribes died");
			} else if (warriors_2[0] <= 0) {
				System.out.println("The first tribe survived");
				printAll(warriors_1);
			} else {
				System.out.println("The second tribe survived");
				printAll(warriors_2);
			}
		}

		scanner.close();
	}

	private static void printAll(Integer[] warriors) {
		for (int j = 0; j < warriors.length; j++) {
			if (warriors[j] > 0) {
				System.out.println(warriors[j]);
			} else {
				break;
			}
		}
	}
}
