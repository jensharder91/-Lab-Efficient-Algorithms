package sheet05.task_05_PlayingAGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
5
33
771
4
111113
6131545511
 */
// result 
// Case 1: T
// Case 2: T
// Case 3: S
// Case 4: T
// Case 5: S
public class PlayingAGame {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.valueOf(br.readLine());

		for (int testCase = 0; testCase < testCases; testCase++) {
			String[] singleChars = br.readLine().split("(?<=.)");

			boolean[] containsDigit = new boolean[10];
			int counter = 0;
			int sum = 0;
			
			for (int i = 0; i < singleChars.length; i++) {
				int value = Integer.valueOf(singleChars[i]);
				if (value == 3 || value == 6 || value == 9) {
					counter++;
				}

				containsDigit[value] = true;
				sum += value;
			}

			int mod = sum % 3;

			if (mod == 0) {
				printResult(testCase, counter);
			} else if (mod == 1) {
				if (containsDigit[1] || containsDigit[4] || containsDigit[7]) {
					counter++;
					printResult(testCase, counter);
				} else {
					printResult(testCase, 0);
				}
			} else {
				if (containsDigit[2] || containsDigit[5] || containsDigit[8]) {
					counter++;
					printResult(testCase, counter);
				} else {
					printResult(testCase, 0);
				}
			}
		}
	}

	private static void printResult(int caseNumer, int validSteps) {
		caseNumer++;
		if (validSteps % 2 == 0) {
			System.out.println("Case " + caseNumer + ": T");
		} else {
			System.out.println("Case " + caseNumer + ": S");
		}
	}
}
