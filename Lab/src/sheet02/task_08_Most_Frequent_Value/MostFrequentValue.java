package sheet02.task_08_Most_Frequent_Value;

import java.util.ArrayList;
import java.util.Scanner;

public class MostFrequentValue {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int n = scanner.nextInt();
		int q = scanner.nextInt();

		ArrayList<TreeObject> treeObjects = new ArrayList<>();
		TreeObject root;

		int index = 0;
		int curNumber = Integer.MAX_VALUE;
		int curNumberCount = 0;
		int startedAdIndex = 0;

		// read all values
		for (int i = 0; i < n; i++) {

			int input = scanner.nextInt();
			if (input == curNumber) {
				curNumberCount++;
			}
			if (input != curNumber) {
				// save old number
				if (curNumberCount > 0) {
					treeObjects.add(new TreeObject(curNumberCount, startedAdIndex, index));
				}

				// init new number
				curNumberCount = 1;
				curNumber = input;
				startedAdIndex = index + 1;
			}

			index++;
		}
		if (curNumberCount > 0) {
			treeObjects.add(new TreeObject(curNumberCount, startedAdIndex, index));
		}

		ArrayList<TreeObject> nextLine = new ArrayList<>();
		ArrayList<TreeObject> curLine = treeObjects;
		// generate tree
		while (curLine.size() > 1) {
			for (int i = 0; i < curLine.size(); i += 2) {
				TreeObject first = curLine.get(i);
				TreeObject second;
				if (i + 1 >= curLine.size()) {
					second = new TreeObject(0, Integer.MAX_VALUE, Integer.MAX_VALUE);
				} else {
					second = curLine.get(i + 1);
				}

				nextLine.add(new TreeObject(Math.max(first.getMaxNumber(), second.getMaxNumber()), first.getIndexFrom(),
						second.getIndexTo(), first, second));
			}
			curLine = nextLine;
			nextLine = new ArrayList<>();
		}
		root = curLine.get(0);

		// all queries
		for (int i = 0; i < q; i++) {
			int from = scanner.nextInt();
			int to = scanner.nextInt();

			int maxInner = Math.max(getInnerMaxLeft(root, from, to, 0), getInnerMaxRight(root, from, to, 0));

			TreeObject leftBoundary = getLeftBoundary(root, from);
			TreeObject rightBoundary = getRightBoundary(root, to);

			int maxLeftBoundary = leftBoundary.getIndexTo() + 1 - from;
			int maxRightBoundary = to + 1 - rightBoundary.getIndexFrom();
			
			//RESULT
			int maxBoundary = Math.max(maxLeftBoundary, maxRightBoundary);
			System.out.println(Math.max(maxBoundary, maxInner));

		}

		scanner.close();
	}

	private static TreeObject getLeftBoundary(TreeObject curRoot, int indexFrom) {
		if (curRoot.getChild1() == null && curRoot.getChild2() == null) {
			return curRoot;
		}
		if (curRoot.getChild1() == null) {
			return curRoot.getChild2();
		}
		if (curRoot.getChild2() == null) {
			return curRoot.getChild1();
		}

		if (indexFrom > curRoot.getChild1().getIndexTo()) {
			return getLeftBoundary(curRoot.getChild2(), indexFrom);
		} else {
			return getLeftBoundary(curRoot.getChild1(), indexFrom);
		}
	}

	private static TreeObject getRightBoundary(TreeObject curRoot, int indexTo) {
		if (curRoot.getChild1() == null && curRoot.getChild2() == null) {
			return curRoot;
		}
		if (curRoot.getChild1() == null) {
			return curRoot.getChild2();
		}
		if (curRoot.getChild2() == null) {
			return curRoot.getChild1();
		}

		if (indexTo < curRoot.getChild2().getIndexFrom()) {
			return getLeftBoundary(curRoot.getChild1(), indexTo);
		} else {
			return getLeftBoundary(curRoot.getChild2(), indexTo);
		}
	}

	private static int getInnerMaxLeft(TreeObject curRoot, int indexFrom, int indexTo, int innerMax) {

		if (curRoot.getChild1() == null && curRoot.getChild2() == null) {
			return innerMax;
		}
		if (curRoot.getChild1() == null) {
			return getInnerMaxLeft(curRoot.getChild2(), indexFrom, indexTo, innerMax);
		}
		if (curRoot.getChild2() == null) {
			return getInnerMaxLeft(curRoot.getChild1(), indexFrom, indexTo, innerMax);
		}

		if (indexFrom > curRoot.getChild1().getIndexTo()) {
			return getInnerMaxLeft(curRoot.getChild2(), indexFrom, indexTo, innerMax);
		} else {
			if (curRoot.getChild2().indexFrom > indexFrom && curRoot.getChild2().getIndexTo() < indexTo) {
				innerMax = Math.max(innerMax, curRoot.getChild2().maxNumber);
			}
			return getInnerMaxLeft(curRoot.getChild1(), indexFrom, indexTo, innerMax);
		}
	}

	private static int getInnerMaxRight(TreeObject curRoot, int indexFrom, int indexTo, int innerMax) {
		if (curRoot.getChild1() == null && curRoot.getChild2() == null) {
			return innerMax;
		}
		if (curRoot.getChild1() == null) {
			return getInnerMaxRight(curRoot.getChild2(), indexFrom, indexTo, innerMax);
		}
		if (curRoot.getChild2() == null) {
			return getInnerMaxRight(curRoot.getChild1(), indexFrom, indexTo, innerMax);
		}

		if (indexTo < curRoot.getChild2().getIndexFrom()) {
			return getInnerMaxRight(curRoot.getChild1(), indexFrom, indexTo, innerMax);
		} else {
			if (curRoot.getChild1().indexFrom > indexFrom && curRoot.getChild1().getIndexTo() < indexTo) {
				innerMax = Math.max(innerMax, curRoot.getChild1().maxNumber);
			}
			return getInnerMaxRight(curRoot.getChild2(), indexFrom, indexTo, innerMax);
		}
	}

	static class TreeObject {

		private int maxNumber;
		private int indexFrom;
		private int indexTo;
		private TreeObject child1 = null;
		private TreeObject child2 = null;

		public TreeObject(int maxNumber, int indexFrom, int indexTo) {
			this(maxNumber, indexFrom, indexTo, null, null);
		}

		public TreeObject(int maxNumber, int indexFrom, int indexTo, TreeObject child1, TreeObject child2) {
			this.maxNumber = maxNumber;
			this.indexFrom = indexFrom;
			this.indexTo = indexTo;
			this.child1 = child1;
			this.child2 = child2;
		}

		public int getMaxNumber() {
			return maxNumber;
		}

		public int getIndexFrom() {
			return indexFrom;
		}

		public int getIndexTo() {
			return indexTo;
		}

		public TreeObject getChild1() {
			return child1;
		}

		public TreeObject getChild2() {
			return child2;
		}

		public String drawTree(int order) {
			String child1String = "";
			String child2String = "";
			String space = "  ";
			for (int i = 0; i < order; i++) {
				space += "  ";
			}
			if (child1 != null) {
				child1String = "\n" + space + "" + child1.drawTree(order + 1);
			}
			if (child2 != null) {
				child2String = "\n" + space + "" + child2.drawTree(order + 1);
			}
			return indexFrom + "|---|" + indexTo + "   max: " + maxNumber + child1String + child2String;
		}

	}

}
