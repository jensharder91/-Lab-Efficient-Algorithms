package sheet03.task_08_RailwayNetwork;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

public class RailwayNetwork {

	// union-find struktur
	private static Town[] towns;
	private static ArrayList<Edge> edges;
	private static byte[] depth;
	private static int componentCounter = 0;

	private static double maxLength = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numberVertices;
		int numberExistingConnections;

		// meatadata for test case
		String[] metaData = br.readLine().split(" ");
		numberVertices = Integer.valueOf(metaData[0]);
		numberExistingConnections = Integer.valueOf(metaData[1]);

		// init
		towns = new Town[numberVertices];
		edges = new ArrayList<>();
		depth = new byte[numberVertices];

		// insert towns and edges to all existing towns
		for (int i = 0; i < numberVertices; i++) {
			String[] townCoord = br.readLine().split(" ");
			towns[i] = new Town(i, new Point(Integer.valueOf(townCoord[0]), Integer.valueOf(townCoord[1])));
			componentCounter++;

			for (int j = 0; j < i; j++) {
				edges.add(new Edge(towns[i], towns[j]));
			}
		}

		// call union(..) for all provided existing edges
		for (int i = 0; i < numberExistingConnections; i++) {
			String[] townNumbers = br.readLine().split(" ");
			union(towns[Integer.valueOf(townNumbers[0]) - 1], towns[Integer.valueOf(townNumbers[1]) - 1]);
			componentCounter--;
		}

		// sort all edges
		Collections.sort(edges, new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return (int) (o1.length - o2.length);
			}
		});

		// pick the samallest edge, check (findRepresentant(..)) if we need edge... if
		// so: insert edge (union(..))
		int index = 0;
		while (componentCounter > 0 && index < edges.size()) {
			Edge edge = edges.get(index);
			if (findRepresentant(edge.town1) != findRepresentant(edge.town2)) {
				union(edge.town1, edge.town2);
				componentCounter--;
				// save max edge we inserted
				if (edge.length > maxLength) {
					maxLength = edge.length;
				}
			}
			index++;
		}

		// System.out.println(maxLength);

		// 3 stellen runden und ausgeben
		double roundend = (Math.round(maxLength * 1000d) / 1000d);
		Double numberToSave = (roundend);
		NumberFormat nf = NumberFormat.getInstance(Locale.UK);
		nf.setMinimumFractionDigits(3);
		System.out.println(nf.format(numberToSave));

	}

	private static void union(Town x, Town y) {
		int represantantX = findRepresentant(x);
		int represantantY = findRepresentant(y);
		if (represantantX == represantantY)
			return;

		if (depth[represantantX] < depth[represantantY])
			towns[represantantX].parent = represantantY;
		else if (depth[represantantX] > depth[represantantY])
			towns[represantantY].parent = represantantX;
		else {
			towns[represantantY].parent = represantantX;
			depth[represantantX]++;
		}
	}

	private static int findRepresentant(Town p) {

		// loop until representant is found
		while (p.townNumber != p.parent) {
			p.parent = towns[p.parent].parent;
			p = towns[p.parent];
		}
		return p.townNumber;
	}

	private static class Town {
		int parent;
		Point point;
		int townNumber;

		Town(int parent, Point point) {
			this.parent = parent;
			this.townNumber = parent;
			this.point = point;
		}
	}

	private static class Edge {
		private Town town1;
		private Town town2;
		private double length;

		public Edge(Town town1, Town town2) {
			this.town1 = town1;
			this.town2 = town2;

			this.length = Math.sqrt((town1.point.x - town2.point.x) * (town1.point.x - town2.point.x)
					+ (town1.point.y - town2.point.y) * (town1.point.y - town2.point.y));
		}
	}

}
