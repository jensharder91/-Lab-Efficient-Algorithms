package sheet03.task_01_ArrangingCircles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/*
4
5 3.0 3.0 3.0 3.0 3.0
3 1.0 3.0 3.0 
5 3.0 3.0 3.0 3.0 0.1
4 3.0 3.0 3.0 3.0
 */
public class ArrangingCircles {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// test cases
		int testCases = Integer.valueOf(br.readLine().split(" ")[0]);

		for (int i = 0; i < testCases; i++) {
			String[] input = br.readLine().split(" ");

			int circles = Integer.valueOf(input[0]);

			double[] radii = new double[circles];

			for (int j = 0; j < circles; j++) {
				radii[j] = Double.valueOf(input[j + 1]);
			}

			ArrayList<double[]> allPermutations = new ArrayList<>();
			permute(radii, radii.length, allPermutations);

			double minPossibleX = Integer.MAX_VALUE;

			// all permuatation
			for (int k = 0; k < allPermutations.size(); k++) {

				double[] circlePointX = new double[circles];

				// place all circles step by step...
				for (int j = 0; j < circles; j++) {

					circlePointX[j] = allPermutations.get(k)[j];

					// check with prev circles (to avoid overlapping)
					for (int m = 0; m < j; m++) {
						circlePointX[j] = Math.max(circlePointX[j],
								circlePointX[m] + Math.sqrt(4 * allPermutations.get(k)[j] * allPermutations.get(k)[m]));
					}
				}

				double permuationMax = 0;
				for (int n = 0; n < circles; n++) {
					permuationMax = Math.max(permuationMax, circlePointX[n] + allPermutations.get(k)[n]);
				}
				minPossibleX = Math.min(minPossibleX, permuationMax);
			}

			// 3 Stellen runden
			minPossibleX = Math.round((minPossibleX) * 1000d) / 1000d;
			NumberFormat nf = NumberFormat.getInstance(Locale.UK);
			nf.setMinimumFractionDigits(3);
			System.out.println(nf.format(minPossibleX));

		}
	}

	static void permute(double[] radii, int n, ArrayList<double[]> permutations) {
		double temp;
		if (n <= 1) {
			permutations.add(radii.clone());
		} else {
			for (int k = 0; k < n; k++) {

				if (radii[k] != radii[n - 1]) { // don't print equal words twice
					// swap text[k], text[n-1]
					temp = radii[k];
					radii[k] = radii[n - 1];
					radii[n - 1] = temp;
					permute(radii, n - 1, permutations);
					// swap text[k], text[n-1]
					temp = radii[k];
					radii[k] = radii[n - 1];
					radii[n - 1] = temp;
				}
			}
			permute(radii, n - 1, permutations);
		}
	}

}
