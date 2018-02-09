package sheet05.task_09_Collinearity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Collinearity {
	
	//private static HashMap<double, Integer> hashmap;
	static ArrayList <Double[]> Points;
	
	public static void main(String[] args) throws IOException  {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// metadata
		int numberPoints = Integer.valueOf(br.readLine().split(" ")[0]);
		Points= new ArrayList<Double[]>();
		int anzahl=0;
		ArrayList<Double> lines = new ArrayList<Double>();
			
		// alle Punkte in Liste
		for (int i = 0; i < numberPoints; i++) {
			String[] Punkt= br.readLine().split(" ");
			Double Point[]= new Double[2];
			Point[0]=Double.valueOf(Punkt[0]);
			Point[1]=Double.valueOf(Punkt[1]);
			Points.add(Point);	
		}
		
		for (int i = 0; i < Points.size(); i++) {
			lines.clear();
			for (int j = i+1; j < Points.size(); j++) {
				double[] Line= new double[2];
				Line=computeLine(i,j);	
				lines.add(Double.valueOf(Line[0]));
			}
			Collections.sort(lines);
			int temp=1;
			for(int j=0; j<lines.size()-1; j++){
				if (Math.abs(lines.get(j)-lines.get(j+1))< 0.000000001){
					temp++;
				}
				else{
					temp=1;
				}
				if (temp >anzahl)
					anzahl=temp;
			}
		}
		
		//output
		anzahl++;
		System.out.println(anzahl);
	}
	
	public static double[] computeLine(int i, int j){
		double parameters[]=new double[2];
		//y=a*x+b
		//parameters[0]=a;
		//parameters[1]=b;
		if ((Points.get(i)[0]-Points.get(j)[0])!=0){
			parameters[0]=(Points.get(i)[1]-Points.get(j)[1])/(Points.get(i)[0]-Points.get(j)[0]);
			parameters[1]=Points.get(i)[0]*parameters[0]-Points.get(i)[1];
		}
		else{
			parameters[0]=Double.MAX_VALUE;
			parameters[1]=Points.get(i)[0];
		} 
			
		return parameters;
	}
	
	
}

