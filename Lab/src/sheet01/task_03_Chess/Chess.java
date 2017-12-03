package sheet01.task_03_Chess;

import java.util.Scanner;

public class Chess {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		if (scanner.hasNextInt()) {

			String str;
			int m;
			int n;

			for (int coutner = scanner.nextInt(); coutner > 0; coutner--) {

				str = scanner.next();
				m = scanner.nextInt();
				n = scanner.nextInt();

				if (str.equals("K")) {// king
					System.out.println((int) Math.ceil((m) / 2.0) * (int) Math.ceil((n) / 2.0));
				} else if (str.equals("Q")) {// queen
					System.out.println(Math.min(m, n));
				} else if (str.equals("r")) {// rook
					System.out.println(Math.min(m, n));
				} else if (str.equals("k")) {// knight
					System.out.println((int) Math.ceil((m * n) / 2.0));
				}

			}
		}

		scanner.close();
	}
}
