package sheet02.task_03_Attractiveness;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

/*
3
abc abd abe abf abg abh
xyz xxx zxy zxy uvw wuv
abd abe abf abg abh abc
3
abc def ghi jkl mno pqr
zcd efg hij klm nop qrs
cde fgh ijk lmn opq rst
6
qwe asd yxc xcv sdf wer
qay edc rfv tgb zhn wsx
wsx edc rfv tgb zhn qay
qwe asd ert dfg yxc zhn
xcv yxc asd sdf wer qwe
qwe wer yxc xcv asd sdf
4
qay wsx edc rfv tgb zhn
zhn tgb rfv edc qay wsx
qwe qwe qwe qwe qwe qay
qay qwe qwe qwe qwe qwe
0
 */
//result 2 3 3 4
public class Attractiveness {

	private static int maxValue = 0;
	private static int maxValueCounter = 0;

	private static HashMap<String, Integer> hashmap;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
		
			String line = br.readLine();

			// meatadata for test case
			int numberPlayer = Integer.valueOf(line);
			if (numberPlayer == 0) {
				break;
			}

			// Node root = new Node();
			maxValue = 0;
			maxValueCounter = 0;
			hashmap = new HashMap<String, Integer>();

			// loop for each player
			for (int i = 0; i < numberPlayer; i++) {

				// get player attribute
				String[] attributes = br.readLine().split(" ");

				// sort alphabetical
				Arrays.sort(attributes);
				String totalString = attributes[0] + attributes[1] + attributes[2] + attributes[3] + attributes[4]
						+ attributes[5];

				Integer value = hashmap.get(totalString);
				if (value == null) {
					value = 0;
				}
				value++;

				hashmap.put(totalString, value);

				if (value > maxValue) {
					maxValue = value;
					maxValueCounter = maxValue;
				} else if (value == maxValue) {
					maxValueCounter += value;
				}
			}
			System.out.println(maxValueCounter);
		}
	}
}
