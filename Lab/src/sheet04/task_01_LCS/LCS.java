package sheet04.task_01_LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.valueOf(br.readLine());
		
		while(testCases > 0){
			//meta data
			int length = Integer.valueOf(br.readLine());
			int [] a = new int [length];
			int [] b = new int [length];
			String[] input = br.readLine().split(" ");
			for (int i=0; i<length; i++)
				a[i] = Integer.valueOf(input[i]);
			input = br.readLine().split(" ");
			for (int i=0; i<length; i++)
				b[i] = Integer.valueOf(input[i]);
			
			System.out.println(LCS_length(length, a, b));
			
			testCases--;
		}

	}
	
	public static int LCS_length(int l, int [] a, int [] b){
		int [] previous = new int [l+1];
		int [] current	= new int [l+1];
		for(int i=0; i<=l; i++){
			previous[i]=0;
			current [i]=0;
		}
		for(int i=1; i<l+1; i++){
			for (int j=1; j<l+1; j++){
				if (a[i-1]==b[j-1])
					current[j]=previous[j-1]+1;
				else current[j]=Math.max(current[j-1], previous[j]);	
			}
			for(int j=1; j<=l; j++)
				previous[j]=current[j];
		}	
		return current[l];
	}
		
}


