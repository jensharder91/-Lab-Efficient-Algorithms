package sheet01.task_05_Company_Excursion;

import java.util.Scanner;

public class CompanyExcursion {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int minimumCost = Integer.MAX_VALUE;

		// meta for testcase
		int participants;
		int limit;
		int accomodationNumer;
		int dateNumer;

		// testcase
		int pricePerPerson;
		int freeRoomsAtDate;
		int cost;

		// TODO break loop at some point? condition?
		while (true) {
			
			if(!scanner.hasNext()) {
				break;
			}

			// reset
			minimumCost = Integer.MAX_VALUE;

			participants = scanner.nextInt();
			limit = scanner.nextInt();
			accomodationNumer = scanner.nextInt();
			dateNumer = scanner.nextInt();

			for (int i = accomodationNumer; i > 0; i--) {
				pricePerPerson = scanner.nextInt();

				cost = pricePerPerson * participants;

				for (int j = dateNumer; j > 0; j--) {
					freeRoomsAtDate = scanner.nextInt();

					if (freeRoomsAtDate >= participants) {
						if (cost <= limit) {
							if (minimumCost > cost) {
								minimumCost = cost;
							}
						}
					}
				}
			}

			if (minimumCost < Integer.MAX_VALUE) {
				System.out.println(minimumCost);
			} else {
				System.out.println("impossible");
			}
		}

		scanner.close();
	}
}
