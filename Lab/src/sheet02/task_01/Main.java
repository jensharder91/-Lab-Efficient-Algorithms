package sheet02.task_01;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		if (scanner.hasNextInt()) {

			int n;
			int k;
			long sum;
      
			while (scanner.hasNextInt()) {
        sum = 0;
				n = scanner.nextInt();
				k = scanner.nextInt();

        //check for valid input
        if ( (n > 0 && n < 146) && ( k >0 && k < 16)) {
        
				  for ( int j=1;  j <= n; j++){
           sum += j* (int)Math.pow(k, j);
          }
				  System.out.println(sum);
        }
				else
         System.out.println("invalid input");
			}
		}

		scanner.close();
	}
}

