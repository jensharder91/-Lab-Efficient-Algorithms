package sheet03.task_03_MaximumProduct;

import java.util.Scanner;

// 5     4 -5 2 -2 -30   3 -3 0 -4   3 0 0 0   3 -1 -2 -3   6 -20 2 -2 0 5 4
// 120  ---  0  ---   0  --- 0  ---  80
public class MaximumProduct {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int numberTestCases = scanner.nextInt();

		for (int i = 0; i < numberTestCases; i++) {
			int k = scanner.nextInt();

			int[] input = new int[k];

			int maxPositive = 0;
			int maxNegative = 0;

			int max = 0;

			for (int j = 0; j < k; j++) {
				input[j] = scanner.nextInt();

				if (input[j] > 0) {
					maxPositive = Math.max(maxPositive * input[j], input[j]);
					maxNegative = Math.min(maxNegative * input[j], 0);
				}
				if (input[j] < 0) {
					int oldMaxPositive = maxPositive;
					int oldMaxNegative = maxNegative;

					maxPositive = Math.max(oldMaxNegative * input[j], 0);
					maxNegative = Math.min(oldMaxPositive * input[j], input[j]);
				}
				if (input[j] == 0) {
					maxPositive = 0;
					maxNegative = 0;
				}

				max = Math.max(maxPositive, max);
			}

			System.out.println(max);

		}

		scanner.close();
	}

}
