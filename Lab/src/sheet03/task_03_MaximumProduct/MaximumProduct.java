package sheet03.task_03_MaximumProduct;

import java.math.BigInteger;
import java.util.Scanner;

// 6     4 -5 2 -2 -30   3 -3 0 -4   3 0 0 0   3 -1 -2 -3   6 -20 2 -2 0 5 4    200   20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20 20
// 120  ---  0  ---   0  --- 6  ---  80
public class MaximumProduct {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int numberTestCases = scanner.nextInt();

		for (int i = 0; i < numberTestCases; i++) {
			int k = scanner.nextInt();

			int input;

			BigInteger maxPositive = BigInteger.ZERO;
			BigInteger maxNegative = BigInteger.ZERO;

			BigInteger max = BigInteger.valueOf(0);

			for (int j = 0; j < k; j++) {
				input = scanner.nextInt();

				if (input > 0) {
					// positive number
					maxPositive = maxPositive.multiply(BigInteger.valueOf(input)).max(BigInteger.valueOf(input));
					// maxPositive = Math.max(maxPositive * input, input);
					maxNegative = maxNegative.multiply(BigInteger.valueOf(input)).min(BigInteger.ZERO);
					// maxNegative = Math.min(maxNegative * input, 0);
				} else if (input < 0) {
					// negative number
					BigInteger oldMaxPositive = maxPositive;
					BigInteger oldMaxNegative = maxNegative;
					// int oldMaxPositive = maxPositive;
					// int oldMaxNegative = maxNegative;
					maxPositive = oldMaxNegative.multiply(BigInteger.valueOf(input)).max(BigInteger.ZERO);
					// maxPositive = Math.max(oldMaxNegative * input, 0);
					maxNegative = oldMaxPositive.multiply(BigInteger.valueOf(input)).min(BigInteger.valueOf(input));
					// maxNegative = Math.min(oldMaxPositive * input, input);
				} else if (input == 0) {
					// reset maxpositive / maxNegative counter
					maxPositive = BigInteger.ZERO;
					maxNegative = BigInteger.ZERO;
				}

				// max = Math.max(maxPositive, max);
				max = maxPositive.max(max);
			}

			System.out.println(max);

		}

		scanner.close();
	}

}
