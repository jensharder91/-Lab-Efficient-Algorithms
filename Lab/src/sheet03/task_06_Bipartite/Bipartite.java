package sheet03.task_06_Bipartite;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Bipartite {

	private static List<List<Integer>> edges;

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNext()) {

			int numberVertices = scanner.nextInt();
			int numberEdges = scanner.nextInt();

			edges = new ArrayList<List<Integer>>(numberVertices);
			// init edge lis
			for (int i = 0; i < numberVertices; i++) {
				edges.add(new ArrayList<>());
			}

			// insert all edges
			for (int i = 0; i < numberEdges; i++) {
				int vertex1 = scanner.nextInt();
				int vertex2 = scanner.nextInt();
				edges.get(vertex1).add(vertex2);
				edges.get(vertex2).add(vertex1);
			}

			// do coloredBFS to check if bipartite
			if (coloredBFS(numberVertices)) {
				System.out.println("bipartite");
			} else {
				System.out.println("not bipartite");
			}
		}

		scanner.close();

	}

	private static boolean coloredBFS(int numberVertices) {

		int[] coloredGroups = new int[numberVertices];
		Queue<Integer> queue = new LinkedList<>();

		Integer current;

		// bfs
		while (true) {
			if (queue.isEmpty()) {
				// check for / get another component...
				for (int i = 0; i < coloredGroups.length; i++) {
					if (coloredGroups[i] == 0) {
						queue.add(i);
						coloredGroups[i] = 1;
						break;// break for loop
					}
				}
				// still empty? -> break and finish
				if (queue.isEmpty()) {
					break;// break while loop
				}
			}
			current = queue.poll();

			// loop all neighbors, check if same color -> return, add to queue otherwise
			for (int j = 0; j < edges.get(current).size(); j++) {
				int curNeighbor = edges.get(current).get(j);
				if (coloredGroups[curNeighbor] == 0) {// not visited y -> add color and add to queue
					coloredGroups[curNeighbor] = coloredGroups[current] + 1;
					queue.add(curNeighbor);
				} else if (coloredGroups[curNeighbor] % 2 == coloredGroups[current] % 2) {// visited -> check color
					// same color -> return false
					return false;
				}
			}
		}
		// checked all vertices, all correct -> return true
		return true;
	}
}
