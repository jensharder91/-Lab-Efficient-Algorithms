package sheet05.task_04_MaximumSubarrayProblem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
4
-2
-3
2
3
-1
4
-1
 */
public class MaximumSubarrayProblem {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int globalMax = 0;
		int curSum = 0;

		while (true) {
			String input = br.readLine();

			if (input == null || input.equals("")) {
				break;
			}

			int value = Integer.valueOf(input);

			curSum += value;

			if (curSum < 0) {
				curSum = 0;
			}

			if (curSum > globalMax) {
				globalMax = curSum;
			}

		}

		System.out.println(globalMax);
	}
}
