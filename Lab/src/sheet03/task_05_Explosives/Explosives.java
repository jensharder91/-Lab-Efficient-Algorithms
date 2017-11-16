package sheet03.task_05_Explosives;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Explosives {
	// union-find struktur
	private static ArrayList<Edge> edges;
	private static ArrayList<Pile> piles;
	private static double totalLength = 0;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (true) {

			totalLength = 0;
			// meatadata for test case
			int numberPiles = scanner.nextInt();
			if (numberPiles == 0) {
				break;
			}

			edges = new ArrayList<>();
			piles = new ArrayList<>();

			// loop for pile, init
			for (int j = 0; j < numberPiles; j++) {
				int x = scanner.nextInt();
				int y = scanner.nextInt();

				Pile newPile = new Pile(new Point(x, y));

				// edges to all existing piles
				for (int i = 0; i < piles.size(); i++) {
					Pile otherPile = piles.get(i);

					double length = Math.sqrt((otherPile.getX() - x) * (otherPile.getX() - x) + (otherPile.getY() - y)
							* (otherPile.getY() - y));
					edges.add(new Edge(length, otherPile, newPile));
				}

				// add new pile
				piles.add(newPile);
			}

			//Kanten sortieren nach Länge
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
			while (!allInOneComponent() && edges.size() > 0) {
				union(edges.get(0));
				edges.remove(0);
			}
			//2 stellen runden udn ausgeben
			System.out.println((Math.round( totalLength * 100d ) / 100d));

		}
		scanner.close();
	}

	private static boolean allInOneComponent() {
		int reprOfComponent = piles.get(0).getRep().getId();

		for (int i = 1; i < piles.size(); i++) {
			if (reprOfComponent != piles.get(i).getRep().getId()) {
				return false;
			}
		}
		return true;
	}

	private static void union(Edge edge) {
		Pile i = edge.getPile1().getRep();
		Pile j = edge.getPile2().getRep();
		if (i.getId() == j.getId()) {
			return;
		}
		if (i.getItemsOfRep().size() > j.getItemsOfRep().size()) {
			for (int k = 0; k < j.getItemsOfRep().size(); k++) {
				j.getItemsOfRep().get(k).setRep(i);
				i.addItemToRep(j.getItemsOfRep().get(k));
			}
			j.clearItemsToRep();
		} else {
			for (int k = 0; k < i.getItemsOfRep().size(); k++) {
				i.getItemsOfRep().get(k).setRep(j);
				j.addItemToRep(i.getItemsOfRep().get(k));
			}
			i.clearItemsToRep();
		}
		totalLength += edge.getLength() + 16;
	}

	private static class Edge {
		private double length;
		private Pile pile1;
		private Pile pile2;

		public Edge(double length, Pile pile1, Pile pile2) {
			this.length = length;
			this.pile1 = pile1;
			this.pile2 = pile2;

//			System.out.println("new Pile: pile1: " + pile1.getId() + " pile2: " + pile2.getId() + "  length: " + length);
		}

		public double getLength() {
			return this.length;
		}

		public Pile getPile1() {
			return this.pile1;
		}

		public Pile getPile2() {
			return this.pile2;
		}
	}

	private static class Pile {
		private Point point;
		private int id;
		private static int counter = 0;
		private ArrayList<Pile> itemOfRep = new ArrayList<>();
		private Pile rep;

		public Pile(Point point) {
			this.point = point;
			this.id = counter;
			this.rep = this;
			this.itemOfRep.add(this);
			counter++;

//			System.out.println("new Pile: id: " + id + "  x: " + point.getX() + "  y: " + point.getY());
		}

		public void addItemToRep(Pile pile) {
			this.itemOfRep.add(pile);
		}

		public void clearItemsToRep() {
			this.itemOfRep.clear();
		}

		public ArrayList<Pile> getItemsOfRep() {
			return this.itemOfRep;
		}

		public void setRep(Pile rep) {
			this.rep = rep;
		}

		public Pile getRep() {
			return this.rep;
		}

		public Point getPoint() {
			return this.point;
		}

		public int getId() {
			return this.id;
		}

		public double getX() {
			return this.point.getX();
		}

		public double getY() {
			return this.point.getY();
		}
	}
}
