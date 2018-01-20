package sheet05.task_06_Triangula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class Triangula {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// metadata
		int numberVertices = Integer.valueOf(br.readLine().split(" ")[0]);
		ArrayList <Double[]> Vertices= new ArrayList<Double[]>();
		double length=0;
		

		// alle Vertex in Liste
		for (int i = 0; i < numberVertices; i++) {
			String[] point= br.readLine().split(" ");
			Double Vertex[]= new Double[2];
			Vertex[0]=Double.valueOf(point[0]);
			Vertex[1]=Double.valueOf(point[1]);
			Vertices.add(Vertex);	
		}
		length+=split(Vertices);
		
		//output
		double roundend = (Math.round(length * 1000d) / 1000d);
		NumberFormat nf = NumberFormat.getInstance(Locale.UK);
		nf.setMinimumFractionDigits(3);
		System.out.println(nf.format(roundend));
		
	}
	
	public static double distance(Double [] xi, Double [] yi) {
		double [] x= {(double)xi[0],(double)xi[1]};
		double [] y= {(double)yi[0],(double)yi[1]};
		return Math.sqrt(
				Math.pow(x[0]-y[0], 2) +
				Math.pow(x[1]-y[1], 2)
			);
	}
	
	public static double  split (ArrayList<Double[]> Points){
		double shortest=Double.MAX_VALUE;
		int start=0;
		int end=Points.size()-1;
		if (Points.size()<=3) 
			return 0;
		else {
			for (int i = 0; i < Points.size(); i++) {
				for (int j = i+2; j < Points.size(); j++) {
					if (i==0 && j==Points.size()-1)
						continue;
					double temp= distance(Points.get(i),Points.get(j));
					if (shortest>temp){
						shortest=temp;
						start=i;
						end=j;
					}
				}		
			}
			if (Points.size()==4)
				return shortest;
			ArrayList<Double[]> Points2= new ArrayList<Double[]>();
			Points2.addAll(Points);
			Points2.removeAll(Points.subList(start+1, end));
			
			ArrayList<Double[]> Points3=new ArrayList<Double[]>();
			Points3.addAll(Points);
			Points3.removeAll(Points2);
			Points3.add(0,Points.get(start));
			Points3.add(Points.get(end));
			
			return shortest+split(Points3)+split(Points2);
		}
	}
}

