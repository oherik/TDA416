package U3;

//import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.awt.Point;
import java.awt.Color;
//import java.util.*;
//import java.text.DecimalFormat;

// EH 2015 01 28
// även den här koden är återanvänd från andra uppgifter så det finns 
// säkert saker som inte är nödvändiga
public class DimpLinkedList {

	// 1.0E300 is almost infinity (approx. = Double.MAX_VALUE
	private static final double infinity = 1.0E300;
	private DrawGraph shape;

	// private PriorityQueue<Node> q = new PriorityQueue<Node>();

	/** this Node is the building block for our list. */
	private static class Node implements Comparable<Node> {
		Point p = null;
		double imp = infinity; // importance of the point
		int nbr = -1; // original position in list, only for debugs

		private Node next = null; // The link to the next node.
		private Node prev = null; // The link to the previous node.

		private Node(Point p, int nbr) { // nbr not needed, only for debug
			this.p = p;
			this.nbr = nbr;
		}

		public int compareTo(Node n) {
			double diff = imp - n.imp;
			if (diff < 0) {
				return -1;
			} else if (diff > 0) {
				return +1;
			} else {
				return 0;
			}
		}
	} // end class Node

	private Node head = null; // A ref. to the head of the list.
	private Node tail = null; // A ref. to the end of the list.

	private int size = 0; // The size of the list.

	// ============================================================
	public DimpLinkedList() {
		this(10, 10);
	}

	/*
	 * Create a drawing area that is width x height so points can have values
	 * between 0 and width/height.
	 * 
	 * @param width logical x-size of drawing window
	 * 
	 * @param height logical y-size of drawing window
	 */
	public DimpLinkedList(int width, int height) {
		shape = new DrawGraph(width, height);
	}

	/**
	 * Creates a "fake" meningless list from a constant array. used for debug
	 * 
	 * @return the number of points in the list
	 */
	public int readFakeShape() {
		int n = 0; // skall bli 25
		// meningslösa punkter
		int[] x = { 2, 4, 6, 8, 3, 5, 7, 2, 4, 6, 8, 3, 5, 7, 2, 4, 6, 8, 3, 5,
				7, 2, 4, 6, 8 };
		int[] y = { 2, 2, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 6, 6, 6, 6, 7, 7,
				7, 8, 8, 8, 8 };
		// int[] x = {0, 1, 3, 4, 5, 6, 9, 10, 9, 7, 5, 4, 2};
		// int[] y = {0, 3, 3, 4, 4, 5, 5, 3, 1, 1, 1, 0, 0};
		for (int i = 0; i < x.length; i++) {
			this.addLast(new Point(x[i], y[i]));
			n++;
		}
		calcInitialImportance();
		return n;
	}

	/**
	 * Creates a list from user input (or file by redirection). Format should be
	 * two integer points per line separated by space x y
	 * 
	 * @return the number of points in the list
	 */

