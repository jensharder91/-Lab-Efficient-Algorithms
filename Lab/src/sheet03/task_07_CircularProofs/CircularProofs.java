package sheet03.task_07_CircularProofs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class CircularProofs {

	/*
22 2
3 2
3 5
5 4
2 3
5 3
4 2
5 4
6 3
3 2
6 1
3 5
6 5
3 2
6 1
3 5
1 3
1 4
5 1
2 3
0 0
*/
// result 1 3 2 4 5 6 --- 1 5 4 2 3 --- 3 2 4 5 6 1 --- 6 1 3 2 4 5 -- 1 2 3 4 5

	private static DependencyItem[] unsortedList;
	private static PriorityQueue<Integer> priorityQueue;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// loop for each test case
		while (true) {

			String[] metaData = br.readLine().split(" ");

			int lemmataNumber = Integer.valueOf(metaData[0]);
			int dependencyNumer = Integer.valueOf(metaData[1]);

			// break condition, last testcase done.
			if (lemmataNumber == 0 && dependencyNumer == 0) {
				break;
			}

			unsortedList = new DependencyItem[lemmataNumber];
			priorityQueue = new PriorityQueue<>();

			// init for all lemma the dependencyItem
			for (int i = 0; i < lemmataNumber; i++) {
				unsortedList[i] = new DependencyItem(i);
			}

			// insert dependencies (locks)
			for (int j = 0; j < dependencyNumer; j++) {
				String[] dependency = br.readLine().split(" ");
				int dependencyFrom = Integer.valueOf(dependency[0]) - 1;
				int dependencyTo = Integer.valueOf(dependency[1]) - 1;

				unsortedList[dependencyFrom].addLock(unsortedList[dependencyTo]);
			}

			// loop and add all unlocked items to queue
			for (int i = 0; i < unsortedList.length; i++) {
				if (unsortedList[i].isUnlocked()) {
					priorityQueue.add(unsortedList[i].value);
				}
			}

			// loop queue, unlock items (evtl add them to queue)
			StringBuffer output = new StringBuffer(lemmataNumber*2);
			while (!priorityQueue.isEmpty()) {

				int curValue = priorityQueue.poll();
				 output.append((curValue + 1) + " ");
				unsortedList[curValue].removeAllLocks();
			}
			System.out.println(output.toString());

		}
	}

	private static class DependencyItem {

		private ArrayList<DependencyItem> lockedItems = new ArrayList<DependencyItem>();
		private int lockCounter = 0;
		private int value;

		public DependencyItem(int value) {
			this.value = value;
		}

		public void increaseLock() {
			this.lockCounter++;
		}

		public void addLock(DependencyItem item) {
			this.lockedItems.add(item);
			item.increaseLock();
		}

		public boolean isUnlocked() {
			return this.lockCounter <= 0;
		}

		public boolean decreaseLock() {
			this.lockCounter--;
			if (this.lockCounter <= 0) {
				return true;
			}
			return false;
		}

		public void removeAllLocks() {
			for (int i = 0; i < this.lockedItems.size(); i++) {
				// decrease lockCounter for each item (not locked anymore from this one)
				boolean unlockedCompletly = this.lockedItems.get(i).decreaseLock();
				// if it is completely unlocked -> priorityQ
				if (unlockedCompletly) {
					priorityQueue.add(this.lockedItems.get(i).value);
				}
			}
			this.lockedItems.clear();
		}
	}
}
