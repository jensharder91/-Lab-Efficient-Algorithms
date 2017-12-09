package sheet04.task_08_ComputingDistance;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;


public class ComputingDistance {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextInt()){
			
			//meta date
			int [] a= new int [2];
			double [] temp= new double [2];
			double [] p= {Double.MAX_VALUE, Double.MAX_VALUE};
			a[0]=scanner.nextInt(); //x
			a[1]=scanner.nextInt(); //y
			int NumbSegm=scanner.nextInt();
			int [][] points = new int [NumbSegm+1][2]; // 
			
			for (int i=0; i<= NumbSegm; i++){
				points[i][0]=scanner.nextInt(); //x-coodinate
				points[i][1]=scanner.nextInt(); //y-coorinate
			}
			
			//computation
			double [] distancesToPoints = new double [NumbSegm+1];
			int closestPointIndex=0;
			double abstand =Double.MAX_VALUE;
			for (int i=0; i<= NumbSegm; i++){
				distancesToPoints[i]=distance(points[i],a);
			// nur wenn pi_x < a_x <pi+1_x (analog fuer die y-Koordinate)
			// ist es moeglich, dass der naechste Punkt auf der Strecke liegt
			// ansosnten ist der naechste Punkt einer der Strecken Punkte
				if(i==0){
					abstand= distancesToPoints[0];
				}
				else {
					if (distancesToPoints[i]< abstand){
						closestPointIndex=i;
						abstand= distancesToPoints[i];
					}
					if ( (points[i-1][0]<a[0] && a[0]<points[i][0]) ||
							points[i-1][1]<a[1] && a[1]<points[i][1]) {
					// Abstand zu Segment und naechstem Punkt
							temp=closestPoint(points[i-1],points[i],a);
							if(abstand >= distance(temp,a)){
								abstand=distance(temp,a);
								closestPointIndex=NumbSegm+1;
								if (p[0]>temp[0])
									p=temp;
							}
					}
				}
				
			
			}	
			//Ausgabe Punkt
			if (closestPointIndex==NumbSegm+1){
				output(p);
			}
			else {
				p[0]=(double)points[closestPointIndex][0];
				p[1]=(double)points[closestPointIndex][1];
				output(p); 
			}
		}
		scanner.close();
	}
	
	static double distance(double [] x, int [] yi) {
		double [] y= {(double)yi[0],(double)yi[1]};
		return Math.sqrt(
				Math.pow(x[0]-y[0], 2) +
				Math.pow(x[1]-y[1], 2)
			);
	}
	static double distance(int [] xi, int [] yi) {
		double [] x= {(double)xi[0],(double)xi[1]};
		double [] y= {(double)yi[0],(double)yi[1]};
		return Math.sqrt(
				Math.pow(x[0]-y[0], 2) +
				Math.pow(x[1]-y[1], 2)
			);
	}
	static double[] closestPoint(int [] xi, int [] yi,int [] ai) {
		double [] punkt = new double[2];
		double [] a= {(double)ai[0],(double)ai[1]};
		double [] x= {(double)xi[0],(double)xi[1]};
		double [] y= {(double)yi[0],(double)yi[1]};
		double [] normVec  = {y[1]-x[1],x[0]-y[0]};
		punkt[0]= (1/((y[0]-x[0])*(-normVec[1])+(y[1]-x[1])*(normVec[0]))
				*((a[0]*(-normVec[1])+a[1]*normVec[0])*(y[0]-x[0])
				-(x[0]*normVec[0] + x[1]*normVec[1])*normVec[0]));
		punkt[1]= (1/((y[0]-x[0])*(-normVec[1])+(y[1]-x[1])*(normVec[0]))
				*((a[0]*(-normVec[1])+a[1]*normVec[0])*(y[1]-x[1])
				-(x[0]*normVec[0] + x[1]*normVec[1])*normVec[1]));
		return punkt;
	}
	
	static void output(double[] point){
		NumberFormat nf = NumberFormat.getInstance(Locale.UK);
		nf.setMinimumFractionDigits(2);
		point[0] = Math.round((point[0]) * 100d) / 100d;
		point[1] = Math.round((point[1]) * 100d) / 100d;
		System.out.println(nf.format(point[0]));
		System.out.println(nf.format(point[1]));
	}

}
	

