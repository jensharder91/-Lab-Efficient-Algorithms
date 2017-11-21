package sheet03.task_04_LongestIncreasingSubsequence;

import java.util.ArrayList;
import java.util.Scanner;

public class LongestIncreasingSubsequence {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		ArrayList<Integer> nums = new ArrayList<>();

		while (scanner.hasNext()) {
			// String newInput = scanner.next();
			// if(newInput.equals("") || newInput.equals("end")) {
			// break;
			// }
			// int newNumber = Integer.valueOf(newInput);

			int newNumber = scanner.nextInt();

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
		scanner.close();

	}

}
