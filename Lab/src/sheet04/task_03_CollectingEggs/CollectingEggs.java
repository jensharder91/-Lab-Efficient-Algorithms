package sheet04.task_03_CollectingEggs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
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

//	private static ArrayList<ArrayList<Integer>> edges;
	private static Node[] nodes;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int testCases = Integer.valueOf(br.readLine().split(" ")[0]);

		for (int testCase = 0; testCase < testCases; testCase++) {
			int locations = Integer.valueOf(br.readLine().split(" ")[0]);
			int neighborships = Integer.valueOf(br.readLine().split(" ")[0]);

//			edges = new ArrayList<ArrayList<Integer>>();
//			for (int i = 0; i < locations; i++) {
//				edges.add(new ArrayList<Integer>());
//			}
			nodes = new Node[locations];
			for(int i = 0; i < locations; i++){
				nodes[i] = new Node();
			}

			for (int neighborship = 0; neighborship < neighborships; neighborship++) {
				String[] newEdge = br.readLine().split(" ");

				int u = Integer.valueOf(newEdge[0]);
				int v = Integer.valueOf(newEdge[1]);

//				edges.get(u).add(v);
//				edges.get(v).add(u);
				
				nodes[u].neighbors.add(nodes[v]);
				nodes[v].neighbors.add(nodes[u]);
			}

			String[] metaData = br.readLine().split(" ");

			int start = Integer.valueOf(metaData[0]);
			int end = Integer.valueOf(metaData[1]);

			dijkstra(start, true);
			dijkstra(end, false);

			int max = 0;
			for (int i = 0; i < locations; i++) {
				max = Math.max(max, nodes[i].getTotalDistance());
			}
			System.out.println("Case " + (testCase + 1) + ": " + max);
		}

	}

	private static void dijkstra(int startingAt, boolean fromStart) {

		Queue<Node> queue = new LinkedList<>();
		Set<Node> set = new HashSet<>();

//		int[] distances = new int[vertexNumber];
//		for (int i = 0; i < vertexNumber; i++) {
//			distances[i] = Integer.MAX_VALUE;
//		}

//		queue.add(startingAt);
//		distances[startingAt] = 0;
		queue.add(nodes[startingAt]);
		if(fromStart) {
			nodes[startingAt].distanceToStart = 0;
		}else {
			nodes[startingAt].distanceToEnd = 0;
		}

		Node current;

		while (!queue.isEmpty()) {
			current = queue.poll();
			set.add(current);

//			for (int i = 0; i < edges.get(current).size(); i++) {
//				Integer neighbor = edges.get(current).get(i);
//
//				if (distances[neighbor] > distances[current] + 1) {
//					distances[neighbor] = distances[current] + 1;
//				}
//
//				if (!set.contains(neighbor)) {
//					queue.add(neighbor);
//				}
//			}
			
			for(Node neighbor : current.neighbors) {
				
				if(fromStart) {
					neighbor.distanceToStart = Math.min(neighbor.distanceToStart, current.distanceToStart +1);
				}else {
					neighbor.distanceToEnd = Math.min(neighbor.distanceToEnd, current.distanceToEnd +1);
				}
				
				if(!set.contains(neighbor)) {
					queue.add(neighbor);
				}
			}
		}
//		return distances;
	}
	
	
	private static class Node{
		
		public ArrayList<Node> neighbors = new ArrayList<>();
		public int distanceToStart = Integer.MAX_VALUE;
		public int distanceToEnd = Integer.MAX_VALUE;
		
		public int getTotalDistance() {
			return distanceToStart + distanceToEnd;
		}
	}
}
