package sheet04.task_04_Friendship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
4 3
0 1
2 3
2 0 2
0 3 1
0 1 4
5 2
0 1
2 3
2 3 4
1 3 2
5 0
0 1
2 3
10 5
0 9
1 2
0 1 1
1 2 1
2 9 1
0 5 3
5 9 4
5 4
0 4
1 3
0 2 3
1 2 4
2 3 2
3 4 1
5 6
0 4
1 3
0 2 3
1 2 4
2 3 2
3 4 1
2 3 3
0 4 5
10 14
0 4
1 3
0 2 2
2 3 3
3 4 5
0 5 1
5 6 10
6 7 1
7 4 1
1 8 1
8 9 3
9 7 4
8 6 3
5 8 2
1 2 6
6 3 1
10 14
0 4
1 3
0 2 2
2 3 3
3 4 5
0 5 1
5 6 10
6 7 1
7 4 1
1 8 1
8 9 3
9 7 4
8 6 3
5 8 2
1 2 1
6 3 1
10 14
0 4
1 3
0 2 2
2 3 3
3 4 5
0 5 1
5 6 10
6 7 1
7 4 1
1 8 1
8 9 3
9 7 4
8 6 3
5 8 2
1 2 2
6 3 1
5 4
0 4
1 3
0 2 1
1 2 1
4 2 1
2 3 1
4 2
0 1
2 3
0 1 2
2 3 3
4 3
0 1
2 3
0 1 2
2 1 2
1 3 3
7 6
0 1
2 3
0 4 2
2 4 3
4 5 1
5 3 1
5 6 1
6 1 3
7 7
0 1
2 3
0 4 2
2 4 3
4 5 1
5 3 1
5 6 1
6 1 3
2 3 4
7 7
0 1
2 3
0 4 2
2 4 3
4 5 1
5 3 1 
5 6 1
2 3 5 
6 1 3 
4 0
0 1
2 3
5 3
0 1
2 3
0 4 1
1 2 1
2 3 4
4 4
0 1
2 3
0 2 2
2 1 3
1 3 1
3 0 2
5 4
0 1
2 3
0 4 2
4 1 3
2 4 1
3 1 1
10 12
0 1
2 3
0 4 1 
4 1 1
0 7 2 
7 1 2
2 4 1 
5 6 2 
4 5 3 
6 3 5
2 7 3 
7 8 6 
8 9 1 
9 3 1
8 10
0 1
2 3
2 4 1 
4 5 2 
4 7 4 
5 6 5 
6 3 1 
7 6 3 
7 1 2 
5 1 1 
0 5 1 
0 7 2
0 0
 */
//result -1 # -1 # -1 # 7 # -1 # 5 # -1 # 8 # -1 # -1 # 2 # -1 # -1 # 7 # -1 # -1 #  -1 # -1 # 5 # -1 # -1
public class Friendship {

	private static Vertex[] vertices;
	private static int vertexNumber;
	private static int edgeNumber;

	private static int home;
	private static int cinema;
	private static int workplace;
	private static int gym;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String[] metaData = br.readLine().split(" ");
			vertexNumber = Integer.valueOf(metaData[0]);
			edgeNumber = Integer.valueOf(metaData[1]);

			if (vertexNumber == 0 && edgeNumber == 0) {
				break;
			}

			// init
			vertices = new Vertex[vertexNumber];

			String[] input1 = br.readLine().split(" ");
			home = Integer.valueOf(input1[0]);
			cinema = Integer.valueOf(input1[1]);

			String[] input2 = br.readLine().split(" ");
			workplace = Integer.valueOf(input2[0]);
			gym = Integer.valueOf(input2[1]);

			// create graph
			for (int edge = 0; edge < edgeNumber; edge++) {
				String[] newEdge = br.readLine().split(" ");
				int from = Integer.valueOf(newEdge[0]);
				int to = Integer.valueOf(newEdge[1]);
				int length = Integer.valueOf(newEdge[2]);

				if (vertices[from] == null) {
					vertices[from] = new Vertex(from);
				}
				if (vertices[to] == null) {
					vertices[to] = new Vertex(to);
				}
				vertices[from].addEdge(new Edge(length, vertices[to]));
			}
			
