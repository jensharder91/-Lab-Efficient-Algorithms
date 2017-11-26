package sheet02.task_03_Attractiveness;

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

			Node root = new Node();
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
				String totalString = attributes[0]+attributes[1]+attributes[2]+attributes[3]+attributes[4]+attributes[5];
				
				byte[] attributeBytes = totalString.getBytes();

				Node curNode = root;
				Node foundNode = null;

				// tree has 18 level, loop for each
				for (int j = 0; j < 18; j++) {
					
					foundNode = curNode.getChild(attributeBytes[j]);

					if (foundNode != null) {
						// go to next level
						curNode = foundNode;
					} else {
						// no level exists with right value... create new subtree
						for (int k = j; k < 18; k++) {
							Node newNode = new Node();
							curNode.addChild(attributeBytes[k], newNode);
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
		private Node[] children = new Node[26];
		private int counter = 0;
		
		private int offset = 97;

		public Node getChild(int index) {
			return this.children[index - offset];
		}

		public void addChild(int index, Node child) {
			this.children[index-offset] = child;
		}

		public int getCounter() {
			return this.counter;
		}

		public void increaseCounter() {
			this.counter++;
		}
	}
}
