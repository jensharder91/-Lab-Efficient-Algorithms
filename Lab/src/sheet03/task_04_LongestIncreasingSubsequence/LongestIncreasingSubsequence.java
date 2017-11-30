package sheet03.task_04_LongestIncreasingSubsequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
0
8
4
12
2
10
6
14
1
9
5
13
11
7
15
 */
//result 6
public class LongestIncreasingSubsequence {
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<Integer> nums = new ArrayList<>();

		while (true) {
			// String newInput = scanner.next();
			// if(newInput.equals("") || newInput.equals("end")) {
			// break;
			// }
			// int newNumber = Integer.valueOf(newInput);
			
			String input = br.readLine();
			
			if(input == null || input.equals("")) {
				break;
			}

			int newNumber = Integer.valueOf(input);

			if (nums.size() == 0 || newNumber > nums.get(nums.size() - 1)) {
				// just add number to our list
				nums.add(newNumber);
			} else {
				int i = 0;
				int j = nums.size() - 1;

				//binary search 
				while (i < j) {
					int mid = (i + j) / 2;
					if (nums.get(mid) < newNumber) {
						i = mid + 1;
					} else {
						j = mid;
					}
				}
				//replace the smallest element which is bigger then the newNumber
				nums.set(j, newNumber);
			}
		}

		System.out.println(nums.size());

	}

}
