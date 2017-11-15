package sheet02.task_02;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int x;
		char [] a;
		char [] b;
		char [] temp;
		int counter;
		int toggle;
		
		while (scanner.hasNextInt()) {
			x = scanner.nextInt();
			counter =0;
			if (x == 0)
				break;
			if (x < 0)
				continue;
			// If x[i; i] = 0, then so are a[i; i] and b[i; i].
			temp = intToBinary(x);
			a = intToBinary(x);
			b = intToBinary(x);
			// If x[i; i] is 1, then so is either a[i; i] or b[i; i].
			// For every range i; : : : ; j, the part a[j; i] contains at most one \1" more and
			//		at most one \1" less than b[j; i].
			// a is larger than b if and only if x[30; 0] contains an odd number of \1"s.
			counter = Integer.bitCount(x);	
			if (counter % 2 == 0){
				toggle = 1;	
			}
			else {
				toggle = -1;
			}
			for(int i= 0; i < temp.length; i++){
				if (temp[i] == '1'){				
					if (toggle == 1){
						a[i]= '0';
					}
					else {
						b[i]= '0';
					}
					toggle*= (-1);
				}
			}
			System.out.println(BinaryToInt(a) + " " + BinaryToInt(b));
		}
		scanner.close();
	}
	
	public static char[] intToBinary(int n){
	    String s = Integer.toBinaryString(n);
		return s.toCharArray();	
	}
	
	public static int BinaryToInt(char [] bits){
		String s= String.valueOf(bits);
	    return Integer.parseInt(s,2);
	}

}
