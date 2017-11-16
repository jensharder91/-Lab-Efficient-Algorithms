package sheet01.task_02;

import java.util.Scanner;
import java.util.Vector;

public class Main {
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		String word; // the input word
		int l=0; // the length of the input word
		Vector<String> permutations= new Vector<String>();
		while (scanner.hasNext()) {
			permutations.clear();
			word = scanner.next();
			l = word.length();
			if ( l > 8)
				System.out.println("the word is to long");
			else {
				char[] permutation = word.toCharArray();        //Convert to array of chars 
				java.util.Arrays.sort(permutation);  
			//	System.out.println(permutation);
				permute (permutation, permutation.length, permutations);
				permutations.sort(null);
				for(int i=0; i<permutations.size(); i++)
					System.out.println(permutations.elementAt(i));
				}
		}
		scanner.close();
	}
	
	static void permute (char [] text, int n, Vector<String> permutations) {
		char temp;
		if (n <= 1 ){
			if (!permutations.contains(String.valueOf(text))){
				permutations.add(String.valueOf(text));
			//	System.out.println(text); 
			}
		}
		else{
			for (int k = 0; k < n; k++){
				
				if (text[k] != text[n-1]) { // don't print equal words twice		
					// swap text[k], text[n-1]
					temp=text[k];
					text[k]=text[n-1];
					text[n-1]=temp;
					permute (text, n-1, permutations); 
					// swap text[k], text[n-1]
					temp=text[k];
					text[k]=text[n-1];
					text[n-1]=temp;
				}
			}
			permute (text, n-1, permutations);
		} 
	}
}
