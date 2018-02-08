package sheet05.task_06_Triangula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Triangula {
	
	static double [][] Matrix;
	static ArrayList <Double[]> Vertices= new ArrayList<Double[]>();
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// metadata
		int numberVertices = Integer.valueOf(br.readLine().split(" ")[0]);
		double length=0;
		Matrix= new double[numberVertices][numberVertices];

		// alle Vertex in Liste
		for (int i = 0; i < numberVertices; i++) {
			String[] point= br.readLine().split(" ");
			Double Vertex[]= new Double[2];
			Vertex[0]=Double.valueOf(point[0]);
			Vertex[1]=Double.valueOf(point[1]);
			Vertices.add(Vertex);	
		}
		
		// Matrix init
//		for(int i=0; i<numberVertices; i++){
//			for(int j=0; j<numberVertices; j++){
//				Matrix[i][j]=0.0;
//			}
//		}
		 
		   for (int gap = 0; gap < numberVertices; gap++)
		   {
		      for (int i = 0, j = gap; j < numberVertices; i++, j++)
		      {
		          if (j < i+2)
		             Matrix[i][j] = 0.0;
		          else
		          {
		              Matrix[i][j] =Double.MAX_VALUE;
		              for (int k = i+1; k < j; k++)
		              {
		                double val = Matrix[i][k] 
		                			+ Matrix[k][j] 
		                			+ distance(Vertices.get(i), Vertices.get(j));
		                if (Matrix[i][j] > val)
		                     Matrix[i][j] = val;
		              }
		          }
		      }
		   }
		length=	Matrix[0][numberVertices-1]
				-distance(Vertices.get(0),Vertices.get(numberVertices-1));
		
		//output
		double roundend = (Math.round(length * 1000d) / 1000d);
		Locale.setDefault(new Locale("en"));
		DecimalFormat df= new DecimalFormat("#############0.000");
		System.out.println(df.format(roundend));
		
	}
	
	public static double distance(Double [] xi, Double [] yi) {
		double [] x= {(double)xi[0],(double)xi[1]};
		double [] y= {(double)yi[0],(double)yi[1]};
		return Math.sqrt(
				Math.pow(x[0]-y[0], 2) +
				Math.pow(x[1]-y[1], 2)
			);
	}
	
//	public static double  getMin (int start, int end){
//		double temp;
//		temp=distance(Vertices.get(start),Vertices.get(end));
//		temp+=Matrix[start][end];
//		temp+=Matrix[end][start];
//		return temp;
//	}
//		
//	double min(double x, double y){
//	    return (x <= y)? x : y;
//	}
		 
//	public static double cost(int i, int j, int k){
//	    return distance(Vertices.get(i), Vertices.get(j));
//	//    		+ distance(Vertices.get(j), Vertices.get(k))
//	//    		+ distance(Vertices.get(k), Vertices.get(i));
//	}
		 
}

