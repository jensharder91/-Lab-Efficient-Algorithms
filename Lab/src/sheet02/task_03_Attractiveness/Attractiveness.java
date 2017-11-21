package sheet02.task_03_Attractiveness;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Attractiveness {

	private static int maxValue = 0;
	private static int maxValueCounter = 0;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (true) {

			// meatadata for test case
			int numberPlayer = scanner.nextInt();
			if (numberPlayer == 0) {
				break;
			}

			Node root = new Node("");
			maxValue = 0;
			maxValueCounter = 0;

			// loop for each player
			for (int i = 0; i < numberPlayer; i++) {

				// get player attribute
				String[] attributes = new String[6];
				for (int j = 0; j < 6; j++) {
					attributes[j] = scanner.next();
				}

				// sort alphabetical
				Arrays.sort(attributes);

				Node curNode = root;
				Node foundNode = null;

				// tree has 6 level, loop for each
				for (int j = 0; j < 6; j++) {
					ArrayList<Node> children = curNode.getChildren();
					foundNode = null;
					// check if limis has expected value pare "abc"
					for (int k = 0; k < children.size(); k++) {
						if (children.get(k).getValue().equals(attributes[j])) {
							foundNode = children.get(k);
							break;
						}
					}

					if (foundNode != null) {
						// go to next level
						curNode = foundNode;
					} else {
						// no level exists with right value... create new subtree
						for (int k = j; k < 6; k++) {
							Node newNode = new Node(attributes[k]);
							curNode.addChild(newNode);
							curNode = newNode;
						}
						counterLogic(curNode);
						break;
					}
				}
				if (foundNode != null) {
					counterLogic(foundNode);
				}
				// printTree(root);
			}
			System.out.println(maxValueCounter);
		}

		scanner.close();
	}

	private static void counterLogic(Node node) {
		node.increaseCounter();
		if (node.getCounter() > maxValue) {
			maxValue = node.getCounter();
			maxValueCounter = maxValue;
		} else if (node.getCounter() == maxValue) {
			maxValueCounter += node.getCounter();
		}
	}

	// private static void printTree(Node root, int level) {
	// String offset = "";
	// for (int i = 0; i < level; i++) {
	// offset += " ";
	// }
	// System.out.println(offset + "value: " + root.getValue() + " counter: " +
	// root.getCounter());
	// for (Node child : root.getChildren()) {
	// printTree(child, level + 1);
	// }
	// }

	// private static void printTree(Node root) {
	// printTree(root, 0);
	// }

	private static class Node {
		private String value;
		private ArrayList<Node> children = new ArrayList<>();
		private int counter = 0;

		public Node(String value) {
			this.value = value;
		}

		public ArrayList<Node> getChildren() {
			return this.children;
		}

		public void addChild(Node child) {
			this.children.add(child);
		}

		public int getCounter() {
			return this.counter;
		}

		public void increaseCounter() {
			this.counter++;
		}

		public String getValue() {
			return this.value;
		}
	}
}
