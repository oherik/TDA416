package U3;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.awt.Point;
import java.awt.Color;
//import java.util.*;
import java.text.DecimalFormat;

// EH 2015 01 28
// även den här koden är återanvänd från andra uppgifter så det finns 
// säkert saker som inte är nödvändiga
public class DimpLinkedList {

	// 1.0E300 is almost infinity (approx. = Double.MAX_VALUE
	private static final double infinity = 1.0E300;
	private DrawGraph shape;
	private PriorityQueue<Node> q = new PriorityQueue<Node>();

	/** this Node is the building block for our list. */
	private static class Node implements Comparable<Node> {
		Point p = null;
		double imp = infinity; // importance of the point
		int nbr = -1; // original position in list, only for debug

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
	
		Scanner sc = new Scanner(System.in);
		int n = 0;
		while (sc.hasNext()) { // antag 2 korrekta tal per rad
			int n1 = sc.nextInt();
			int n2 = sc.nextInt();
			this.addLast(new Point(n1, n2));
			n++;
		}
		// sc.close();
		
		this.calcInitialImportance();
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
			// System.out.println( (int)p1.getX()+" "+ (int)p1.getY()+" "+
			// (int)p2.getX()+" "+ (int)p2.getY()); // debug
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
		System.out.println("aL");
		Node n = head;
		while (n.next != null) {
			n = n.next;
		}
		n.next = new Node(p, 0);
	} // end addLast

	/**
	 * Reduces the list to the sought-after k most important points.
	 * 
	 * @param k
	 *            the number of remaining points
	 */
	public void importanceRemoveList(int k) {
		System.out.println("iRL");
		if (k < 3 || k > size) {
			throw new IndexOutOfBoundsException(
					"importanceRemoveList: k m�ste vara minst 3, annars �r programmet meningsl�st, och inte st�rre �n antalet punkter");
		}
		Node n = head;
		Node minImpNode = n;
		double minImp = n.imp;
		while(n.next!=null){
			if(n.imp<minImp){
				minImpNode = n;
			}
			n=n.next;
		}
		minImpNode.prev.next=minImpNode.next;
		size--;
		if(k<size){
			importanceRemoveList(k);
		}
	}

	/**
	 * Calculates the initial important measure for all nodes. Assume there are
	 * at least 3 nodes otherwise it's all meaningless.
	 */
	public void calcInitialImportance() {
		System.out.println("cII");
		Node tjafs = head;
		tjafs.imp = infinity;
		tjafs = tjafs.next;
		while (tjafs.next != null) {
			tjafs.imp = importanceOfP(tjafs.prev.p, tjafs.p, tjafs.next.p);
			tjafs = tjafs.next;
		}
		tjafs.imp = infinity;
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
		 * debug System.out.println("punkterna (l: p: r): " + l + ": " + p +
		 * ": " + r); System.out.println("l-p= " + String.format( "%5.2f ", l1)
		 * + " p-r= " + String.format( "%5.2f ", l2) + " l-r= " + String.format(
		 * "%5.2f ", l3) + " (l1+l2-l3)= " + String.format( "%5.2f ",
		 * (l1+l2-l3)) ); System.out.println(); // end debug
		 */
		return l1 + l2 - l3;
	}

}
