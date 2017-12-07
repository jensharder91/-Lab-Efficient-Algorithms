package sheet04.task_05_SmallWorldExperiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/*
3 3
William Harry
Harry Kate
Kate William
0 0
 */
public class SmallWorldExperiment {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] metaData = br.readLine().split(" ");

		int numberPersons = Integer.valueOf(metaData[0]);
		int numberConnections = Integer.valueOf(metaData[1]);

		int[][] matrix = new int[numberPersons][numberPersons];
		for (int i = 0; i < numberPersons; i++) {
			for (int j = 0; j < numberPersons; j++) {

				matrix[i][j] = Integer.MAX_VALUE;

			}
		}

		int index = 0;
		HashMap<String, Integer> personMap = new HashMap<>();
		int maxValue = 0;

		for (int connection = 0; connection < numberConnections; connection++) {
			String[] newConnection = br.readLine().split(" ");

			Integer node1 = personMap.get(newConnection[0]);
			if (node1 == null) {
				node1 = index;
				personMap.put(newConnection[0], index);
				index++;
			}

			Integer node2 = personMap.get(newConnection[1]);
			if (node2 == null) {
				node2 = index;
				personMap.put(newConnection[1], index);
				index++;
			}
			// System.out.println("#######");
			// System.out.println(newConnection[0]);
			// System.out.println(node1);
			// System.out.println(personMap.get(newConnection[0]));
			// System.out.println("#");
			// System.out.println(newConnection[1]);
			// System.out.println(node2);
			// System.out.println(personMap.get(newConnection[1]));

			matrix[node1][node2] = 1;
			matrix[node2][node1] = 1;
		}

		for (int k = 0; k < numberPersons; k++) {
			for (int i = 0; i < numberPersons; i++) {
				for (int j = 0; j < numberPersons; j++) {

					// if (matrix[i][k] + matrix[k][j] < matrix[i][j]) {
					// matrix[i][j] = matrix[i][k] + matrix[k][j];
					// }

					// because problem with Integer.MAX_VALUE use this one:
					if (matrix[i][k] < matrix[i][j] - matrix[k][j]) {
						matrix[i][j] = matrix[i][k] + matrix[k][j];
					}
				}
			}
		}

		boolean fullyConnected = true;
		for (int i = 0; i < numberPersons; i++) {
			for (int j = i + 1; j < numberPersons; j++) {

				if (matrix[i][j] == Integer.MAX_VALUE) {
					fullyConnected = false;
					break;
				}
				maxValue = Math.max(maxValue, matrix[i][j]);

			}
		}
		if (fullyConnected) {
			System.out.println(maxValue);
		} else {
			System.out.println("INFINITY");
		}
	}

}