			if (vertices[home] == null) {
				vertices[home] = new Vertex(home);
			}
			if (vertices[gym] == null) {
				vertices[gym] = new Vertex(gym);
			}
			if (vertices[workplace] == null) {
				vertices[workplace] = new Vertex(workplace);
			}
			if (vertices[cinema] == null) {
				vertices[cinema] = new Vertex(cinema);
			}

			getAndRemoveAllShortestPaths();

			//removed the home or cinema? -> not possible
			if(vertices[home] == null || vertices[cinema] == null) {
				System.out.println(-1);
			}else {
				System.out.println(goToCinema());
			}

		}

	}

	private static void getAndRemoveAllShortestPaths() {

		PriorityQueue<Vertex> queue = new PriorityQueue<>(vertexNumber, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.numberInPath - o2.numberInPath;
			}
		});
		Set<Vertex> set = new HashSet<>();

		int shortestPath = Integer.MAX_VALUE;

		queue.add(vertices[workplace]);
		vertices[workplace].numberInPath = 0;

		while (!queue.isEmpty()) {
			Vertex current = queue.poll();
			set.add(current);

			// check break conditions
			if (current.numberInPath > shortestPath) {
				continue;
			}
			if (current.getValue() == gym) {
				shortestPath = current.numberInPath;
				continue;
			}

			for (Edge edge : current.getEdges()) {
				Vertex neighbor = edge.getTo();

				if (neighbor.numberInPath > current.numberInPath + edge.getLength()) {
					neighbor.numberInPath = current.numberInPath + edge.getLength();
					neighbor.preVertexInBfs.clear();
					neighbor.preVertexInBfs.add(current);
				} else if (neighbor.numberInPath == current.numberInPath + edge.getLength()) {
					neighbor.preVertexInBfs.add(current);
				}

				if (!set.contains(neighbor)) {
					queue.add(neighbor);
				}
			}
		}

		// remove all shorestPath vertices
		removeAll(vertices[gym]);
	}

	private static void removeAll(Vertex vertex) {
		if (vertices[vertex.getValue()] != null) {
			vertices[vertex.getValue()] = null;
		}

		for (int i = 0; i < vertex.preVertexInBfs.size(); i++) {
			removeAll(vertex.preVertexInBfs.get(i));
		}
	}

	private static int goToCinema() {

		PriorityQueue<Vertex> queue = new PriorityQueue<>(vertexNumber, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.distanceFromHome - o2.distanceFromHome;
			}
		});
		Set<Vertex> set = new HashSet<>();

		queue.add(vertices[home]);
		vertices[home].distanceFromHome = 0;

		while (!queue.isEmpty()) {
			Vertex current = queue.poll();
			set.add(current);

			if (current.getValue() == cinema) {
				return current.distanceFromHome;
			}

			for (Edge edge : current.getEdges()) {
				Vertex neighbor = edge.getTo();

				if (neighbor.distanceFromHome > current.distanceFromHome + edge.getLength()) {
					neighbor.distanceFromHome = current.distanceFromHome + edge.getLength();
				}

				if (!set.contains(neighbor) && vertices[neighbor.getValue()] != null) {
					queue.add(neighbor);
				}
			}
		}

		return -1;
	}

	private static class Vertex {
		private ArrayList<Edge> edges = new ArrayList<>();
		private int value;

		// for bfs
		int numberInPath = Integer.MAX_VALUE;
		ArrayList<Vertex> preVertexInBfs = new ArrayList<>();
		int distanceFromHome = Integer.MAX_VALUE;

		public Vertex(int value) {
			this.value = value;
		}

		public void addEdge(Edge edge) {
			this.edges.add(edge);
		}

		public ArrayList<Edge> getEdges() {
			return edges;
		}

		public int getValue() {
			return value;
		}
	}

	private static class Edge {
		private int length;
		private Vertex to;

		Edge(int length, Vertex to) {
			this.length = length;
			this.to = to;
		}

		public int getLength() {
			return length;
		}

		public Vertex getTo() {
			return to;
		}

	}
}