	public int readShape() {
		int n = 0;
		System.out.println("Please enter filename (for example ./fig1.txt)");
		Scanner input = new Scanner(System.in);
		File file = new File(input.nextLine());
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNext()) { // antag 2 korrekta tal per rad
				int n1 = sc.nextInt();
				int n2 = sc.nextInt();
				this.addLast(new Point(n1, n2));
				n++;
			}
			sc.close();
			this.calcInitialImportance();
		} catch (FileNotFoundException e) {
			System.out.println("readShape: File not found.");
		}
		return n;
	}

	// primitive output used for debugging
	@Override
	public String toString() {
		int count = -1;
		StringBuilder str0 = new StringBuilder("index:      "); // index
		StringBuilder str4 = new StringBuilder("o index:    "); // original
																// index
		StringBuilder str1 = new StringBuilder("x:          "); // x coordinate
		StringBuilder str2 = new StringBuilder("y:          "); // y coordinate
		StringBuilder str3 = new StringBuilder("importance: "); // importance
		Node ptr = head;
		while (ptr != null) {
			count++;
			str0.append(String.format("%5d ", count));
			str4.append(String.format("%5d ", ptr.nbr));
			str1.append(String.format("%5.0f ", ptr.p.getX()));
			str2.append(String.format("%5.0f ", ptr.p.getY()));
			if (ptr.imp == infinity) {
				str3.append("  inf ");
			} else {
				str3.append(String.format("%5.2f ",
						(int) (ptr.imp * 100) / 100.0));
			}
			ptr = ptr.next;
		}
		return (str4.toString() + "\n" + str0.toString() + "\n"
				+ str1.toString() + "\n" + str2.toString() + "\n"
				+ str3.toString() + "\n");
	}

	// more advance drawing than toString
	/**
	 * Draws a line between every point in the list.
	 * 
	 * @param c
	 *            The color to draw in
	 * @param layer
	 *            draw in base layer (true) or overlay layer (false)
	 */
	public void drawShape(Color c, boolean layer) {
		DrawGraph.Layer l = null;
		if (layer) {
			l = DrawGraph.Layer.BASE;
		} else {
			l = DrawGraph.Layer.OVERLAY;
		}
		shape.clearLayer(l);
		Node ptr = head;

		Point p1 = ptr.p;

		ptr = ptr.next;
		while (ptr != null) {
			Point p2 = ptr.p;
			shape.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(),
					(int) p2.getY(), c, 2.0, l);
			p1 = p2;
			ptr = ptr.next;
		}
		shape.repaint();
	}

	/**
	 * Adds a point to the end of the list
	 * 
	 * @param p
	 *            point to add
	 */
	public void addLast(Point p) {
		if (p == null) {
			throw new NullPointerException("addLast: Point is null");
		}
		if (tail == null) {
			tail = new Node(p, size);
			head = tail;
		} else {
			addLastHelp(tail, p);
		}
		size++;
	} // end addLast

	private void addLastHelp(Node n, Point p) {
		n.next = new Node(p, size);
		n.next.prev = n;
		tail = n.next;
	} // helper function for addLast

	/**
	 * Reduces the list to the sought-after k most important points.
	 * 
	 * @param k
	 *            the number of remaining points
	 */
	public void importanceRemoveList(int k) {
		if (k < 2 || k > size) {
			throw new IndexOutOfBoundsException(
					"importanceRemoveList: k m�ste vara minst 2 och inte st�rre �n antalet punkter");
		}
		Node n = head;
		Node minImpNode = n;
		while (n.next != null) {
			if (n.imp < minImpNode.imp) {
				minImpNode = n;
			}
			n = n.next;
		}
		rebindPointersForRemoval(minImpNode);
		newImportance(minImpNode.prev);
		newImportance(minImpNode.next);
		size--;
		if (k < size) {
			importanceRemoveList(k);
		}
	}

	private void rebindPointersForRemoval(Node n) {
		n.next.prev = n.prev;
		n.prev.next = n.next;
	} // hj�lpmetod till importanceRemoveList

	private void newImportance(Node n) {
		if (n.imp != infinity) {
			n.imp = importanceOfP(n.prev.p, n.p, n.next.p);
		}
	} // hj�lpmetod till importacneRemoveList

	/**
	 * Calculates the initial important measure for all nodes. Assume there are
	 * at least 3 nodes otherwise it's all meaningless.
	 */
	public void calcInitialImportance() {
		head.imp = tail.imp = infinity;
		Node n = head.next;
		while (n.next != null) {
			n.imp = importanceOfP(n.prev.p, n.p, n.next.p);
			n = n.next;
		}
	}

	/**
	 * Given three points, the important measure of the middle one, p, is
	 * calculated.
	 * 
	 * @param l
	 *            ,p,r the three points
	 * @return the important measure of point p
	 */
	private static double importanceOfP(Point l, Point p, Point r) {
		double l1 = l.distance(p); // use Points distance function :-)
		double l2 = p.distance(r);
		double l3 = l.distance(r);

		/*
		 * debugSystem.out.println("punkterna (l: p: r): " + l + ": " + p + ": "
		 * + r);System.out.println("l-p= " + String.format("%5.2f ", l1) +
		 * " p-r= " + String.format("%5.2f ", l2) + " l-r= " +
		 * String.format("%5.2f ", l3) + " (l1+l2-l3)= " +
		 * String.format("%5.2f ", (l1 + l2 - l3)));System.out.println(); // end
		 * debug
		 */
		return l1 + l2 - l3;
	}

}
