package sheet05.task_02_EdgeOrientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

/*
2
1
1 2
 */// result 1

/*
4
5
1 2
1 3
2 3
2 4
3 4
 *///result 2

/*
10
18
1 2 
2 3 
3 4 
4 5 
5 6 
6 7 
7 8 
8 1 
9 1 
9 2 
9 3 
9 4 
9 5 
9 6 
9 7 
9 8 
3 10 
10 5
 *///result 2

/*
7
15
1 2 
2 3 
3 4 
4 5 
5 6 
6 1 
1 3 
3 5 
5 1 
6 2 
7 2 
7 3 
7 4 
7 5 
7 6
 */

public class EdgeOrientation {

	private static Edge[] allEdges;
	private static Node[] allNodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// metadata
		int numberNode = Integer.valueOf(br.readLine().split(" ")[0]);
		int numberEdges = Integer.valueOf(br.readLine().split(" ")[0]);

		allEdges = new Edge[numberEdges];
		allNodes = new Node[numberNode];

		for (int i = 0; i < numberEdges; i++) {
			String[] edgeInput = br.readLine().split(" ");

			int node1 = Integer.valueOf(edgeInput[0]) - 1;
			int node2 = Integer.valueOf(edgeInput[1]) - 1;

			if (allNodes[node1] == null) {
				allNodes[node1] = new Node(node1);
			}
			if (allNodes[node2] == null) {
				allNodes[node2] = new Node(node2);
			}

			allEdges[i] = new Edge(i, allNodes[node1], allNodes[node2]);
		}

		// modifiedDFS (for all graph components)
		modifiedDFS();

		for (int i = 0; i < allEdges.length; i++) {
			if (allEdges[i] != null) {
				Node node1 = allEdges[i].getNode1();
				Node node2 = allEdges[i].getNode2();

				if (node1.getDeg() > node2.getDeg()) {
					node2.increaseDeg();
				} else if (node1.getDeg() < node2.getDeg()) {
					node1.increaseDeg();
				} else {
					if (node1.getRemainingEdgesCount() > node2.getRemainingEdgesCount()) {
						node2.increaseDeg();
					} else if (node1.getRemainingEdgesCount() < node2.getRemainingEdgesCount()) {
						node1.increaseDeg();
					} else {
						node1.increaseDeg();
					}
				}
			}
		}

		System.out.println(Node.maxDegInc);
	}

	private static void modifiedDFS() {
		for (int i = 0; i < allNodes.length; i++) {
			if (allNodes[i] != null && !allNodes[i].visited) {
				DFSUtil(allNodes[i]);
			}
		}

	}

	private static void DFSUtil(Node v) {
		// Mark the current node as visited and print it
		v.visited = true;

		// Recur for all the vertices adjacent to this vertex
		Iterator<Edge> i = v.getEdges().listIterator();// adj[v].listIterator();
		while (i.hasNext()) {
			Edge edge = i.next();
			Node neighborNode = edge.getNeighborNode(v);
			if (neighborNode.getDeg() == 0) {

				allEdges[edge.id] = null;
				v.increaseUsedEdge();
				neighborNode.increaseUsedEdge();

				neighborNode.increaseDeg();
				DFSUtil(neighborNode);
			}
		}
	}

	private static class Node {

		private static int maxDegInc = 0;

		private ArrayList<Edge> edges = new ArrayList<>();
		private int degInc = 0;
		private int usedEdges = 0;

		int id;
		boolean visited = false;

		public Node(int id) {
			this.id = id;
		}

		public void increaseDeg() {
			degInc++;

			if (degInc > maxDegInc) {
				maxDegInc = degInc;
			}
		}

		public void increaseUsedEdge() {
			usedEdges++;
		}

		public int getDeg() {
			return degInc;
		}

		public int getRemainingEdgesCount() {
			return edges.size() + usedEdges;
		}

		public void registerEdge(Edge edge) {
			edges.add(edge);
		}

		public ArrayList<Edge> getEdges() {
			return edges;
		}

	}

	private static class Edge {

		private Node node1;
		private Node node2;
		int id;

		public Edge(int id, Node node1, Node node2) {
			super();
			this.node1 = node1;
			this.node2 = node2;
			this.id = id;

			node1.registerEdge(this);
			node2.registerEdge(this);
		}

		public Node getNeighborNode(Node start) {
			if (node1.id == start.id) {
				return node2;
			}
			if (node2.id == start.id) {
				return node1;
			}
			return null;
		}

		public Node getNode1() {
			return node1;
		}

		public Node getNode2() {
			return node2;
		}

	}
}
