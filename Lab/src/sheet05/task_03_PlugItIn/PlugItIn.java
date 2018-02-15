package sheet05.task_03_PlugItIn;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

/*
2 3 3
1 1
2 2
2 3
 */ //result 2 + 1

/*
4 5 3
1 2
2 2
3 1
 */ //result 2

/*
4 5 11
1 1
1 2
1 3
2 1
2 2
2 3
3 1
3 2
3 3
4 4
4 5
 *///result: 4 + 1

/*
5 7 11
1 1
1 2
1 3
2 3
2 4
3 4
3 5
4 5
4 6
5 6
5 7
 */// result 5 + 2

/*
3 6 8
1 1
1 2
1 3
2 3
2 4
3 4
3 5
3 6

 */ //result 5

public class PlugItIn {
//	private static int[][] adjazenzmatrix;
	private static int source;
	private static int sink;
	private static int numberSockets;
	private static int numberDevices;
	private static int numberEdges;
	private static int numberNodes;
	private static Node[] nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get metadata
		String[] metdadata = br.readLine().split(" ");
		numberSockets = Integer.valueOf(metdadata[0]);
		numberDevices = Integer.valueOf(metdadata[1]);
		numberEdges = Integer.valueOf(metdadata[2]);

		numberNodes = numberSockets + numberDevices + 2; // +2 -> source, sink

		// init
		int maxFlow = 0;
//		adjazenzmatrix = new int[numberNodes][numberNodes];
		nodes = new Node[numberNodes];
		source = 0;
		sink = numberNodes - 1;
		
		// source
		Node sourceNode = new Node(0);
		nodes[source] = sourceNode;
		for (int i = 1; i <= numberSockets; i++) {
//			adjazenzmatrix[source][i] = 1;
			nodes[i] = new Node(i);
			sourceNode.edges.add(new Edge(sourceNode, nodes[i]));
		}

		// sink control
		Node sinkNode = new Node(sink);
		nodes[sink] = sinkNode;
		for (int i = 1; i <= numberDevices; i++) {
//			adjazenzmatrix[numberSockets + i][sink] = 1;
			nodes[i + numberSockets] = new Node(i + numberSockets);
			nodes[i + numberSockets].edges.add(new Edge(nodes[i + numberSockets], sinkNode));
		}

		// get inputfrom edges
		for (int edge = 0; edge < numberEdges; edge++) {
			String[] edgeInput = br.readLine().split(" ");

//			adjazenzmatrix[Integer.valueOf(edgeInput[0])][Integer.valueOf(edgeInput[1]) + numberSockets] = 1;
			int a = Integer.valueOf(edgeInput[0]);
			int b = Integer.valueOf(edgeInput[1]) + numberSockets;
			nodes[a].edges.add(new Edge(nodes[a], nodes[b]));
		}

		// max flow without plugbar
		maxFlow = fordFulkerson();
		boolean addedOneFlow = false;

		// try to apply sockets
		for (int i = 1; i <= numberSockets; i++) {
			if (i > 1) {
//				adjazenzmatrix[source][i - 1] = 1;
				
				Edge edge = nodes[source].getEdgeByNeighbor(nodes[i-1]);
				if(edge != null) {
					nodes[source].edges.remove(edge);
				}
				
				Edge edge2 = nodes[source].getEdgeByNeighbor(nodes[i-1]);
				if(edge2 != null) {
					nodes[source].edges.remove(edge2);
				}
			}
//			adjazenzmatrix[source][i] = 3;
			nodes[source].edges.add(new Edge(nodes[source], nodes[i]));
			nodes[source].edges.add(new Edge(nodes[source], nodes[i]));
			
			int newFlow = fordFulkerson();
			if(newFlow == 1) {
				if(!addedOneFlow) {
					maxFlow = maxFlow + newFlow;
				}
				addedOneFlow = true;
			}
			if(newFlow == 2) {
				if(addedOneFlow) {
					maxFlow = maxFlow + 1;
				}else {
					maxFlow = maxFlow + 2;
				}
				break;
			}
		}

		System.out.println(maxFlow);
	}

	private static boolean bfs(int parent[]) {

		boolean visited[] = new boolean[numberNodes];
		for (int i = 0; i < numberNodes; ++i) {
			visited[i] = false;
		}

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(source);
		visited[source] = true;
		parent[source] = -1;

		while (queue.size() != 0) {
			int u = queue.poll();

			Node curNode = nodes[u];
			for (Edge edge : curNode.edges) {
				int v = edge.getNeighbot(curNode).id;
				if (visited[v] == false) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
			}
		}

		return visited[sink];
	}

	private static int fordFulkerson() {

		// init
		int parent[] = new int[numberNodes];
		int maxFlow = 0;

		while (bfs(parent)) {

//			int tempFlow = Integer.MAX_VALUE;
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
//				tempFlow = Math.min(tempFlow, adjazenzmatrix[u][v]);
				
//				adjazenzmatrix[u][v] -= tempFlow;
//				adjazenzmatrix[v][u] += tempFlow;
				nodes[u].edges.remove(nodes[u].getEdgeByNeighbor(nodes[v]));
				nodes[v].edges.add(new Edge(nodes[v], nodes[u]));
			}

			// update adjazenzmatrix
//			for (int v = sink; v != source; v = parent[v]) {
//				int u = parent[v];
//				adjazenzmatrix[u][v] -= tempFlow;
//				adjazenzmatrix[v][u] += tempFlow;
//			}

			// add to maxFlow
//			maxFlow += tempFlow;
			maxFlow++;
		}

		return maxFlow;
	}
	
	private static class Node{
		
		public int id;
		public ArrayList<Edge> edges = new ArrayList<>();
		
		public Node(int id) {
			this.id = id;
		}
		
		public Edge getEdgeByNeighbor(Node neighbor) {
			for(Edge edge: edges) {
				if(edge.node1.id == neighbor.id) {
					return edge;
				}
				if(edge.node2.id == neighbor.id) {
					return edge;
				}
			}
			return null;
		}
	}
	
	private static class Edge{
		
		private Node node1;
		private Node node2;
		
		public Edge(Node node1, Node node2) {
			this.node1 = node1;
			this.node2 = node2;
			
//			if(node1 == null || node2 == null) {
//				System.out.println("null :(");
//			}
		}
		
		public Node getNeighbot(Node node) {
			if(node.id == this.node1.id) {
				return node2;
			}else if(node.id == this.node2.id) {
				return node1;
			}
			return null;
		}
		
	}
}
