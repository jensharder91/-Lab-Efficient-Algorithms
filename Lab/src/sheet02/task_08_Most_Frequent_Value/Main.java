package sheet02.task_08_Most_Frequent_Value;

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextInt()) {
			System.out.println(scanner.nextInt());
		}
		
		scanner.close();
	}

}
