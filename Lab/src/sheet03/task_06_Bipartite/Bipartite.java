package sheet03.task_06_Bipartite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/*
5 5
0 1
0 2
2 3
3 4
4 0

4 3
0 2
3 2
0 3

10 7
0 1
0 3
2 1
3 2
4 5
9 6
7 6

6 5
3 4
5 4
5 3
0 1
2 1

5 1
0 1
 */
// bipartite  ---  not bipartite  ---  bipartite  ---  not bipartite  ---  bipartite
public class Bipartite {

	// private static List<List<Integer>> edges;
	private static Vertex[] vertices;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {

			String input = br.readLine();

			if (input == null) {
				break;
			}
			
			if(input.equals("")) {
				continue;
			}

			String[] splitInput = input.split(" ");

			int numberVertices = Integer.valueOf(splitInput[0]);
			int numberEdges = Integer.valueOf(splitInput[1]);

			vertices = new Vertex[numberVertices];

			// insert all edges
			for (int i = 0; i < numberEdges; i++) {

				String[] edge = br.readLine().split(" ");
				int vertex1 = Integer.valueOf(edge[0]);
				int vertex2 = Integer.valueOf(edge[1]);

				if (vertices[vertex1] == null) {
					vertices[vertex1] = new Vertex();
				}
				if (vertices[vertex2] == null) {
					vertices[vertex2] = new Vertex();
				}

				vertices[vertex1].addNeighbor(vertices[vertex2]);
				vertices[vertex2].addNeighbor(vertices[vertex1]);
			}

			// do coloredBFS to check if bipartite
			if (coloredBFS(numberVertices)) {
				System.out.println("bipartite");
			} else {
				System.out.println("not bipartite");
			}
		}
	}

	private static boolean coloredBFS(int numberVertices) {

		// int[] coloredGroups = new int[numberVertices];
		Queue<Vertex> queue = new LinkedList<>();

		Vertex current;

		int searchComponentIndex = 0;
		// bfs
		while (true) {
			if (queue.isEmpty()) {
				// check for / get another component...
				while (searchComponentIndex < vertices.length) {
					if (vertices[searchComponentIndex] == null) {
						vertices[searchComponentIndex] = new Vertex();
					}
					if (vertices[searchComponentIndex].getColor() == 0) {
						queue.add(vertices[searchComponentIndex]);
						vertices[searchComponentIndex].setColor(1);
						searchComponentIndex++;
						break;// break for loop
					}
					searchComponentIndex++;
				}
				// still empty? -> break and finish
				if (queue.isEmpty()) {
					break;// break while loop
				}
			}
			current = queue.poll();

			// loop all neighbors, check if same color -> return, add to queue otherwise
			ArrayList<Vertex> curNeighbors = current.getNeigbors();
			for (int j = 0; j < curNeighbors.size(); j++) {
				Vertex curNeighbor = curNeighbors.get(j);
				if (curNeighbor.getColor() == 0) {// not visited y -> add color and add to queue
					curNeighbor.setColor(current.getColor() * (-1));
					queue.add(curNeighbor);
				} else if (curNeighbor.getColor() == current.getColor()) {// visited -> check color
					// same color -> return false
					return false;
				}
			}
		}
		// checked all vertices, all correct -> return true
		return true;
	}

	private static class Vertex {
		private int color = 0;

		private ArrayList<Vertex> neigbors = new ArrayList<>();

		public Vertex() {

		}

		public void addNeighbor(Vertex vertex) {
			this.neigbors.add(vertex);
		}

		public ArrayList<Vertex> getNeigbors() {
			return neigbors;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}

	}
}
