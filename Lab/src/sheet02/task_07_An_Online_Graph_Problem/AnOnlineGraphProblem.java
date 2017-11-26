package sheet02.task_07_An_Online_Graph_Problem;

import java.util.Scanner;

public class AnOnlineGraphProblem {

	// union-find struktur
	private static int[] parent;
	private static byte[] depth;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int numberTextCases = scanner.nextInt();
		int counterPositive;
		int counterNegative;
		int numberVertices;
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

			parent = new int[numberVertices];
			depth = new byte[numberVertices];
			// each item has own itam as representant
			for (int j = 0; j < numberVertices; j++) {
				parent[j] = j;
			}

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
					if (findRepresentant(eventVertex_1) == findRepresentant(eventVertex_2)) {
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

	private static void union(int x, int y) {
		int represantantX = findRepresentant(x);
		int represantantY = findRepresentant(y);
		if (represantantX == represantantY)
			return;

		if (depth[represantantX] < depth[represantantY])
			parent[represantantX] = represantantY;
		else if (depth[represantantX] > depth[represantantY])
			parent[represantantY] = represantantX;
		else {
			parent[represantantY] = represantantX;
			depth[represantantX]++;
		}
	}

	private static int findRepresentant(int p) {

		// loop until representant is found
		while (p != parent[p]) {
			parent[p] = parent[parent[p]];
			p = parent[p];
		}
		return p;

	}

}
