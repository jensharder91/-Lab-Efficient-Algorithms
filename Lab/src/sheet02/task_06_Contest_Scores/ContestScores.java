package sheet02.task_06_Contest_Scores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

/*
2 4 0 I
5 4 5 C
2 4 7 C
2 5 9 R
2 5 10 C

//result 
2 2 37
5 1 5
############################
10 5 12 I
10 5 7 I
3 5 7 I
10 5 3 I
3 5 3 I
3 9 15 I
3 9 10 C
10 9 15 I
10 9 10 C
10 5 10 C
3 5 3 I
3 5 15 C
10 5 3 I
10 5 2 C
3 5 2 C

//result 
10 2 100
3 2 105

############################
4 4 1 R
45 4 4 I
42 5 4 C
42 7 6 I
45 1 2 R
45 4 12 I
42 8 5 I
3 2 12 R
46 5 2 R
45 4 2 I
42 1 4 I
45 7 3 C
46 2 5 I
3 1 12 I
42 7 1 I
42 7 2 R 
45 3 1 I
45 4 2 C
42 7 4 I
46 2 10 C
45 9 2 I
46 5 5 I
42 7 1 C
46 1 10 C

//result 
46 2 40
42 2 65
45 2 65
3 0 20
3 0 0
 */
public class ContestScores {

	private static final int numberTeams = 50;
	private static final int numberProblems = 20;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Team[] allTeams = new Team[numberTeams];
		for (int i = 0; i < numberTeams; i++) {
			allTeams[i] = new Team(i + 1);
		}

		while (true) {
			// input
			String input = br.readLine();

			if (input == null || input.equals("")) {
				break;
			}

			String[] data = input.split(" ");

			int team = Integer.valueOf(data[0]);
			int problem = Integer.valueOf(data[1]);
			int time = Integer.valueOf(data[2]);
			String letter = data[3];

			// format for arrays;
			team--;
			problem--;

			if (letter.equals("C")) {
				allTeams[team].submitCorrectAnswer(problem, time);
			} else if (letter.equals("I")) {
				allTeams[team].submitWrongAnswer(problem);
			} else if (letter.equals("R")) {
				allTeams[team].submitClarificationRequest();
			}
		}

		Arrays.sort(allTeams, new Comparator<Team>() {

			@Override
			public int compare(Team o1, Team o2) {

				int solvedProblems = o2.solvedProblems - o1.solvedProblems;

				if (solvedProblems == 0) {
					int time = o1.totalTimeOfCorrectAnswers - o2.totalTimeOfCorrectAnswers;

					if (time == 0) {
						return o1.teamNumber - o2.teamNumber;
					}
					return time;
				}
				return solvedProblems;
			}
		});

		for (int i = 0; i < allTeams.length; i++) {
			if (allTeams[i].didSubmit) {
				System.out.println(allTeams[i]);
			}
		}
	}

	private static class Team {
		public int teamNumber;
		public int solvedProblems = 0;
		public int totalTimeOfCorrectAnswers = 0;
		public boolean didSubmit = false;
		private int[] timeWrongAnswers = new int[numberProblems];
		private boolean[] correct = new boolean[numberProblems];

		public Team(int teamNumber) {
			this.teamNumber = teamNumber;
		}

		@Override
		public String toString() {
			return teamNumber + " " + solvedProblems + " " + totalTimeOfCorrectAnswers;
		}

		public void submitCorrectAnswer(int problem, int time) {
			didSubmit = true;
			if (correct[problem] == false) {
				totalTimeOfCorrectAnswers += timeWrongAnswers[problem] + time;
				correct[problem] = true;
				solvedProblems++;
			}
		}

		public void submitWrongAnswer(int problem) {
			didSubmit = true;
			timeWrongAnswers[problem] += 20;
		}

		public void submitClarificationRequest() {
			didSubmit = true;
		}

	}
}
