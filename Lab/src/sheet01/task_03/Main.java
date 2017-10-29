package sheet01.task_03;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		if (scanner.hasNextInt()) {

			String str;
			int m;
			int n;
			int coutner = scanner.nextInt();
			// check for invalid input
			if ( counter > 100) 
				counter = 100;
			for (counter; coutner > 0; coutner--) {

				str = scanner.next();
				m = scanner.nextInt();
				n = scanner.nextInt();
			// check for invalid input
				if ( m > 100 || m < 4 || n > 100 || n < 4) 
					System.out.println("invalid input");
				else {
					if (str.equals("K")) {// king
						System.out.println((int) Math.ceil((m) / 2.0) * (int) Math.ceil((n) / 2.0));
					} else if (str.equals("Q")) {// queen
						int smaller = m < n ? m : n;
						int bigger = m < n ? n : m;

						// simple cases, because assume smaller < 5
						switch (smaller) {
						case 1:
							System.out.println(1);
							break;
						case 2:
							if (bigger < 3)
								System.out.println(1);
							else
								System.out.println(2);
							break;
						case 3:
							if (bigger < 4)
								System.out.println(2);
							else
								System.out.println(3);
							break;
						case 4:
							if (bigger < 5)
								System.out.println(3);
							else
								System.out.println(4);
							break;
						default:
							System.out.println("error");
							break;
						}
					} else if (str.equals("r")) {// rook
						System.out.println(Math.min(m, n));
					} else if (str.equals("k")) {// knight
						System.out.println((int) Math.ceil((m * n) / 2.0));
					}
				}
			}
		}

		scanner.close();
	}
}
