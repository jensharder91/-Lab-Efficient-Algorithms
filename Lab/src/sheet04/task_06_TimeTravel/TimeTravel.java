package sheet04.task_06_TimeTravel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
2
3 4
0 1 900
1 0 1
1 2 13
2 1 -22
3 3
0 1 1
1 2 1
2 0 1
 */

public class TimeTravel {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.valueOf(br.readLine().split(" ")[0]);

		// loop for each test case
		for (int testCase = 0; testCase < testCases; testCase++) {

			// meta input for each testcase (nodes + connections)
			String[] input = br.readLine().split(" ");
			int nodes = Integer.valueOf(input[0]);
			int connections = Integer.valueOf(input[1]);

			// init edges
			Edge[] edges = new Edge[connections];

			// init distacne from source
			int[] distacnesToSource = new int[nodes];

			// read and save all connections / edges
			for (int connection = 0; connection < connections; connection++) {
				String[] connectionInput = br.readLine().split(" ");
				edges[connection] = new Edge(Integer.valueOf(connectionInput[0]), Integer.valueOf(connectionInput[1]),
						Integer.valueOf(connectionInput[2]));
			}

			// init
			for (int i = 0; i < nodes; i++) {
				distacnesToSource[i] = Integer.MAX_VALUE;
			}
			distacnesToSource[0] = 0;

			// loop (node -1) time all connections..
			for (int i = 1; i < nodes; ++i) {
				for (int j = 0; j < connections; ++j) {
					int u = edges[j].from;
					int v = edges[j].to;
					int weight = edges[j].weight;
					if (distacnesToSource[u] != Integer.MAX_VALUE
							&& distacnesToSource[u] + weight < distacnesToSource[v])
						distacnesToSource[v] = distacnesToSource[u] + weight;
				}
			}

			// one step more...
			boolean possible = false;
			for (int j = 0; j < connections; ++j) {
				int u = edges[j].from;
				int v = edges[j].to;
				int weight = edges[j].weight;
				// still n -1 not enough -> has to be a negative circle
				if (distacnesToSource[u] != Integer.MAX_VALUE && distacnesToSource[u] + weight < distacnesToSource[v]) {
					possible = true;
				}
			}
			if (possible) {
				System.out.println("possible");
			} else {
				System.out.println("not possible");
			}
		}
	}

	private static class Edge {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

	}
}
