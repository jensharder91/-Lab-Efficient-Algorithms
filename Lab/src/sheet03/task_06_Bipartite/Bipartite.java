package sheet03.task_06_Bipartite;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bipartite {



	private static List<List<Integer>> edges;
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		int numberVertices = scanner.nextInt();
		int numberEdges = scanner.nextInt();
		
		edges = new ArrayList<List<Integer>>(numberVertices);
	
		
		for(int i = 0; i < numberEdges; i++) {
			int vertex1 = scanner.nextInt();
			int vertex2 = scanner.nextInt();
			edges.get(vertex1).add(vertex2);
			edges.get(vertex2).add(vertex1);
		}
		
		scanner.close();
		
	}
	
	private static void coloredBFS(int numberVertices) {
		
		int[] coloredGroups = new int[numberVertices];
		
		
		
		
	}
	



}

