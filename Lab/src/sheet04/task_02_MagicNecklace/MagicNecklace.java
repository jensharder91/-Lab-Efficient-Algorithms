package sheet04.task_02_MagicNecklace;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
3
3
1
2
3
7
5
6
4
7
3
8
2
16
50
1
20
21
16
17
18
19
2
3
4
5
6
7
8
9
 */
// 3   --  7   -- 9
public class MagicNecklace {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.valueOf(br.readLine());

		for (int testcase = 0; testcase < testCases; testcase++) {

			ArrayList<Integer> nums = new ArrayList<>();

			int numberPearls = Integer.valueOf(br.readLine());

			int[] input = new int[2 * numberPearls];

			for (int pearl = 0; pearl < numberPearls; pearl++) {
				int curPearl = Integer.valueOf(br.readLine());
				input[numberPearls - 1 - pearl] = curPearl;
				input[numberPearls + pearl] = curPearl;
			}

			for (int nextPearl = 0; nextPearl < input.length; nextPearl++) {

				if (nums.size() == 0 || input[nextPearl] > nums.get(nums.size() - 1)) {
					// just add number to our list
					nums.add(input[nextPearl]);
				} else {
					int i = 0;
					int j = nums.size() - 1;

					// binary search
					while (i < j) {
						int mid = (i + j) / 2;
						if (nums.get(mid) < input[nextPearl]) {
							i = mid + 1;
						} else {
							j = mid;
						}
					}
					// replace the smallest element which is bigger then the newNumber
					nums.set(j, input[nextPearl]);
				}
			}

			System.out.println(nums.size());

		}
	}
}
