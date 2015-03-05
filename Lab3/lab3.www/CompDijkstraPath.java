//Grupp 7 - Erik �hrn & Paula Eriksson Imable

import java.util.ArrayList;

/**
 * Ett k�element som anv�nds f�r Dijkstras algoritm
 * 
 * @author Erik �hrn & Paula Eriksson Imable
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
	 * Skapar ett k�element. F�r en nod, kostnaden dit fr�n startnoden samt
	 * v�gen dit fr�n startnoden
	 * 
	 *
	 * @param node
	 *            noden
	 * @param cost
	 *            kostnaden dit fr�n startnoden
	 * @param path
	 *            v�gen dit fr�n startnoden
	 * @throws IndexOutOfBoundsException
	 *             om cost �r mindre �n noll
	 */

	public CompDijkstraPath(int node, double cost, ArrayList<E> path) {
		if (cost < 0)
			throw new IndexOutOfBoundsException(
					"CompDijkstraPath: Kostnaden kan inte vara mindre �n noll");
		this.node = node;
		this.cost = cost;
		this.path = path;
	}

	/**
	 * 
	 * @return v�gen till noden fr�n startnoden
	 */
	public ArrayList<E> getPath() {
		return path;
	}

	/**
	 * 
	 * @return kostnaden till noden via v�gen den tagit
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
	 * J�mf�r med ett annat k�element.
	 * 
	 * @param dijkstraElement
	 *            det andra elementet
	 * @returns 0 om elementen har samma vikt, ett ta mindre �n noll om detta
	 *          elementets kostnad �r minst eller ett tal st�rre �n noll om det
	 *          andra elementets kostnad �r minst.
	 * @throws NullPointerException
	 *             om det andra elementet �r null
	 */
	@Override
	public int compareTo(CompDijkstraPath<E> dijkstraElement) {
		if (dijkstraElement == null)
			throw new NullPointerException(
					"CompDijkstraPath - compareTo: Cannot compare null elements.");
		return Double.compare(this.cost, dijkstraElement.getCost());
	}
}