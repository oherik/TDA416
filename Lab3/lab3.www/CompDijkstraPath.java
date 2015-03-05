//Grupp 7 - Erik Öhrn & Paula Eriksson Imable

import java.util.ArrayList;

/**
 * Ett köelement som används för Dijkstras algoritm
 * 
 * @author Erik Öhrn & Paula Eriksson Imable
 *
 * @param <E>
 */
public class CompDijkstraPath<E extends Edge> implements
		Comparable<CompDijkstraPath<E>> {
	// Variabeldeklaration
	private int node;
	private double cost;
	ArrayList<E> path;

	/**
	 * 
	 * Skapar ett köelement. Får en nod, kostnaden dit från startnoden samt
	 * vägen dit från startnoden
	 * 
	 *
	 * @param node
	 *            noden
	 * @param cost
	 *            kostnaden dit från startnoden
	 * @param path
	 *            vägen dit från startnoden
	 * @throws IndexOutOfBoundsException
	 *             om cost är mindre än noll
	 */

	public CompDijkstraPath(int node, double cost, ArrayList<E> path) {
		if (cost < 0)
			throw new IndexOutOfBoundsException(
					"CompDijkstraPath: Kostnaden kan inte vara mindre än noll");
		this.node = node;
		this.cost = cost;
		this.path = path;
	}

	/**
	 * 
	 * @return vägen till noden från startnoden
	 */
	public ArrayList<E> getPath() {
		return path;
	}

	/**
	 * 
	 * @return kostnaden till noden via vägen den tagit
	 */

	public double getCost() {
		return cost;
	}

	/**
	 * 
	 * @return noden
	 */

	public int getNode() {
		return node;
	}

	/**
	 * Jämför med ett annat köelement.
	 * 
	 * @param dijkstraElement
	 *            det andra elementet
	 * @returns 0 om elementen har samma vikt, ett ta mindre än noll om detta
	 *          elementets kostnad är minst eller ett tal större än noll om det
	 *          andra elementets kostnad är minst.
	 * @throws NullPointerException
	 *             om det andra elementet är null
	 */
	@Override
	public int compareTo(CompDijkstraPath<E> dijkstraElement) {
		if (dijkstraElement == null)
			throw new NullPointerException(
					"CompDijkstraPath - compareTo: Cannot compare null elements.");
		return Double.compare(this.cost, dijkstraElement.getCost());
	}
}