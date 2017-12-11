package sheet04.task_07_ComputingAMaximumFlow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 
4
2 1
0 1 3.14
6 7
0 1 1.0
0 2 1.0
0 3 1.0
1 4 1.0
2 4 1.0
3 5 2.5
4 5 0.5
6 9
0 1 2.0
0 3 7.0
1 2 6.0
2 5 3.0
2 3 1.0
3 4 3.0
1 3 6.0
4 2 2.0
4 5 4.0
6 7
0 1 2.0
0 3 7.0
2 5 3.0
2 3 3.0
1 3 6.0
4 2 2.0
2 3 1.0
 *
 */
// result
// 3. 14
// 1. 50
// 5.0
// 0

public class ComputingAMaximumFlow {

	private static double[][] adjazenzmatrix;
	private static int source;
	private static int sink;
	private static int numberNodes;
	private static int numberEdges;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.valueOf(br.readLine().split(" ")[0]);

		// loop testcases
		for (int i = 0; i < testCases; i++) {

			// get metadata
			String[] metdadata = br.readLine().split(" ");
			numberNodes = Integer.valueOf(metdadata[0]);
			numberEdges = Integer.valueOf(metdadata[1]);

			// init
			double maxFlow = 0;
			adjazenzmatrix = new double[numberNodes][numberNodes];
			source = 0;
			sink = numberNodes - 1;

			// get inputfrom edges
			for (int edge = 0; edge < numberEdges; edge++) {
				String[] edgeInput = br.readLine().split(" ");

				adjazenzmatrix[Integer.valueOf(edgeInput[0])][Integer.valueOf(edgeInput[1])] = Double
						.valueOf(edgeInput[2]);
			}

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
				double useFlow = Integer.MAX_VALUE;
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

			// print out rounded result			
			double roundend = (Math.round(maxFlow * 100d) / 100d);
			NumberFormat nf = NumberFormat.getInstance(Locale.UK);
			nf.setMinimumFractionDigits(2);
			System.out.println(nf.format(roundend));

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
