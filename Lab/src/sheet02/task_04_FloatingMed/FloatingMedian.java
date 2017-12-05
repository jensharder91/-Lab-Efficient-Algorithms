package sheet02.task_04_FloatingMed;

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

/* 
 * 4 Floating median
Task: You are given online a sequence of non-negative integers (ai)i2[1:n]. Compute
for each k 2 [1 : n] the median of the subset fai : i 2 [1 : k]g. The median
m of a set fa1; : : : ; akg is dened as follows: Let ~a1  : : :  ~ak denote the numbers
(ai)i2[k] in non-decreasing order. Then, the median is dened as follows
m(fa1; : : : ; akg) =
(
~a(k+1)=2; if k is odd;
b(~ak=2 + ~ak=2+1)=2c; otherwise.
Note that we simulated integer division in the previous line.
Input: The input consists of at most 11000 integers. a1 is given in line 1, and
so on.
Output: For line k, print the median of the set (a1; : : : ; ak).
Sample Input:
1
2
3
5
8
Sample Output:
1
1
2
2
3
 */
public class FloatingMedian {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		int median=0;
		int counter=0;
		int nextNumb;
		int position=0;
		int t=1; // zur Modulo Vermeidung
	
		ArrayList<Integer> Numbers =new ArrayList<Integer>();
		
		while (scanner.hasNextInt() && counter < 11000) {
			nextNumb = scanner.nextInt();
			
			sortedInsert(nextNumb, Numbers);
			
			if(t==1){ // k ungerade
				position= (counter+1)/2;
				median = Numbers.get(position);
			}
			else{ //k gerade
				position=counter/2;
				median = (Numbers.get(position)+Numbers.get(position+1))/2;
			}
			System.out.println(median);	
			counter++;
			t*=(-1);
		}
		scanner.close();
	}
	
	// sortiertes einfuegen von a in L
	static void sortedInsert(int a, ArrayList<Integer> L)
    {
        int index = Collections.binarySearch(L,a);
        if (index < 0)
        {
            index = -(index + 1); 
        }
        L.add(index, a);
    }

}
