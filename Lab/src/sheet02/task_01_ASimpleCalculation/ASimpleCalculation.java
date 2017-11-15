package sheet02.task_01;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		if (scanner.hasNextInt()) {

			int n;
			int k;
			BigInteger zero = BigInteger.valueOf(0);
			BigInteger sum; 
			BigInteger temp;
      
			while (scanner.hasNextInt()) {
				sum = zero;
				n = scanner.nextInt();
				k = scanner.nextInt();
				temp= BigInteger.ONE; //

				//check for valid input
				if ( (n > 0 && n < 146) && ( k >= 0 && k < 16)) {
        
				  for ( int j=1;  j <= n; j++){
					  temp=temp.multiply(BigInteger.valueOf(k));
					  sum = sum.add(temp.multiply(BigInteger.valueOf(j)));
				  }
				  
				  System.out.println(sum.toString());
				}
				else
					continue;
			
			}

			scanner.close();
		}
	}
		
}

