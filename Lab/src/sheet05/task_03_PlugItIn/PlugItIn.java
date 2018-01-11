package sheet05.task_03_PlugItIn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

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

public class PlugItIn {
	private static int[][] adjazenzmatrix;
	private static int source;
	private static int sink;
	private static int numberSockets;
	private static int numberDevices;
	private static int numberEdges;
	private static int numberNodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// get metadata
		String[] metdadata = br.readLine().split(" ");
		numberSockets = Integer.valueOf(metdadata[0]);
		numberDevices = Integer.valueOf(metdadata[1]);
		numberEdges = Integer.valueOf(metdadata[2]);

		numberNodes = numberSockets + numberDevices + 3; // +3 -> source, sink, max flow control

		// init
		int maxFlow = 0;
		adjazenzmatrix = new int[numberNodes][numberNodes];
		source = 0;
		int maxFlowControl = numberNodes - 2;
		sink = numberNodes - 1;

		// get inputfrom edges
		for (int edge = 0; edge < numberEdges; edge++) {
			String[] edgeInput = br.readLine().split(" ");

			adjazenzmatrix[Integer.valueOf(edgeInput[0])][Integer.valueOf(edgeInput[1]) + numberSockets] = 1;
		}

		// source
		for (int i = 1; i <= numberSockets; i++) {
			adjazenzmatrix[source][i] = 1;
		}

		// max flow control
		for (int i = 1; i <= numberDevices; i++) {
			adjazenzmatrix[numberSockets + i][maxFlowControl] = 1;
		}

		// sink
		adjazenzmatrix[maxFlowControl][sink] = numberSockets;

		// Ford-Fulkerson
		while (true) {
			ArrayList<Integer> inversePath = getShortestPath();
			// no path exists (anymore), quit
			if (inversePath.size() < 1) {
				break;
			}
			// System.out.println("path size: "+inversePath.size());
			// for (int j = inversePath.size() - 1; j > 1; j--) {
			// System.out.print(inversePath.get(j) + " ");
			// }
			// System.out.print("\n");

			// find out how much flow this paths has INVERSE
			int useFlow = Integer.MAX_VALUE;
			for (int j = inversePath.size() - 1; j > 0; j--) {
				useFlow = Math.min(useFlow, adjazenzmatrix[inversePath.get(j)][inversePath.get(j - 1)]);
			}

			// update matrix INVERSE
			for (int j = inversePath.size() - 1; j > 0; j--) {
				adjazenzmatrix[inversePath.get(j)][inversePath.get(j - 1)] -= useFlow;
				adjazenzmatrix[inversePath.get(j - 1)][inversePath.get(j)] += useFlow;
			}

			// add to maxFlow
			maxFlow += useFlow;
		}

		// check if a plug-bar is useful
		boolean addOne = false;
		boolean addTwo = false;
		for (int i = 1; i <= numberSockets; i++) {
			boolean tempAddOne = false;
			for (int j = 1; j <= numberDevices; j++) {
				if (adjazenzmatrix[i][numberSockets + j] == 1) {
					if (tempAddOne) {
						addTwo = true;
						break;
					}
					addOne = true;
					tempAddOne = true;
				}
			}
			if (addTwo) {
				break;
			}
		}

		if (addTwo) {
			System.out.println(maxFlow + 2);
		} else if (addOne) {
			// TODO complex logic to check if two are still possible
			System.out.println(maxFlow + 1);
		} else {
			System.out.println(maxFlow);
		}
	}

	private static ArrayList<Integer> getShortestPath() {

		ArrayList<Integer> inversePath = new ArrayList<>();

		Map<Integer, Integer> map = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		Integer current;

		set.add(source);

		// init all edges to queue
		for (int i = 0; i < numberNodes; i++) {
			if (adjazenzmatrix[source][i] > 0 && !set.contains(i)) {
				queue.add(i);
				set.add(i);
				map.put(i, source);
			}
		}

		// BFS
		while (!queue.isEmpty()) {
			current = queue.poll();

			// check break condition
			if (current.intValue() == sink) {
				break;
			}

			for (int i = 0; i < numberNodes; i++) {
				if (adjazenzmatrix[current][i] != 0 && !set.contains(i)) {
					queue.add(i);
					set.add(i);
					map.put(i, current);
				}
			}
		}

		// go through path and add to list (inverse!!)
		Integer cur = sink;
		inversePath.add(cur);
		while (true) {
			cur = map.get(cur);
			if (cur == null) {
				break;
			}
			inversePath.add(cur);
		}

		// no complete path -> no path -> clear
		if (inversePath.get(inversePath.size() - 1) != source) {
			inversePath.clear();
		}
		return inversePath;
	}
}
