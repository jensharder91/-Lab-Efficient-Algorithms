package sheet05.task_03_PlugItIn;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

/*
2 3 3
1 1
2 2
2 3
 */ //result 2 + 1

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

		numberNodes = numberSockets + numberDevices + 2; // +2 -> source, sink

		// init
		int maxFlow = 0;
		adjazenzmatrix = new int[numberNodes][numberNodes];
		source = 0;
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

		// sink control
		for (int i = 1; i <= numberDevices; i++) {
			adjazenzmatrix[numberSockets + i][sink] = 1;
		}
		
		//max flow without plugbar
		maxFlow = fordFulkerson();

		//try to apply sockets
		for(int i = 1; i <= numberSockets; i++) {
			for(int j = 1; j <= numberSockets; j++) {
				if(i != j) {
					adjazenzmatrix[source][j] = 1;
				}else {
					adjazenzmatrix[source][j] = 3;
				}
			}
			maxFlow = maxFlow + fordFulkerson();
		}
		
		System.out.println(maxFlow);
	}


  private static boolean bfs(int parent[])  {

      boolean visited[] = new boolean[numberNodes];
      for(int i=0; i<numberNodes; ++i) {
    	  visited[i]=false;
      }

      LinkedList<Integer> queue = new LinkedList<Integer>();
      queue.add(source);
      visited[source] = true;
      parent[source]=-1;

      while (queue.size()!=0){
          int u = queue.poll();

          for (int v=0; v<numberNodes; v++){
              if (visited[v]==false && adjazenzmatrix[u][v] > 0){
                  queue.add(v);
                  parent[v] = u;
                  visited[v] = true;
              }
          }
      }
      
      return (visited[sink] == true);
  }

  private static int fordFulkerson(){
	  
      //init 
      int parent[] = new int[numberNodes];
      int maxFlow = 0;

      while (bfs(parent)){

          int tempFlow = Integer.MAX_VALUE;
          for (int v=sink; v!=source; v=parent[v]){
              int u = parent[v];
              tempFlow = Math.min(tempFlow, adjazenzmatrix[u][v]);
          }

          // update adjazenzmatrix
          for (int v=sink; v != source; v=parent[v]){
              int u = parent[v];
              adjazenzmatrix[u][v] -= tempFlow;
              adjazenzmatrix[v][u] += tempFlow;
          }

          // add to maxFlow
          maxFlow += tempFlow;
      }
      
      return maxFlow;
  }
}
