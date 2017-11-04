package sheet01.task_02;

import java.util.Scanner;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String word; // the input word
		int l=0; // the length of the input word
		while (scanner.hasNext()) {
			word = scanner.next();
			l = word.length();
			if ( l > 8)
				System.out.println("the word is to long");
			else {
				char[] permutation = word.toCharArray();        //Convert to array of chars 
				java.util.Arrays.sort(permutation);  
				System.out.println(permutation);
				permute (permutation);			
				}
			}
			System.out.println("another test");
	}
	
	static void permute (char [] text, int n) {
		char temp;
		if (n == 1){
		System.out.println(text); 
		}
		else{
			for (int k=n; k>=1; k=k-1){
				if (text[k] == text[n])  // don't print equal words twice
					continue;
				// swap text[k], text[n]
				temp=text[k];
				text[k]=text[n];
				text[n]=temp;
				permute (text, n-1); 
				// swap text[k], text[n]
				temp=text[k];
				text[k]=text[n];
				text[n]=temp;
			}
		} 
	}
}
