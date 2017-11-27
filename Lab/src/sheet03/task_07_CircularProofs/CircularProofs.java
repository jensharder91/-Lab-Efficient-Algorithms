package sheet03.task_07_CircularProofs;

import java.util.ArrayList;
import java.util.Scanner;

public class CircularProofs {

	// 6 2 3 2 3 5 5 4 2 3 5 3 4 2 5 4 6 3 3 2 6 1 3 5 6 5 3 2 6 1 3 5 1 3 1 4 0 0
	// result 1 3 2 4 5 6 --- 1 5 4 2 3 --- 3 2 4 5 6 1 --- 6 1 3 2 4 5

	private static DependencyItem[] unsortedList;
	private static int[] resultArray;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			int lemmataNumber = scanner.nextInt();
			int dependencyNumer = scanner.nextInt();

			// break condition
			if (lemmataNumber == 0 && dependencyNumer == 0) {
				break;
			}

			resultArray = new int[lemmataNumber];
			unsortedList = new DependencyItem[lemmataNumber];

			// create for each lemma a new entry in array. double link entities
			DependencyItem prev = null;
			for (int j = 0; j < lemmataNumber; j++) {
				DependencyItem now = new DependencyItem(j);
				unsortedList[j] = now;
				// double link entities
				if (j > 0) {
					prev.setNextItem(now);
					now.setPrevItem(prev);
				}
				prev = now;
			}

			// insert dependencies (locks)
			for (int j = 0; j < dependencyNumer; j++) {
				int dependencyFrom = scanner.nextInt() - 1;
				int dependencyTo = scanner.nextInt() - 1;

				unsortedList[dependencyFrom].addLock(unsortedList[dependencyTo]);
			}

			// loop in array and sort entities (insert in sortedArray)
			int indexResultArray = 0;
			DependencyItem current = unsortedList[0];
			while (indexResultArray < resultArray.length) {
				// is unlocked? -> use this one
				if (current.isUnlocked()) {

					// link the prev item to the next, and the next to the prev
					// (remove the current item, because we used it)
					DependencyItem beforeCurrent = current.getPrevItem();
					DependencyItem afterCurrent = current.getNextItem();
					if (beforeCurrent != null) {
						beforeCurrent.setNextItem(afterCurrent);
					}
					if (afterCurrent != null) {
						afterCurrent.setPrevItem(beforeCurrent);
					}
					// insert in resultArray
					resultArray[indexResultArray] = current.getVaue() + 1;
					indexResultArray++;

					// unlock all items, depending from this one
					DependencyItem next = current.removeAllLocks();
					// get next item
					if (current.getNextItem() == null) {
						current = next;
					} else if (next.getVaue() < current.getNextItem().getVaue()) {
						current = next;
					} else {
						current = current.getNextItem();
					}

				} // else... just go to next entity
				else {
					current = current.getNextItem();
				}
			}

			// print result
			System.out.print(resultArray[0]);
			for (int i = 1; i < resultArray.length; i++) {
				System.out.print(" " + resultArray[i]);
			}
			System.out.print("\n");
		}

		scanner.close();
	}

	private static class DependencyItem {

		private ArrayList<DependencyItem> lockedItems = new ArrayList<DependencyItem>();
		private int lockCounter = 0;
		private int value;
		private DependencyItem prevItem = null;
		private DependencyItem nextItem = null;

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

		public DependencyItem removeAllLocks() {
			DependencyItem next = nextItem;
			for (int i = 0; i < this.lockedItems.size(); i++) {
				// decrease lockCounter for each item (not locked anymore from this one)
				boolean unlockedCompletly = this.lockedItems.get(i).decreaseLock();
				// if it is completely unlocked -> check if this is the next item to go
				if (unlockedCompletly) {
					if (next == null) {
						next = this.lockedItems.get(i);
					} else if (this.lockedItems.get(i).getVaue() < next.getVaue()) {
						next = this.lockedItems.get(i);
					}
				}
			}
			this.lockedItems.clear();
			return next;
		}

		public int getVaue() {
			return this.value;
		}

		public DependencyItem getPrevItem() {
			return prevItem;
		}

		public void setPrevItem(DependencyItem prevItem) {
			this.prevItem = prevItem;
		}

		public DependencyItem getNextItem() {
			return nextItem;
		}

		public void setNextItem(DependencyItem nextItem) {
			this.nextItem = nextItem;
		}
	}
}
