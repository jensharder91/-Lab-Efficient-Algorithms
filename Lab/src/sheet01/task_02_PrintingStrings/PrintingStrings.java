package sheet01.task_02_PrintingStrings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

//AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz
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
			// Character[] permutationObject = new Character[word.length()];
			// for (int i = 0; i < permutationObject.length; i++) {
			// permutationObject[i] = new Character(word.charAt(i));
			// }
			// java.util.Arrays.sort(permutationObject, new Comparator<Character>() {
			// @Override
			// public int compare(Character o1, Character o2) {
			// int lowerCaseTest = Character.toLowerCase(o1) - Character.toLowerCase(o2);
			// if (lowerCaseTest == 0) {
			// return o1 - o2;
			// }
			// return lowerCaseTest;
			// }
			// });
			// char[] permutation = new char[permutationObject.length];
			// for (int i = 0; i < permutationObject.length; i++) {
			// permutation[i] = permutationObject[i].charValue();
			// }
			permute(permutation, permutation.length, permutations);
			Collections.sort(permutations, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					int index = 0;
					while (index < o1.length() && index < o2.length()) {
						char char1 = o1.charAt(index);
						char char2 = o2.charAt(index);
						if (char1 - char2 != 0) {

							int lowerCaseTest = Character.toLowerCase(char1) - Character.toLowerCase(char2);

							if (lowerCaseTest == 0) {
								return char1 - char2;
							}
							return lowerCaseTest;
						}
						index++;
					}

					return 0;
				}
			});
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
