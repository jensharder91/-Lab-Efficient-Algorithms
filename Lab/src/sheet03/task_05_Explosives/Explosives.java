package sheet03.task_05_Explosives;

import java.awt.Point;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Explosives {
	// union-find struktur
	private static int[] repr;
	private static ArrayList<Edge> edges;
	private static ArrayList<Point> piles;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {

			// meatadata for test case
			int numberPiles = scanner.nextInt();
			if (numberPiles == 0) {
				break;
			}

			edges = new ArrayList<>();

			initUnionFind(numberPiles);

			// loop for pile, init
			for (int j = 0; j < numberPiles; j++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				Point newPoint = new Point(x, y);

				// edges to all existing piles
				for (int i = 0; i < piles.size(); i++) {
					Point otherPile = piles.get(i);

					double length = Math.sqrt((otherPile.getX() - x) * (otherPile.getX() - x) + (otherPile.getY() - y))
							* (otherPile.getY() - y);
					edges.add(new Edge(length, otherPile, newPoint));
				}

				// add new pile
				piles.add(newPoint);
			}

			Collections.sort(edges, new Comparator<Edge>() {

				@Override
				public int compare(Edge arg0, Edge arg1) {
					double val = arg0.getLength() - arg1.getLength();
					if (val > 0) {
						return 1;
					} else if (val < 0) {
						return -1;
					}
					return 0;
				}
			});

			// create mst
			while (!allInOneComponent()) {
				union(edges.get(0).getPoint1(), edges.get(0).getPoint2());
			}
		}
		scanner.close();
	}

	private static boolean allInOneComponent() {
		int reprOfComponent = repr[0];

		for (int i = 1; i < piles.size(); i++) {
			if (reprOfComponent != repr[i]) {
				return false;
			}
		}
		return true;
	}

	private static void initUnionFind(int numberOfVertices) {
		// union-find
		items = new ArrayList[numberOfVertices];
		repr = new int[numberVertices];
		for (int n = 0; n < numberVertices; n++) {
			items[n] = new ArrayList<>();
			items[n].add(n);
			repr[n] = n;
		}
	}

	private static void union(int x, int y) {
		int i = repr[x];
		int j = repr[y];
		if (i == j) {
			return;
		}
		if (items[i].size() > items[j].size()) {
			for (int k = 0; k < items[j].size(); k++) {
				repr[items[j].get(k)] = i;
			}
			items[i].addAll(items[j]);
			items[j].clear();
		} else {
			for (int k = 0; k < items[i].size(); k++) {
				repr[items[i].get(k)] = j;
			}
			items[j].addAll(items[i]);
			items[i].clear();
		}
	}

	private static class Edge {
		private double length;
		private Point point1;
		private Point point2;

		public Edge(double length, Point point1, Point point2) {
			this.length = length;
			this.point1 = point1;
			this.point2 = point2;
		}

		public double getLength() {
			return this.length;
		}

		public Point getPoint1() {
			return this.point1;
		}

		public Point getPoint2() {
			return this.point2;
		}
	}
}
