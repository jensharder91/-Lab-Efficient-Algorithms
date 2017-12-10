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
0 0
 */
//result 7   -1     5   -1  8  -1  -1
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

			getAndRemoveAllShortestPaths();

			System.out.println(goToCinema());

		}

	}

	private static void getAndRemoveAllShortestPaths() {

		Set<Vertex> set = new HashSet<>();
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(vertexNumber, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.numberInPath - o2.numberInPath;
			}
		});
		Vertex current;

		int shortestPath = Integer.MAX_VALUE;

		set.add(vertices[workplace]);
		queue.add(vertices[workplace]);

		// // init all edges to queue
		// for (int i = 0; i < vertices[workplace].getEdges().size(); i++) {
		// queue.add(vertices[workplace].getEdges().get(i).getTo());
		// }

		// BFS
		while (!queue.isEmpty()) {
			current = queue.poll();

			// check break conditions
			if (current.numberInPath > shortestPath) {
				continue;
			}
			if (current.getValue() == gym) {
				shortestPath = current.numberInPath;
				continue;
			}

			for (int i = 0; i < current.getEdges().size(); i++) {
				Vertex nextVertex = current.getEdges().get(i).getTo();
				if (!set.contains(nextVertex)) {
					queue.add(nextVertex);
					set.add(nextVertex);
					nextVertex.numberInPath = current.numberInPath + current.getEdges().get(i).getLength();
					nextVertex.preVertexInBfs.add(current);
				} else {
					if (nextVertex.numberInPath == current.numberInPath + current.getEdges().get(i).getLength()) {
						nextVertex.preVertexInBfs.add(current);
					}
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

		// Map<Vertex, Integer> map = new HashMap<>();
		Set<Vertex> set = new HashSet<>();
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(vertexNumber, new Comparator<Vertex>() {

			@Override
			public int compare(Vertex o1, Vertex o2) {
				// TODO Auto-generated method stub
				return o1.distanceFromHome - o2.distanceFromHome;
			}

		});
		Vertex current;

		set.add(vertices[home]);
		queue.add(vertices[home]);
		// map.put(vertices[home], 0);

		// // init all edges to queue
		// for (int i = 0; i < vertices[workplace].getEdges().size(); i++) {
		// queue.add(vertices[workplace].getEdges().get(i).getTo());
		// map.put(vertices[workplace],
		// }

		// BFS
		while (!queue.isEmpty()) {
			current = queue.poll();

			// check break conditions
			if (current.getValue() == cinema) {
				// return map.get(current);
				return current.distanceFromHome;
			}

			for (int i = 0; i < current.getEdges().size(); i++) {
				Vertex nextVertex = current.getEdges().get(i).getTo();
				if (vertices[nextVertex.getValue()] != null) {
					if (!set.contains(nextVertex)) {
						// map.put(nextVertex, map.get(current) +
						// current.getEdges().get(i).getLength());
						nextVertex.distanceFromHome = current.distanceFromHome + current.getEdges().get(i).getLength();
						queue.add(nextVertex);
						set.add(nextVertex);
					} else {

					}
				}
			}
		}

		// Vertex cur = vertices[cinema];
		// int totalLength = 0;
		// while (map.get(cur) != null) {
		// Edge curEdge = map.get(cur);
		// totalLength += curEdge.getLength();
		// cur = curEdge.getFrom();
		// }
		// if (totalLength > 0) {
		// return totalLength;
		// }
		return -1;
	}

	private static class Vertex {
		private ArrayList<Edge> edges = new ArrayList<>();
		private int value;

		// for bfs
		int numberInPath;
		ArrayList<Vertex> preVertexInBfs = new ArrayList<>();
		int distanceFromHome;

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
