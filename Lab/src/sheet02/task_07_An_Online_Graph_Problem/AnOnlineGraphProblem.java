package sheet02.task_07_An_Online_Graph_Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
2
4 6
q 1 2
n 1 2
q 2 1
n 1 3
n 2 4
q 3 4
2 2
q 1 1
q 1 2
 */
public class AnOnlineGraphProblem {

	// union-find struktur
	private static int[] parent;
	private static byte[] depth;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numberTextCases = Integer.valueOf(br.readLine());
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
			String[] metaData = br.readLine().split(" ");
			numberVertices = Integer.valueOf(metaData[0]);
			numberEvents = Integer.valueOf(metaData[1]);

			parent = new int[numberVertices];
			depth = new byte[numberVertices];
			// each item has own itam as representant
			for (int j = 0; j < numberVertices; j++) {
				parent[j] = j;
			}

			// loop for every event
			for (int j = 0; j < numberEvents; j++) {

				// event data
				String[] eventData = br.readLine().split(" ");
				letter = eventData[0];
				eventVertex_1 = Integer.valueOf(eventData[1]) - 1;
				eventVertex_2 = Integer.valueOf(eventData[2]) - 1;

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
