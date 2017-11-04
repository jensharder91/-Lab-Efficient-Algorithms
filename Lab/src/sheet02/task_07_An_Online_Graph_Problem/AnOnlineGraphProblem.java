package sheet02.task_07_An_Online_Graph_Problem;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class AnOnlineGraphProblem {

	private static ArrayList<Integer>[] connections;
	private static int counterPositive;
	private static int counterNegative;
	private static int numberVertices;

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
			connections = new ArrayList[numberVertices];
			// init connections
			for (int n = 0; n < numberVertices; n++) {
				connections[n] = new ArrayList<>();
			}

			// loop for every event
			for (int j = 0; j < numberEvents; j++) {

				// event data
				letter = scanner.next();
				eventVertex_1 = scanner.nextInt();
				eventVertex_2 = scanner.nextInt();

				// distinguish event
				if (letter.equals("q")) {
					// if query is successful -> increase positive counter, otherwise increase
					// negative counter
					if (query(eventVertex_1 - 1, eventVertex_2 - 1)) {
						counterPositive++;
					} else {
						counterNegative++;
					}
				} else if (letter.equals("n")) {
					// insert new edge
					insert(eventVertex_1 - 1, eventVertex_2 - 1);
				}
			}
			System.out.println(counterPositive + " " + counterNegative);
		}
		scanner.close();
	}

	// insert the edge in both vertices
	private static void insert(int eventVertex_1, int eventVertex_2) {
		ArrayList<Integer> list_1 = connections[eventVertex_1];
		list_1.add(Integer.valueOf(eventVertex_2));
		connections[eventVertex_1] = list_1;

		ArrayList<Integer> list_2 = connections[eventVertex_2];
		list_2.add(Integer.valueOf(eventVertex_1));
		connections[eventVertex_2] = list_2;
	}

	// BFS to check if a connection exists
	private static boolean query(int eventVertex_1, int eventVertex_2) {

		// same vertex
		if (eventVertex_1 == eventVertex_2) {
			return true;
		}

		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[numberVertices];
		visited[eventVertex_1] = true;
		connections[eventVertex_1].forEach(myInt -> {
			if (!visited[myInt]) {
				queue.add(myInt);
			}
		});

		Integer current;

		while (!queue.isEmpty()) {
			current = queue.poll();
			visited[current] = true;

			// check break condition
			if (current.intValue() == eventVertex_2) {
				return true;
			}

			connections[current].forEach(myInt -> {
				if (!visited[myInt]) {
					queue.add(myInt);
				}
			});
		}

		return false;
	}

}
