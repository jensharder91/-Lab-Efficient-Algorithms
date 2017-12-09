package sheet04.task_01_LCS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class LCS {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int testCases = Integer.valueOf(br.readLine());
		
		while(testCases > 0){
			//meta data
			int length = Integer.valueOf(br.readLine());
			int [] a = new int [length];
			int [] b = new int [length];
			int [][] c = new int [length+1][length+1];
			String[] input = br.readLine().split(" ");
			for (int i=0; i<length; i++)
				a[i] = Integer.valueOf(input[i]);
			input = br.readLine().split(" ");
			for (int i=0; i<length; i++)
				b[i] = Integer.valueOf(input[i]);
			c= LCS_length(length, a, b);
			
			System.out.println(c[length][length]);
			
			testCases--;
		}

	}
	
	public static int[][] LCS_length(int l, int [] a, int [] b){
		int [][] z = new int [l+1][l+1];
		for(int i=0; i<=l; i++){
			z[i][0]=0;
			z[0][i]=0;
		}
		for(int i=1; i<l+1; i++){
			for (int j=1; j<l+1; j++){
				if (a[i-1]==b[j-1])
					z[i][j]=z[i-1][i-1]+1;
				else if(z[i-1][j]>=z[i][j-1]) // max von z[i-1][j] und z[i][j-1]
						z[i][j]=z[i-1][j];
				else z[i][j]=z[i][j-1];	
			}
		}
			
		return z;
	}
		
}


