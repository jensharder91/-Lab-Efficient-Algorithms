package sheet04.task_03_CollectingEggs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
/*
6
4
3
3 1
2 1
1 0
3 0
2
1
0 1
0 1
10
11
3 2
1 2
4 1
8 9
7 8
7 9
5 6
0 8 
0 5 
6 7
2 5
3 9
10
11
3 2
1 2
4 1
8 9
7 8
7 9
5 6
0 8 
0 5 
6 7
2 5
3 5
10
11
3 2
1 2
4 1
8 9
7 8
7 9
5 6
0 8 
0 5 
6 7
2 5
1 4
10
11
3 2
1 2
4 1
8 9
7 8
7 9
5 6
0 8 
0 5 
6 7
2 5
4 4
 */
//result
// Case 1 : 4
// Case 2 : 1
// Case 3 : 9
// Case 4 : 8
// Case 5 : 11
// Case 6 : 12

public class CollectingEggs {

	private static ArrayList<ArrayList<Integer>> edges;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.valueOf(br.readLine().split(" ")[0]);

		for (int testCase = 0; testCase < testCases; testCase++) {
			int locations = Integer.valueOf(br.readLine().split(" ")[0]);
			int neighborships = Integer.valueOf(br.readLine().split(" ")[0]);

			edges = new ArrayList<ArrayList<Integer>>();
			for (int i = 0; i < locations; i++) {
				edges.add(new ArrayList<Integer>());
			}

			for (int neighborship = 0; neighborship < neighborships; neighborship++) {
				String[] newEdge = br.readLine().split(" ");

				int u = Integer.valueOf(newEdge[0]);
				int v = Integer.valueOf(newEdge[1]);

				edges.get(u).add(v);
				edges.get(v).add(u);
			}

			String[] metaData = br.readLine().split(" ");

			int start = Integer.valueOf(metaData[0]);
			int end = Integer.valueOf(metaData[1]);

			int[] ditances1 = dijkstra(start, locations);
			int[] ditances2 = dijkstra(end, locations);

			int max = 0;
			for (int i = 0; i < locations; i++) {
				max = Math.max(max, ditances1[i] + ditances2[i]);
			}
			System.out.println("Case " + (testCase + 1) + ": " + max);
		}

	}

	private static int[] dijkstra(int startingAt, int vertexNumber) {

		PriorityQueue<Integer> queue = new PriorityQueue<>();
		Set<Integer> set = new HashSet<>();

		int[] distances = new int[vertexNumber];
		for (int i = 0; i < vertexNumber; i++) {
			distances[i] = Integer.MAX_VALUE;
		}

		queue.add(startingAt);
		distances[startingAt] = 0;

		Integer current;

		while (!queue.isEmpty()) {
			current = queue.poll();
			set.add(current);

			for (int i = 0; i < edges.get(current).size(); i++) {
				Integer neighbor = edges.get(current).get(i);

				if (distances[neighbor] > distances[current] + 1) {
					distances[neighbor] = distances[current] + 1;
				}

				if (!set.contains(neighbor)) {
					queue.add(neighbor);
				}
			}
		}
		return distances;
	}
}
