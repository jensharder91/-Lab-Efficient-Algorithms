package sheet05.task_01_1984_MinCut;

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


public class MinCut_1984 {
	
		private static int[][] adjazenzmatrix;
		private static int source;
		private static int sink;
		private static int numberNodes;
		private static int numberEdges;

		public static void main(String[] args) throws NumberFormatException, IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String[] metadata = br.readLine().split(" ");
			numberNodes = Integer.valueOf(metadata[0]);
			// every node v is later split  into v and numberNodes+v
			// to be able to add the edge {v,numberNodes+v} with cost of the node
			numberEdges = Integer.valueOf(metadata[1]);

			// loop testcases
			while ( numberNodes+numberEdges > 0)  {

				// init
				int maxFlow = 0;
				adjazenzmatrix = new int[2*numberNodes][2*numberNodes];
				source = 1;
				sink = numberNodes; 
				
				// get input from nodes
				for (int node = 1; node < numberNodes-1; node++) {
					String[] nodeInput = br.readLine().split(" ");

					adjazenzmatrix[Integer.valueOf(nodeInput[0])]
								[Integer.valueOf(nodeInput[0])+numberNodes]
							= Integer.valueOf(nodeInput[1]);
				}
				adjazenzmatrix[1][1+numberNodes]= Integer.MAX_VALUE;

				// get input from edges
				for (int edge = 0; edge < numberEdges; edge++) {
					String[] edgeInput = br.readLine().split(" ");

					adjazenzmatrix[Integer.valueOf(edgeInput[0])+numberNodes][Integer.valueOf(edgeInput[1])]
							= Integer.valueOf(edgeInput[2]);
				}

				// Ford-Fulkerson
				while (true) {
					ArrayList<Integer> inversePath = getShortestPath();
					// no path exists (anymore), quit
					if (inversePath.size() < 1) {
						break;
					}
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

				// print out result			
				System.out.println(maxFlow);
				
				// get next metadata
				metadata = br.readLine().split(" ");
				numberNodes = Integer.valueOf(metadata[0]);
				numberEdges = Integer.valueOf(metadata[1]);
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
			for (int i = 0; i < 2*numberNodes; i++) {
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
				for (int i = 0; i < 2*numberNodes; i++) {
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




