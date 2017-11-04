package sheet02.task_06_Contest_Scores;

import java.util.Scanner;

public class ContestScores {

	private static final int numberTeams = 50;
	private static final int numberProblems = 20;

	public static void main(String[] args) {

		int[] teamTime = new int[numberTeams];
		boolean[][] correctTable = new boolean[numberTeams][numberProblems];
		Scanner scanner = new Scanner(System.in);

		while (true) {
			
			String nextline = scanner.nextLine();
			if(nextline.equals("")) {
				break;
			}
			
			String[] splitResult = nextline.split(" ");
			
			int team = Integer.valueOf(splitResult[0]);
			int problem = Integer.valueOf(splitResult[1]);
			int time = Integer.valueOf(splitResult[2]);
			String letter = splitResult[3];

			// format for arrays;
			team--;
			problem--;

			if (letter.equals("C") && !correctTable[team][problem]) {
				teamTime[team] += time;
				correctTable[team][problem] = true;
			} else if (letter.equals("I")) {
				teamTime[team] += 20;
			}
		}

		for (int i = 0; i < numberTeams; i++) {
			if (teamTime[i] > 0) {
				int totalSolvedNumber = 0;
				for (int j = 0; j < numberProblems; j++) {
					if (correctTable[i][j]) {
						totalSolvedNumber++;
					}
				}
				System.out.println((i + 1) + " " + totalSolvedNumber + " " + teamTime[i]);
			}
		}

		scanner.close();
	}
}
