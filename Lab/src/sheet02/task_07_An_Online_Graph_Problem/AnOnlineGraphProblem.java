package sheet02.task_07_An_Online_Graph_Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AnOnlineGraphProblem {

	private static int counterPositive;
	private static int counterNegative;
	private static int numberVertices;

	// union-find struktur
	private static int[] repr;
	private static ArrayList<Integer>[] items;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numberTextCases = scanner.nextInt();
		int numberEvents;
		String letter;

		int eventVertex_1;
		int eventVertex_2;

		// loop once for every test case
		for (int i = 0; i < numberTextCases; i++) {

			// reset counter
			counterPositive = 0;
			counterNegative = 0;

			// meatadata for test case
			numberVertices = scanner.nextInt();
			numberEvents = scanner.nextInt();

			initUnionFind(numberVertices);

			// loop for every event
			for (int j = 0; j < numberEvents; j++) {

				// event data
				letter = scanner.next();
				eventVertex_1 = scanner.nextInt() - 1;
				eventVertex_2 = scanner.nextInt() - 1;

				// distinguish event
				if (letter.equals("q")) {
					// if query is successful -> increase positive counter, otherwise increase
					// negative counter
					if (repr[eventVertex_1] == repr[eventVertex_2]) {
						counterPositive++;
					} else {
						counterNegative++;
					}
				} else if (letter.equals("n")) {
					// insert new edge
					union(eventVertex_1, eventVertex_2);
				}
			}
			System.out.println(counterPositive + " " + counterNegative);
		}
		scanner.close();
	}

	@SuppressWarnings("unchecked")
	private static void initUnionFind(int numberOfVertices) {
		// union-find
		items = new ArrayList[numberOfVertices];
		repr = new int[numberVertices];
		for (int n = 0; n < numberVertices; n++) {
			items[n] = new ArrayList<>();
			items[n].add(n);
			repr[n] = n;
		}
	}

	private static void union(int x, int y) {
		int i = repr[x];
		int j = repr[y];
		if (i == j) {
			return;
		}
		if (items[i].size() > items[j].size()) {
			for (int k = 0; k < items[j].size(); k++) {
				repr[items[j].get(k)] = i;
			}
			items[i].addAll(items[j]);
			items[j].clear();
		} else {
			for (int k = 0; k < items[i].size(); k++) {
				repr[items[i].get(k)] = j;
			}
			items[j].addAll(items[i]);
			items[i].clear();
		}
	}

}
