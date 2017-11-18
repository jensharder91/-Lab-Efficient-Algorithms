package sheet01.task_02_PrintingStrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PrintingStrings {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		
		String word; // the input word
		ArrayList<String> permutations = new ArrayList<String>();
		int numberOftestCases = scanner.nextInt();
		
		for (int n = 0; n < numberOftestCases; n++) {
			permutations.clear();
			word = scanner.next();

			char[] permutation = word.toCharArray(); // Convert to array of chars
			java.util.Arrays.sort(permutation);
			permute(permutation, permutation.length, permutations);
			Collections.sort(permutations);
			for (int i = 0; i < permutations.size(); i++)
				System.out.println(permutations.get(i));

		}
		scanner.close();
	}

	static void permute(char[] text, int n, ArrayList<String> permutations) {
		char temp;
		if (n <= 1) {
			if (!permutations.contains(String.valueOf(text))) {
				permutations.add(String.valueOf(text));
				// System.out.println(text);
			}
		} else {
			for (int k = 0; k < n; k++) {

				if (text[k] != text[n - 1]) { // don't print equal words twice
					// swap text[k], text[n-1]
					temp = text[k];
					text[k] = text[n - 1];
					text[n - 1] = temp;
					permute(text, n - 1, permutations);
					// swap text[k], text[n-1]
					temp = text[k];
					text[k] = text[n - 1];
					text[n - 1] = temp;
				}
			}
			permute(text, n - 1, permutations);
		}
	}
}
