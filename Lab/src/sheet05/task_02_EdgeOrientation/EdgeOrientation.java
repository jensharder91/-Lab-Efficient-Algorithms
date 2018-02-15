package sheet05.task_02_EdgeOrientation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/*
2
0
 */// result 0

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
15
21
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
8 9
8 10
9 10
9 11
10 11
14 15
 */// result 3

public class EdgeOrientation {

	private static int[][] adjazenzmatrix;
	private static int source;
	private static int sink;
	private static int numberNodes;
	private static int numberEdges;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get metadata
		String[] metdadataNodes = br.readLine().split(" ");
		numberNodes = Integer.valueOf(metdadataNodes[0]) + 2; // add source and sink

		String[] metdadataEdges = br.readLine().split(" ");
		numberEdges = Integer.valueOf(metdadataEdges[0]);

		adjazenzmatrix = new int[numberNodes][numberNodes];

		for (int i = 0; i < numberEdges; i++) {
			String[] edgeInput = br.readLine().split(" ");
			adjazenzmatrix[Integer.valueOf(edgeInput[0])][Integer.valueOf(edgeInput[1])] = 1;
			// adjazenzmatrix[Integer.valueOf(edgeInput[1])][Integer.valueOf(edgeInput[0])]
			// = 1;
		}

		source = 0;
		sink = numberNodes - 1;
		int maxFlow = 0;

		// // source
		// for (int i = 1; i <= numberNodes; i++) {
		// adjazenzmatrix[source][i] = 0;
		// }
		//
		// // sink
		// for (int i = 1; i <= numberNodes; i++) {
		// adjazenzmatrix[numberNodes + i][sink] = 1;
		// }

		printGraph();

		boolean increasedFlow = true;
		int maxDegree = 0;

		for (int i = 1; i <= numberNodes - 2; i++) {

			adjazenzmatrix[i][sink] = 1;
			adjazenzmatrix[source][i] = numberEdges;
		}

		System.out.println(fordFulkerson());
		// while (increasedFlow) {
		//
		// System.out.println("while... next loop ");
		//
		// increasedFlow = false;
		// maxDegree++;
		// System.out.println("maxDegree = " + maxDegree);
		//
		// for (int i = 1; i <= numberNodes - 2; i++) {
		//
		// adjazenzmatrix[i][sink] = maxDegree;
		// }
		//
		// int ff = fordFulkerson();
		//
		// if (ff > 0) {
		// System.out.println("ff: " + ff);
		// increasedFlow = true;
		// }
		//
		// // for(int i = 1; i <= numberNodes -2 ; i++) {
		// // if (i > 1) {
		// // adjazenzmatrix[source][i - 1] = 0;
		// // adjazenzmatrix[i -1 ][sink] = maxDegree;
		// // }
		// // adjazenzmatrix[source][i] = numberEdges;
		// //
		// //
		// // adjazenzmatrix[i][sink] = 0;
		// //
		// // System.out.println("A");
		// // printGraph();
		// //
		// // int ff = fordFulkerson();
		// //
		// // System.out.println("B");
		// // printGraph();
		// //
		// // if(ff > 0) {
		// // System.out.println("ff: "+ff);
		// // increasedFlow = true;
		// // break;
		// // }
		// // }
		//
		// }
		//
		// System.out.println(maxDegree - 1);

	}

	private static void printGraph() {
		for (int a = 0; a < numberNodes; a++) {
			for (int b = 0; b < numberNodes; b++) {
				System.out.print(adjazenzmatrix[a][b] + "   ");
			}
			System.out.print("\n");
		}

		System.out.println();
		System.out.println("##### #####");
		System.out.println();

	}

	private static boolean bfs(int start, int parent[]) {

		boolean visited[] = new boolean[numberNodes];
		for (int i = 0; i < numberNodes; ++i) {
			visited[i] = false;
		}

		LinkedList<Integer> queue = new LinkedList<Integer>();
		queue.add(start);
		visited[start] = true;
		parent[start] = source;

		while (queue.size() != 0) {
			int u = queue.poll();

			// if(visited[u]) {
			// continue;
			// }

			for (int v = 0; v < numberNodes; v++) {
				if (visited[v] == false && adjazenzmatrix[u][v] > 0 && v!= 0) {

					 if (v != sink || parent[u] != source) {
					queue.add(v);
					// if(u != source) {
					visited[v] = true;
					parent[v] = u;
					// }
					 }
				}
			}
		}

		return (visited[sink] == true);
	}

	private static int fordFulkerson() {

		// init
		int parent[] = new int[numberNodes];
		int maxFlow = 0;
		boolean cancel = false;

		while (true) {
			
			System.out.println("++++++************++++++");
			System.out.println("A");
			printGraph();
			
			cancel = true;
			for(int i = 1; i <= numberNodes -2; i++) {
				parent = new int[numberNodes];
				if(bfs(i, parent)) {
					cancel = false;
					break;
				}
			}
			if(cancel) {
				System.out.println("CANCEL");
				break;
			}
			
			System.out.println("++++++");
			System.out.println("B");
			printGraph();

			int tempFlow = Integer.MAX_VALUE;
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				tempFlow = Math.min(tempFlow, adjazenzmatrix[u][v]);
			}

			// update adjazenzmatrix
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				adjazenzmatrix[u][v] -= tempFlow;
				// adjazenzmatrix[v][u] += tempFlow;
				adjazenzmatrix[v][u] = Math.max(adjazenzmatrix[v][u] += tempFlow, 1);
			}

			// add to maxFlow
			maxFlow += tempFlow;
		}

		return maxFlow;
	}
}